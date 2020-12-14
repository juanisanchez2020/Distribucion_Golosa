package negocio;

import java.util.ArrayList;
import Modelo.Persistencia;

public class Negocio {

	private ArrayList<Centro> centrosDeDistribucion;
	private ArrayList<Cliente> clientes;

	public Negocio() {
		centrosDeDistribucion = new ArrayList<Centro>();
		clientes = new ArrayList<Cliente>();
	}

	public void cargarDatos() {
		cargarClientes();
		cargarCentros();
	}

	public ArrayList<Cliente> cargarClientes() {
		Persistencia p = new Persistencia();
		return importarClientes(p);
	}

	public ArrayList<Centro> cargarCentros() {
		Persistencia p = new Persistencia();
		return importarCentros(p);
	}

	public DatosOptimizados optimizarCentros(int cantidad) {
		Heuristica h = new Heuristica();
		return h.optimizarCentros(cantidad, centrosDeDistribucion, clientes);
	}

	private ArrayList<Cliente> importarClientes(Persistencia p) {
		var clientesP = p.importarClientes();
		if (!hayDuplicados(clientesP)) {
			clientes = clientesP;
			return clientesP;
		}
		return null;
	}

	private ArrayList<Centro> importarCentros(Persistencia p) {
		var centrosP = p.importarCentrosDeDistribucion();
		if (!hayDuplicados(centrosP)) {
			centrosDeDistribucion = centrosP;
			return centrosDeDistribucion;
		}
		return null;
	}

	private <T> boolean hayDuplicados(ArrayList<T> list) {
		int i;
		for (T obj : list) {
			i = 0;
			for (T obj2 : list) {
				if (obj.equals(obj2))
					i++;
			}
			if (i > 1)
				return true;
		}
		return false;
	}

	public void eliminarCliente(int n) {
		clientes.remove(n);
	}

	public void eliminarCentro(int n) {
		centrosDeDistribucion.remove(n);
	}

	public void agregarCentroDeDistribucion(Centro centro) {
		centrosDeDistribucion.add(centro);
	}

	public void agregarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void exportarCentros() {
		Persistencia p = new Persistencia();
		p.exportarCentros(centrosDeDistribucion);
	}

	public void exportarClientes() {
		Persistencia p = new Persistencia();
		p.exportarClientes(clientes);
	}
	
	public ArrayList<Centro> getCentrosDeDistribucion() { return centrosDeDistribucion; }
	public ArrayList<Cliente> getClientes() { return clientes; }
}
