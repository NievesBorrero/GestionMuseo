package gestionMuseo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.swing.JOptionPane;

import gestionMuseo.GestionMuseo;
import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.ObraNoExisteException;
import gestionMuseo.excepciones.TituloNoValidoException;
import gestionMuseo.jerarquia.ObraDeArte;

/**
 * JDialog que muestra las obras contenidas en un listIterator dando la
 * posibilidad de eliminarlas en ese momento.
 * 
 * @author Nieves Borrero.
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

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int opcion = JOptionPane.showOptionDialog(contentPanel,
						"¿Estas seguro de que desea eliminarlo?", "Confirmar",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (opcion == JOptionPane.YES_OPTION) {

					itObras.remove();
					actualizarListIterator(Principal.museo);

					if (itObras.hasNext()) {
						nextObra();
						actualizarBotones();

					}

					else if (itObras.hasPrevious()) {
						previousObra();
						actualizarBotones();
					} else
						setVisible(false);

				}
			}

		});

	}

	protected void actualizarBotones() {

		if (!itObras.hasNext())
			btnDerecha.setEnabled(false);

		else
			btnDerecha.setEnabled(true);

		if (itObras.hasPrevious())
			btnAnterior.setEnabled(false);

		else
			btnAnterior.setEnabled(true);
	}

	void actualizarListIterator(GestionMuseo exposicion) {
		try {
			itObras = exposicion.getList();
			actualizarBotones();
		} catch (NoHayFondosException e) {
			// Aquí no debería entrar
		}
	}

}
