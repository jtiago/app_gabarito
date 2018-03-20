package br.modelo;

public class Turma {
	
	private int id;
	private String curso;
	private String sigla;
	private String periodo;
	
	public Turma(int id, String curso, String sigla,String periodo) {
		super();
		this.id = id;
		this.curso = curso;
		this.sigla = sigla;
		this.periodo = periodo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
}
