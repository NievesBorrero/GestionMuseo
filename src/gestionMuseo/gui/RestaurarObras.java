package gestionMuseo.gui;
/**
 * @author Nieves Borrero.
 */
import gestionMuseo.Exposicion;
import gestionMuseo.enumeraciones.EstadoDeConservacion;
import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.enumeraciones.TipoDeGrabado;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.ObraNoDaniadaException;
import gestionMuseo.jerarquia.Dibujo;
import gestionMuseo.jerarquia.Escultura;
import gestionMuseo.jerarquia.Grabado;
import gestionMuseo.jerarquia.ObraDeArte;
import gestionMuseo.jerarquia.Pintura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class RestaurarObras extends MostrarObrasMuseo {
	private ObraDeArte obra;
	
	public RestaurarObras(Exposicion exposicion) throws NoHayFondosException{
		super(exposicion);
		
		setTitle("Depto. Restauracion");
		btnEliminar.setVisible(true);
		btnEliminar.setText("Restaurar");
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					obra = exposicion.devolverPorCodigo(Integer
							.parseInt(textcod.getText()));
					
					int opcion = JOptionPane
							.showOptionDialog(
									contentPanel,
									"El coste de restauracion es "
											+ obra.getCosteExposicion()+" euros, "
											+ "\n estas segur@ de que deseas restaurarla?",
									"Confirmar",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									null, null);

					if (opcion == JOptionPane.YES_OPTION) {
							obra.restaurar();
							exposicion.setModificada(true);
							
					}
					
					} catch (ObraNoDaniadaException e1) {
							JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		
	}
	


}