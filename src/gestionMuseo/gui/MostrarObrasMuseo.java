package gestionMuseo.gui;


import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.jerarquia.ObraDeArte;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ListIterator;

/**
 * JDialog que muestra las obras introducidas por parámetro en un iterador.
 * 
 * @author Nieves Borrero.
 *
 */

public class MostrarObrasMuseo extends DialogoGeneral {

	private static final long serialVersionUID = 1L;
	
	ListIterator<ObraDeArte> itObras;

	public MostrarObrasMuseo(ListIterator<ObraDeArte> itObras)
			throws NoHayFondosException {
		super();

		setTitle("Mostrando fondos del museo...");

		setItObras(itObras);

		inhabilitarComponentes();
		btnIzquierda.setVisible(false);
		
		cbOrdenar.setSelectedItem(null);

		actualizarMostrar(itObras);

		btnDerecha.setText("Salir");
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		btnAnterior.setText("<<");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousObra();
			}
		});

		btnSiguiente.setText(">>");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextObra();
			}
		});
		cbOrdenar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cbOrdenar.getSelectedItem() == "Título") {
					Principal.exposicion.ordenarPorTitulo();
					actualizarItObras();
					nextObra();
					btnAnterior.setEnabled(false);

				} else if (cbOrdenar.getSelectedItem() == "Coste restauración") {
					Principal.exposicion.ordenarPorcosteRestauracion();
					actualizarItObras();
					nextObra();
					btnAnterior.setEnabled(false);

				}
			}
		});
	}

	
	private void actualizarMostrar(ListIterator<ObraDeArte> itObras) {
		mostrar(itObras.next());
		if (!itObras.hasNext())
			btnSiguiente.setEnabled(false);
	}

	public void setItObras(ListIterator<ObraDeArte> itObras) {
		this.itObras = itObras;
	}

	/**
	 * Avanza una posición en el iterador de obras de arte
	 */
	protected void nextObra() {

		ObraDeArte next = itObras.next();

		if (next.equals(obraMostrada))
			next = itObras.next();

		mostrar(next);

		if (itObras.hasNext()) {
			btnSiguiente.setEnabled(true);
		} else {
			btnSiguiente.setEnabled(false);
			itObras.previous();
		}
		if (itObras.hasPrevious())
			btnAnterior.setEnabled(true);
		else
			btnAnterior.setEnabled(false);

	}

	/**
	 * Retrasa una posición en el iterador de obras de arte.
	 */
	protected void previousObra() {

		ObraDeArte previous = itObras.previous();

		if (previous.equals(obraMostrada))
			previous = itObras.previous();

		mostrar(previous);

		if (itObras.hasNext()) {
			btnSiguiente.setEnabled(true);
		} else
			btnSiguiente.setEnabled(false);
		if (itObras.hasPrevious())
			btnAnterior.setEnabled(true);
		else {
			btnAnterior.setEnabled(false);
			itObras.next();
		}

	}

	/**
	 * Actualiza el iterador con las obras de arte que hay en los fonfod de la
	 * exposición.
	 */
	void actualizarItObras() {
		try {
			itObras = Principal.exposicion.getList();
		} catch (NoHayFondosException e) {
			// Aquí no debería entrar
		}
	}
	
	/**
	 * Inhabilita los componentes del JDialog padre que se deban inhabilitar en este caso.
	 */
	private void inhabilitarComponentes() {		
		cbTipo.setEnabled(false);
		textTitulo.setEnabled(false); 
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
		textcod.setEnabled(false);
		btnAnterior.setEnabled(false); // Al principio siempre debe aparecer
										// inhabilitado
		rbSala_1.setEnabled(false);
		rbSala_2.setEnabled(false);
		rbSala_3.setEnabled(false);
		rbAlmacen.setEnabled(false);

		spCosteExp.setEnabled(false);
		spCostRest.setEnabled(false);
		spFama.setEnabled(false);
		spprof.setEnabled(false);
		spValor.setEnabled(false);

	}


}