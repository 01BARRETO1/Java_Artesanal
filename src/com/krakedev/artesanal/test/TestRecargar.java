package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestRecargar {

	public static void main(String[] args) {
		Maquina rubia=new Maquina("Club","Más fina", 0.02, 8000, "Artesanal_Club");
		System.out.println("================---------\n estado Incial");
		rubia.imprimir();
		System.out.println("---------================\n Recarga 1");
		boolean 
		 recarga = rubia.recargarCerveza(3000);
		rubia.imprimir();
		System.out.println("\n*********Carga completada = "+ recarga+"************");
		
		System.out.println("---------================\n Recarga 2");
		 recarga = rubia.recargarCerveza(2000);
		rubia.imprimir();
		System.out.println("\n*********Carga completada = "+ recarga+"************");
		
		System.out.println("---------================\n Recarga 3");
		 recarga = rubia.recargarCerveza(2900);
		rubia.imprimir();
		System.out.println("\n*********Carga completada = "+ recarga+"************");
		
		

	}

}
