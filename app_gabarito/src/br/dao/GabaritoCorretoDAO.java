package br.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.conexao.ConexaoGabarito;
import br.modelo.Correto;
import br.util.RetornaDiretorio;

public class GabaritoCorretoDAO {
	
	Connection conn = ConexaoGabarito.conectar();
	
	public void insert(String turma, String semestre, String resposta, String data, int qntResposta){
		PreparedStatement pst = null;
		
		try {
			pst = (PreparedStatement) conn.prepareStatement("INSERT INTO ga_correto " +
					"(turma, semestre, qntResposta, resposta, data) VALUES (?,?,?,?,?)");
			pst.setString(1, turma);
			pst.setString(2, semestre);
			pst.setInt(3, qntResposta);
			pst.setString(4, resposta);
			pst.setString(5, data);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getFiltroSemestreTurma(String semestre, String turma) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement("SELECT * FROM ga_correto WHERE semestre = ? AND turma = ?");
			pst.setString(1, semestre);
			pst.setString(2, turma);
			rs = pst.executeQuery();
			
			if(!rs.next()){
				return "Gabarito n�o encontrado";
			}else{
				return rs.getString("resposta");
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Correto> getTodos() throws SQLException{
		ArrayList<Correto> lista = new ArrayList<Correto>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement("SELECT * FROM ga_correto where semestre = ? order by turma asc");
			pst.setString(1, RetornaDiretorio.SEMESTRE);
			rs = pst.executeQuery();
			
			if(!rs.next()){
				return lista;
			}else{
				do{
					int id = rs.getInt("id");
					String turma2 = rs.getString("turma");
					String resposta = rs.getString("resposta");
					int qntresposta = rs.getInt("qntresposta");
					String semestre2 = rs.getString("semestre");
					lista.add(new Correto(id,turma2,qntresposta,resposta,semestre2));
				}while(rs.next());
			}
			
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void deleta(String codigo) throws SQLException{
		PreparedStatement pst = null;
		Connection conn = ConexaoGabarito.conectar();
		
		try {
			pst = conn.prepareStatement("DELETE FROM ga_correto WHERE id = ?");
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
