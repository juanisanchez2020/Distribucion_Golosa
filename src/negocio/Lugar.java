package negocio;

import java.io.Serializable;

public class Lugar implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private Ubicacion ubicacion;
	

	public Lugar(String nombre, Ubicacion ubicacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}
	
	public String getNombre() { return nombre; }
	public Ubicacion getUbicacion() { return ubicacion; }

}
