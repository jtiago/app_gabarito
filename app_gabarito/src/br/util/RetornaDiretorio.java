package br.util;

public class RetornaDiretorio {
	
	public String getUrlWebContent(){
		String dir = System.getProperty("user.dir")+"\\WebContent\\";
		System.out.println(dir);
		return dir;	
	}
}
