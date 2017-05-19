package gestionMuseo.gui;

import gestionMuseo.Exposicion;
import gestionMuseo.enumeraciones.EstadoDeConservacion;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.jerarquia.ObraDeArte;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VisitarExposicion extends MostrarObrasMuseo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VisitarExposicion(Exposicion exposicion) throws NoHayFondosException{
		super(exposicion);
		lblcod.setVisible(false);
		textcod.setVisible(false);
		rbSala_1.setVisible(false);
		rbSala_2.setVisible(false);
		rbSala_3.setVisible(false);
		rbAlmacen.setVisible(false);
		panel_salas.setVisible(false);
		ingresosYgastos.setVisible(false);
		panel_adquisicion.setVisible(false);
		textFecha.setVisible(false);
		lblFechaDeIngreso.setVisible(false);
		
		setTitle(exposicion.getNombreExposicion());
		
		itObras= exposicion.listExpuestas();

		
		
	}

}
