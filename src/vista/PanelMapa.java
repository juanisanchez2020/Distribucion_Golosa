package vista;

import javax.swing.JFrame;
import negocio.DatosOptimizados;

public class PanelMapa extends JFrame {
	
	private Mapa mapa;
	private static final long serialVersionUID = 1L;
	
	public PanelMapa(DatosOptimizados data) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		setBounds(690, 0, 680, 680);
		getContentPane().setLayout(null);
		mapa = new Mapa(data);
		setContentPane(mapa.getMap());	
	}
}
