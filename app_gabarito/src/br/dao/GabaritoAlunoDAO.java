package br.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.conexao.ConexaoGabarito;
import br.modelo.Aluno;

public class GabaritoAlunoDAO {
	
	private Connection connGabarito = ConexaoGabarito.conectar();

	//Insere todos os dados do aluno
	public void insert(String matricula, String nome, String turma, String semestre,
			String resposta, String data, int qtdResposta, int codPessoa,int count) throws Exception{
		
		if(getVerificaAlunoCadastrado(matricula, turma, semestre) == 0){
			PreparedStatement pst = null;
			try {
				pst = (PreparedStatement) connGabarito.prepareStatement("INSERT INTO ga_aluno " +
						"(matricula, nome, turma, semestre, resposta, data, qtdResposta, codPessoa,posicao_leitura) "+
						"VALUES (?,?,?,?,?,?,?,?,?)");
				pst.setString(1, matricula);
				pst.setString(2, nome);
				pst.setString(3, turma);
				pst.setString(4, semestre);
				pst.setString(5, resposta);
				pst.setString(6, data);
				pst.setInt(7, qtdResposta);
				pst.setInt(8, codPessoa);
				pst.setInt(9, count);
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} else {
			//throw new Exception("Aluno já cadastrado: "+matricula);
			System.out.println("Aluno já cadastrado: "+ matricula);
		}
	}	
	
	//Insere matriculas nao encontradas
	public void insertMatriculaNaoEncontrada(String matricula, String turma) throws Exception{
		
		if(getVerificaMatNaoEncontrada(matricula) <= 0 ){
		PreparedStatement pst = null;
			try {
				pst = (PreparedStatement) connGabarito.prepareStatement("INSERT INTO nao_encontradas " +
						"(matricula, turma) VALUES (?, ?)");
				pst.setString(1, matricula);
				pst.setString(2, turma);
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(pst != null) pst.close();
			}
		}else{
			System.out.println("Matricula já cadastrada!");
		}
	}
	
	//verifica se o aluno já esta cadastrado
	public int getVerificaAlunoCadastrado(String matricula, String turma, String semestre) throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = (PreparedStatement) connGabarito.prepareStatement("SELECT COUNT(*) AS c FROM ga_aluno " +
					"WHERE matricula = ? and turma = ? and semestre = ?");
			pst.setString(1, matricula);
			pst.setString(2, turma);
			pst.setString(3, semestre);
			rs = pst.executeQuery();
			rs.next();
			
			return Integer.parseInt(rs.getString("c"));
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return 0;
		} finally{
			if (rs != null) rs.close();
			if (pst != null) pst.close();
		}
	}
	
	//verifica se a matricula nao encontrada  já existe no banco GABARITO
	public int getVerificaMatNaoEncontrada(String matricula) throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = (PreparedStatement) connGabarito.prepareStatement("SELECT COUNT(*) AS c FROM nao_encontradas " +
					"WHERE matricula = ?");
			pst.setString(1, matricula);
			rs = pst.executeQuery();
			rs.next();
			
			return Integer.parseInt(rs.getString("c"));
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return 0;
		} finally{
			if (rs != null) rs.close();
			if (pst != null) pst.close();
		}
	}
	
	public ArrayList<Aluno> getFiltroSemestreTurma(String semestre, String turma) throws SQLException{
		ArrayList<Aluno> lista = new ArrayList<Aluno>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = connGabarito.prepareStatement("SELECT * FROM ga_aluno WHERE semestre = ? AND turma = ? ORDER BY nome ASC");
			pst.setString(1, semestre);
			pst.setString(2, turma);
			rs = pst.executeQuery();
			
			if(!rs.next()){
				return lista;
			}else{
				do{
					int id = rs.getInt("id");
					String matricula = rs.getString("matricula");
					String nome = rs.getString("nome");
					String turma2 = rs.getString("turma");
					String resposta = rs.getString("resposta");
					String data = rs.getString("data");
					String qtdresposta = rs.getString("qtdresposta");
					String semestre2 = rs.getString("semestre");
					Integer posicao_leitura = rs.getInt("posicao_leitura");
					lista.add(new Aluno(id,matricula,nome,turma2,resposta,data,qtdresposta,semestre2,posicao_leitura));
				}while(rs.next());
			}
			
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Aluno> getIdAluno(Integer codigo){
		List<Aluno> listaAluno = new ArrayList<Aluno>();
		Aluno aluno = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = connGabarito.prepareStatement("select * from ga_aluno where codigo = ?");
			pst.setInt(1, codigo);
			rs = pst.executeQuery();
			
			if(!rs.next()){
				return null;
			} else {
				aluno = new Aluno();
				aluno.setId(rs.getInt("codigo"));
				aluno.setNome(rs.getString("nome"));
				aluno.setTurma(rs.getString("turma"));
				aluno.setMatricula(rs.getString("matricula"));
				listaAluno.add(aluno);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return listaAluno;
	}
	
	public Aluno getAluno(String codigo){
		
		Aluno aluno = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = connGabarito.prepareStatement("select * from ga_aluno where id = ?");
			pst.setString(1, codigo);
			rs = pst.executeQuery();
			
			if(!rs.next()){
				return null;
			} else {
				aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setNome(rs.getString("nome"));
				aluno.setTurma(rs.getString("turma"));
				aluno.setMatricula(rs.getString("matricula"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return aluno;
	}
	
	public void updateNome(String matricula, String nome) throws SQLException{
		PreparedStatement pst = null;
		Connection conn = ConexaoGabarito.conectar();
		
		try {
			pst = conn.prepareStatement("UPDATE ga_aluno SET nome = ? WHERE matricula = ?");
			pst.setString(1, nome);
			pst.setString(2, matricula);
			pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pst.close();
			if(conn != null) conn.close();
		}
	}
	
	public void deleta(String codigo) throws SQLException{
		PreparedStatement pst = null;
		Connection conn = ConexaoGabarito.conectar();
		
		try {
			pst = conn.prepareStatement("DELETE FROM ga_aluno WHERE id = ?");
			pst.setInt(1, Integer.parseInt(codigo));
			pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(pst != null) pst.close();
			if(conn != null) conn.close();
		}
	}
}
