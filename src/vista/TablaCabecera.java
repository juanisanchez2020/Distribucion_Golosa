package vista;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import negocio.Centro;

public class TablaCabecera extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;

	public TablaCabecera() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 200, 150);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		model = new DefaultTableModel();
		model.addColumn("Centros");
		table.setModel(model);
		table.setDefaultEditor(Object.class, null); 
	}

	public void importar(ArrayList<Centro> centros) {	
		deleteAllRows(model);
		model.setRowCount(0);
		for (Centro centro : centros)
		{
			model.addRow(new Object [] { centro.getNombre() });
		}
	}
	
	public static void deleteAllRows(final DefaultTableModel model) { 
	    for(int i = model.getRowCount() - 1; i >= 0; i--) { 
	     model.removeRow(i); 
	    } 
	}
	
	public int selectedRow() {
		return table.getSelectedRow();
	}
	
	public void addRowChangedListener(ListSelectionListener listSelectionListener) {
		table.getColumnModel().getSelectionModel().addListSelectionListener(listSelectionListener);
		table.getSelectionModel().addListSelectionListener(listSelectionListener);
	}
}
