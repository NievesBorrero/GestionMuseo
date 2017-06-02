package gestionMuseo.gui;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.enumeraciones.TipoDeGrabado;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.excepciones.AdquisicionException;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.DimensionNoValidaException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.PeriodoNoValidoException;
import gestionMuseo.excepciones.SinMaterialException;
import gestionMuseo.excepciones.SinSoporteException;
import gestionMuseo.excepciones.SinTecnicaException;
import gestionMuseo.excepciones.SinTipoEsculturaException;
import gestionMuseo.excepciones.SinTipoException;
import gestionMuseo.excepciones.SinTipoGrabadoException;

import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * JDialog que permite el ingreso de obras en el museo.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */
public class IngresarObras extends DialogoGeneral {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que permite crear el diálogo.
	 */
	public IngresarObras() {
		super();

		setTitle("Ingreso de obras de arte");

		hacerComponentesInvisibles();
		hacerComponentesVisibles();

		btnAnterior.setText("Limpiar");

		btnIzquierda.setText("a\u00f1adir");
		btnDerecha.setText("Salir");

		limpiar();

		/**
		 * Al pulsar el botón se podrá añadir al museo una obra del tipo
		 * señalado.
		 */
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double fama = obtenerFama();
				double valor = obtenerValor();
				double alto = obtenerAltura();
				double ancho = obtenerAnchura();
				double profundidad = obtenerProfundidad();

				try {

					if (cbTipo.getSelectedIndex() == -1)
						throw new SinTipoException(
								"No ha seleccionado ningún tipo de obra");
					if (cbPH.getSelectedIndex() == -1)
						throw new PeriodoNoValidoException(
								"Debe introducir un periodo hist\u00f3rico");
					if (!rbDonada.isSelected() && !rbComprada.isSelected())
						throw new AdquisicionException(
								"No ha se\u00f1alado si la obra fue donada o comprada");

					else if (cbTipo.getSelectedItem() == "Pintura")
						ingresarPintura(fama, valor, alto, ancho);
					else if (cbTipo.getSelectedItem() == "Escultura")
						ingresarEscultura(fama, valor, alto, ancho,
								profundidad);
					else if (cbTipo.getSelectedItem() == "Grabado")
						ingresarGrabado(fama, valor, alto, ancho);
					else
						ingresarDibujo(fama, valor, alto, ancho);
					Principal.museo.setModificado(true);
					limpiar();
				} catch (AutorNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (PeriodoNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}catch (EstiloNoValidoException e1) {
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
				} catch (DimensionNoValidaException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} 

			}

		});

		/**
		 * Al pulsar el botón se limpian los campos.
		 */
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
	 * Transforma el dato del JSpinner profundidad en un decimal y lo devuelve.
	 * 
	 * @return decimal.
	 */
	private double obtenerProfundidad() {
		return Double.parseDouble(spprof.getValue().toString());
	}

	/**
	 * Transforma el dato del JSpinner anchura en un decimal y lo devuelve.
	 * 
	 * @return decimal.
	 */
	private double obtenerAnchura() {
		return Double.parseDouble(spAncho.getValue().toString());
	}

	/**
	 * Transforma el dato del JSpinner altura en un decimal y lo devuelve.
	 * 
	 * @return decimal.
	 */
	private double obtenerAltura() {
		return Double.parseDouble(spAlto.getValue().toString());
	}

	/**
	 * Transforma el dato del JSpinner valor en un decimal y lo devuelve.
	 * 
	 * @return decimal.
	 */
	private double obtenerValor() {
		return Double.parseDouble(spValor.getValue().toString());
	}

	/**
	 * Transforma el dato del JSpinner fama en un decimal y lo devuelve.
	 * 
	 * @return decimal.
	 */
	private double obtenerFama() {
		return Double.parseDouble(spFama.getValue().toString());
	}

	/**
	 * Hace visibles los componentes que no están a la vista en el JDialog padre
	 * y que son necesarios.
	 */
	private void hacerComponentesVisibles() {
		spCosteExp.setVisible(false);
		spCostRest.setVisible(false);
		lblCosteDeExposicion.setVisible(false);
		lblCosteRestauracin.setVisible(false);
		btnAnterior.setVisible(true);
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
		lblestado.setVisible(false);
		textEstado.setVisible(false);
		// lblTotal.setVisible(false);
		// textTotal.setVisible(false);
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
	 * @throws PeriodoNoValidoException 
	 * @throws DimensionNoValidaException 
	 */
	private void ingresarPintura(double fama,
			double valor, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinMaterialException, SinSoporteException, DimensionNoValidaException, PeriodoNoValidoException {
			
			if (cb1.getSelectedIndex()== -1)
				throw new SinMaterialException("Debe seleccionar el material");
			else if (cb2.getSelectedIndex()== -1)
				throw new SinMaterialException("Debe seleccionar el soporte");
			else if (alto==0)
				throw new DimensionNoValidaException("La altura no es válida");
			else if (ancho==0)
				throw new DimensionNoValidaException("La anchura no es válida");
			Principal.museo.ingresarPintura(textTitulo.getText(),
					textAutor.getText(), textLocal.getText(),
					(EstiloArtistico) cbEA.getSelectedItem(),
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
	 * @throws PeriodoNoValidoException 
	 * @throws DimensionNoValidaException 
	 */
	private void ingresarDibujo(double fama,
			double valor, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinTecnicaException, DimensionNoValidaException{
		if (cb1.getSelectedIndex()== -1)
			throw new SinTecnicaException("Debe seleccionar la técnica de dibujo");
		else if (alto==0)
			throw new DimensionNoValidaException("La altura no es válida");
		else if (ancho==0)
			throw new DimensionNoValidaException("La anchura no es válida");
		
			Principal.museo.ingresarDibujo(textTitulo.getText(),
					textAutor.getText(), textLocal.getText(),
					(EstiloArtistico) cbEA.getSelectedItem(),
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
	 * @throws PeriodoNoValidoException 
	 * @throws DimensionNoValidaException 
	 */
	private void ingresarGrabado(double fama,
			double valor, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinTipoGrabadoException, DimensionNoValidaException{
		if (cb1.getSelectedIndex()== -1)
			throw new SinTipoGrabadoException("Debe seleccionar el tipo de grabado");
		else if (alto==0)
			throw new DimensionNoValidaException("La altura no es válida");
		else if (ancho==0)
			throw new DimensionNoValidaException("La anchura no es válida");
			Principal.museo.ingresarGrabado(textTitulo.getText(),
					textAutor.getText(), textLocal.getText(),
					(EstiloArtistico) cbEA.getSelectedItem(),
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
	 * @throws EstiloNoValidoException
	 * @throws SinTipoEsculturaException
	 * @throws SinMaterialException 
	 * @throws DimensionNoValidaException 
	 */
	private void ingresarEscultura(double fama,
			double valor, double alto, double ancho, double profundidad)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinMaterialException, SinTipoEsculturaException, 
			DimensionNoValidaException{
			if (cb1.getSelectedIndex()== -1)
			throw new SinMaterialException("Debe seleccionar el material");
			else if (cb2.getSelectedIndex()== -1)
			throw new SinMaterialException("Debe seleccionar el tipo de escultura");
			else if (alto==0)
				throw new DimensionNoValidaException("La altura no es válida");
			else if (ancho==0)
				throw new DimensionNoValidaException("La anchura no es válida");
			else if (profundidad==0)
				throw new DimensionNoValidaException("La profundidad no es válida");
			Principal.museo.ingresarEscultura(textTitulo.getText(),
					textAutor.getText(), textLocal.getText(),
					(EstiloArtistico) cbEA.getSelectedItem(),
					rbDonada.isSelected(), textPersona.getText(), fama, valor,
					(TipoEscultura) cb1.getSelectedItem(),
					(MaterialEscultura) cb2.getSelectedItem(), alto, ancho,
					profundidad);
		
	}

}
