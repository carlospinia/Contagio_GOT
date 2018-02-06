package Code;

import java.io.*;

import java.util.ArrayList;

import com.csvreader.CsvWriter;

public class CrearCSV {

	private static int SANO = 0;
	private static int INFECTADO = 1;
	private static final int MUERTO = 2;
	
	static ArrayList<NodosAt> GotNodos = new ArrayList<>();
	static ArrayList<AristasAt> GotAristas = new ArrayList<>();
	
	public static void main (String[] args) throws IOException{
		System.out.println("Primero vamos a crear los CSVs");
		leerArchivo("GotNodos.txt");
		leerArchivo("GotAristas.txt");
		escribirCSV_Aristas();
		llenarVecinos(GotNodos, GotAristas);
		System.out.println("CSVs iniciales creados sin problemas");
		Menu m = new Menu(GotNodos, GotAristas);
		m.menuPpal();
		System.out.println("FIN DEL PROGRAMA");
		
	}
		
	public static void leerArchivo(String archivo) throws IOException{
		
		String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            if(archivo.equalsIgnoreCase("GotNodos.txt")){
            	if(!cadena.toLowerCase().equalsIgnoreCase("id,label")){
            		parsearNodos(cadena, GotNodos);
            	}
            }
            else if (archivo.equalsIgnoreCase("GotAristas.txt")){
            	if(!cadena.toLowerCase().equalsIgnoreCase("source,target,type,id,weight")){
            		parsearAristas(cadena, GotAristas);
            	}
            }
        }
        b.close();
    }

	
	public static void escribirCSV_Aristas() throws IOException{
		
		String outputFile = "AristasGOT.csv", estado;
		CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ';');
		
		csvOutput.write("Source");
		csvOutput.write("Target");
		csvOutput.write("Type");
		csvOutput.write("State");
		csvOutput.write("Weight");
		csvOutput.endRecord();
		
		for(AristasAt datos : GotAristas){
			csvOutput.write(datos.getSource());
			csvOutput.write(datos.getTarget());
			csvOutput.write(datos.getTipo());
			if(datos.getInfected() == SANO) estado = "Sano";
		
			else estado = "Infectado";
			csvOutput.write(estado);
			csvOutput.write(String.valueOf(datos.getPeso()));
			csvOutput.endRecord();
		}
		csvOutput.close();
	}
	
	public static void escribirCSV_Nodos(ArrayList<NodosAt> nodos, String t, String personaje, int vuelta) throws IOException{
		String path = "Resultados/" + personaje;
		File directorio = new File(path);
		directorio.mkdirs();

		String outputFile = path + "/NodosGOT_" + t + "_" + personaje + "_t" + vuelta + ".csv";
		//File archivo = new File (outputFile);
		//String outputFile = "NodosGOT_" + t + "_" + personaje + "_" + prob + ".csv";
		CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ';');
		String estado;

		csvOutput.write("Id");
		csvOutput.write("Label");
		csvOutput.write("State");
		csvOutput.endRecord();

		for(NodosAt datos : nodos){
			csvOutput.write(datos.getId());
			csvOutput.write(datos.getLabel());
			if(datos.getEstado() == SANO)
				estado = "Sano";
			else if (datos.getEstado() == MUERTO) estado = "Muerto";
			else
				estado = "Infectado";
			csvOutput.write(estado);
			csvOutput.endRecord();
		}
		csvOutput.close();
	}
	
	public static void parsearNodos(String cadena, ArrayList<NodosAt> GotNodos){
		
		char[] cad;
		cad = cadena.toCharArray();
		ArrayList<String> vecinos = new ArrayList<String>();
		boolean salir = false;
		int i = 0, infectado;
		String id, label;
		while(!salir){
			if (cad[i] == ','){
				id = cadena.substring(0, i);
				label = id;
				infectado = 0;
				NodosAt Nodos = new NodosAt(id, label, infectado,vecinos );
				GotNodos.add(Nodos);
				salir = true;
			}
			++i;
		}
	}
	
	public static void parsearAristas(String cadena, ArrayList<AristasAt> GotAristas){
		
		char[] cad;
		cad = cadena.toCharArray();
		boolean salir = false;
		int i = 0, j = 0, h = 0, peso = 0;
		String source = null, target = null, type = null;
		int infected = 0;
		
		while(!salir){
			if (cad[i] == ','){
				if(h == 0){
					source = cadena.substring(j,i);
					j = i + 1;
					++i;
					h++;
				}
				else if(h == 1){
					target = cadena.substring(j,i);
					j = i + 1;
					++i;
					h++;
				}
				else if(h == 2){
					type = cadena.substring(j,i);
					j = i+1;
					++i;
					++h;
					salir = true;
				}
			
			}
			++i;

		}
		
		int w = cadena.length()-1;
		while(cad[w] != ','){
			w--;
		}
		String num = cadena.substring(w+1);
		peso = Integer.parseInt(num);
		if (!source.equalsIgnoreCase(target)) {
			AristasAt Arista = new AristasAt(source, target, type, infected, peso);
			GotAristas.add(Arista);
		}
	}

	
	public static void llenarVecinos(ArrayList<NodosAt> GotNodos, ArrayList<AristasAt> GotAristas ){
	
		for(NodosAt datosN : GotNodos){
			for(AristasAt datosA : GotAristas){
				if(datosN.getId().equalsIgnoreCase(datosA.getSource())){
					datosN.setVecinos(datosA.getTarget());
				}
				if(datosN.getId().equalsIgnoreCase(datosA.getTarget())){
					datosN.setVecinos(datosA.getSource());
				}
			}
		}
	}
}
