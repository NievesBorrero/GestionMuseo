package gestionMuseo.gui;

import gestionMuseo.enumeraciones.EstadoDeConservacion;
import gestionMuseo.enumeraciones.Sala;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.ObraExpuestaException;
import gestionMuseo.excepciones.ObraNoDaniadaException;
import gestionMuseo.excepciones.ObraNoExisteException;
import gestionMuseo.jerarquia.ObraDeArte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.JOptionPane;

/**
 * Muestra las obras y permite exponerlas, si el estado de la obra es malo, da
 * opción a restaurarla para poder exponerla.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */
public class ExponerObras extends MostrarObrasMuseo {

	private static final long serialVersionUID = 1L;
	private ObraDeArte obra;

	/**
	 * Create the dialog.
	 * 
	 * @param sala
	 * @throws NoHayFondosException
	 */
	public ExponerObras(ListIterator<ObraDeArte> itObras)
			throws NoHayFondosException {
		super(itObras);
		btnIzquierda.setText("Exponer");
		btnIzquierda.setVisible(true);
		setTitle("Exponer obras de arte");
		rbSala_1.setEnabled(true);
		rbSala_2.setEnabled(true);
		rbSala_3.setEnabled(true);
		rbAlmacen.setVisible(false);

		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					obra = Principal.museo.devolverPorCodigo(pasarTextAEntero(textcod.getText()));
					
					if (rbSala_1.isSelected() || rbSala_2.isSelected()
							|| rbSala_3.isSelected()) {

						if (obra.getEstadoConservacion() == EstadoDeConservacion.MALO) {

							int opcion = JOptionPane
									.showOptionDialog(
											contentPanel,
											"La obra esta en mal estado\n su coste de restauracion es "
													+ obra.calcularPrecioRestauracion()
													+ " euros, "
													+ "\n deseas restaurarla para exponerla?",
											"Confirmar",
											JOptionPane.YES_NO_CANCEL_OPTION,
											JOptionPane.QUESTION_MESSAGE, null,
											null, null);

							if (opcion == JOptionPane.YES_OPTION) {
								try {
									RestaurarYExponer();
								} catch (ObraNoDaniadaException e1) {
									JOptionPane.showMessageDialog(getContentPane(),
											e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
									// Aquí no debería entrar.
								} catch (NoHayFondosException e1) {
									JOptionPane.showMessageDialog(getContentPane(),
											e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
								}

							}
						} else {

							exponer();

							JOptionPane.showMessageDialog(contentPanel,
									"Buena eleccion, obra expuesta",
									"Obra expuesta",
									JOptionPane.INFORMATION_MESSAGE);
						}

					} else
						JOptionPane.showMessageDialog(contentPanel,
								"Selecciona una sala donde exponer la obra",
								"Error", JOptionPane.ERROR_MESSAGE);

				} catch (ObraExpuestaException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(getContentPane(),
							"Debes introducir el codigo", "Error",
							JOptionPane.ERROR_MESSAGE);
					// Aquí no debería entrar.
				} catch (ObraNoExisteException e2) {
					JOptionPane.showMessageDialog(contentPanel,
							e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			
		});

	}

	/**
	 * Según el botón seleccionado devuelve una sala.
	 * 
	 * @return sala.
	 */
	protected Sala getSala() {
		if (rbSala_1.isSelected())
			return Sala.SALA1;
		else if (rbSala_2.isSelected())
			return Sala.SALA2;
		else if (rbSala_3.isSelected())
			return Sala.SALA3;
		else
			return null;
	}
	
	/** 
	 * Restaura una obra y la expone.
	 * @throws NoHayFondosException
	 * @throws ObraNoDaniadaException
	 * @throws ObraExpuestaException
	 * @throws EstadoNoAdecuadoException
	 */
	private void RestaurarYExponer() throws NoHayFondosException,
	ObraNoDaniadaException, ObraExpuestaException{
		Principal.museo.restaurar(obra);
		textEstado.setText(obra.getEstadoConservacion().toString().toLowerCase());
		exponer();
	}
	
	/**
	 * Expone una obra.
	 * @throws ObraExpuestaException
	 * @throws EstadoNoAdecuadoException
	 */
	private void exponer() throws ObraExpuestaException{
		obra.exponerObra(getSala());
		Principal.museo.setModificado(true);
	}

}
