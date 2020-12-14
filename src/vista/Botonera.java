package vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Botonera extends JPanel {

	private static final long serialVersionUID = 1L;

	public Botonera(Panel panel) {
		setLayout(null);

		JButton btnEdicion = new JButton("Tema");
		btnEdicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.cambiarTema();
			}
		});
		btnEdicion.setBounds(10, 11, 89, 23);
		add(btnEdicion);

		JButton btn_info = new JButton("Info");
		btn_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"     Segundo Semestre 2020\r\n" + "        Sanchez Juan Ignacio\r\n"
								+ "        Schmidt Maximiliano\r\n" + "          Sosa Martín Leonel",
						"Distribucion Golosa - Programacion 3", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btn_info.setBounds(109, 11, 89, 23);
		add(btn_info);

		JButton btnGenerar = new JButton("Optimizar");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String resp = "";
				int cantCentros = -1;
				int centrosExistentes = panel.getCantidadCentros();
				while (cantCentros == -1) {
					resp = JOptionPane.showInputDialog(null, "Introduzca la cantidad de centros maximos",
							"Atencion", JOptionPane.INFORMATION_MESSAGE);

					if (resp == null) {
						cantCentros = -1;
						break;
					}
					
					cantCentros = stringToInt(resp);
					
					if (!(cantCentros > 0 && cantCentros <= centrosExistentes)) {
						JOptionPane.showMessageDialog(null,
								"Por favor ingrese un numero entre 1 y " + centrosExistentes);
						cantCentros = -1;
					}

				}
				panel.optimizar(cantCentros);
			}
		});
		btnGenerar.setBounds(295, 565, 89, 23);
		add(btnGenerar);

		JButton btnExportarCentros = new JButton("Exportar Centros");
		btnExportarCentros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int resultado = JOptionPane.showConfirmDialog(null,
						"Esta seguro que desea exportar? Podria estar sobreescribiendo un archivo", "Exportar datos",
						dialogButton);
				if (resultado == 0) { // opcion SI
					panel.exportarCentros();
				}
			}
		});
		btnExportarCentros.setBounds(542, 565, 134, 23);
		add(btnExportarCentros);

		JButton btnExportarClientes = new JButton("Exportar Clientes");
		btnExportarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int resultado = JOptionPane.showConfirmDialog(null,
						"Esta seguro que desea exportar? Podria estar sobreescribiendo un archivo", "Exportar datos",
						dialogButton);
				if (resultado == 0) { // opcion SI
					panel.exportarClientes();
				}
			}
		});
		btnExportarClientes.setBounds(145, 565, 134, 23);
		add(btnExportarClientes);

		JButton btnImportarClientes = new JButton("Importar Clientes");
		btnImportarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// preguntar si desea sobreescribir los datos
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int resultado = JOptionPane.showConfirmDialog(null,
						"Esta seguro que desea importar? Sus datos actuales se perderan", "Importar datos",
						dialogButton);
				if (resultado == 0) { // opcion SI
					panel.importarClientes();
				}

			}
		});
		btnImportarClientes.setBounds(8, 565, 134, 23);
		add(btnImportarClientes);

		JButton btnImportarCentros = new JButton("Importar Centros");
		btnImportarCentros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// preguntar si desea sobreescribir los datos
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int resultado = JOptionPane.showConfirmDialog(null,
						"Esta seguro que desea importar? Sus datos actuales se perderan", "Importar datos",
						dialogButton);
				if (resultado == 0) { // opcion SI
					panel.importarCentros();
				}

			}
		});
		btnImportarCentros.setBounds(401, 565, 134, 23);
		add(btnImportarCentros);
	}

	private int stringToInt(String string) {

		try {
			if (string.isEmpty())
				return -1;
			return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Por favor ingrese un numero");
			return -1;
		}
	}
}
