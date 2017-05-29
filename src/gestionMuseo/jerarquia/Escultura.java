package gestionMuseo.jerarquia;

import java.io.Serializable;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.SinMaterialException;
import gestionMuseo.excepciones.SinTipoEsculturaException;

public class Escultura extends ObraDeArte implements Serializable{

	private static final long serialVersionUID = 1L;
	
	TipoEscultura tipoEscultura;
	MaterialEscultura materialEscultura;
	private double profundidad;

	public Escultura(String nombre, String autor, String localizacion,
			EstiloArtistico estiloArtistico, boolean donada,
			String personaEntidad, double fama, double valor,
			TipoEscultura tipoEscultura, MaterialEscultura materialEscultura,
			double ancho, double alto, double profundidad)
			throws AutorNoValidoException,
			EstiloNoValidoException, SinMaterialException, SinTipoEsculturaException{
		super(nombre, autor, localizacion, estiloArtistico, donada,
				personaEntidad, fama, valor, alto, ancho);
		setMaterialEscultura(materialEscultura);
		setTipoEscultura(tipoEscultura);
		setCosteRestauracion(calcularPrecioRestauracion());
	}
	
	public Escultura(int codigo){
		super(codigo);
	}

	public TipoEscultura getTipoEscultura() {
		return tipoEscultura;
	}

	public void setTipoEscultura(TipoEscultura tipoEscultura) throws SinTipoEsculturaException {
		if (tipoEscultura != null)
			this.tipoEscultura = tipoEscultura;
		else
			throw new SinTipoEsculturaException("Debe se\u00f1alar el tipo de escultura");
	}

	public MaterialEscultura getMaterialEscultura() {
		return materialEscultura;
	}

	public void setMaterialEscultura(MaterialEscultura materialEscultura) throws SinMaterialException {
		if (materialEscultura != null)
			this.materialEscultura = materialEscultura;
		else
			throw new SinMaterialException("Debe se\u00f1alar el material de la escultura");
		
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
