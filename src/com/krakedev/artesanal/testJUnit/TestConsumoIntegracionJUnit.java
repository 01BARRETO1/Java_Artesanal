package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.NegocioMejorado;

public class TestConsumoIntegracionJUnit {

	// 1) Cliente actualizado
	// Se prueba que al consumir cerveza el totalConsumido del cliente se actualiza
	// Resultado esperado: totalConsumido del cliente aumenta en (cantidad *
	// precioPorMl)
	@Test
	public void clienteActualizado_alConsumirCerveza_totalConsumidoIncrementado() {
		// Preparación del negocio y cliente
		NegocioMejorado negocio = new NegocioMejorado();
		negocio.registrarCliente("Carlos Ruiz", "1717171717");
		Cliente cliente = negocio.buscarClientePorCedula("1717171717");
		assertNotNull(cliente, "El cliente registrado debe existir.");

		int codigoCliente = cliente.getCodigo();

		// Preparación de la máquina real con código conocido y precio por ml
		Maquina maquina = new Maquina("Pilsner", "Cerveza clara", 0.01, "M-1000");
		// Recargar suficiente cantidad para poder servir
		boolean recargoOk = maquina.recargarCerveza(500.0); // 500 ml
		assertTrue(recargoOk, "La recarga inicial debe ser permitida.");

		// Agregar la máquina al negocio
		negocio.getMaquinas().add(maquina);

		// Estado inicial: total consumido 0.0
		assertEquals(0.0, cliente.getTotalConsumido(), 0.0001, "Total inicial debe ser 0.0");

		// Acción: cliente consume 200 ml
		double cantidad = 200.0;
		negocio.consumirCerveza(codigoCliente, "M-1000", cantidad);

		// Verificación: totalConsumido debe ser cantidad * precioPorMl
		double esperado = cantidad * maquina.getPrecioPorMl();
		assertEquals(esperado, cliente.getTotalConsumido(), 0.0001,
				"Total consumido debe incrementarse correctamente.");
	}

	// 2) Máquina afectada
	// Se prueba que al servir cerveza la cantidadActual de la máquina disminuye la
	// cantidad servida
	// Resultado esperado: cantidadActual final = cantidadInicial - cantidadServida;
	// además el cliente recibe el cargo.
	@Test
	public void maquinaAfectada_alServirCerveza_cantidadActualDisminuyeYClienteRecibeCargo() {
		// Preparación
		NegocioMejorado negocio = new NegocioMejorado();
		negocio.registrarCliente("María López", "0808080808");
		Cliente cliente = negocio.buscarClientePorCedula("0808080808");
		assertNotNull(cliente, "El cliente registrado debe existir.");
		int codigoCliente = cliente.getCodigo();

		// Crear máquina con precio por ml y código
		Maquina maquina = new Maquina("IPA", "Cerveza amarga", 0.02, "M-2000");
		// Recargar 1000 ml
		boolean recargoOk = maquina.recargarCerveza(1000.0);
		assertTrue(recargoOk, "La recarga inicial debe ser permitida.");

		// Agregar la máquina al negocio
		negocio.getMaquinas().add(maquina);

		// Cantidad inicial
		double inicial = maquina.getCantidadActual();
		assertEquals(1000.0, inicial, 0.0001, "Cantidad inicial debe ser la recargada (1000 ml).");

		// Acción: servir 250 ml
		double servir = 250.0;
		negocio.consumirCerveza(codigoCliente, "M-2000", servir);

		// Verificación: la máquina debe haber disminuido su cantidadActual en 'servir'
		double esperadoCantidad = inicial - servir;
		assertEquals(esperadoCantidad, maquina.getCantidadActual(), 0.0001,
				"La cantidad actual de la máquina debe disminuir en la cantidad servida.");

		// Verificación adicional: el cliente debe haber sido cargado con precio =
		// servir * precioPorMl
		double esperadoCargo = servir * maquina.getPrecioPorMl();
		assertEquals(esperadoCargo, cliente.getTotalConsumido(), 0.0001,
				"El cliente debe tener el total consumido igual al cargo calculado.");
	}

