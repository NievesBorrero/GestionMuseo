package gestionMuseo.jerarquia;

import java.io.Serializable;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.TituloNoValidoException;

public class Dibujo extends ObraDeArte implements Serializable{
	
	TecnicaDeDibujo tecnica;
	Soporte soporte= Soporte.PAPEL;
	protected double plusExposicion = getCosteExposicion()*0.05; 
	

	public Dibujo(String titulo, String autor, String localizacion,
			EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama,
 double valor,
			TecnicaDeDibujo tecnica, double alto, double ancho)
			throws AutorNoValidoException,
			TituloNoValidoException, EstiloNoValidoException{
		super(titulo, autor, localizacion, estiloArtistico,
				donada, personaEntidad, fama, valor, alto, ancho);
		this.tecnica = tecnica;
		setCosteExposicion(costeExposicion+plusExposicion);
		setCosteRestauracion(calcularPrecioRestauracion());
		
	}

	public TecnicaDeDibujo getTecnica() {
		return tecnica;
	}

	public void setTecnica(TecnicaDeDibujo tecnica) {
		this.tecnica = tecnica;
	}

	public Soporte getSoporte() {
		return soporte;
	}

	public void setSoporte(Soporte soporte) {
		this.soporte = soporte;
	}

	@Override
	double calcularPrecioRestauracion() {
		return getValor()+soporte.getPrecio();
	}


}
