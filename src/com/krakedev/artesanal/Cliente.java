package com.krakedev.artesanal;

import java.util.ArrayList;

public class Cliente {
	private String nombre;
	private String cedula;
	private int codigo;
	private double totalConsumido;
	
	private ArrayList<Cliente> clientes= new ArrayList<Cliente>();//🧪PARTE 2: Clientes. 8. Crear atributo ArrayList
	
	//constructor
	
	public Cliente(String nombre,String cedula ) {
		this.nombre=nombre;
		this.cedula=cedula;
		
	}
	
	//get and set
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public double getTotalConsumido() {
		return totalConsumido;
	}
	public void setTotalConsumido(double totalConsumido) {
		this.totalConsumido = totalConsumido;
	}
	
	//9. Método registrarCliente
	
	public void registrarCliente(String nombre, String cedula) {
		//Genera código con lógica de ultimoCodigo,
		this.codigo=100;
		//Crea una instancia de Cliente.
		Cliente cliente = new Cliente(nombre, cedula);
		cliente.setCodigo(codigo);
		codigo++;
		//Agrega el objeto creado (cliente) a la lista.
		clientes.add(cliente);
		
	}
	
	
}
