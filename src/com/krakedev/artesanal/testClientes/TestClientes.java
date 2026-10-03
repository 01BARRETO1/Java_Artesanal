package com.krakedev.artesanal.testClientes;

import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.NegocioMejorado;

public class TestClientes {

	public static void main(String[] args) {
		NegocioMejorado negocioMejorado = new NegocioMejorado();
		Cliente cliente = new Cliente("","");
		cliente.registrarCliente("Marcelo","123456789");
		

	}

}
