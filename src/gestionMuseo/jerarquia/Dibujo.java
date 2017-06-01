package gestionMuseo.jerarquia;

import java.io.Serializable;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.PeriodoHistorico;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.DimensionNoValidaException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.PeriodoNoValidoException;
import gestionMuseo.excepciones.SinTecnicaException;

public class Dibujo extends ObraDeArte implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	TecnicaDeDibujo tecnica;
	Soporte soporte= Soporte.PAPEL;
	protected double plusExposicion = getCosteExposicion()*0.05; 
	

	public Dibujo(String titulo, String autor, String localizacion,
			EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama,
			double valor,
			TecnicaDeDibujo tecnica, double alto, double ancho)
			throws AutorNoValidoException,
			EstiloNoValidoException, SinTecnicaException, DimensionNoValidaException{
				super(titulo, autor, localizacion, estiloArtistico,
				donada, personaEntidad, fama, valor, alto, ancho);
				setTecnica(tecnica);
				setCosteExposicion(costeExposicion+plusExposicion);
				setCosteRestauracion(calcularPrecioRestauracion());
	}
	
	public Dibujo(int codigo){
		super(codigo);
	}
	
	public TecnicaDeDibujo getTecnica() {
		return tecnica;
	}

	public void setTecnica(TecnicaDeDibujo tecnica) throws SinTecnicaException {
		if (tecnica != null)
			this.tecnica = tecnica;
		else
			throw new SinTecnicaException("Debe se\u00f1alar la t\u00e9cnica de dibujo");	
	}

	public Soporte getSoporte() {
		return soporte;
	}

	public void setSoporte(Soporte soporte) {
		this.soporte = soporte;
	}

	@Override
	public
	double calcularPrecioRestauracion() {
		return getValor()+soporte.getPrecio()+ getEstadoConservacion().getPrecio();
	}


}
