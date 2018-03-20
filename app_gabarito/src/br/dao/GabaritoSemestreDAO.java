package br.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.conexao.ConexaoGabarito;
import br.modelo.Semestre;

public class GabaritoSemestreDAO {
	
	public void insert(String semestre, String dataabertura, String dataencerramento) throws SQLException{
		Connection conn = ConexaoGabarito.conectar();
		PreparedStatement pst = null;
		
		try {
			pst = (PreparedStatement) conn.prepareStatement("INSERT INTO ga_semestre " +
					"(semestre, dataabertura, dataencerramento) VALUES (?,?,?)");
			pst.setString(1, semestre);
			pst.setString(2, dataabertura);
			pst.setString(3, dataencerramento);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) conn.close();
		}
	}
	
	public ArrayList<Semestre> getTodosSemestre() throws SQLException{
		ArrayList<Semestre> lista = new ArrayList<Semestre>();
		Connection conn = ConexaoGabarito.conectar();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM ga_semestre ORDER BY semestre ASC");
			rs = pst.executeQuery();
			
			if(!rs.next()){
				return lista;
			}else{
				do{
					int id = rs.getInt("id");
					String semestre = rs.getString("semestre");
					String dataabertura = rs.getString("dataabertura");
					String dataencerramento = rs.getString("dataencerramento");
					lista.add(new Semestre(id,semestre,dataabertura,dataencerramento));
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
	
	public ArrayList<Semestre> getSemestre(String codigo) throws SQLException{
		ArrayList<Semestre> lista = new ArrayList<Semestre>();
		Connection conn = ConexaoGabarito.conectar();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM ga_semestre WHERE id = ?");
			pst.setString(1, codigo);
			rs = pst.executeQuery();
			
			if(!rs.next()){
				return lista;
			}else{
				do{
					int id = rs.getInt("id");
					String semestre = rs.getString("semestre");
					String dataabertura = rs.getString("dataabertura");
					String dataencerramento = rs.getString("dataencerramento");
					lista.add(new Semestre(id,semestre,dataabertura,dataencerramento));
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
	
	public ArrayList<Semestre> getFiltroSemestre(String Semestre) throws SQLException{
		ArrayList<Semestre> lista = new ArrayList<Semestre>();
		Connection conn = ConexaoGabarito.conectar();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM ga_semestre WHERE semestre != ?");
			pst.setString(1, Semestre);
			rs = pst.executeQuery();
			
			if(!rs.next()){
				return lista;
			}else{
				do{
					int id = rs.getInt("id");
					String semestre = rs.getString("semestre");
					String dataabertura = rs.getString("dataabertura");
					String dataencerramento = rs.getString("dataencerramento");
					lista.add(new Semestre(id,semestre,dataabertura,dataencerramento));
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
	
	public void alteraSemestre(String id, String semestre, String dataabertura, String dataencerramento) throws SQLException{
		PreparedStatement pst = null;
		Connection conn = ConexaoGabarito.conectar();
		
		try {
			pst = conn.prepareStatement("UPDATE ga_semestre SET semestre = ?, dataabertura = ?, dataencerramento = ? "+
					"WHERE id = ?");
			pst.setString(1, semestre);
			pst.setString(2, dataabertura);
			pst.setString(3, dataencerramento);
			pst.setString(4, id);
			pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pst.close();
			if(conn != null) conn.close();
		}
	}
	
	public void deletaSemestre(String codigo) throws SQLException{
		PreparedStatement pst = null;
		Connection conn = ConexaoGabarito.conectar();
		
		try {
			pst = conn.prepareStatement("DELETE FROM ga_semestre WHERE id = ?");
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
