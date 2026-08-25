package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestLlenar {

	public static void main(String[] args) {
		
		Maquina rubia=new Maquina("Club","Más fina", 0.02, 8000);
		rubia.imprimir();
		rubia.llenarMaquina();
		rubia.imprimir();
		
		Maquina negra=new Maquina("Pilsener", "Cerveza más barata y buena", 0.02);
		negra.imprimir();
		negra.llenarMaquina();
		negra.imprimir();
		

	}

}
