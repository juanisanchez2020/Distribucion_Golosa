package vista;

import java.awt.EventQueue;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import negocio.Centro;
import negocio.Cliente;
import negocio.DatosOptimizados;
import negocio.Negocio;

public class Panel {

	private JFrame frame;
	private Botonera botonera;
	public Tabla tablaClientes;
	public Tabla tablaCentros;
	private Negocio negocio;
	private Temas temas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel window = new Panel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Panel() {
		negocio = new Negocio();
		initialize();
		temas = new Temas();
		temas.temaPorDefecto();
	}

	public void importarClientes() {
		var clientes = negocio.cargarClientes();
		if (clientes == null) {
			JOptionPane.showMessageDialog(null, "Se intentaron importar clientes repetidos. Se aborto el proceso.",
					"Atencion", JOptionPane.ERROR_MESSAGE);
		} else
			tablaClientes.importarRegistros(clientes);
	}

	public void importarCentros() {
		var centros = negocio.cargarCentros();
		if (centros == null) {
			JOptionPane.showMessageDialog(null,
					"Se intentaron importar centros de distribucion repetidos. Se aborto el proceso.", "Atencion",
					JOptionPane.ERROR_MESSAGE);
		} else
			tablaCentros.importarRegistros(centros);
	}

	public int eliminarCliente(int n) {
		negocio.eliminarCliente(n);
		return n;
	}

	public int eliminarCentro(int n) {
		negocio.eliminarCentro(n);
		return n;
	}

	public void optimizar(int cantCentros) {
		if (cantCentros == -1)
			return;
		DatosOptimizados ads = negocio.optimizarCentros(cantCentros);
		PanelDialog panelDetalle = new PanelDialog(ads);
		panelDetalle.setVisible(true);
	}

	private void initialize() {

		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Comparador Goloso");
		frame.setBounds(50, 50, 700, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		negocio = new Negocio();

		Consumer<Integer> funcionEliminarCliente = this::eliminarCliente;
		tablaClientes = new Tabla(funcionEliminarCliente);
		frame.getContentPane().add(tablaClientes);
		tablaClientes.setBounds(5, 200, 335, 350);

		Consumer<Integer> funcionEliminarCentro = this::eliminarCentro;
		tablaCentros = new Tabla(funcionEliminarCentro);
		frame.getContentPane().add(tablaCentros);
		tablaCentros.setBounds(345, 200, 335, 350);

		botonera = new Botonera(this);
		frame.getContentPane().add(botonera);
		botonera.setBounds(0, 0, 700, 621);

		CampoRegistro campoRegistro = new CampoRegistro(this);
		campoRegistro.setBounds(10, 49, 674, 136);
		botonera.add(campoRegistro);
	}

	public void cambiarTema() {
		temas.cambiarTema();
	}

	public void agregarCentro(Centro centro) {
		negocio.agregarCentroDeDistribucion(centro);
		tablaCentros.agregarRegistro(centro);
	}

	public void agregarCliente(Cliente cliente) {
		negocio.agregarCliente(cliente);
		tablaClientes.agregarRegistro(cliente);
	}

	public void exportarCentros() {
		negocio.exportarCentros();
	}

	public void exportarClientes() {
		negocio.exportarClientes();
	}

	public int getCantidadCentros() { // TODO Auto-generated method stub
		return negocio.getCentrosDeDistribucion().size();
	}
}
