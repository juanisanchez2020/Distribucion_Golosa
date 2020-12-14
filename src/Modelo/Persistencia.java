package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import negocio.Centro;
import negocio.Cliente;
import negocio.ConjuntoDeLugares;
import negocio.Lugar;

public class Persistencia {
	private String direccionClientes = "clientes.json";
	private String direccionCentros = "centros.json";
	
	
	public ArrayList<Cliente> importarClientes() {
		Gson gson = new Gson();
		ConjuntoDeLugares conjunto = new ConjuntoDeLugares();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(direccionClientes));
			conjunto = gson.fromJson(reader, ConjuntoDeLugares.class);
			
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			for (Lugar lugar : conjunto.getLugares())
				clientes.add(new Cliente(lugar.getNombre(), lugar.getUbicacion()));

			return clientes;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Centro> importarCentrosDeDistribucion() {
		Gson gson = new Gson();
		ConjuntoDeLugares conjunto = new ConjuntoDeLugares();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(direccionCentros));
			conjunto = gson.fromJson(reader, ConjuntoDeLugares.class);
			
			ArrayList<Centro> centros = new ArrayList<Centro>();
			for (Lugar lugar : conjunto.getLugares())
				centros.add(new Centro(lugar.getNombre(), lugar.getUbicacion()));

			return centros;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void exportarCentros(ArrayList<Centro> centrosDeDistribucion) {
		ConjuntoDeLugares conjunto = new ConjuntoDeLugares(centrosDeDistribucion);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(direccionCentros);
			writer.write(gson.toJson(conjunto));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void exportarClientes(ArrayList<Cliente> clientes) {		
		ConjuntoDeLugares conjunto = new ConjuntoDeLugares(clientes);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(direccionClientes);
			writer.write(gson.toJson(conjunto));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
//	private <T> ArrayList<T> crearLista(ConjuntoDeLugares conjunto) {
//		ArrayList<T> clientes = new ArrayList<T>();
//		for (Lugar lugar : conjunto.getLugares())
//			clientes.add(new T(lugar));
//	}
	
//opcional:
//	public void guardar(ConjuntoDePersonas array) {
//	Gson gson = new GsonBuilder().setPrettyPrinting().create();
//	try {
//		FileWriter writer = new FileWriter("jsonPretty.json");
//		writer.write(gson.toJson(array));
//		writer.close();
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//}
	
}
