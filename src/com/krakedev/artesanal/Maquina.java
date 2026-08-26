package com.krakedev.artesanal;

public class Maquina {
	private String nombreCerveza;
	private String descripcion;
	private double precioPorMl;
	private double capacidadMaxima;
	private double cantidadActual;
	//🔥 RETO – Taller de Mejora y Pruebas 
	private String codigo;// Este atributo debe tener solo; Método get
	
	public Maquina(String nombreCerveza, String descripcion, double precioPorMl,
			double capacidadMaxima, String codigo ) {
		
		this.nombreCerveza=nombreCerveza;
		this.descripcion=descripcion;
		this.precioPorMl=precioPorMl;
		this.capacidadMaxima=capacidadMaxima;
		this.cantidadActual=0;
		
		//Incluir el atributo código en todos los constructores de la clase. 
		this.codigo=codigo;//
		
		
	}
	
	public Maquina(String nombreCerveza, String descripcion, double precioPorMl, String codigo) {
		
		this.nombreCerveza=nombreCerveza;
		this.descripcion=descripcion;
		this.precioPorMl=precioPorMl;
		this.capacidadMaxima=10000;
		this.cantidadActual=0;
		
		//Incluir el atributo código en todos los constructores de la clase. 
		this.codigo=codigo;//	
		
	}
	
	
	//get and set
	public String getNombreCerveza() {
		return nombreCerveza;
	}
	public void setNombreCerveza(String nombreCerveza) {
		this.nombreCerveza = nombreCerveza;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecioPorMl() {
		return precioPorMl;
	}
	public void setPrecioPorMl(double precioPorMl) {
		this.precioPorMl = precioPorMl;
	}
	public double getCapacidadMaxima() {
		return capacidadMaxima;
	}
	
	public double getCantidadActual() {
		return cantidadActual;
	}
	
	// Método get del atributo codigo
	public String getCodigo() {
		return codigo;
	}
	
	//Método
	
	public void imprimir() {
		
		String mensaje="Nombre de la cerveza: " + nombreCerveza+
						" \n descripción: "+ descripcion+
						" \n Precio por ML: "+precioPorMl+
						" \n Capacidad Máxima: "+capacidadMaxima+
						" \n Cantidad Actual: "+cantidadActual+
						" \n Código: "+codigo;
		
		
		System.out.println("_________________________\n"+mensaje);
		
	}
	
	//método llenar máquina
	
	

	public void llenarMaquina() {
		this.cantidadActual=this.capacidadMaxima - 200; //capacidad máxima menos 200 ml  
	}
	
	//método recargar cerveza
	public boolean recargarCerveza(double cantidad) {
		double limitePermitido = capacidadMaxima - 200;//capacidad máxima menos 200 ml 
		if(cantidadActual + cantidad<=limitePermitido) {
			
			cantidadActual = cantidadActual + cantidad;
			
			return true;
		}else {
			return false;
		}
	}
	
	public double servirCerveza(double cantidad) {
		if(cantidadActual >= cantidad) {
			cantidadActual = cantidadActual-cantidad;
			
			double valor;
			valor= cantidad * precioPorMl;
			return valor;
		}else {
			return 0;
		}
	}
	
}
