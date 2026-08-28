package com.krakedev.artesanal.testNegocio;

import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.Negocio;

public class TestNegocio {

	public static void main(String[] args) {
		
		Maquina MaquinaNueva = new Maquina("Cerveza club","Cerveza helada",0.02,8000,"Cerveza_Club");
		
		Negocio negocio1=new Negocio("Mi negocio", MaquinaNueva);
		
		System.out.println("Nombre: " + negocio1.getNombre());
		
		System.out.println("Máquina: " + negocio1.getMaquinaDeGuerra());
		
		Maquina m1= negocio1.getMaquinaDeGuerra();
		double capacidad = m1.getCapacidadMaxima();
	}

}
