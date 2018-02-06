package Code;

public class AristasAt {

	private String source, target, tipo;
	private int infected;
	private int peso;
	
	
	public AristasAt(String source, String target, String tipo, int infected, int peso) {
		this.source = source;
		this.target = target;
		this.tipo = tipo;
		this.infected = infected;
		this.peso = peso;
	}

	public int getInfected() { return this.infected; }
	
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTipo() { return tipo; }

	public int getPeso(){
		return this.peso;
	}
}
