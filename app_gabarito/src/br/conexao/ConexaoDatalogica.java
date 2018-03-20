package br.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDatalogica {
	
public static Connection conectar(){
		
		Connection conn;
		String url = "jdbc:jtds:sqlserver://192.168.1.244:1433/logos";
		String usuario = "sa";
		String senha = "#Logos!";
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url, usuario, senha);
			return conn;
		} catch (SQLException ex) {
			System.err.println("SQLExecpiton: "+ ex.getMessage());
			System.err.println("SQLState: "+ ex.getSQLState());
			System.err.println("VendorError: "+ ex.getErrorCode());
			return null;
		} catch (ClassNotFoundException e){
			System.err.println("Problemas ao tentar conectar com BD" + e.getMessage());
			return null;
		} 
			
		
	}

}
