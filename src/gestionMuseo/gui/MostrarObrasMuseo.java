package gestionMuseo.gui;

/**
 * @author Nieves Borrero.
 */
import gestionMuseo.Exposicion;
import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.PeriodoHistorico;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.enumeraciones.TipoDeGrabado;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.jerarquia.Dibujo;
import gestionMuseo.jerarquia.Escultura;
import gestionMuseo.jerarquia.Grabado;
import gestionMuseo.jerarquia.ObraDeArte;
import gestionMuseo.jerarquia.Pintura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class MostrarObrasMuseo extends DialogoGeneral {

	static ListIterator<ObraDeArte> itObras; // QUITAR ESTATICOOO
	private Exposicion exposicion;

	public MostrarObrasMuseo(Exposicion exposicion) throws NoHayFondosException {
		super(exposicion);

		this.exposicion = exposicion;

		setTitle("Mostrando fondos del museo...");

		itObras = exposicion.getList();
		textTitulo.setEnabled(false); // Lo inhabilitamos
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
		textcod.setEnabled(false);
		btnAnterior.setEnabled(false); // Al principio siempre debe aparecer
										// inhabilitado
		rbSala_1.setEnabled(false);
		rbSala_2.setEnabled(false);
		rbSala_3.setEnabled(false);
		rbAlmacen.setEnabled(false);

		mostrar(itObras.next());
		if (!itObras.hasNext())
			btnDerecha.setEnabled(false);

		btnCentro.setText("Salir");
		btnCentro.addActionListener(new ActionListener() {
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

		btnDerecha.setText(">>");
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextObra();
			}
		});

	}

	/**
	 * Avanza una posición en el iterador de obras de arte
	 */
	protected void nextObra() {

		ObraDeArte next = itObras.next();

		if(next.equals(obraMostrada))
			next=itObras.next();
		
		mostrar(next);

		if (itObras.hasNext()) {
			btnDerecha.setEnabled(true);
		} else {
			btnDerecha.setEnabled(false);
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

		if(previous.equals(obraMostrada))
			previous=itObras.previous();
		
		mostrar(previous);

		//mostrar(itObras.previous());

		if (itObras.hasNext()) {
			btnDerecha.setEnabled(true);
		} else
			btnDerecha.setEnabled(false);
		if (itObras.hasPrevious())
			btnAnterior.setEnabled(true);
		else {
			btnAnterior.setEnabled(false);
			itObras.next();
		}

	}

}