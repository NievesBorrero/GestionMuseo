package gestionMuseo.gui;

import gestionMuseo.Exposicion;
import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.enumeraciones.TipoDeGrabado;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.TituloNoValidoException;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IngresarObras extends DialogoGeneral {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que permite crear el diálogo.
	 */
	public IngresarObras(Exposicion exposicion) {
		super();
		
		setTitle("Ingreso de obras de arte");
		hacerComponentesInvisibles();
		
		btnIzquierda.setText("a\u00f1adir");
		btnDerecha.setText("Salir");

		limpiar();
					
		/**
		 * Al pulsar el botón se podrá añadir al museo una obra del tipo señalado.
		 */
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double fama=  Double.parseDouble(spFama.getValue().toString());
				double valor= Double.parseDouble(spValor.getValue().toString());
				double alto= Double.parseDouble(spAlto.getValue().toString());
				double ancho= Double.parseDouble(spAncho.getValue().toString());
				double profundidad=  Double.parseDouble(spprof.getValue().toString());			
				
					try {
						if(cbTipo.getSelectedItem()=="Pintura")
							ingresarPintura(exposicion, fama, valor, alto, ancho);
						else if(cbTipo.getSelectedItem()=="Escultura")
							ingresarEscultura(exposicion, fama, valor, alto,
									ancho, profundidad);
						else if(cbTipo.getSelectedItem()=="Grabado")
							ingresarGrabado(exposicion, fama, valor, alto,
									ancho);
						else
							ingresarDibujo(exposicion, fama, valor, alto, ancho);
						limpiar();
						exposicion.setModificada(true);
					}  catch (TituloNoValidoException e1) {
						JOptionPane.showMessageDialog(contentPanel, e1.getMessage(),
								"Error", JOptionPane.ERROR_MESSAGE);
					
				} catch (AutorNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (EstiloNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
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
	 * @param exposicion
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @throws AutorNoValidoException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException
	 */
	private void ingresarPintura(Exposicion exposicion, double fama,
			double valor, double alto, double ancho)
			throws AutorNoValidoException, TituloNoValidoException,
			EstiloNoValidoException {
		exposicion.ingresarPintura(textTitulo.getText(),
				textAutor.getText(), textLocal.getText(),
				(EstiloArtistico) cbEA.getSelectedItem(),
				rbDonada.isSelected(), textPersona.getText(),
				fama, valor,
				(Soporte) cb1.getSelectedItem(),
				(MaterialPintura) cb2.getSelectedItem(),
				alto, ancho);
	}
	
	/**
	 * Permite ingresar un dibujo en el museo.
	 * @param exposicion
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @throws AutorNoValidoException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException
	 */
	private void ingresarDibujo(Exposicion exposicion, double fama,
			double valor, double alto, double ancho)
			throws AutorNoValidoException, TituloNoValidoException,
			EstiloNoValidoException {
		exposicion.ingresarDibujo(textTitulo.getText(),
				textAutor.getText(), textLocal.getText(),
				(EstiloArtistico) cbEA.getSelectedItem(),
				rbDonada.isSelected(), textPersona.getText(),
				fama, valor,
				(TecnicaDeDibujo) cb1.getSelectedItem(),
				alto, ancho);
	}
	
	/**
	 * Permite ingresar un grabado en el museo.
	 * @param exposicion
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @throws AutorNoValidoException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException
	 */
	private void ingresarGrabado(Exposicion exposicion, double fama,
			double valor, double alto, double ancho)
			throws AutorNoValidoException, TituloNoValidoException,
			EstiloNoValidoException {
		exposicion.ingresarGrabado(textTitulo.getText(),
				textAutor.getText(), textLocal.getText(),
				(EstiloArtistico) cbEA.getSelectedItem(),
				rbDonada.isSelected(), textPersona.getText(),
				fama, valor,
				(TipoDeGrabado) cb1.getSelectedItem(),
				alto, ancho);
	}
	
	/**
	 * Permite ingresar una escultura en el museo.
	 * @param exposicion
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @param profundidad
	 * @throws AutorNoValidoException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException
	 */
	private void ingresarEscultura(Exposicion exposicion, double fama,
			double valor, double alto, double ancho, double profundidad)
			throws AutorNoValidoException, TituloNoValidoException,
			EstiloNoValidoException {
		exposicion.ingresarEscultura(textTitulo.getText(),
				textAutor.getText(), textLocal.getText(),
				(EstiloArtistico) cbEA.getSelectedItem(),
				rbDonada.isSelected(), textPersona.getText(),
				fama, valor,
				(TipoEscultura) cb1.getSelectedItem(),
				(MaterialEscultura) cb2.getSelectedItem(),
				alto, ancho, profundidad);
	}
	
}
