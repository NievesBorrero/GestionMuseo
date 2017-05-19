package gestionMuseo.gui;

import gestionMuseo.Exposicion;
import gestionMuseo.excepciones.NoHayFondosException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MostrarPinturas extends MostrarObrasMuseo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MostrarPinturas(Exposicion exposicion) throws NoHayFondosException {
		super(exposicion);
		itObras= exposicion.getListPintura();
		setTitle("Mostrando pinturas...");
			
	}



}
