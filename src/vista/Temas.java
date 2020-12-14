package vista;

import java.util.LinkedList;
import java.util.List;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;

public class Temas
{
	int temaActual;
	private List<LookAndFeel> temasDisponibles;

	public Temas()
	{
		temaActual = 0;
		temasDisponibles = new LinkedList<LookAndFeel>();

		temasDisponibles.add(new FlatIntelliJLaf());
		temasDisponibles.add(new FlatDarculaLaf());
	}

	public LookAndFeel siguienteTema()
	{
		temaActual = temaActual + 1;
		if (temaActual == temasDisponibles.size())
			temaActual = 0;
		return temasDisponibles.get(temaActual);
	}

	public void cambiarTema()
	{
		cambiarTema(siguienteTema());
	}

	public static void cambiarTema(LookAndFeel tema)
	{
		try
		{
			UIManager.setLookAndFeel(tema);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		FlatLaf.updateUI();
	}
	
	public void temaPorDefecto()
	{
		try
		{
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		FlatLaf.updateUI();
	}
	
	public LookAndFeel temaActual() {
		return temasDisponibles.get(temaActual);
	}

}
