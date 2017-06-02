package gestionMuseo.gui;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.enumeraciones.TipoDeGrabado;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.DimensionNoValidaException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.ObraNoExisteException;
import gestionMuseo.excepciones.SinMaterialException;
import gestionMuseo.excepciones.SinSoporteException;
import gestionMuseo.excepciones.SinTecnicaException;
import gestionMuseo.excepciones.SinTipoEsculturaException;
import gestionMuseo.excepciones.SinTipoGrabadoException;
import gestionMuseo.jerarquia.Dibujo;
import gestionMuseo.jerarquia.Escultura;
import gestionMuseo.jerarquia.Grabado;
import gestionMuseo.jerarquia.ObraDeArte;
import gestionMuseo.jerarquia.Pintura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.JOptionPane;

/**
 * JDialog que recorre el iterador de obras de arte permitiendo modificarlas y
 * guardar los cambios.
 * 
 * @author Nieves María Borrero Barea.
 *
 */
public class ModificarObra extends MostrarObrasMuseo {

	private static final long serialVersionUID = 1L;

	public ModificarObra(ListIterator<ObraDeArte> itObras)
			throws NoHayFondosException {
		super(itObras);
		btnIzquierda.setVisible(true);
		btnIzquierda.setText("Guardar");
		habilitarComponentes();
		inhabilitarComponentes();
		setTitle("Modificar datos");

		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double fama = Double.parseDouble(spFama.getValue().toString());
				double valor = Double
						.parseDouble(spValor.getValue().toString());
				double alto = Double.parseDouble(spAlto.getValue().toString());
				double ancho = Double
						.parseDouble(spAncho.getValue().toString());
				double profundidad = Double.parseDouble(spprof.getValue()
						.toString());

				try {
					if (getObraMostrada() instanceof Pintura) {
						modificarPintura(fama, valor, alto, ancho);
					} else if (getObraMostrada() instanceof Escultura) {
						modificarEscultura(fama, valor, alto, ancho,
								profundidad);
					} else if (getObraMostrada() instanceof Grabado) {
						modificarGrabado(fama, valor, alto, ancho);
					} else {
						modificarDibujo(fama, valor, alto, ancho);
					}

					Principal.museo.setModificado(true);

				} catch (AutorNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (EstiloNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ObraNoExisteException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (SinMaterialException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (SinSoporteException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (SinTipoEsculturaException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (SinTipoGrabadoException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (SinTecnicaException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}

	/**
	 * Modifica los datos de una pintura del museo.
	 * 
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @param pintura
	 * @throws AutorNoValidoException
	 * @throws EstiloNoValidoException
	 * @throws ObraNoExisteException
	 * @throws SinSoporteException
	 * @throws SinMaterialException
	 */
	private void modificarPintura(double fama, double valor, double alto,
			double ancho) throws AutorNoValidoException,
			EstiloNoValidoException, ObraNoExisteException,
			SinMaterialException, SinSoporteException {
		try {
			itObras.set(new Pintura(
					textTitulo.getText(), textAutor.getText(), textLocal.getText(),
					(EstiloArtistico) cbEA.getSelectedItem(),
					rbDonada.isSelected(), textPersona.getText(), fama, valor,
					(Soporte) cb1.getSelectedItem(),
					(MaterialPintura) cb2.getSelectedItem(), alto, ancho));
		} catch (DimensionNoValidaException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Modifica los datos de un dibujo del museo.
	 * 
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @param dibujo
	 * @throws AutorNoValidoException
	 * @throws EstiloNoValidoException
	 * @throws SinTecnicaException
	 */
	private void modificarDibujo(double fama, double valor, double alto,
			double ancho) throws AutorNoValidoException,
			EstiloNoValidoException, SinTecnicaException {
		try {
			itObras.set(new Dibujo(
					textTitulo.getText(), textAutor.getText(), textLocal.getText(),
					(EstiloArtistico) cbEA.getSelectedItem(),
					rbDonada.isSelected(), textPersona.getText(), fama, valor,
					(TecnicaDeDibujo) cb1.getSelectedItem(), alto, ancho));
		} catch (DimensionNoValidaException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Modifica los datos de un grabado del museo.
	 * 
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @param grabado
	 * @throws AutorNoValidoException
	 * @throws EstiloNoValidoException
	 * @throws SinTipoGrabadoException
	 */
	private void modificarGrabado(double fama, double valor, double alto,
			double ancho) throws AutorNoValidoException,
			EstiloNoValidoException, SinTipoGrabadoException {
		try {
			itObras.set(new Grabado(
					textTitulo.getText(), textAutor.getText(), textLocal.getText(),
					(EstiloArtistico) cbEA.getSelectedItem(),
					rbDonada.isSelected(), textPersona.getText(), fama, valor,
					(TipoDeGrabado) cb1.getSelectedItem(), alto, ancho));
		} catch (DimensionNoValidaException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Modifica los datos de una escultura del museo.
	 * 
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @param profundidad
	 * @param escultura
	 * @throws AutorNoValidoException
	 * @throws EstiloNoValidoException
	 * @throws SinMaterialException
	 * @throws SinTipoEsculturaException
	 */
	private void modificarEscultura(double fama, double valor, double alto,
			double ancho, double profundidad)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinTipoEsculturaException, SinMaterialException {
		try {
			itObras.set(new Escultura(
					textTitulo.getText(), textAutor.getText(), textLocal.getText(),
					(EstiloArtistico) cbEA.getSelectedItem(),
					rbDonada.isSelected(), textPersona.getText(), fama, valor,
					(TipoEscultura) cb1.getSelectedItem(),
					(MaterialEscultura) cb2.getSelectedItem(), alto, ancho,
					profundidad));
		} catch (DimensionNoValidaException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Habilita los componentes del diálogo padre que son necesarios.
	 */
	private void habilitarComponentes() {
		cbTipo.setEnabled(true);
		textTitulo.setEnabled(true);
		textAutor.setEnabled(true);
		textLocal.setEnabled(true);
		textPersona.setEnabled(true);
		rbDonada.setEnabled(true);
		ingresosYgastos.setEnabled(true);
		spprof.setEnabled(true);
		cbPH.setEnabled(true);
		cbEA.setEnabled(true);
		rbDonada.setEnabled(true);
		rbComprada.setEnabled(true);
		spAlto.setEnabled(true);
		spAncho.setEnabled(true);
		lblCombobox1.setEnabled(true);
		lblCombobox2.setEnabled(true);
		cb1.setEnabled(true);
		cb2.setEnabled(true);
		textcod.setEnabled(true);
		rbSala_1.setEnabled(true);
		rbSala_2.setEnabled(true);
		rbSala_3.setEnabled(true);
		rbAlmacen.setEnabled(true);
		spFama.setEnabled(true);
		spprof.setEnabled(true);
		spValor.setEnabled(true);

	}

	/**
	 * Inhabilita los componentes del diálogo padre que no son necesarios.
	 */
	void inhabilitarComponentes() {
		cbTipo.setEnabled(false);
		textcod.setEnabled(false);
		rbSala_1.setEnabled(false);
		rbSala_2.setEnabled(false);
		rbSala_3.setEnabled(false);
		rbAlmacen.setEnabled(false);
	}

}
