package br.modelo;

public class Semestre {
	
	private int id;
	private String semeste;
	private String dataabertura;
	private String dataencerramento;
	
	public Semestre(int id, String semeste, String dataabertura,
			String dataencerramento) {
		super();
		this.id = id;
		this.semeste = semeste;
		this.dataabertura = dataabertura;
		this.dataencerramento = dataencerramento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSemeste() {
		return semeste;
	}

	public void setSemeste(String semeste) {
		this.semeste = semeste;
	}

	public String getDataabertura() {
		return dataabertura;
	}

	public void setDataabertura(String dataabertura) {
		this.dataabertura = dataabertura;
	}

	public String getDataencerramento() {
		return dataencerramento;
	}

	public void setDataencerramento(String dataencerramento) {
		this.dataencerramento = dataencerramento;
	}
}
