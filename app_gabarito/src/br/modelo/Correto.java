package br.modelo;

public class Correto {
	
	private int id;
	private String turma;
	private int qntresposta;
	private String resposta;
	private String data;
	private String semestre;
	
	public Correto(int id, String turma, int qntresposta, String resposta,
			String semestre) {
		super();
		this.id = id;
		this.turma = turma;
		this.qntresposta = qntresposta;
		this.resposta = resposta;
		this.semestre = semestre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public int getQntresposta() {
		return qntresposta;
	}

	public void setQntresposta(int qntresposta) {
		this.qntresposta = qntresposta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
}
