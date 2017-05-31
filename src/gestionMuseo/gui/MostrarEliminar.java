package gestionMuseo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.jerarquia.ObraDeArte;

/**
 * JDialog que muestra las obras contenidas en el museo dando la
 * posibilidad de eliminarlas en ese momento.
 * 
 * @author Nieves María Borrero Barea.
 *
 */
public class MostrarEliminar extends MostrarObrasMuseo {

	private static final long serialVersionUID = 1L;

	public MostrarEliminar(ListIterator<ObraDeArte> itObras)
			throws NoHayFondosException {
		super(itObras);
		btnIzquierda.setVisible(true);
		btnIzquierda.setText("Eliminar");

		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				itObras.remove();
				comprobarBotones(itObras);
			}

		});
	}

	/**
	 * Avanza o retrasa la posición del iterador y comprueba si los botones
	 * deben inhabilitarse.
	 * 
	 * @param itObras
	 */
	private void comprobarBotones(ListIterator<ObraDeArte> itObras) {
		if (itObras.hasNext() && !itObras.hasPrevious()) {
			nextObra();
			btnAnterior.setEnabled(false);
		} else if (itObras.hasNext() && itObras.hasPrevious()) {
			nextObra();
		} else if (!itObras.hasPrevious() && !itObras.hasNext())
			setVisible(false);

		else if (!itObras.hasNext()) {
			previousObra();
			btnSiguiente.setEnabled(false);
		}
	}

}
