package gestionMuseo.gui;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.ObraNoDaniadaException;
import gestionMuseo.jerarquia.ObraDeArte;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.JOptionPane;

public class RestaurarObras extends MostrarObrasMuseo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ObraDeArte obra;
	
	public RestaurarObras(ListIterator <ObraDeArte> itObras) throws NoHayFondosException{
		super(itObras);
		
		setTitle("Depto. Restauracion");
		btnIzquierda.setVisible(true);
		btnIzquierda.setText("Restaurar");
		
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					obra = Principal.exposicion.devolverPorCodigo(Integer
							.parseInt(textcod.getText()));
					
					int opcion = JOptionPane
							.showOptionDialog(
									contentPanel,
									"El coste de restauracion es "
											+ obra.getCosteExposicion()+" euros, "
											+ "\n esta segur@ de que desea restaurarla?",
									"Confirmar",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									null, null);

					if (opcion == JOptionPane.YES_OPTION) {
							obra.restaurar();
							Principal.exposicion.setModificada(true);
							
					}
					
					} catch (ObraNoDaniadaException e1) {
							JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		
	}
	


}