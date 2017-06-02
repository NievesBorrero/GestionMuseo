package gestionMuseo.jerarquia;

import java.io.Serializable;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.DimensionNoValidaException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.SinMaterialException;
import gestionMuseo.excepciones.SinTipoEsculturaException;
/**
 * Clase que extiende de obra de arte, permitiendo crear una escultura.
 * @author Nieves María Borrero Barea.
 *
 */
public class Escultura extends ObraDeArte implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoEscultura tipoEscultura;
	private MaterialEscultura materialEscultura;
	private double profundidad;

	public Escultura(String nombre, String autor, String localizacion,
			EstiloArtistico estiloArtistico, boolean donada,
			String personaEntidad, double fama, double valor,
			TipoEscultura tipoEscultura, MaterialEscultura materialEscultura,
			double ancho, double alto, double profundidad)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinMaterialException, SinTipoEsculturaException,
			DimensionNoValidaException {
		super(nombre, autor, localizacion, estiloArtistico, donada,
				personaEntidad, fama, valor, alto, ancho);
		setMaterialEscultura(materialEscultura);
		setTipoEscultura(tipoEscultura);
		setCosteRestauracion(calcularPrecioRestauracion());
	}

	/**
	 * Constructor que crea una escultura por código.
	 * 
	 * @param codigo
	 */
	public Escultura(int codigo) {
		super(codigo);
	}

	/**
	 * Devuelve el tipo de obra
	 * 
	 * @return tipo de escultura
	 */
	public TipoEscultura getTipoEscultura() {
		return tipoEscultura;
	}

	/**
	 * Modifica el tipo de escultura, si no se introduce ningún tipo lanza una
	 * excepción.
	 * 
	 * @param tipoEscultura
	 * @throws SinTipoEsculturaException
	 */
	public void setTipoEscultura(TipoEscultura tipoEscultura)
			throws SinTipoEsculturaException {
		if (tipoEscultura != null)
			this.tipoEscultura = tipoEscultura;
		else
			throw new SinTipoEsculturaException(
					"Debe se\u00f1alar el tipo de escultura");
	}

	/**
	 * Devuelve el material de la escultura.
	 * 
	 * @return material de escultura.
	 */
	public MaterialEscultura getMaterialEscultura() {
		return materialEscultura;
	}

	/**
	 * Modifica el material de escultura, si no se introduce ningún material,
	 * lanza una excepción.
	 * 
	 * @param materialEscultura
	 * @throws SinMaterialException
	 */
	public void setMaterialEscultura(MaterialEscultura materialEscultura)
			throws SinMaterialException {
		if (materialEscultura != null)
			this.materialEscultura = materialEscultura;
		else
			throw new SinMaterialException(
					"Debe se\u00f1alar el material de la escultura");

	}

	/**
	 * Devuelve la profundidad.
	 * 
	 * @return profundidad.
	 */
	public double getProfundidad() {
		return profundidad;
	}

	/**
	 * Modifica la profundidad de la obra, si no tiene profundidad (es 0 o
	 * menor), lanza una excepción.
	 * 
	 * @param profundidad
	 * @throws DimensionNoValidaException
	 */
	public void setProfundidad(double profundidad)
			throws DimensionNoValidaException {
		if (profundidad <= 0)
			throw new DimensionNoValidaException("la profundidad no es válida");

		this.profundidad = profundidad;
	}
	
	/**
	 * Calcula el precio de restauración de una escultura en particular.
	 */
	@Override
	public double calcularPrecioRestauracion() {

		return valor + materialEscultura.getPrecio()
				+ getEstadoConservacion().getPrecio();
	}


}
