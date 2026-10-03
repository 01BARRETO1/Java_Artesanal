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
	//4. Método agregarMaquina
	public boolean agregarMaquina(String nombre, String descripcion, double precio) {
		//7. Validación de duplicados, 
		//Generar código invocando al método generarCodigo
		String numCod=generarCodigo();
		//7. Validación de duplicados->No permitir códigos repetidos
		Maquina repetida = recuperarMaquina(numCod);
		if(repetida != null) {
			//Ya existe una máquina con ese código
			return false;
		}else {
			//Crear objeto Maquina con los valores que recibe y el resultado de generarCodigo
			Maquina nuevaMaquina = new Maquina(nombre, descripcion, precio, numCod);
			//Agregar el objeto creado a la lista
			maquinas.add(nuevaMaquina);
			return true;
		}
			
	}
	
	//5. Método cargarMaquinas
	
	public void cargarMaquinas() {
		//Usando un for, invocar al método llenarMaquina de todas las maquinas
		// Recorre todas las máquinas
		for(int i = 0; i<maquinas.size(); i++) {
			//guardo en m las Maquinas con la posición 
			Maquina m = maquinas.get(i);
			// Invoca el método llenarMaquina de cada objeto Maquina
			m.llenarMaquina();	
		}
		
	}
	
	//6. Método recuperarMaquina
	
	public Maquina recuperarMaquina(String codigoMaquina) {
		for(int i = 0; i<maquinas.size(); i++) {
			//guardo en m las Maquinas con la posición 
			Maquina m = maquinas.get(i);
			//Busca en la lista si coincide el codigo
			if(m.getCodigo().equals(codigoMaquina)) {
				return m;
			}
			
		}
		return null;
	}
	
	

}
