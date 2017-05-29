package gestionMuseo.gui;

import gestionMuseo.GestionMuseo;
import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.enumeraciones.TipoDeGrabado;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.excepciones.AdquisicionException;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.SinMaterialException;
import gestionMuseo.excepciones.SinSoporteException;
import gestionMuseo.excepciones.SinTecnicaException;
import gestionMuseo.excepciones.SinTipoEsculturaException;
import gestionMuseo.excepciones.SinTipoException;
import gestionMuseo.excepciones.SinTipoGrabadoException;

import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IngresarObras extends DialogoGeneral {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que permite crear el diálogo.
	 */
	public IngresarObras(GestionMuseo museo) {
		super();

		setTitle("Ingreso de obras de arte");
		hacerComponentesInvisibles();
		spCosteExp.setVisible(false);
		spCostRest.setVisible(false);
		lblCosteDeExposicion.setVisible(false);
		lblCosteRestauracin.setVisible(false);

		btnIzquierda.setText("a\u00f1adir");
		btnDerecha.setText("Salir");

		limpiar();

		/**
		 * Al pulsar el botón se podrá añadir al museo una obra del tipo
		 * señalado.
		 */
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
					if (cbTipo.getSelectedIndex() == -1)
						throw new SinTipoException(
								"No ha seleccionado ningún tipo de obra");
					if (!rbDonada.isSelected() && !rbComprada.isSelected())
						throw new AdquisicionException(
								"No ha se\u00f1alado si la obra fue donada o comprada");

					else if (cbTipo.getSelectedItem() == "Pintura")
						ingresarPintura(museo, fama, valor, alto, ancho);
					else if (cbTipo.getSelectedItem() == "Escultura")
						ingresarEscultura(museo, fama, valor, alto, ancho,
								profundidad);
					else if (cbTipo.getSelectedItem() == "Grabado")
						ingresarGrabado(museo, fama, valor, alto, ancho);
					else
						ingresarDibujo(museo, fama, valor, alto, ancho);
					limpiar();
					museo.setModificado(true);
				} catch (AutorNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (EstiloNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (SinTipoException e1) {
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
				} catch (AdquisicionException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

				limpiar();

			}

		});

		/**
		 * Al pulsar el botón el diálogo se hace invisible.
		 */
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

	}

	/**
	 * Hace invisibles los componentes del JDialog padre que no son necesarios.
	 */

	private void hacerComponentesInvisibles() {
		btnSiguiente.setVisible(false);
		textcod.setVisible(false);
		lblcod.setVisible(false);
		rbSala_1.setVisible(false);
		rbSala_2.setVisible(false);
		rbSala_3.setVisible(false);
		rbAlmacen.setVisible(false);
		panel_salas.setVisible(false);
		textFecha.setVisible(false);
		lblFechaDeIngreso.setVisible(false);
		btnAnterior.setVisible(false);
		lblOrdenarPor.setVisible(false);
		cbOrdenar.setVisible(false);
	}

	/**
	 * Permite ingresar una pintura en el museo.
	 * 
	 * @param exposicion
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @throws AutorNoValidoException
	 * @throws EstiloNoValidoException
	 * @throws SinSoporteException
	 * @throws SinMaterialException
	 */
	private void ingresarPintura(GestionMuseo exposicion, double fama,
			double valor, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinMaterialException, SinSoporteException {
		exposicion.ingresarPintura(textTitulo.getText(), textAutor.getText(),
				textLocal.getText(), (EstiloArtistico) cbEA.getSelectedItem(),
				rbDonada.isSelected(), textPersona.getText(), fama, valor,
				(Soporte) cb1.getSelectedItem(),
				(MaterialPintura) cb2.getSelectedItem(), alto, ancho);
	}

	/**
	 * Permite ingresar un dibujo en el museo.
	 * 
	 * @param exposicion
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @throws AutorNoValidoException
	 * @throws EstiloNoValidoException
	 * @throws SinTecnicaException
	 */
	private void ingresarDibujo(GestionMuseo exposicion, double fama,
			double valor, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinTecnicaException {
		exposicion.ingresarDibujo(textTitulo.getText(), textAutor.getText(),
				textLocal.getText(), (EstiloArtistico) cbEA.getSelectedItem(),
				rbDonada.isSelected(), textPersona.getText(), fama, valor,
				(TecnicaDeDibujo) cb1.getSelectedItem(), alto, ancho);
	}

	/**
	 * Permite ingresar un grabado en el museo.
	 * 
	 * @param exposicion
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @throws AutorNoValidoException
	 * @throws EstiloNoValidoException
	 * @throws SinTipoGrabadoException
	 */
	private void ingresarGrabado(GestionMuseo exposicion, double fama,
			double valor, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinTipoGrabadoException {
		exposicion.ingresarGrabado(textTitulo.getText(), textAutor.getText(),
				textLocal.getText(), (EstiloArtistico) cbEA.getSelectedItem(),
				rbDonada.isSelected(), textPersona.getText(), fama, valor,
				(TipoDeGrabado) cb1.getSelectedItem(), alto, ancho);
	}

	/**
	 * Permite ingresar una escultura en el museo.
	 * 
	 * @param exposicion
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @param profundidad
	 * @throws AutorNoValidoException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException
	 * @throws SinTipoEsculturaException
	 * @throws SinMaterialException
	 */
	private void ingresarEscultura(GestionMuseo exposicion, double fama,
			double valor, double alto, double ancho, double profundidad)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinMaterialException, SinTipoEsculturaException {
		exposicion.ingresarEscultura(textTitulo.getText(), textAutor.getText(),
				textLocal.getText(), (EstiloArtistico) cbEA.getSelectedItem(),
				rbDonada.isSelected(), textPersona.getText(), fama, valor,
				(TipoEscultura) cb1.getSelectedItem(),
				(MaterialEscultura) cb2.getSelectedItem(), alto, ancho,
				profundidad);
	}

}
