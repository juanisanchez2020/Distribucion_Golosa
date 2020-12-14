package negocio;

import java.util.ArrayList;
import java.util.Collections;

public class Heuristica {

	ArrayList<Centro> centrosOptimizados;
	ArrayList<Cliente> clientesOptimizados;
	int cantidad;

	public DatosOptimizados optimizarCentros(int cantidad, ArrayList<Centro> centros, ArrayList<Cliente> clientes) {
		if(centros == null || clientes == null || centros.size() == 0 || clientes.size() == 0)
			throw new IllegalArgumentException();
		
		centrosOptimizados = clonarCentros(centros);
		clientesOptimizados = clonarClientes(clientes);
		this.cantidad = cantidad;
		optimizar();
		return new DatosOptimizados(centrosOptimizados, clientesOptimizados);
	}

	private ArrayList<Centro> clonarCentros(ArrayList<Centro> lista) {
		ArrayList<Centro> listaClonada = new ArrayList<Centro>();		
		for (Centro centro : lista) {
			listaClonada.add(new Centro(centro));
		}		
		return listaClonada;
	}
	
	private ArrayList<Cliente> clonarClientes(ArrayList<Cliente> lista) {
		ArrayList<Cliente> listaClonada = new ArrayList<Cliente>();		
		for (Cliente cliente : lista) {
			listaClonada.add(new Cliente(cliente));
		}		
		return listaClonada;
	}
	
	private void optimizar() {		
		asignarClienteCentroSegunDistancia();
		calcularUtilidadCentroReconocerClientes();
		Collections.sort(centrosOptimizados, new ComparadorGoloso());
		reasignarClientesHuerfanos();

//		for (Centro centro : centrosOptimizados) {
//			clientesOptimizados
//					.sort((c1, c2) -> Double.compare(calcularDistancia(centro, c1), calcularDistancia(centro, c2)));
//			centro.setClientesAsociados(clientesOptimizados);
//			centro.setActivo(true);
//		}		
	}

	private void asignarClienteCentroSegunDistancia() {
		for (Cliente cliente : clientesOptimizados) {
			cliente.setDistanciaAlCentro(Double.MAX_VALUE);
			for (Centro centro : centrosOptimizados) {
				double dist = calcularDistancia(centro, cliente);
				if (cliente.getDistanciaAlCentro() > dist) {
					cliente.setCentroAsociado(centro);
					cliente.setDistanciaAlCentro(dist);
				}
			}
		}
	}

	private void calcularUtilidadCentroReconocerClientes() {
		for (Centro centro : centrosOptimizados) {
			double distanciaAcum = 0;
			for (Cliente cliente : clientesOptimizados) {
				if (cliente.getCentroAsociado().equals(centro)) {
					centro.getClientesAsociados().add(cliente);
					distanciaAcum += cliente.getDistanciaAlCentro();
				}
			}
			if (centro.getClientesAsociados().size() != 0)
				centro.setActivo(true);

			centro.setUtilidad(centro.calcularUtilidad(distanciaAcum));
		}
	}

	private void reasignarClientesHuerfanos() {
		ArrayList<Cliente> clientesHuerfanos;
		int index = 0;
		if (cantActivos() < cantidad)
			return;
		while (cantActivos() > cantidad) {
			index = activoDeMenorUtilidad();
			clientesHuerfanos = centrosOptimizados.get(index).getClientesAsociados();
			centrosOptimizados.get(index).setActivo(false);
			Centro centroIdeal = centrosOptimizados.get(0);
			for (Cliente cliente : clientesHuerfanos) {
				for (Centro centro : centrosOptimizados) {
					double dist = calcularDistancia(centro, cliente);
					if (cliente.getDistanciaAlCentro() > dist && centro.getActivo()) {
						cliente.setCentroAsociado(centro);
						cliente.setDistanciaAlCentro(dist);
						centroIdeal = centro;
					} else
						cliente.setDistanciaAlCentro(Double.MAX_VALUE);
				}
				centroIdeal.getClientesAsociados().add(cliente);
				//centrosOptimizados.get(centrosOptimizados.indexOf((cliente.getCentroAsociado()))).getClientesAsociados().add(cliente);
			}
		}		
		//System.out.println("salgo del while");
	}

	private int cantActivos() {
		int x = 0;
		for (Centro centro : centrosOptimizados)
			if (centro.getActivo())
				x++;
		return x;
	}

	private int activoDeMenorUtilidad() {
		double util = Double.MAX_VALUE;
		int index = 0;
		for (Centro centro : centrosOptimizados) {
			//System.out.println(centro.getNombre() + " " + centro.getActivo());
			if (centro.getActivo() && centro.getUtilidad() < util) {
				util = centro.getUtilidad();
				index = centrosOptimizados.indexOf(centro);
			}
		}
		return index;
//		return centrosOptimizados.size() - 1;
	}

//	private Cliente obtener(ArrayList<Cliente> clientesOptimizados, Cliente cliente) {
//		return clientesOptimizados.get(clientesOptimizados.indexOf(cliente));
//	}

	public double calcularDistancia(Centro centro, Cliente cliente) {
		double distancia = centro.getUbicacion().calcularDistancia(cliente.getUbicacion());
		return distancia;
	}

	public ArrayList<Centro> getCentrosOptimizados() { return centrosOptimizados; }

	public ArrayList<Cliente> getClientesOptimizados() { return clientesOptimizados; }
}
