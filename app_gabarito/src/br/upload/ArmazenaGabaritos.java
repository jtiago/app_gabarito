package br.upload;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;

import br.reader.LeituraArquivo;
import br.util.RetornaDiretorio;

public class ArmazenaGabaritos {
	
	
	private ArrayList<String> naoMatricula = new ArrayList<String>();
	private String caminhoTemp = "C:/Tiago/workspace/app_gabarito/WebContent/temp";
	DiskFileUpload upload = new DiskFileUpload();
	private RetornaDiretorio dir = new RetornaDiretorio();
	private String filename;
	private String turma;
	private String semestre;
	private String qtnQuestoes;
	private String tipo;

	private String extensoesPermitidas;
	private String erro = null;

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getQtnQuetoes() {
		return qtnQuestoes;
	}

	public void setQtnQuetoes(String qtnQuetoes) {
		this.qtnQuestoes = qtnQuetoes;
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFilename() {
		return filename;
	}

	public String getExtensoesPermitidas() {
		return extensoesPermitidas;
	}

	public void setExtensoesPermitidas(String extensoesPermitidas) {
		this.extensoesPermitidas = extensoesPermitidas;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public boolean doFilePost(HttpServletRequest request, ServletContext context) {

		if (request.getContentType() == null)
			return false;

		if (!request.getContentType().startsWith("multipart/form-data")) {
			setErro("Seu formulário não enviar arquivos");
			return false;
		}

		upload.setRepositoryPath(caminhoTemp);

		try {

			Date data = new Date();
			SimpleDateFormat formataDataPasta = new SimpleDateFormat("yyyy");
			SimpleDateFormat formataDataBanco = new SimpleDateFormat("dd/MM/yy");
			String ano = formataDataPasta.format(data);
			String dataBD = formataDataBanco.format(data);
			
			List list = upload.parseRequest(request);
			Iterator iterator = list.iterator();
			FileItem arqItem = null;
			File caminho = new File("");
			boolean criar = false;

			for (int j = 0; iterator.hasNext(); j++) {
				FileItem item = (FileItem) iterator.next();

				if (item.isFormField()) {
					if (item.getFieldName().equals("tipo")) {
						tipo = item.getString();
					}
					if (item.getFieldName().equals("turma")) {
						turma = item.getString();
					}
					if (item.getFieldName().equals("semestre")) {
						semestre = item.getString();
					}
					if (item.getFieldName().equals("qtn_questoes")) {
						qtnQuestoes = item.getString();
						if(turma.length() > 0 & semestre.length() > 0  & qtnQuestoes.length() > 0)
							criar = true; 
					}
					//while para criar o diretorio somente se o campos acima tiverem preenhido corretamente
					while(criar){
						//caminho = new File("C:/Tiago/workspace/app_gabarito/WebContent/upload/"+turma+"_"+semestre);
						//caminho = new File("/upload/"+turma.trim()+"_"+ano.trim());
						caminho = new File("C:\\gabarito\\upload\\"+turma.trim()+"_"+ano.trim());
						System.out.println(caminho.getAbsolutePath());
						caminho.mkdirs();
						criar = false;
					}
					
				} else {
					arqItem = item;
					filename = arqItem.getName();

					if ((filename.equals(null)) || (filename.equals(""))) {
						setErro("Por Favor preencher todos campos corretamente!");
						return false;
					} else {
						filename = (new File(filename)).getName();

						//muda o nopme do filename
						String ext[] = filename.split("\\.");
						int i = ext.length;
						if (i > 1)
							filename = tipo+"."+ext[i - 1];

						if (isPermicao(filename)) {
							arqItem.write(new File(caminho+"/"+ filename.trim()));
						} else {
							setErro("A extenção do arquivo não é permitida");
							return false;
						}
					}
				}
			}// for

			if (turma.length() <= 0 || semestre.length() <= 0  || qtnQuestoes.length() <= 0) {
				setErro("Por Favor preencher todos campos corretamente!");
				return false;
			}
			
			LeituraArquivo gabarito = new LeituraArquivo();
			if(tipo.length() == 5){
				naoMatricula = gabarito.lerGabaritoAlunos(turma, semestre, dataBD, ano, Integer.parseInt(qtnQuestoes));
			}else{
				gabarito.lerGabaritoCorreto(turma, semestre, dataBD, ano, Integer.parseInt(qtnQuestoes));
			}
			

		} catch (FileUploadBase.SizeLimitExceededException slee) {
			slee.printStackTrace();
			setErro("Tamanho Excedido");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			setErro("Uma Exceção ocorreu: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	//Metodo para retornar um ArrayList das matriculas nao encontradas no banco
	public ArrayList<String> getListMatNaoEncontradas(){
		return naoMatricula; 
	}

	public boolean isPermicao(String fileName) {
		String lowerCaseName = fileName.toLowerCase();
		StringTokenizer st = new StringTokenizer(extensoesPermitidas, " ,");

		while (st.hasMoreTokens()) {
			if (lowerCaseName.endsWith("." + st.nextToken()))
				return true;
		}
		return false;
	}
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
	}
}
