package gestionMuseo.gui;

import gestionMuseo.Exposicion;
import gestionMuseo.excepciones.NoHayFondosException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MostrarDibujos extends MostrarObrasMuseo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MostrarDibujos(Exposicion exposicion) throws NoHayFondosException {
		super(exposicion);
		itObras= exposicion.gestListDibujo();
		setTitle("Mostrando dibujos...");
	}

	

}
