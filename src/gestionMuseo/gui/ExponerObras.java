package gestionMuseo.gui;

import gestionMuseo.GestionMuseo;
import gestionMuseo.enumeraciones.EstadoDeConservacion;
import gestionMuseo.enumeraciones.Sala;
import gestionMuseo.excepciones.EstadoNoAdecuadoException;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.ObraExpuestaException;
import gestionMuseo.excepciones.ObraNoDaniadaException;
import gestionMuseo.excepciones.ObraNoExpuestaException;
import gestionMuseo.jerarquia.ObraDeArte;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

public class ExponerObras extends MostrarObrasMuseo {

	ObraDeArte obra;

	/**
	 * Create the dialog.
	 * 
	 * @param sala
	 * @throws NoHayFondosException
	 */
	public ExponerObras(ListIterator <ObraDeArte> itObras) throws NoHayFondosException {
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
					obra = Principal.museo.devolverPorCodigo(Integer
							.parseInt(textcod.getText()));
					if (rbSala_1.isSelected() || rbSala_2.isSelected()
							|| rbSala_3.isSelected()) {

						if (obra.getEstadoConservacion() == EstadoDeConservacion.MALO) {

							int opcion = JOptionPane
									.showOptionDialog(
											contentPanel,
											"La obra esta en mal estado\n su coste de restauracion es "
													+ obra.getCosteExposicion()+" euros, "
													+ "\n deseas restaurarla para exponerla?",
											"Confirmar",
											JOptionPane.YES_NO_CANCEL_OPTION,
											JOptionPane.QUESTION_MESSAGE, null,
											null, null);

							if (opcion == JOptionPane.YES_OPTION) {
								try {
									obra.restaurar();
									obra.exponerObra(getSala());
									Principal.museo.setModificada(true);
								} catch (ObraNoDaniadaException e1) {
									// Aquí no debería entrar.
								}

							}
						}
						else{
							
						obra.exponerObra(getSala());
						Principal.museo.setModificada(true);

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
				} catch (EstadoNoAdecuadoException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
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

}
