package negocio;

import java.io.Serializable;

public class Ubicacion implements Serializable {

	private static final long serialVersionUID = 1L;
	private double latitud;
	private double longitud;

	public Ubicacion(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Ubicacion() {
		this.latitud = 0;
		this.longitud = 0;
	}

	//calcula la distancia a traves de Pitagoras, CentroDeDistribucion con un cliente, tambien se podria hace con una direccion
	public double calcularDistancia(Ubicacion u) {
		 return Math.sqrt(Math.pow(this.getLatitud() - u.getLatitud(), 2) + Math.pow(this.getLongitud() - u.getLongitud(), 2));	
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass() != obj.getClass())
			return false;
		Ubicacion otro = (Ubicacion) obj;
		if ((this.getLatitud() != otro.getLatitud()) && (this.getLongitud() != otro.getLongitud()))
			return false;
		else
			return true;
	}

	public double getLatitud() { return latitud; }

	public double getLongitud() { return longitud; }

}
