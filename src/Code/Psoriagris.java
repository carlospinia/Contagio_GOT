package Code;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Psoriagris {

    private static final int SANO = 0;
    private static final int INFECTADO = 1;
    private static final int MUERTO = 2;

    private static int TIEMPO_MIN_MUETE = 8;

    private String p0;
    private ArrayList<NodosAt> nodos;
    private ArrayList<AristasAt> aristas;
    private double probContagio;
    private double probCuracion;

    public Psoriagris(ArrayList<NodosAt> nodos, ArrayList<AristasAt> aristas, String personaje_inicio, double probCont, double probCur) {
        this.aristas = aristas;
        this.nodos = nodos;
        this.p0 = personaje_inicio;
        this.probContagio = probCont;
        this.probCuracion = probCur;
    }

    private NodosAt buscarNodo(String nombre) { //Funcion que busca el nodo principal
        boolean encontrado = false;
        int i = 0;
        while (!encontrado) {
            NodosAt nodoInicial;
            String n = this.nodos.get(i).getId();
            if (n.equals(nombre)) {
                return nodoInicial = this.nodos.get(i);
            } else i++;
        }
        return null;
    }

    private AristasAt buscarArista(String source, String target) { //Funcion que busca el nodo principal

        int i = 0;
        while (i < aristas.size()) {
            if (this.aristas.get(i).getSource().equalsIgnoreCase(source)
                    && this.aristas.get(i).getTarget().equalsIgnoreCase(target) ||
                    this.aristas.get(i).getSource().equalsIgnoreCase(target)
                            && this.aristas.get(i).getTarget().equalsIgnoreCase(source)) {

                return this.aristas.get(i);
            } else i++;
        }
        return null;
    }

    private void infectarNodo(String nombre, int tiempo) { //Funcion que infecta un nodo
        this.buscarNodo(nombre).setEstado(INFECTADO);
        this.buscarNodo(nombre).setTiempoInfeccion(tiempo);
    }


    private void pesoVecinos(ArrayList<Integer> pesoVecinos, NodosAt nodoAct) {
        int pesoVecino;
        for (int i = 0; i < nodoAct.vecinos.size(); i++) { //guardamos los pesos de los vecinos del nodo actual
            String nombre = nodoAct.vecinos.get(i);
            pesoVecino = getPesoVecino(nodoAct, nombre);
            pesoVecinos.add(pesoVecino);
        }
    }

    private void addVecinosInfectados(double maxPeso, ArrayList<Integer> pesoVecinos, ArrayList<Integer> infectados, NodosAt nodoAct) {
        for (int i = 0; i < pesoVecinos.size(); ++i) {
            double probabilidad = Math.random(); //Creamos un random entre 0 - 1
            double probContagio = ((double) pesoVecinos.get(i) / maxPeso) * this.probContagio;

            if (probabilidad < probContagio) { //si la probabilidad de contagio es menor contagiamos
                NodosAt nodoVecino = buscarNodo(nodoAct.vecinos.get(i));
                if (nodoVecino.getEstado() == SANO) {
                    int pos = this.nodos.indexOf(nodoVecino);
                    infectados.add(pos); //Guardamos el que vamos a infectar
                }
            }
        }
    }

    private int getMaxPeso(int maxPeso) {
        int peso;
        for (int i = 0; i < aristas.size(); i++) {
            peso = aristas.get(i).getPeso();
            if (peso > maxPeso)
                maxPeso = peso;
        }
        return maxPeso;
    }

    private int infectarNodos(int contador, ArrayList<Integer> infectados, int tiempo) {
        for (int h = 0; h < infectados.size(); h++) {
            int pos = infectados.get(h);
            if (nodos.get(pos).getEstado() == SANO) {
                this.nodos.get(pos).setEstado(INFECTADO);
                this.nodos.get(pos).setTiempoInfeccion(tiempo);
                contador++;
                System.out.println(this.nodos.get(pos).getId());
            }
        }
        return contador;
    }

    private int getPesoVecino(NodosAt nodoAct, String target) {
        NodosAt nodo = buscarNodo(nodoAct.getId());
        String vecino = target;
        AristasAt arista = buscarArista(nodo.getId(), vecino);
        if (arista != null)
            return arista.getPeso();
        return 0;
    }

    private void comprobarMuertes(){
        for (int i = 0; i < this.nodos.size(); i++) {
            NodosAt nodoAct = this.nodos.get(i);
            if (nodoAct.getEstado() == INFECTADO && nodoAct.getTiempoInfeccion() >= TIEMPO_MIN_MUETE){
                double prob = Math.random(); //Creamos un random entre 0 - 1
                if(prob < probCuracion)
                    nodoAct.setEstado(MUERTO);
            }
        }
    }

    private void aumentarTiempoInfectados(){
        for (int i = 0; i < this.nodos.size(); i++){
            NodosAt nodoAct = this.nodos.get(i);
            if (nodoAct.getEstado() == INFECTADO){
                nodoAct.setTiempoInfeccion(nodoAct.getTiempoInfeccion()+1);
            }
        }
    }



    public void contagio() throws IOException {

        int contador = 1, maxPeso = 0, peso = 0, pesoVecino = 0, vuelta = 0, sinCambios = 0, contadorGlogal = 0;
        ArrayList<Integer> pesoVecinos = new ArrayList<>();
        ArrayList<Integer> infectados = new ArrayList<>();

        infectarNodo(p0, vuelta);
        CrearCSV.escribirCSV_Nodos(nodos, Integer.toString(contador), p0, vuelta);

        maxPeso = getMaxPeso(maxPeso);

        while (contador < this.nodos.size() && sinCambios < 5) {
            comprobarMuertes();
            aumentarTiempoInfectados();
            for (int j = 0; j < this.nodos.size(); j++) { //recorremos todos los nodos
                NodosAt nodoAct = this.nodos.get(j); //Cogemos el nodo actual

                if (nodoAct.getEstado() == INFECTADO) { //si el nodo esta infectado
                    pesoVecinos(pesoVecinos, nodoAct);
                    addVecinosInfectados(maxPeso, pesoVecinos, infectados, nodoAct);
                    pesoVecinos.clear();
                }
            }

            vuelta++;

            if (infectados.size() > 0) {
                contador = infectarNodos(contador, infectados, vuelta);
                infectados.clear();
                CrearCSV.escribirCSV_Nodos(nodos, Integer.toString(contador), p0, vuelta);
            } else
                sinCambios++;
            System.out.println(contador + "\n");
        }
    }


}
