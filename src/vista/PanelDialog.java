package vista;

import java.awt.Font;

import javax.swing.JFrame;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import negocio.DatosOptimizados;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private DatosOptimizados data;
	private JTextField txt_distanciaPromedio;
	private JTextField txt_cantidadClientes;
	private JTextField txt_nombreCentro;
	private JTextField txt_ubicacionCentro;
	
	
	public PanelDialog(DatosOptimizados data) {
		this.data = data;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Centros de Distribución");
		setBounds(0, 0, 700, 650);
		getContentPane().setLayout(null);
		
		txt_distanciaPromedio = new JTextField();
		txt_distanciaPromedio.setBounds(240, 166, 273, 31);
		getContentPane().add(txt_distanciaPromedio);
		txt_distanciaPromedio.setColumns(10);
	
		txt_cantidadClientes = new JTextField();
		txt_cantidadClientes.setBounds(240, 124, 273, 31);
		getContentPane().add(txt_cantidadClientes);
		txt_cantidadClientes.setColumns(10);
		
		txt_nombreCentro = new JTextField();
		txt_nombreCentro.setBounds(240, 40, 273, 31);
		getContentPane().add(txt_nombreCentro);
		txt_nombreCentro.setColumns(10);
		
		txt_ubicacionCentro = new JTextField();
		txt_ubicacionCentro.setBounds(240, 82, 273, 31);
		getContentPane().add(txt_ubicacionCentro);
		txt_ubicacionCentro.setColumns(10);
		
		TablaDetalle tablaDetalle = new TablaDetalle();
		tablaDetalle.setBounds(10, 200, 660, 350);
		getContentPane().add(tablaDetalle);
		
		TablaCabecera tablaCabecera = new TablaCabecera();
		tablaCabecera.importar(data.getCentrosActivos());
		tablaCabecera.addRowChangedListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && tablaCabecera.selectedRow() != -1) {
					tablaDetalle.importar(data.getCentrosActivos().get(tablaCabecera.selectedRow()).getClientesAsociados());
					int centroSeleccionado = tablaCabecera.selectedRow();
					setInfo(centroSeleccionado);
					/*
					 
					 * setDistanciaPromedio(centroSeleccionado);
					 * setCantidadClientes(centroSeleccionado); setNombreCentro(centroSeleccionado);
					 * setUbicacionCentro(centroSeleccionado);
					 */
				}
			}
		});
		tablaCabecera.setBounds(10, 40, 200, 150);
		getContentPane().add(tablaCabecera);
		
		JButton btnVisualizarMapa = new JButton("Visualizar Mapa");
		btnVisualizarMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelMapa panelMapa = new PanelMapa(data);
				panelMapa.setVisible(true);
			}
		});
		btnVisualizarMapa.setBounds(539, 166, 127, 31);
		getContentPane().add(btnVisualizarMapa);
	}
	
	/*
	 * private void setDistanciaPromedio(int index) {
	 * txt_distanciaPromedio.setText("Distancia Promedio: " +
	 * data.getCentrosActivos().get(index).getUtilidad()); }
	 * 
	 * private void setCantidadClientes(int index) {
	 * txt_cantidadClientes.setText("Cantidad de Clientes: " +
	 * data.getCentrosActivos().get(index).getClientesAsociados().size()); }
	 * 
	 * private void setNombreCentro(int index) { txt_nombreCentro.setText("Centro: "
	 * + data.getCentrosActivos().get(index).getNombre()); }
	 * 
	 * private void setUbicacionCentro(int index) {
	 * txt_ubicacionCentro.setText("Latitud: " +
	 * data.getCentrosActivos().get(index).getUbicacion().getLatitud() +
	 * "	Longitud: " +
	 * data.getCentrosActivos().get(index).getUbicacion().getLongitud()); }
	 */
	
	private void setInfo(int index) {
		txt_nombreCentro.setText("Centro: " + data.getCentrosActivos().get(index).getNombre());
		setFont(txt_nombreCentro);
		txt_ubicacionCentro.setText("Latitud: " + data.getCentrosActivos().get(index).getUbicacion().getLatitud() + 
				" Longitud: " + data.getCentrosActivos().get(index).getUbicacion().getLongitud());
		setFont(txt_ubicacionCentro);
		txt_distanciaPromedio.setText("Distancia Promedio: " + data.getCentrosActivos().get(index).getUtilidad());
		setFont(txt_distanciaPromedio);
		txt_cantidadClientes.setText("Cantidad de Clientes: " + data.getCentrosActivos().get(index).getClientesAsociados().size());
		setFont(txt_cantidadClientes);
	}
	
	private void setFont(JTextField text) {
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setFont(new Font("Tahoma", Font.BOLD, 11));
		text.setEditable(false);
	}
}