	// 3) Valores correctos: agregar y recuperar máquinas
	// Se prueba que agregarMaquina inserta la máquina en la lista y
	// recuperarMaquina devuelve la instancia correcta.
	// Resultado esperado: agregarMaquina retorna true; recuperarMaquina con el
	// código devuelto devuelve la misma instancia; recuperarMaquina con código
	// inexistente devuelve null.
	@Test
	public void valoresCorrectos_agregarYRecuperarMaquina_funcionaYRecuperaCorrectamente() {
		// Preparación
		NegocioMejorado negocio = new NegocioMejorado();

		// Lista inicialmente vacía
		assertTrue(negocio.getMaquinas().isEmpty(), "La lista de máquinas debe iniciar vacía.");

		// Agregar una máquina mediante el método público (genera código internamente)
		boolean agregado = negocio.agregarMaquina("Stout", "Cerveza oscura", 0.015);
		assertTrue(agregado, "Se espera que agregarMaquina retorne true cuando no hay duplicados.");

		// Debe existir exactamente una máquina en la lista
		assertEquals(1, negocio.getMaquinas().size(), "Debe haber exactamente una máquina en la lista.");

		// Obtener la máquina y su código
		Maquina m = negocio.getMaquinas().get(0);
		String codigo = m.getCodigo();
		assertNotNull(codigo, "El código generado no debe ser null.");

		// Recuperar por código debe devolver la misma instancia
		Maquina recuperada = negocio.recuperarMaquina(codigo);
		assertNotNull(recuperada, "recuperarMaquina debe devolver la máquina agregada.");
		assertEquals(m, recuperada, "La máquina recuperada debe ser la misma instancia que la agregada.");

		// Recuperar con código inexistente debe devolver null
		Maquina inexistente = negocio.recuperarMaquina("CODIGO-INEXISTENTE-XYZ");
		assertNull(inexistente, "recuperarMaquina con código inexistente debe devolver null.");

	}

	// Test 1: registrarConsumo actualiza el totalConsumido del cliente una sola vez
	// Qué se prueba: llamar a registrarConsumo con un cliente y un precio positivo.
	// Resultado esperado: el totalConsumido del cliente aumenta exactamente en el
	// precio pasado.
	@Test
	public void registrarConsumo_unRegistro_totalConsumidoIncrementado() {
		NegocioMejorado negocio = new NegocioMejorado();

		// Crear cliente y asignarle un código (simula cliente recuperado)
		Cliente cliente = new Cliente("Luis", "0101010101");
		cliente.setCodigo(123);
		cliente.setTotalConsumido(0.0);

		// Acción: registrar consumo por 12.5
		negocio.registrarConsumo(cliente, 12.5);

		// Verificación: totalConsumido debe ser 12.5
		assertEquals(12.5, cliente.getTotalConsumido(), 0.0001, "El totalConsumido debe incrementarse en 12.5");
	}

	// Test 2: registrarConsumo acumulativo con múltiples invocaciones
	// Qué se prueba: llamar varias veces a registrarConsumo para el mismo cliente.
	// Resultado esperado: los valores se acumulan correctamente.
	@Test
	public void registrarConsumo_multiplesRegistros_valorAcumulado() {
		NegocioMejorado negocio = new NegocioMejorado();

		Cliente cliente = new Cliente("Sofía", "0202020202");
		cliente.setCodigo(200);
		cliente.setTotalConsumido(5.0); // ya tiene un consumo previo

		// Acción: registrar dos consumos adicionales
		negocio.registrarConsumo(cliente, 3.0);
		negocio.registrarConsumo(cliente, 7.0);

		// Verificación: totalConsumido debe ser 5 + 3 + 7 = 15
		assertEquals(15.0, cliente.getTotalConsumido(), 0.0001,
				"El totalConsumido debe acumular los tres valores correctamente.");
	}

	// Test 3: registrarConsumo con precio cero no modifica el totalConsumido
	// Qué se prueba: registrar un consumo con precio 0.0.
	// Resultado esperado: el totalConsumido permanece igual.
	@Test
	public void registrarConsumo_precioCero_noModificaTotal() {
		NegocioMejorado negocio = new NegocioMejorado();

		Cliente cliente = new Cliente("Ana", "0303030303");
		cliente.setCodigo(300);
		cliente.setTotalConsumido(10.0);

		// Acción: registrar consumo con precio 0.0
		negocio.registrarConsumo(cliente, 0.0);

		// Verificación: totalConsumido sigue siendo 10.0
		assertEquals(10.0, cliente.getTotalConsumido(), 0.0001,
				"El totalConsumido no debe cambiar cuando el precio es 0.0");
	}
}
