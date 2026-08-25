package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestAtributos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Maquina rubia = new Maquina("Pilsener", "Cerveza rubia", 0.02, 10000);
		
		rubia.imprimir();
		
		rubia.setNombreCerveza("Club");
		rubia.setDescripcion("cerveza con aroma más intenso");
		rubia.imprimir();
	}

}
