package vista;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import negocio.Centro;
import negocio.Cliente;
import negocio.DatosOptimizados;

public class Mapa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JMapViewer mapa;

	/**
	 * Create the application.
	 */
	public Mapa(DatosOptimizados data) {

		// setConjuntosCoordenadas(centros, clientes);
		Coordinate coordenadaInicio = new Coordinate(-34.528263, -58.804315); 
		
		mapa = new JMapViewer();
		mapa.setBounds(0, 0, 750, 750);
		mapa.setZoomControlsVisible(false);
		mapa.setDisplayPosition(coordenadaInicio, 12); 

		setConjuntosCoordenadas(data);

		mapa.setVisible(true);
	}

	public void setConjuntosCoordenadas(DatosOptimizados data) {

		for (Centro c : data.getCentrosOptimizados()) {

				Coordinate coordCentro = new Coordinate(c.getUbicacion().getLatitud(), c.getUbicacion().getLongitud());
				MapMarker dot = new MapMarkerDot("", coordCentro);
				dot.getStyle().setBackColor(Color.GREEN);
				mapa.addMapMarker(dot);
		}

		for (Cliente c : data.getClientesOptimizados()) {
			Coordinate coordCliente = new Coordinate(c.getUbicacion().getLatitud(), c.getUbicacion().getLongitud());
			MapMarker dot = new MapMarkerDot("", coordCliente);
			dot.getStyle().setBackColor(Color.BLUE);
			mapa.addMapMarker(dot);

			Coordinate coordCentroAsoc = new Coordinate(c.getCentroAsociado().getUbicacion().getLatitud(),
					c.getCentroAsociado().getUbicacion().getLongitud());

			List<Coordinate> route = new ArrayList<Coordinate>(
					Arrays.asList(coordCliente, coordCentroAsoc, coordCentroAsoc));
			mapa.addMapPolygon(new MapPolygonImpl(route));
		}

		mapa.setMapMarkerVisible(true);
	}

	public JPanel getMap() { return mapa; }
}
