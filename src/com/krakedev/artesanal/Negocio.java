package com.krakedev.artesanal;

public class Negocio {
	
	private String nombre;
	private Maquina maquinaDeGuerra;
	private int ultimoCodigo=100;
	
	//constructor vacío
	public Negocio() {
		
	}
	
	//constructor
	public Negocio(String nombre, Maquina maquinaDeGuerra) {
		
		this.nombre = nombre;
		this.maquinaDeGuerra = maquinaDeGuerra;
	}
	//get and set
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Maquina getMaquinaDeGuerra() {
		return maquinaDeGuerra;
	}
	public void setMaquinaDeGuerra(Maquina maquinaDeGuerra) {
		this.maquinaDeGuerra = maquinaDeGuerra;
	}
	//
	public void asignarCodigoCliente(Cliente cliente) {
		
		cliente.setCodigo(ultimoCodigo);
		ultimoCodigo ++;
		
	}
	
	public void cargarMaquina() {
		maquinaDeGuerra.llenarMaquina();
	}
	
	public void consumirCervezaMaquina(Cliente cliente, double ml) {
		
		double valor=maquinaDeGuerra.servirCerveza(ml);
		cliente.setTotalConsumido(cliente.getTotalConsumido() + valor);
		
		
	}

}
