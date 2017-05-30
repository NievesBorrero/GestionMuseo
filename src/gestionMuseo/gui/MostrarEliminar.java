package gestionMuseo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;
import javax.swing.JOptionPane;
import gestionMuseo.GestionMuseo;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.jerarquia.ObraDeArte;

/**
 * JDialog que muestra las obras contenidas en un listIterator dando la
 * posibilidad de eliminarlas en ese momento.
 * 
 * @author Nieves Borrero.
 *
 */
public class MostrarEliminar extends MostrarObrasMuseo {

	private static final long serialVersionUID = 1L;

	public MostrarEliminar(ListIterator<ObraDeArte> itObras)
			throws NoHayFondosException {
		super(itObras);

		btnIzquierda.setVisible(true);
		btnIzquierda.setText("Eliminar");

		btnIzquierda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int opcion = JOptionPane.showOptionDialog(contentPanel,
						"¿Estas seguro de que desea eliminarlo?", "Confirmar",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (opcion == JOptionPane.YES_OPTION) {

					itObras.remove();
					//actualizarListIterator(Principal.museo);
					
					if(!itObras.hasNext()&&!itObras.hasPrevious())
						setVisible(false);

					else if (!itObras.hasNext()) {
						previousObra();
//						if (!itObras.hasNext())
//							btnDerecha.setEnabled(false);
//						else 
//							btnDerecha.setEnabled(true);
//						if (!itObras.hasPrevious())
//							btnAnterior.setEnabled(false);
//						else 
//							btnAnterior.setEnabled(true);
						//actualizarBotones();

					}else{
						nextObra();
//						if (!itObras.hasNext())
//							btnDerecha.setEnabled(false);
//						if (!itObras.hasPrevious())
//							btnAnterior.setEnabled(false);
						//actualizarBotones();
					} 
				}
			}

		});

	}

	protected void actualizarBotones() {

		if (!itObras.hasNext())
			btnDerecha.setEnabled(false);

		else
			btnDerecha.setEnabled(true);

		if (itObras.hasPrevious())
			btnAnterior.setEnabled(false);

		else
			btnAnterior.setEnabled(true);
	}

	void actualizarListIterator(GestionMuseo exposicion) {
		try {
			itObras = exposicion.getList();
			actualizarBotones();
		} catch (NoHayFondosException e) {
			// Aquí no debería entrar
		}
	}

}
