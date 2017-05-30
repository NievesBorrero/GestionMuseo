package gestionMuseo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gestionMuseo.GestionMuseo;
import gestionMuseo.excepciones.ObraNoExisteException;
import gestionMuseo.jerarquia.Grabado;
import gestionMuseo.jerarquia.ObraDeArte;

public class BuscarPorTitulo extends DialogoGeneral {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the dialog.
	 */
	public BuscarPorTitulo(GestionMuseo museo) {

		InhabilitarComponentes();
		hacerComponentesInvisibles();
		limpiar();
		
		btnIzquierda.setText("Buscar");
		btnDerecha.setText("Cancelar");

		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
					mostrar(museo.devolverPorTitulo(textTitulo.getText()));
				} catch (ObraNoExisteException e) {
					JOptionPane.showMessageDialog(getContentPane(),
							e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
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
		textcod.setEnabled(false);
		rbSala_1.setEnabled(false);
		rbSala_2.setEnabled(false);
		rbSala_3.setEnabled(false);
		rbAlmacen.setEnabled(false);
	}

}
