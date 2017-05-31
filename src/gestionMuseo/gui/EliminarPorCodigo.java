package gestionMuseo.gui;

import gestionMuseo.GestionMuseo;
import gestionMuseo.excepciones.ObraNoExisteException;
import gestionMuseo.jerarquia.Dibujo;
import gestionMuseo.jerarquia.Escultura;
import gestionMuseo.jerarquia.Grabado;
import gestionMuseo.jerarquia.ObraDeArte;
import gestionMuseo.jerarquia.Pintura;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

/**
 * JDialog que permite eliminar una obra introduciendo su código. Al pulsar el
 * botón eliminar, muestra la obra y pregunta antes de borrar.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */
public class EliminarPorCodigo extends DialogoGeneral {

	private static final long serialVersionUID = 1L;

	public EliminarPorCodigo(GestionMuseo exposicion) {
		super();

		InhabilitarComponentes();
		hacerComponentesInvisibles();

		btnIzquierda.setText("Eliminar");
		btnDerecha.setText("Cancelar");

		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int codigo = pasarTextAEntero(textcod.getText());
					ObraDeArte aEliminar = exposicion.devolverPorCodigo(codigo);
					mostrar(aEliminar);
					int n = JOptionPane.showOptionDialog(contentPanel,
							"Estas seguro de que desea eliminarlo?",
							"Confirmar", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);

					if (n == JOptionPane.YES_OPTION) {

						if (aEliminar instanceof Pintura)
							exposicion.eliminarPintura(codigo);
						if (aEliminar instanceof Escultura)
							exposicion.eliminarEscultura(codigo);
						if (aEliminar instanceof Grabado)
							exposicion.eliminarGrabado(codigo);
						if (aEliminar instanceof Dibujo)
							exposicion.eliminarDibujo(codigo);
						exposicion.setModificado(true);
						limpiar();
					}
				} catch (ObraNoExisteException e) {
					JOptionPane.showMessageDialog(getContentPane(),
							e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							"Debe introducir el codigo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}


		});
		
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

	}
	
	
	/**
	 * Hace invisibles los componentes del JDialog padre que no son necesarios.
	 */
	private void hacerComponentesInvisibles() {
		btnAnterior.setVisible(false);
	}

	/**
	 * Inhabilita los componentes del JDialog padre que se deban inhabilitar en
	 * este caso.
	 */
	private void InhabilitarComponentes() {
		cbTipo.setEnabled(false);
		textAutor.setEnabled(false);
		textLocal.setEnabled(false);
		textPersona.setEnabled(false);
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
		btnSiguiente.setVisible(false);
		textTitulo.setEnabled(false);
		rbSala_1.setEnabled(false);
		rbSala_2.setEnabled(false);
		rbSala_3.setEnabled(false);
		rbAlmacen.setEnabled(false);
	}

}
