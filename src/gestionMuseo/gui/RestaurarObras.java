package gestionMuseo.gui;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.ObraNoDaniadaException;
import gestionMuseo.jerarquia.ObraDeArte;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.JOptionPane;

public class RestaurarObras extends MostrarObrasMuseo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public RestaurarObras(ListIterator <ObraDeArte> itObras) throws NoHayFondosException{
		super(itObras);
		
		setTitle("Depto. Restauracion");
		btnIzquierda.setVisible(true);
		btnIzquierda.setText("Restaurar");
		
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int indice=Principal.museo.indexOf(obraMostrada);
					
					int opcion = JOptionPane
							.showOptionDialog(
									contentPanel,
									"El coste de restauracion es "
											+ Principal.museo.getObra(indice).calcularPrecioRestauracion()+" euros, "
											+ "\n esta segur@ de que desea restaurarla?",
									"Confirmar",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									null, null);

					if (opcion == JOptionPane.YES_OPTION) {
						Principal.museo.getObra(indice).restaurar();
							Principal.museo.setModificado(true);
							
					}
					
					} catch (ObraNoDaniadaException e1) {
							JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoHayFondosException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
	}
	


}