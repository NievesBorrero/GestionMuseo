package gestionMuseo.gui;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.enumeraciones.TipoDeGrabado;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.ObraNoExisteException;
import gestionMuseo.excepciones.TituloNoValidoException;
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
 * JDialog que recorre el iterador de obras de arte permitiendo modificarlas y guardar los cambios.
 * @author Nieves Borrero.
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

		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double fama=  Double.parseDouble(spFama.getValue().toString());
				double valor= Double.parseDouble(spValor.getValue().toString());
				double alto= Double.parseDouble(spAlto.getValue().toString());
				double ancho= Double.parseDouble(spAncho.getValue().toString());
				double profundidad=  Double.parseDouble(spprof.getValue().toString());
				
				Pintura pintura=null;
				Escultura escultura=null;
				Grabado grabado=null;
				Dibujo dibujo= null;
				
					try {
						if(getObraMostrada() instanceof Pintura){
							pintura = (Pintura) obraMostrada;
							modificarPintura(fama, valor, alto, ancho, pintura);
						}
						else if(getObraMostrada() instanceof Escultura){
							escultura = (Escultura) obraMostrada;
							modificarEscultura(fama, valor, alto, ancho,
									profundidad, escultura);		
						}
						else if(getObraMostrada() instanceof Grabado){
							grabado= (Grabado) obraMostrada;
							modificarGrabado(fama, valor, alto, ancho, grabado);
						}
						else{
							dibujo= (Dibujo) obraMostrada;
							modificarDibujo(fama, valor, alto, ancho, dibujo);
						}
						
						//actualizarItObras();
						
					} catch (AutorNoValidoException e1) {
						JOptionPane.showMessageDialog(contentPanel,
								e1.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (TituloNoValidoException e1) {
						JOptionPane.showMessageDialog(contentPanel,
								e1.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (EstiloNoValidoException e1) {
						JOptionPane.showMessageDialog(contentPanel,
								e1.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (ObraNoExisteException e1) {
						JOptionPane.showMessageDialog(contentPanel,
								e1.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			

			
		});
	}
	
	/**
	 * Modifica los datos de una pintura del museo.
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @param pintura
	 * @throws AutorNoValidoException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException
	 * @throws ObraNoExisteException
	 */
	private void modificarPintura(double fama, double valor,
			double alto, double ancho, Pintura pintura)
			throws AutorNoValidoException, TituloNoValidoException,
			EstiloNoValidoException, ObraNoExisteException {
		Principal.museo.modificarPintura(
		Principal.museo.indexOf(pintura), textTitulo.getText(),
		textAutor.getText(), textLocal.getText(),
		(EstiloArtistico) cbEA.getSelectedItem(),
		rbDonada.isSelected(), textPersona.getText(),
		fama, valor,
		(Soporte) cb1.getSelectedItem(),
		(MaterialPintura) cb2.getSelectedItem(),
		alto, ancho);
	}
	
	/**
	 * Modifica los datos de un dibujo del museo.
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @param dibujo
	 * @throws AutorNoValidoException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException
	 */
	private void modificarDibujo(double fama, double valor,
			double alto, double ancho, Dibujo dibujo)
			throws AutorNoValidoException, TituloNoValidoException,
			EstiloNoValidoException {
		Principal.museo.modificarDibujo(
				Principal.museo.indexOf(dibujo), textTitulo.getText(),
				textAutor.getText(), textLocal.getText(),
				(EstiloArtistico) cbEA.getSelectedItem(),
				rbDonada.isSelected(), textPersona.getText(),
				fama, valor, 
				(TecnicaDeDibujo) cb1.getSelectedItem(),
				alto, ancho);
	}

	/**
	 * Modifica los datos de un grabado del museo.
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @param grabado
	 * @throws AutorNoValidoException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException
	 */
	private void modificarGrabado(double fama, double valor,
			double alto, double ancho, Grabado grabado)
			throws AutorNoValidoException, TituloNoValidoException,
			EstiloNoValidoException {
		Principal.museo.modificarGrabado(
				Principal.museo.indexOf(grabado), textTitulo.getText(),
				textAutor.getText(), textLocal.getText(),
				(EstiloArtistico) cbEA.getSelectedItem(),
				rbDonada.isSelected(), textPersona.getText(),
				fama, valor, 
				(TipoDeGrabado) cb1.getSelectedItem(),
				alto, ancho);
	}
	
	/**
	 * Modifica los datos de una escultura del museo.
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @param profundidad
	 * @param escultura
	 * @throws AutorNoValidoException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException
	 */
	private void modificarEscultura(double fama, double valor,
			double alto, double ancho, double profundidad,
			Escultura escultura) throws AutorNoValidoException,
			TituloNoValidoException, EstiloNoValidoException {
		Principal.museo.modificarEscultura(
				Principal.museo.indexOf(escultura), textTitulo.getText(),
				textAutor.getText(), textLocal.getText(),
				(EstiloArtistico) cbEA.getSelectedItem(),
				rbDonada.isSelected(), textPersona.getText(),
				fama, valor, 
				(TipoEscultura) cb1.getSelectedItem(),
				(MaterialEscultura) cb2.getSelectedItem(),
				alto, ancho, profundidad);
	}
	
	
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
		spCosteExp.setEnabled(true);
		spCostRest.setEnabled(true);
		spFama.setEnabled(true);
		spprof.setEnabled(true);
		spValor.setEnabled(true);

	}
	
	void inhabilitarComponentes(){
		cbTipo.setEnabled(false);
		textcod.setEnabled(false);
	}

}
