package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestLlenarJUnit {
	
	@Test
	public void testLlenarMaquina() {
		
		Maquina rubia = new Maquina("PILSENER", "Cerveza", 0.02, 8000);
		rubia.llenarMaquina();
		
		assertEquals(7900, rubia.getCantidadActual(), 0.0001);
		
	}

}
