package negocio;

import java.util.ArrayList;

public class DatosOptimizados {
	ArrayList<Centro> centrosOptimizados;
	ArrayList<Cliente> clientesOptimizados;
	ArrayList<Centro> centrosActivos;
	
	public DatosOptimizados(ArrayList<Centro> centrosOptimizados, ArrayList<Cliente> clientesOptimizados) {
		this.centrosOptimizados = centrosOptimizados;
		this.clientesOptimizados = clientesOptimizados;
		centrosActivos(); 
	}

	public void centrosActivos() {
		ArrayList<Centro> activos = new ArrayList<Centro>();
		
		for (Centro centro: centrosOptimizados) {
			if(centro.getActivo()) {
				activos.add(centro);
			}
		}
		this.centrosActivos = activos;
	}
	
	public int cantidadCentrosActivos() {
		int activos = 0;
		for (Centro centro: centrosOptimizados) {
			if(centro.getActivo()) {
				activos++;
			}
		}
		return activos;
	}
	
	public ArrayList<Centro> getCentrosOptimizados() { return centrosOptimizados; }
	public ArrayList<Cliente> getClientesOptimizados() { return clientesOptimizados; }	
	public ArrayList<Centro> getCentrosActivos() {return centrosActivos;}
}
