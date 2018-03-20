package br.modelo;

public class Aluno {
	
	private int id;
	private String matricula;
	private String nome;
	private String turma;
	private String resposta;
	private String data; 
	private String qtdresposta;
	private int codpessoa;
	private String semestre;
	private int posicao_leitura;
	private int qt_ac;
	private String porcentagem;
	private String conceito;
	
	public Aluno() {
		// TODO Auto-generated constructor stub
	}
	
	public Aluno(int id, String matricula, String nome, String turma,
			String resposta, String data, String qtdresposta,
			String semestre, int posicao_leitura) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.nome = nome;
		this.turma = turma;
		this.resposta = resposta;
		this.data = data;
		this.qtdresposta = qtdresposta;
		this.semestre = semestre;
		this.posicao_leitura = posicao_leitura;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
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

	public String getQtdresposta() {
		return qtdresposta;
	}

	public void setQtdresposta(String qtdresposta) {
		this.qtdresposta = qtdresposta;
	}

	public int getCodpessoa() {
		return codpessoa;
	}

	public void setCodpessoa(int codpessoa) {
		this.codpessoa = codpessoa;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public int getPosicao_leitura() {
		return posicao_leitura;
	}

	public void setPosicao_leitura(int posicao_leitura) {
		this.posicao_leitura = posicao_leitura;
	}

	public int getQt_ac() {
		return qt_ac;
	}

	public void setQt_ac(int qt_ac) {
		this.qt_ac = qt_ac;
	}

	public String getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(String porcentagem) {
		this.porcentagem = porcentagem;
	}

	public String getConceito() {
		return conceito;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}
}
