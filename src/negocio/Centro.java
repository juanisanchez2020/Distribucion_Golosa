package negocio;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.ILugar;


public class Centro implements Serializable, ILugar {

	private static final long serialVersionUID = 1L;
	private ArrayList<Cliente> clientesAsociados;
	private double utilidad;
	private Boolean activo;
	private String nombre;
	private Ubicacion ubicacion;
	
	public Centro(String nombre, Ubicacion ubicacion) {
		if(nombre == null || nombre.equals("")) {
			throw new IllegalArgumentException();
		}
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.clientesAsociados = new ArrayList<Cliente>();
		this.activo = false;
	}
			
	public Centro() {
		this.nombre = "";
		this.ubicacion = new Ubicacion();
		this.clientesAsociados = new ArrayList<Cliente>();
		activo = false;
	}

	public Centro(Centro centro) {
		this.clientesAsociados = new ArrayList<Cliente>();
		for (Cliente cliente : centro.getClientesAsociados())
			this.clientesAsociados.add(cliente);
		this.utilidad = centro.getUtilidad();
		this.activo = centro.getActivo();
		this.nombre = centro.getNombre();
		this.ubicacion = centro.getUbicacion();		
	}
	
	public double calcularUtilidad (double distanciaAcum) {
		return (double) Math.round(distanciaAcum / getClientesAsociados().size()*100000d)/100000d;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.getClass() != obj.getClass())
			return false;
		Centro otro = (Centro) obj;
		if ((this.getNombre() == otro.getNombre()) || (this.getUbicacion().equals(otro.getUbicacion())))
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		return "Centro\n[clientesAsociados=" + clientesAsociados.toString() + "\nutilidad=" + utilidad + "\nactivo=" + activo
				+ "\nnombre=" + nombre + "\nubicacion=" + ubicacion + "]\n";
	}

	public ArrayList<Cliente> getClientesAsociados() { return clientesAsociados; }
	public void setUtilidad(double utilidad) { this.utilidad = utilidad; }
	public void setActivo(Boolean activo) { this.activo = activo; }
	public void setClientesAsociados(ArrayList<Cliente> clientesAsociados) { this.clientesAsociados = clientesAsociados; }
	public ArrayList<Cliente> getConjuntoDeClientes() { return clientesAsociados; }
	public double getUtilidad() { return utilidad; }
	public Boolean getActivo() { return activo; }	
	public String getNombre() { return nombre; }
	public Ubicacion getUbicacion() { return ubicacion; }
}
