package gestionMuseo.jerarquia;

import java.io.Serializable;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.DimensionNoValidaException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.SinMaterialException;
import gestionMuseo.excepciones.SinSoporteException;
/**
 * Clase que extiende de obra de arte, permitiendo crear una pintura.
 * @author Nieves Borrero
 *
 */
public class Pintura extends ObraDeArte implements Serializable {

	private static final long serialVersionUID = 1L;

	private Soporte soporte;
	private MaterialPintura material;
	private double plusExposicion = getCosteExposicion() * 0.20; // Cuesta más
																	// exponer
																	// una
																	// pintura.

	public Pintura(String nombre, String autor, String localizacion,
			EstiloArtistico estiloArtistico, boolean donada,
			String personaEntidad, double fama, double valor, Soporte soporte,
			MaterialPintura material, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinMaterialException, SinSoporteException,
			DimensionNoValidaException{
		super(nombre, autor, localizacion, estiloArtistico, donada,
				personaEntidad, fama, valor, alto, ancho);
		setSoporte(soporte);
		setMaterial(material);
		setCosteExposicion(costeExposicion + plusExposicion);
		setCosteRestauracion(calcularPrecioRestauracion());
	}
	/**
	 * Constructor por codigo
	 * @param codigo
	 */
	public Pintura(int codigo) {
		super(codigo);
	}
	/**
	 * Devuelve el soporte.
	 * @return soporte
	 */
	public Soporte getSoporte() {
		return soporte;
	}
	/**
	 * 
	 * @param soporte
	 * @throws SinSoporteException
	 */
	public void setSoporte(Soporte soporte) throws SinSoporteException {
		if (soporte != null)
			this.soporte = soporte;
		else
			throw new SinSoporteException(
					"Debe se\u00f1alar el soporte de la pintura");
	}
	/**
	 * 
	 * @return MaterialPintura
	 */
	public MaterialPintura getMaterial() {
		return material;
	}
	/**
	 * 
	 * @param material
	 * @throws SinMaterialException
	 */
	public void setMaterial(MaterialPintura material)
			throws SinMaterialException {
		if (material != null)
			this.material = material;
		else
			throw new SinMaterialException(
					"Debe se\u00f1alar el material de la pintura");
	}

	@Override
	public void daniarObra() {
		this.danio = danio + 2;
		comprobarEstadoConservacion();
	}

	@Override
	public double calcularPrecioRestauracion() {
		return valor + material.getPrecio() + soporte.getPrecio()
				+ getEstadoConservacion().getPrecio();

	}

}
