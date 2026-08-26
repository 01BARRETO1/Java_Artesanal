package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestServir {

	public static void main(String[] args) {
		Maquina rubia=new Maquina("Club","Más fina", 0.02, 8000, "Aretesanal_Club");
		System.out.println("================---------\n estado Incial");
		rubia.imprimir();
		System.out.println("================---------\n Llenando maquina...");
		rubia.llenarMaquina();
		rubia.imprimir();
		System.out.println("================---------\n SERVIR 1000 ML...");
		double precio;
		precio=rubia.servirCerveza(1000);
		System.out.println("--------\n total a pagar : $"+precio);
		
		System.out.println("================---------\n SERVIR 2000 ML...");
		
		precio=rubia.servirCerveza(2000);
		System.out.println("--------\n total a pagar : $"+precio);
		rubia.imprimir();
		
		System.out.println("================---------\n SERVIR 6000 ML...");
		precio=rubia.servirCerveza(6000);
		System.out.println("--------\n total a pagar : $"+precio);
		rubia.imprimir();
		
		
		

	}

}
