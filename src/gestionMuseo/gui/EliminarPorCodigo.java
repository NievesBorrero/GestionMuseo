package gestionMuseo.gui;

import gestionMuseo.Exposicion;
import gestionMuseo.excepciones.ObraNoExisteException;
import gestionMuseo.jerarquia.Dibujo;
import gestionMuseo.jerarquia.Escultura;
import gestionMuseo.jerarquia.Grabado;
import gestionMuseo.jerarquia.ObraDeArte;
import gestionMuseo.jerarquia.Pintura;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

public class EliminarPorCodigo extends DialogoGeneral {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EliminarPorCodigo(Exposicion exposicion) {
		super(exposicion);
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		
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
		btnEliminar.setVisible(false);
		textTitulo.setEnabled(false);
		textcod.setEnabled(true);
		btnAnterior.setVisible(false); 
		rbSala_1.setEnabled(false);
		rbSala_2.setEnabled(false);
		rbSala_3.setEnabled(false);
		rbAlmacen.setEnabled(false);
		
		btnCentro.setText("Eliminar");
		btnDerecha.setText("Cancelar");
		
		btnCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int codigo= Integer.parseInt(textcod.getText());
				ObraDeArte aEliminar= exposicion.devolverPorCodigo(codigo);
				mostrar(aEliminar);
				int n = JOptionPane.showOptionDialog(contentPanel,
						"Estas seguro de que desea eliminarlo?", "Confirmar",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				
				switch (n) {
				case JOptionPane.YES_OPTION:
					
					try {
						if(aEliminar instanceof Pintura)
							exposicion.eliminarPintura(codigo);
						if(aEliminar instanceof Escultura)
							exposicion.eliminarEscultura(codigo);
						if(aEliminar instanceof Grabado)
							exposicion.eliminarGrabado(codigo);
						if(aEliminar instanceof Dibujo)
							exposicion.eliminarDibujo(codigo);
					} catch (ObraNoExisteException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(),
								"Error", JOptionPane.ERROR_MESSAGE);
					}
						exposicion.setModificada(true);
						limpiar();
				}
				
			}
		});
		
		
		
	}

	

}
