package gestionMuseo.jerarquia;

import java.io.Serializable;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.TituloNoValidoException;

public class Pintura extends ObraDeArte implements Serializable {

	private Soporte soporte;
	private MaterialPintura material;
	protected double plusExposicion = getCosteExposicion()*0.20; // Cuesta m�s exponer una pintura.

	public Pintura(String nombre, String autor, String localizacion,
			EstiloArtistico estiloArtistico, boolean donada,
			String personaEntidad, double fama, double valor, Soporte soporte,
			MaterialPintura material, double alto, double ancho)
			throws AutorNoValidoException,
			TituloNoValidoException, EstiloNoValidoException{
		super(nombre, autor, localizacion, estiloArtistico, donada,
				personaEntidad, fama, valor, alto, ancho);
		this.soporte = soporte;
		this.material = material;
		setCosteExposicion(costeExposicion+plusExposicion);
		setCosteRestauracion(calcularPrecioRestauracion());
	}

	public Pintura(int codigo){
		super(codigo);
	}

	public Soporte getSoporte() {
		return soporte;
	}

	public void setSoporte(Soporte soporte) {
		this.soporte = soporte;
	}

	public MaterialPintura getMaterial() {
		return material;
	}

	public void setMaterial(MaterialPintura material) {
		this.material = material;
	}

	@Override
	public void daniarObra() {
		this.danio = danio + 2;
		comprobarEstadoConservacion();
	}

	@Override
	public double calcularPrecioRestauracion() {
		return valor + material.getPrecio() + soporte.getPrecio();

	}

	@Override
	public String toString() {
		return super.toString() + "soporte=" + soporte + ", material="
				+ material + ", dimensiones:"
				+ DFORMAT.format(obtenerDimensiones()) + " metros]";
	}


}
