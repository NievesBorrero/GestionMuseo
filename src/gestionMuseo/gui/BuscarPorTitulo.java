package gestionMuseo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gestionMuseo.excepciones.ObraNoExisteException;

/**
 * Permite buscar una obra de arte por su título.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */

public class BuscarPorTitulo extends DialogoGeneral {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea el diálogo.
	 */
	public BuscarPorTitulo() {

		InhabilitarComponentes();
		hacerComponentesInvisibles();
		limpiar();
		setTitle("Buscando obra...");
		
		btnIzquierda.setText("Buscar");
		btnDerecha.setText("Cancelar");

		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
					mostrar(Principal.museo.devolverPorTitulo(textTitulo.getText()));
				} catch (ObraNoExisteException e) {
					JOptionPane.showMessageDialog(getContentPane(),
							e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
		textcod.setEnabled(false);
		rbSala_1.setEnabled(false);
		rbSala_2.setEnabled(false);
		rbSala_3.setEnabled(false);
		rbAlmacen.setEnabled(false);
		spValor.setEnabled(false);
		spFama.setEnabled(false);
		textEstado.setEnabled(false);
	}

}
