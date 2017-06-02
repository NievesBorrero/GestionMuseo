package gestionMuseo.jerarquia;

import java.io.Serializable;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.TipoDeGrabado;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.DimensionNoValidaException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.SinTipoGrabadoException;
/**
 * Clase que extiende de obra de arte, permitiendo crear una escultura.
 * @author nieves
 *
 */
public class Grabado extends ObraDeArte implements Serializable {

	private static final long serialVersionUID = 1L;
	private TipoDeGrabado tipoDeGrabado;

	public Grabado(String nombre, String autor, String localizacion,
			EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TipoDeGrabado tipoDeGrabado, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinTipoGrabadoException, DimensionNoValidaException
	{
		super(nombre, autor, localizacion, estiloArtistico, donada,
				personaEntidad, fama, valor, alto, ancho);
		setTipoDeGrabado(tipoDeGrabado);
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
	public Grabado(String titulo) {
		super(titulo);
	}

	@Override
	public double calcularPrecioRestauracion() {
		return valor + tipoDeGrabado.getPrecio()
				+ getEstadoConservacion().getPrecio();
	}
	/**
	 * 
	 * @return Tipo de Grabado
	 */
	public TipoDeGrabado getTipoDeGrabado() {
		return tipoDeGrabado;
	}
	/**
	 * 
	 * @param tipoDeGrabado
	 * @throws SinTipoGrabadoException
	 */
	public void setTipoDeGrabado(TipoDeGrabado tipoDeGrabado)
			throws SinTipoGrabadoException {
		if (tipoDeGrabado != null)
			this.tipoDeGrabado = tipoDeGrabado;
		else
			throw new SinTipoGrabadoException(
					"Debe se\u00f1alar el tipo de escultura");

	}

}
