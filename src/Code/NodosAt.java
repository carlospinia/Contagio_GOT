package Code;

import java.util.ArrayList;
import java.util.List;

public class NodosAt {
	
	private String id, label;
	private int estado;
	ArrayList<String> vecinos = new ArrayList<String>();
	private int tiempoInfeccion;
	
	public NodosAt(String id, String label, int estado, ArrayList<String> vecinos) {
		this.id = id;
		this.label = label;
		this.estado = estado;
		this.vecinos = vecinos;
		this.tiempoInfeccion = -1;
	}
	
	public List<String> getVecinos(){
		return this.vecinos;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public void setVecinos(String nombre){
		this.vecinos.add(nombre);
	}

	public int getTiempoInfeccion(){
		return this.tiempoInfeccion;
	}

	public void setTiempoInfeccion(int t){
		this.tiempoInfeccion = t;
	}
}
