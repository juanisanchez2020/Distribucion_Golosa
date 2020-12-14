package vista;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import interfaces.ILugar;

import javax.swing.JScrollPane;

public class Tabla extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private Consumer<Integer> funcionEliminarFila;

	public Tabla(Consumer<Integer> funcionEliminarFila) {

		this.funcionEliminarFila = funcionEliminarFila;
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 335, 350);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if (columnIndex == 0 || columnIndex == 3) //solo se puede editar la primer columna
					return true;
				return false;
			}
		};
		model.addColumn("Nombre");
		model.addColumn("Latitud");
		model.addColumn("Longitud");
		model.addColumn("Acciones");
		table.setModel(model);
		alinearDerecha();
	}

	public void agregarRegistro(ILugar registro) {
		model.addRow(new Object[] { registro.getNombre(), registro.getUbicacion().getLatitud(),
				registro.getUbicacion().getLongitud(), new ButtonColumn(table, delete, 3) });
	}

	public void importarRegistros(ArrayList<? extends ILugar> list) {
		model.setRowCount(0);
		for (ILugar elem : list)
			model.addRow(new Object[] { elem.getNombre(), elem.getUbicacion().getLatitud(),
					elem.getUbicacion().getLongitud(), new ButtonColumn(table, delete, 3) });
	}

	public int selectedRow() {
		return table.getSelectedRow();
	}

	public int selectedColumn() {
		return table.getSelectedColumn();
	}

	private void alinearDerecha() {
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
	}

	Action delete = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			JTable table = (JTable) e.getSource();
			int modelRow = Integer.valueOf(e.getActionCommand());
			((DefaultTableModel) table.getModel()).removeRow(modelRow);
			funcionEliminarFila.accept(modelRow);
		}
	};

//	public void addRowChangedListener(ListSelectionListener listSelectionListener) {
//		table.getColumnModel().getSelectionModel().addListSelectionListener(listSelectionListener);
//		table.getSelectionModel().addListSelectionListener(listSelectionListener);
//	}

}
