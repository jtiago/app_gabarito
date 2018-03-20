package br.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import br.dao.DatalogicaAlunoDAO;
import br.dao.GabaritoAlunoDAO;
import br.dao.GabaritoCorretoDAO;


public class LeituraArquivo {
	
	private String erro = null;
	
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}

	public void lerGabaritoCorreto(String turma, String professor, String data, String ano, int qtdResposta) {
		String linha = null;
		GabaritoCorretoDAO dao = new GabaritoCorretoDAO();
		ArrayList<String> resposta = new ArrayList<String>();
		//StringBuffer concatena = new StringBuffer();

		try {
			FileReader readerGabaritoCorreto = new FileReader(
					"C:\\gabarito\\upload\\"+turma+"_" + ano + "/" +"correto.dat");
			BufferedReader leitorGabaritoCorreto = new BufferedReader(readerGabaritoCorreto);
			
			//ler cada linha do arquivo e isere na lista somente resposta
			while ((linha = leitorGabaritoCorreto.readLine()) != null) {
				resposta.add(linha.substring(8, qtdResposta+8));
			}

			//percorre a lista usando iterator camuflado do for insere no banco junto
			//com os paramentros recebido via metodo de Armazena gabarito
			for (String itemLista : resposta){
				dao.insert(turma, professor, itemLista, data, qtdResposta);
				System.out.println(itemLista);
			}
			
			readerGabaritoCorreto.close();
			leitorGabaritoCorreto.close();

		} catch (FileNotFoundException file) {
			setErro("A quantidade de questões digitado não é compatível com o gabarito lido na máquina LC-3000"+file.getMessage());
			file.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//metodo para ler o arquivo dat aluno, chamar do DAO e retorna um ArrayList com as 
	//matriculas nao encontradas no Banco DATALOGICA
	public ArrayList<String> lerGabaritoAlunos(String turma,String professor,String data, 
			String ano, int qtdResposta) {
		
		DatalogicaAlunoDAO daoDatalogica = new DatalogicaAlunoDAO();
		GabaritoAlunoDAO daoAluno = new GabaritoAlunoDAO();
		ArrayList<String> matricula = new ArrayList<String>();
		ArrayList<String> resposta = new ArrayList<String>();
		ArrayList<String> naoMatricula = new ArrayList<String>();
		String linha = null;
		String codigo = null;
		String nome = null;
		
		try {
			 
			FileReader readerGabaritoAlunos = new FileReader(
					"C:\\gabarito\\upload\\"+turma.trim()+"_"+ano+"/" + "aluno.dat");
			BufferedReader leitorGabaritoAlunos = new BufferedReader(readerGabaritoAlunos);
			
			//ler cada linha do arquivo e isere na lista separadas Matricula e Resposta
			while ((linha = leitorGabaritoAlunos.readLine()) != null) {
				matricula.add(linha.substring(0, 8));
				resposta.add(linha.substring(8, qtdResposta+8));
			}
			
			//usa iterador com obj instaciado e percorre para ser iserido no BD a cada Laço
			Iterator<String> matriculaIterator = matricula.iterator();
			Iterator<String> respostaIterator = resposta.iterator();
			int cout = 1;
			while(matriculaIterator.hasNext()){
				String itemMatricula = matriculaIterator.next();
				String itemResposta = respostaIterator.next();
				//capturo codPessoa do Aluno no BD DATALOGICA
				codigo = daoDatalogica.codPessoa(itemMatricula);
				//verifica se encontrou a matricula consultada, add no ArrayList os nao achado
				//senao insere tudo no banco
				System.out.println(">>>>>>>[LOG]<<<<<<-> Codigo Pessoa: "+codigo);
				System.out.println(">>>>>>>[LOG]<<<<<<-> Codigo Pessoa: "+codigo.length() );
				if (codigo.length() == 32){
					daoAluno.insertMatriculaNaoEncontrada(itemMatricula, turma);
					naoMatricula.add(itemMatricula);
					
					//insire todos os dados no BD gabarito
					daoAluno.insert(itemMatricula, "null", turma, professor, itemResposta, data, 
							qtdResposta, 0,cout);
					
				} else {
					//capturo o nome do aluno usando o codPessoa no BD DATALOGICA
					nome = daoDatalogica.getNome(codigo);
					//insire todos os dados no BD gabarito
					daoAluno.insert(itemMatricula, nome, turma, professor, itemResposta, data, 
							qtdResposta, Integer.parseInt(codigo),cout);
				}
				cout++;
			}
			
			readerGabaritoAlunos.close();
			leitorGabaritoAlunos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return naoMatricula;
	}
	
}


