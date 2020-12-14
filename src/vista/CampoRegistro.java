package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import negocio.Centro;
import negocio.Cliente;
import negocio.Ubicacion;

import javax.swing.JTextField;
import javax.swing.JButton;

public class CampoRegistro extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField txt_nombre;
	private JTextField txt_longitud;
	private JTextField txt_latitud;

	public CampoRegistro(Panel panel) {
		setLayout(null);

		JLabel lb_registro = new JLabel("Nuevo Registro");
		lb_registro.setHorizontalAlignment(SwingConstants.CENTER);
		setFont(lb_registro);
		lb_registro.setBounds(272, 11, 125, 29);
		add(lb_registro);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		setFont(lblNewLabel);
		lblNewLabel.setBounds(21, 52, 68, 19);
		add(lblNewLabel);
		
		txt_nombre = new JTextField();
		txt_nombre.setBounds(91, 51, 119, 22);
		add(txt_nombre);
		txt_nombre.setColumns(10);

		
		JLabel lb_longitud = new JLabel("Longitud:");
		lb_longitud.setHorizontalAlignment(SwingConstants.LEFT);
		setFont(lb_longitud);
		lb_longitud.setBounds(440, 54, 68, 14);
		add(lb_longitud);
		

		JButton btnAgregarCentro = new JButton("Agregar Centro");
		btnAgregarCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Centro centro = new Centro(getNombre(), new Ubicacion(Double.parseDouble(getLatitud()), Double.parseDouble(getLongitud())));
					panel.agregarCentro(centro);
					limpiarCampos();	
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "El Centro no puede ser agregado. \nVerifique que los datos ingresados sean correctos", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnAgregarCentro.setBounds(443, 94, 150, 23);
		add(btnAgregarCentro);
		
		txt_longitud = new JTextField();
		txt_longitud.setColumns(10);
		txt_longitud.setBounds(510, 52, 119, 22);
		add(txt_longitud);
		
		JLabel lb_latitud = new JLabel("Latitud: ");
		lb_latitud.setHorizontalAlignment(SwingConstants.LEFT);
		setFont(lb_latitud);
		lb_latitud.setBounds(240, 54, 68, 14);
		add(lb_latitud);
		
		txt_latitud = new JTextField();
		txt_latitud.setColumns(10);
		txt_latitud.setBounds(300, 52, 119, 22);
		add(txt_latitud);
		
		JButton btnAgregarCliente = new JButton("Agregar Cliente");
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Cliente cliente = new Cliente(getNombre(), new Ubicacion(Double.parseDouble(getLatitud()), Double.parseDouble(getLongitud())));
					panel.agregarCliente(cliente);
					limpiarCampos();	
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "El cliente no puede ser agregado.\nVerifique que los datos ingresados sean correctos", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnAgregarCliente.setBounds(63, 94, 150, 23);
		add(btnAgregarCliente);

	}

	private void limpiarCampos() {
		txt_nombre.setText("");
		txt_latitud.setText("");
		txt_longitud.setText("");
	}
	
	public String getNombre() {
		return txt_nombre.getText();
	}
	
	public String getLatitud() {
		return txt_latitud.getText();
	}
	
	public String getLongitud() {
		return txt_longitud.getText();
	}

	private void setFont(JLabel label) {
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
	}
}
