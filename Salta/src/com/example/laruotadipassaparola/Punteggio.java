package com.example.laruotadipassaparola;

public class Punteggio {
	
	private String posizione ;
	private int punteggio ;
	private String nome ;
	
	public String getPosizione(){
		return this.posizione ;
	}
	public int getPunteggio(){
		return this.punteggio ;
	}
	
	public String getNome(){
		return this.nome ;
	}
	
	public void setPosizione(String posizione){
		this.posizione = posizione ;
	}
	
	public void setPunteggio(int punteggio){
		this.punteggio = punteggio ;
	}
	
	public void setNome(String nome){
		this.nome = nome ;
	}
}
