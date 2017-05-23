package gestionMuseo.jerarquia;

import java.io.Serializable;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.TipoDeGrabado;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.TituloNoValidoException;

public class Grabado extends ObraDeArte implements Serializable{

	private TipoDeGrabado tipoDeGrabado;

	public Grabado(String nombre, String autor, String localizacion,
			EstiloArtistico estiloArtistico, boolean donada,
			String personaEntidad, double fama, double valor,
			TipoDeGrabado tipoDeGrabado, double alto, double ancho)
			throws AutorNoValidoException,
			TituloNoValidoException, EstiloNoValidoException{
		super(nombre, autor, localizacion, estiloArtistico, donada,
				personaEntidad, fama, valor, alto, ancho);
		this.tipoDeGrabado = tipoDeGrabado;
		setCosteRestauracion(calcularPrecioRestauracion());
	}

	/**
	 * Constructor para instanciar un grabado a partir de un código introducido
	 * por parámetro.
	 * 
	 * @param codigo
	 */
	public Grabado(int codigo) {
		super(codigo);
	}
	
	/**
	 * Constructor para instanciar un grabado a partir de un título introducido
	 * por parámetro.
	 * 
	 * @param codigo
	 */
//	public Grabado(String titulo) {
//		super(titulo);
//	}

	@Override
	double calcularPrecioRestauracion() {
		return valor + tipoDeGrabado.getPrecio();
	}

	public TipoDeGrabado getTipoDeGrabado() {
		return tipoDeGrabado;
	}

	public void setTipoDeGrabado(TipoDeGrabado tipoDeGrabado) {
		this.tipoDeGrabado = tipoDeGrabado;
	}

}
