package vista;


import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import negocio.Cliente;

public class TablaDetalle extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;

	public TablaDetalle() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 660, 350);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		model = new DefaultTableModel();
		model.addColumn("Cliente");
		model.addColumn("Latitud");
		model.addColumn("Longitud");
		model.addColumn("Distancia al Centro");
		table.setModel(model);
		table.setDefaultEditor(Object.class, null); 
		alinearDerecha();
	}

	public void agregarPersona(String s, double i, double i2, double i3) {
		model.addRow(new Object[] { s, i, i2, i3});
	}
	
	public static void deleteAllRows(final DefaultTableModel model) { 
	    for(int i = model.getRowCount() - 1; i >= 0; i--) { 
	     model.removeRow(i); 
	    } 
	}
	
	public void importar(ArrayList<Cliente> clientes) {	
		deleteAllRows(model);
		model.setRowCount(0);
		for (Cliente cliente : clientes)
		{
			model.addRow(new Object [] { cliente.getNombre(), "Latitud: " + cliente.getUbicacion().getLatitud(), "Longitud: " + cliente.getUbicacion().getLongitud(), cliente.getDistanciaAlCentro()} );
		}
	}
	
	private void alinearDerecha() {
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		//table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
	}

}
