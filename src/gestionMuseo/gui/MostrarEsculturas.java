package gestionMuseo.gui;

import gestionMuseo.Exposicion;
import gestionMuseo.excepciones.NoHayFondosException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MostrarEsculturas extends MostrarObrasMuseo {

	public MostrarEsculturas(Exposicion exposicion) throws NoHayFondosException {
		super(exposicion);
		itObras= exposicion.gestListEscultura();
		setTitle("Mostrando esculturas...");
	}


	
}
