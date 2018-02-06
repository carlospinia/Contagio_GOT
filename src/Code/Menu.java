package Code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private ArrayList<NodosAt> nodos;
	private ArrayList<AristasAt> aristas;

	public Menu(ArrayList<NodosAt> nodos, ArrayList<AristasAt> aristas) {
		this.aristas = aristas;
		this.nodos = nodos;
	}


	public void menuPpal() throws IOException {
		int opcion = 0;
		System.out.println("Opciones disponibles" + "\n");
		System.out.println("1. Elegir Personaje" + "\n");
		System.out.println("2. Usar personajes por defecto" + "\n");
		System.out.println("Nota: Los personajes por defecto son, Jon Snow y Arianne Martell para los WhiteWalkers, y Daenerys y Victarion-Greyjoy para la Psoriagris " + "\n");
		System.out.println("Opcion:" + " ");
		Scanner sc = new Scanner(System.in);
		opcion = sc.nextInt();

		if (opcion == 1) {
			String nombre = menuMostrarPersonajes();
			int enfermedad = this.obtenerEnfermedad();
			int probCont = this.obtenerContagio();
			if (enfermedad == 1) {
				int probCur = this.obtenerCuracion();
				Psoriagris psoriagris = new Psoriagris(this.nodos, this.aristas, nombre, (double) probCont / 100, (double) probCur / 100);
				psoriagris.contagio();
			}
			else if (enfermedad == 2) {
				WhiteWalkers ww = new WhiteWalkers(this.nodos, this.aristas, nombre, (double) probCont / 100);
				ww.contagio();
			}
		}
		else if (opcion == 2) {
			int nombEnfermedad = this.obtenerEnfermedad();
			if (nombEnfermedad == 1) {
				String nombre = this.obtenerNombrePsoriagris();
				int probCont = this.obtenerContagio();
				int probCur = this.obtenerCuracion();
				Psoriagris psoriagris = new Psoriagris(this.nodos, this.aristas, nombre, (double) probCont / 100, (double) probCur / 100);
				psoriagris.contagio();
			}
			else {
				String nombre = this.obtenerNombreWhiteWalkers();
				int probCont = this.obtenerContagio();
				WhiteWalkers whiteWalkers = new WhiteWalkers(this.nodos, this.aristas, nombre, (double) probCont / 100);
			}
		}
	}

	private String menuMostrarPersonajes() {
		boolean salir = false;
		String inicio, fin;
		Scanner sc1 = new Scanner(System.in);
		int opcion1;
		do {
			System.out.println("Los personajes estan divididos por secciones segun su letra, elija una opcion para poder ver el personaje" + "\n");
			System.out.println("1. Personajes A-J" + "\n");
			System.out.println("2. Personajes K-O" + "\n");
			System.out.println("3. Personajes P-Z" + "\n");
			Scanner sc = new Scanner(System.in);
			int opcion = sc.nextInt();

			if (opcion == 1) {
				inicio = "Addam-Marbrand";
				fin = "Jyck";
			}
			else if (opcion == 2) {
				inicio = "Karyl-Vance";
				fin = "Oznak-zo-Pahl";
			}
			else {
				inicio = "Palla";
				fin = "Zollo";
			}
			
			int ini = getPos(inicio);
			int last = getPos(fin);
			for(int i = ini; i < last; ++i) {
				System.out.println(this.nodos.get(i).getId());
			}

			System.out.println("\n");
			System.out.println("Pulse 4 si quiere volver a elegir un listado");
			System.out.println("Pulse 5 si quiere Elegir el personaje");
			opcion1 = sc1.nextInt();

		}while(opcion1 == 4);
		System.out.flush();
		System.out.println("Escriba el personaje exactamente igual: ");
		Scanner sc2 = new Scanner(System.in);
		String opcion2 = sc2.nextLine();
		return opcion2;
	}
	
	private int getPos(String str) {
		boolean salir = false;
		int i = 0;
		while(!salir) {
			if (this.nodos.get(i).getId().equalsIgnoreCase(str)) salir = true;
			++i;
		}
		return i;
	}
	
	private String obtenerNombreWhiteWalkers() {
		int opcion = 0;
		do {
			System.out.println("Personajes disponibles" + "\n" + "\n");
			System.out.println("1. Jon-Snow" + "\n");
			System.out.println("2. Arianne-Martell " + "\n");

			Scanner sc = new Scanner(System.in);
			opcion = sc.nextInt();
		} while (opcion < 1 || opcion > 2);

		if (opcion == 1) return "Jon-Snow";
		else return "Arianne-Martell";
	}

	private String obtenerNombrePsoriagris() {
		int opcion = 0;
		do {
			System.out.println("Personajes disponibles" + "\n" + "\n");
			System.out.println("1. Daenerys-Targaryen" + "\n");
			System.out.println("2. Victarion-Greyjoy" + "\n");
			Scanner sc = new Scanner(System.in);
			opcion = sc.nextInt();
		}while(opcion < 1 || opcion > 2);

		if(opcion == 1) return "Daenerys-Targaryen";
		else return "Victarion-Greyjoy";
	}

	private int obtenerEnfermedad() {
        int tipo;
        do {
			System.out.println("Contagios disponibles" + "\n" + "\n");
			System.out.println("1. Psoriagris" + "\n");
			System.out.println("2. Caminantes Blancos" + "\n");

			Scanner sc = new Scanner(System.in);
			tipo = sc.nextInt();
		} while (tipo < 0 || tipo > 2);
		return tipo;
	}

	private int obtenerContagio() {
		int probCont = 0;
		do {
			System.out.println("Introduce la probabilidad de contagio (de 0 a 100, incluidos): ");
			Scanner sc = new Scanner(System.in);
			probCont = sc.nextInt();
		} while (probCont < 0 || probCont > 100);
		return probCont;
	}

	private int obtenerCuracion() {
		int probCur = 0;
		do {
			System.out.println("Introduce la probabilidad de curacion (de 0 a 100, incluidos): ");
			Scanner sc = new Scanner(System.in);
			probCur = sc.nextInt();
		} while (probCur < 0 || probCur > 100);
		return probCur;
	}

}
