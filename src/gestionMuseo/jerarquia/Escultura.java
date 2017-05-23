package gestionMuseo.jerarquia;

import java.io.Serializable;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.TituloNoValidoException;

public class Escultura extends ObraDeArte implements Serializable{

	TipoEscultura tipoEscultura;
	MaterialEscultura materialEscultura;
	private double profundidad;

	public Escultura(String nombre, String autor, String localizacion,
			EstiloArtistico estiloArtistico, boolean donada,
			String personaEntidad, double fama, double valor,
			TipoEscultura tipoEscultura, MaterialEscultura materialEscultura,
			double ancho, double alto, double profundidad)
			throws AutorNoValidoException,
			TituloNoValidoException, EstiloNoValidoException{
		super(nombre, autor, localizacion, estiloArtistico, donada,
				personaEntidad, fama, valor, alto, ancho);
		this.materialEscultura = materialEscultura;
		this.tipoEscultura = tipoEscultura;
		setCosteRestauracion(calcularPrecioRestauracion());
	}
	
	public Escultura(int codigo){
		super(codigo);
	}

	public TipoEscultura getTipoEscultura() {
		return tipoEscultura;
	}

	public void setTipoEscultura(TipoEscultura tipoEscultura) {
		this.tipoEscultura = tipoEscultura;
	}

	public MaterialEscultura getMaterialEscultura() {
		return materialEscultura;
	}

	public void setMaterialEscultura(MaterialEscultura materialEscultura) {
		this.materialEscultura = materialEscultura;
	}

	public double getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(double profundidad) {
		this.profundidad = profundidad;
	}

	@Override
	double calcularPrecioRestauracion() {

		return valor + materialEscultura.getPrecio();
	}

	@Override
	public double obtenerDimensiones() {
		return super.obtenerDimensiones() * profundidad;
	}

}
