package com.krakedev.artesanal;

import java.util.ArrayList;

public class NegocioMejorado {
	//Agregar un atributo maquinas del tipo ArrayList de Maquina
	ArrayList<Maquina> maquinas;
	
	//
	
	//get and set

	public ArrayList<Maquina> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(ArrayList<Maquina> maquinas) {
		this.maquinas = maquinas;
	}
	
	//Costructor, Inicializar el ArrayList
	public NegocioMejorado() {
		maquinas = new ArrayList<Maquina>();
	}
	
	//Método generarCodigo
	
	public String generarCodigo() {
		//aleatorio de 0-100
		int aleatorio= (int)(Math.random()*101);
		return "M-"+aleatorio;
		
	}
	

}
