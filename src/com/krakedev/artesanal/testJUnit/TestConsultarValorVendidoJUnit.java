package com.krakedev.artesanal.testJUnit;
import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.NegocioMejorado;

import static org.junit.jupiter.api.Assertions.*;

public class TestConsultarValorVendidoJUnit {
	private static final double DELTA = 1e-6;

    @Test
    // Prueba: Sin clientes registrados. Resultado esperado: el valor vendido total debe ser 0.0.
    public void consultarValorVendido_SinClientes_RetornaCero() {
        NegocioMejorado negocio = new NegocioMejorado();

        double total = negocio.consultarValorVendido();

        assertEquals(0.0, total, DELTA, "Cuando no hay clientes, el total vendido debe ser 0.0");
    }

    @Test
    // Prueba: Un solo cliente con consumo registrado mediante registrarConsumo. Resultado esperado: retorna el consumo registrado.
    public void consultarValorVendido_UnClienteConConsumo_RetornaConsumoDelCliente() {
        NegocioMejorado negocio = new NegocioMejorado();

        // Registrar cliente
        String cedula = "0102030405";
        negocio.registrarCliente("Ana", cedula);

        // Recuperar cliente por cédula y registrar consumo
        Cliente cliente = negocio.buscarClientePorCedula(cedula);
        assertNotNull(cliente, "El cliente recién registrado no debe ser null");

        negocio.registrarConsumo(cliente, 75.50);

        double total = negocio.consultarValorVendido();

        assertEquals(75.50, total, DELTA, "El total vendido debe ser igual al consumo registrado del único cliente");
        assertTrue(total > 0.0, "El total debe ser mayor que 0 cuando hay consumo registrado");
    }

    @Test
    // Prueba: Varios clientes con consumos distintos. Resultado esperado: retorna la suma de todos los consumos.
    public void consultarValorVendido_VariosClientes_RetornaSumaDeConsumos() {
        NegocioMejorado negocio = new NegocioMejorado();

        // Registrar clientes
        negocio.registrarCliente("Luis", "1111111111");
        negocio.registrarCliente("María", "2222222222");

        Cliente c1 = negocio.buscarClientePorCedula("1111111111");
        Cliente c2 = negocio.buscarClientePorCedula("2222222222");

        assertNotNull(c1, "Cliente 1 debe existir");
        assertNotNull(c2, "Cliente 2 debe existir");

        // Registrar consumos
        negocio.registrarConsumo(c1, 30.0);
        negocio.registrarConsumo(c2, 45.25);

        double total = negocio.consultarValorVendido();
        double esperado = 30.0 + 45.25;

        assertEquals(esperado, total, DELTA, "El total vendido debe ser la suma de los consumos de todos los clientes");
        assertFalse(total == 0.0, "El total no debe ser 0 cuando hay consumos registrados");
    }

    @Test
    // Prueba: Un cliente sin consumo y otro con consumo. Resultado esperado: el total es solo el consumo del cliente que consumió.
    public void consultarValorVendido_ClienteSinConsumo_NoAfectaTotal() {
        NegocioMejorado negocio = new NegocioMejorado();

        // Registrar dos clientes
        negocio.registrarCliente("Pedro", "3333333333");
        negocio.registrarCliente("Sofía", "4444444444");

        Cliente sinConsumo = negocio.buscarClientePorCedula("3333333333");
        Cliente conConsumo = negocio.buscarClientePorCedula("4444444444");

        assertNotNull(sinConsumo, "Cliente sin consumo debe existir");
        assertNotNull(conConsumo, "Cliente con consumo debe existir");

        // Solo registrar consumo para uno de ellos
        negocio.registrarConsumo(conConsumo, 120.0);

        double total = negocio.consultarValorVendido();

        assertEquals(120.0, total, DELTA, "El total debe reflejar únicamente el consumo del cliente que consumió");
    }

    @Test
    // Prueba auxiliar: buscarClientePorCedula con cédula inexistente devuelve null; además, si no hay clientes, consultarValorVendido sigue siendo 0.0.
    public void buscarClientePorCedula_Inexistente_RetornaNull_y_consultarValorVendidoPermaneceCero() {
        NegocioMejorado negocio = new NegocioMejorado();

        // No se registra ningún cliente
        Cliente resultado = negocio.buscarClientePorCedula("9999999999");
        assertNull(resultado, "Buscar una cédula inexistente debe devolver null");

        double total = negocio.consultarValorVendido();
        assertEquals(0.0, total, DELTA, "Con ningún cliente registrado, el total vendido debe ser 0.0");
    }
}