package gestionMuseo.gui;

import gestionMuseo.enumeraciones.EstadoDeConservacion;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.ObraNoDaniadaException;
import gestionMuseo.excepciones.ObraNoExisteException;
import gestionMuseo.jerarquia.ObraDeArte;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.JOptionPane;

/**
 * JDialog que permite restaurar las obras del museo mientras se muestran.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */
public class Restaurar extends MostrarObrasMuseo {

	private static final long serialVersionUID = 1L;
	private ObraDeArte obra;

	/**
	 * Create the dialog.
	 * 
	 * @param sala
	 * @throws NoHayFondosException
	 */
	public Restaurar(ListIterator<ObraDeArte> itObras)
			throws NoHayFondosException {
		super(itObras);
		btnIzquierda.setText("Restaurar");
		btnIzquierda.setVisible(true);
		setTitle("Exponer obras de arte");
		rbSala_1.setEnabled(true);
		rbSala_2.setEnabled(true);
		rbSala_3.setEnabled(true);
		rbAlmacen.setVisible(false);

		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion;
					obra = Principal.museo
							.devolverPorCodigo(pasarTextAEntero(textcod
									.getText()));
					if(obra.getEstadoConservacion()==EstadoDeConservacion.BUENO)
						throw new ObraNoDaniadaException("La obra no está dañada");
					
					opcion = JOptionPane
							.showOptionDialog(
									contentPanel,
									"Restaurar la obra cuesta "
											+ obra.calcularPrecioRestauracion()
											+ " euros, "
											+ "\n esta segur@ de que desea restaurarla?",
									"Confirmar",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null, null,
									null);
					if (opcion == JOptionPane.YES_OPTION) {

						restaurar();

					}
				} catch (ObraNoDaniadaException e2) {
					JOptionPane.showMessageDialog(contentPanel,
							e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}catch (HeadlessException e2) {
					JOptionPane.showMessageDialog(contentPanel,
							e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(contentPanel,
							e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ObraNoExisteException e2) {
					JOptionPane.showMessageDialog(contentPanel,
							e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NoHayFondosException e2) {
					JOptionPane.showMessageDialog(contentPanel,
							e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} 

			}

		});

	}

	/**
	 * Restaura una obra.
	 * 
	 * @throws NoHayFondosException
	 * @throws ObraNoDaniadaException
	 */
	private void restaurar() throws NoHayFondosException,
			ObraNoDaniadaException {
		Principal.museo.restaurar(obra);
		textEstado.setText(obra.getEstadoConservacion().toString()
				.toLowerCase());
		Principal.museo.setModificado(true);
	}

}
