package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.Negocio;

public class TestConsumoCliente {

	@Test
	public void probarConsumo() {

		Maquina maquinaCervecera = new Maquina("Pilsener", "Rubia", 0.002, 8000, "Cerveza_Pilsener");
		Negocio barDeMoe = new Negocio("Bar de Moe", maquinaCervecera);
		Cliente cliente = new Cliente("Alcoholico1", "123456789");

		barDeMoe.cargarMaquina();

		barDeMoe.consumirCervezaMaquina(cliente, 100);

		assertEquals(7700, maquinaCervecera.getCantidadActual(), 0.0001);

		assertEquals(0.2, cliente.getTotalConsumido(), 0.0001);

		barDeMoe.consumirCervezaMaquina(cliente, 200);
		assertEquals(7500, maquinaCervecera.getCantidadActual(), 0.0001);

		assertEquals(0.6, cliente.getTotalConsumido(), 0.0001);

	}

}
