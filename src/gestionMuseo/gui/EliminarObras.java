package gestionMuseo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.swing.JOptionPane;

import gestionMuseo.Exposicion;
import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.ObraNoExisteException;
import gestionMuseo.excepciones.TituloNoValidoException;
import gestionMuseo.jerarquia.ObraDeArte;

public class EliminarObras extends MostrarObrasMuseo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EliminarObras(Exposicion exposicion) throws NoHayFondosException {
		super(exposicion);

		btnEliminar.setVisible(true);
		btnEliminar.setText("Eliminar");

		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int opcion = JOptionPane.showOptionDialog(contentPanel,
						"¿Estas seguro de que desea eliminarlo?", "Confirmar",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (opcion == JOptionPane.YES_OPTION) {

					itObras.remove();	
					
					if (itObras.hasNext()){
						nextObra();
						actualizarBotones();  
					}
						
					else if (itObras.hasPrevious()){
						previousObra();
						actualizarBotones();
					}
					else
						setVisible(false);
				

				}
			}

		});

	}

	protected void actualizarBotones() {
		if (!itObras.hasNext())
			btnDerecha.setEnabled(false);
		if(!itObras.hasPrevious())
			btnAnterior.setEnabled(false);
			
		
		
	}
}
