package gestionMuseo.gui;


import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.jerarquia.ObraDeArte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.JOptionPane;

/**
 * JDialog que muestra las obras introducidas por parámetro en un iterador.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */

public class MostrarObrasMuseo extends DialogoGeneral {

	private static final long serialVersionUID = 1L;
	
	ListIterator<ObraDeArte> itObras;

	public MostrarObrasMuseo(ListIterator<ObraDeArte> itObras)
			throws NoHayFondosException {
		
		super();
		
		setTitle("Mostrando fondos del museo...");

		setItObras(itObras);

		inhabilitarComponentes();
		
		btnIzquierda.setVisible(false);
		btnDerecha.setText("Salir");

		actualizarMostrar(itObras);

		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		btnAnterior.setText("<<");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousObra();
			}
		});

		btnSiguiente.setText(">>");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextObra();
			}
		});
		
	}
		
	/**
	 * Muestra la obra posicionada en el primer índice del iterador.
	 * @param itObras
	 */
	private void actualizarMostrar(ListIterator<ObraDeArte> itObras) {
		mostrar(itObras.next());
		if (!itObras.hasNext())
			btnSiguiente.setEnabled(false);
	}
	
	/**
	 * Permite modificar el iterador de obras de arte.
	 * @param itObras
	 */
	public void setItObras(ListIterator<ObraDeArte> itObras) {
		this.itObras = itObras;
	}

	/**
	 * Avanza una posición en el iterador de obras de arte
	 */
	protected void nextObra() {

		ObraDeArte next = itObras.next();

		if (next.equals(obraMostrada))
			next = itObras.next();

		mostrar(next);

		if (itObras.hasNext()) {
			btnSiguiente.setEnabled(true);
		} else {
			btnSiguiente.setEnabled(false);
			itObras.previous();
		}
		if (itObras.hasPrevious())
			btnAnterior.setEnabled(true);
		else
			btnAnterior.setEnabled(false);

	}

	/**
	 * Retrasa una posición en el iterador de obras de arte.
	 */
	protected void previousObra() {

		ObraDeArte previous = itObras.previous();

		if (previous.equals(obraMostrada))
			previous = itObras.previous();

		mostrar(previous);

		if (itObras.hasNext()) {
			btnSiguiente.setEnabled(true);
		} else
			btnSiguiente.setEnabled(false);
		if (itObras.hasPrevious())
			btnAnterior.setEnabled(true);
		else {
			btnAnterior.setEnabled(false);
			itObras.next();
		}

	}
	
	/**
	 * Actualiza el iterador con las obras de arte que hay en los fondos de la
	 * exposición.
	 */
	void actualizarItObras() {
		try {
			itObras = Principal.museo.getList();
		} catch (NoHayFondosException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Inhabilita los componentes del JDialog padre que se deban inhabilitar en este caso.
	 */
	private void inhabilitarComponentes() {		
		cbTipo.setEnabled(false);
		textTitulo.setEnabled(false); 
		textAutor.setEnabled(false);
		textLocal.setEnabled(false);
		textPersona.setEnabled(false);
		textEstado.setEnabled(false);
		rbDonada.setEnabled(false);
		ingresosYgastos.setEnabled(false);
		spprof.setEnabled(false);
		cbPH.setEnabled(false);
		cbEA.setEnabled(false);
		rbDonada.setEnabled(false);
		rbComprada.setEnabled(false);
		spAlto.setEnabled(false);
		spAncho.setEnabled(false);
		lblCombobox1.setEnabled(false);
		lblCombobox2.setEnabled(false);
		cb1.setEnabled(false);
		cb2.setEnabled(false);
		textcod.setEnabled(false);
		btnAnterior.setEnabled(false); // Al principio siempre debe aparecer
										// inhabilitado
		rbSala_1.setEnabled(false);
		rbSala_2.setEnabled(false);
		rbSala_3.setEnabled(false);
		rbAlmacen.setEnabled(false);

		spCosteExp.setEnabled(false);
		spCostRest.setEnabled(false);
		spFama.setEnabled(false);
		spprof.setEnabled(false);
		spValor.setEnabled(false);

	}


}