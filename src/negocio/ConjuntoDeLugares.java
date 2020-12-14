package negocio;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.ILugar;

public class ConjuntoDeLugares implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Lugar> lugares;
	
	public ConjuntoDeLugares() {
		this.lugares = new ArrayList<Lugar>();
	}
	
	public ConjuntoDeLugares(ArrayList<? extends ILugar> lugares) {
		this.lugares = new ArrayList<Lugar>();
		for (ILugar lugar : lugares)
			this.lugares.add(new Lugar(lugar.getNombre(),lugar.getUbicacion()));
	}

	public ArrayList<Lugar> getLugares() { return lugares; }
}
