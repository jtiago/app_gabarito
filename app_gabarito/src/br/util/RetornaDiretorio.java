package br.util;

public class RetornaDiretorio {
	
	public static final String SEMESTRE = "2018.1";
	
	public String getUrlWebContent(){
		String dir = System.getProperty("user.dir")+"\\WebContent\\";
		System.out.println(dir);
		return dir;	
	}
}
