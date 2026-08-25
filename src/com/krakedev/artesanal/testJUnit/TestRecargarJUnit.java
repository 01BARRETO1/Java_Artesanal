package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestRecargarJUnit {
	
	@Test
	
	public void testRecargaexitosa() {
		Maquina rubia = new Maquina("PILSENER", "Cerveza", 0.02, 8000);
		boolean resultado= rubia.recargarCerveza(3000);
		
		assertTrue(resultado);
		assertEquals(3000, rubia.getCantidadActual(), 0.001);
		
	}
	
@Test
	
	public void testRecargaFallidaPorDesborde() {
		Maquina negra = new Maquina("CLUB", "Cerveza fría", 0.03, 8000);
		negra.recargarCerveza(7000);
		boolean resultado= negra.recargarCerveza(1000);
		
		assertTrue(resultado);
		assertEquals(3000, negra.getCantidadActual(), 0.001);
		
	}

}
