package com.example.laruotadipassaparola;

public class Lettera {
	private int stato ;
	private String lettera ;
	private String domanda ;
	private String risposta ;
	
	public int getStato(){
		return this.stato ;
	}
	
	public String getLettera(){
		return this.lettera ;
	}
	
	public String getDomanda(){
		return this.domanda ;
	}
	
	public String getRisposta(){
		return this.risposta ;
	}
	
	public void setStato(int stato){
		this.stato = stato ;
	}
	
	public void setLettera(String lettera){
		this.lettera = lettera ;
	}
	
	public void setDomanda(String domanda){
		this.domanda = domanda ;
	}
	
	public void setRisposta(String risposta){
		this.risposta = risposta ;
	}
}
