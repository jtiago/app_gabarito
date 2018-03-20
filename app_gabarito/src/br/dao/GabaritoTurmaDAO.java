package br.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.conexao.ConexaoGabarito;
import br.modelo.Turma;

public class GabaritoTurmaDAO {
	
	public void insert(String curso, String sigla,String periodo) throws SQLException{
		Connection conn = ConexaoGabarito.conectar();
		PreparedStatement pst = null;
		
		try {
			pst = (PreparedStatement) conn.prepareStatement("INSERT INTO ga_turma " +
					"(curso, sigla, periodo) VALUES (?,?,?)");
			pst.setString(1, curso);
			pst.setString(2, sigla);
			pst.setString(3, periodo);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) conn.close();
		}
	}
	
	public ArrayList<Turma> getTodasTurmas() throws SQLException{
		ArrayList<Turma> lista = new ArrayList<Turma>();
		Connection conn = ConexaoGabarito.conectar();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM ga_turma ORDER BY sigla ASC");
			rs = pst.executeQuery();
			
			if(!rs.next()){
				return lista;
			}else{
				do{
					int id = rs.getInt("id");
					String curso = rs.getString("curso");
					String sigla = rs.getString("sigla");
					String periodo = rs.getString("periodo");
					lista.add(new Turma(id,curso,sigla,periodo));
				}while(rs.next());
			}
			
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(pst != null) pst.close();
			if(rs != null) rs.close();
			if(conn != null) conn.close();
		}
	}
	
	public ArrayList<Turma> getTurma(String codigo) throws SQLException{
		ArrayList<Turma> lista = new ArrayList<Turma>();
		Connection conn = ConexaoGabarito.conectar();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM ga_turma WHERE id = ?");
			pst.setString(1, codigo);
			rs = pst.executeQuery();
			
			if(!rs.next()){
				return lista;
			}else{
				do{
					int id = rs.getInt("id");
					String curso = rs.getString("curso");
					String sigla = rs.getString("sigla");
					String periodo = rs.getString("periodo");
					lista.add(new Turma(id,curso,sigla,periodo));
				}while(rs.next());
			}
			
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(pst != null) pst.close();
			if(rs != null) rs.close();
			if(conn != null) conn.close();
		}
	}
	
	public ArrayList<Turma> getFiltraSemestre(String semestre) throws SQLException{
		ArrayList<Turma> lista = new ArrayList<Turma>();
		Connection conn = ConexaoGabarito.conectar();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM ga_turma WHERE periodo = ? order by sigla asc");
			pst.setString(1, semestre);
			rs = pst.executeQuery();
			
			if(!rs.next()){
				return lista;
			}else{
				do{
					int id = rs.getInt("id");
					String curso = rs.getString("curso");
					String sigla = rs.getString("sigla");
					String periodo = rs.getString("periodo");
					lista.add(new Turma(id,curso,sigla,periodo));
				}while(rs.next());
			}
			
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(pst != null) pst.close();
			if(rs != null) rs.close();
			if(conn != null) conn.close();
		}
	}
	
	public void alteraTurma(String id,String sigla,String curso,String periodo) throws SQLException{
		PreparedStatement pst = null;
		Connection conn = ConexaoGabarito.conectar();
		
		try {
			pst = conn.prepareStatement("UPDATE ga_turma SET curso = ?, sigla = ?,periodo = ? "+
					"WHERE id = ?");
			pst.setString(1, curso);
			pst.setString(2, sigla);
			pst.setString(3, periodo);
			pst.setString(4, id);
			pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pst.close();
			if(conn != null) conn.close();
		}
	}
	
	public void deletaTurma(String codigo) throws SQLException{
		PreparedStatement pst = null;
		Connection conn = ConexaoGabarito.conectar();
		
		try {
			pst = conn.prepareStatement("DELETE FROM ga_turma WHERE id = ?");
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
