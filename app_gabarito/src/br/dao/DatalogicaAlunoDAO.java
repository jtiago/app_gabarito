package br.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.conexao.ConexaoDatalogica;

public class DatalogicaAlunoDAO {
	
	private Connection connDatalogica = ConexaoDatalogica.conectar();
	
	//retorna o codigo pessoa para pesquisar nome aluno
	public String codPessoa (String numMatricula){
		PreparedStatement pst = null;
		try{
		pst = (PreparedStatement) connDatalogica.prepareStatement("SELECT co_pessoa FROM tb_usuarios " +
				"WHERE nu_matricula = ?");
		pst.setString(1, numMatricula);
		ResultSet rs = pst.executeQuery();
		rs.next();
		
		return rs.getString("co_pessoa");
		
		}catch(SQLException sql){
			return sql.getMessage();
		}
	}
	
	//retorna o nome aluno
	public String getNome (String codPessoa){
		PreparedStatement pst = null;
		try{
			pst = (PreparedStatement) connDatalogica.prepareStatement("SELECT nome  FROM PESSOAS " +
					"WHERE codpessoa = ?");
			pst.setString(1, codPessoa);
			ResultSet rsNome = pst.executeQuery();
			rsNome.next();
			
			return rsNome.getString("nome");
			
			}catch(SQLException sql){
				return sql.getMessage();
			}
	}

}
