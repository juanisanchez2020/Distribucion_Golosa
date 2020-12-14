package negocio;

import java.io.Serializable;

import interfaces.ILugar;

public class Cliente implements Serializable, ILugar {

	private static final long serialVersionUID = 1L;
	private Centro centroAsociado;
	private double distanciaAlCentro;
	private String nombre;
	private Ubicacion ubicacion;

	
	public Cliente(String nombre, Ubicacion ubicacion) {
		if(nombre == null || nombre.equals("")) {
			throw new IllegalArgumentException();
		}
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.distanciaAlCentro = Double.MAX_VALUE;
		this.centroAsociado = new Centro();
	}
	
	public Cliente(Cliente cliente) {
		this.centroAsociado = new Centro(cliente.getCentroAsociado());
		this.distanciaAlCentro = cliente.getDistanciaAlCentro();
		this.nombre = cliente.getNombre();
		this.ubicacion = cliente.getUbicacion();
	}

	@Override
	public boolean equals(Object obj) {
		if (this.getClass() != obj.getClass())
			return false;
		Cliente otro = (Cliente) obj;
		if ((this.getNombre() == otro.getNombre()) || (this.getUbicacion().equals(otro.getUbicacion())))
			return true;
		else
			return false;
	}
		
	public Centro getCentroAsociado() { return centroAsociado; }
	public double getDistanciaAlCentro() { return distanciaAlCentro; }
	public double setDistanciaAlCentro(double distancia) { distanciaAlCentro=distancia; return distancia; }
	public void setCentroAsociado(Centro centroAsociado) {this.centroAsociado = centroAsociado;}
	public String getNombre() { return nombre; }
	public Ubicacion getUbicacion() { return ubicacion; }
	
}
