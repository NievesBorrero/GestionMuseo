package gestionMuseo.jerarquia;

import java.io.Serializable;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.DimensionNoValidaException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.PeriodoNoValidoException;
import gestionMuseo.excepciones.SinMaterialException;
import gestionMuseo.excepciones.SinSoporteException;
import gestionMuseo.enumeraciones.PeriodoHistorico;

public class Pintura extends ObraDeArte implements Serializable {

	private static final long serialVersionUID = 1L;

	private Soporte soporte;
	private MaterialPintura material;
	protected double plusExposicion = getCosteExposicion() * 0.20; // Cuesta m�s
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

	public Pintura(int codigo) {
		super(codigo);
	}

	public Soporte getSoporte() {
		return soporte;
	}

	public void setSoporte(Soporte soporte) throws SinSoporteException {
		if (soporte != null)
			this.soporte = soporte;
		else
			throw new SinSoporteException(
					"Debe se\u00f1alar el soporte de la pintura");
	}

	public MaterialPintura getMaterial() {
		return material;
	}

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

	@Override
	public String toString() {
		return super.toString() + "soporte=" + soporte + ", material="
				+ material + ", dimensiones:"
				+ DFORMAT.format(obtenerDimensiones()) + " metros]";
	}

}
