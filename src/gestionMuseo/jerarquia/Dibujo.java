package gestionMuseo.jerarquia;

import java.io.Serializable;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.DimensionNoValidaException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.SinTecnicaException;
/**
 * Clase que extiende de obra de arte, permitiendo crear un dibujo.
 * @author Nieves María Borrero Barea.
 *
 */
public class Dibujo extends ObraDeArte implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private TecnicaDeDibujo tecnica;
	private Soporte soporte= Soporte.PAPEL;
	double plusExposicion = getCosteExposicion()*0.05; 
	

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
	/**
	 * Constructor por código
	 * @param codigo
	 */
	public Dibujo(int codigo){
		super(codigo);
	}
	/**
	 * 
	 * @return Tecnica de Dibujo
	 */
	public TecnicaDeDibujo getTecnica() {
		return tecnica;
	}
	/**
	 * 
	 * @param tecnica
	 * @throws SinTecnicaException
	 */
	public void setTecnica(TecnicaDeDibujo tecnica) throws SinTecnicaException {
		if (tecnica != null)
			this.tecnica = tecnica;
		else
			throw new SinTecnicaException("Debe se\u00f1alar la t\u00e9cnica de dibujo");	
	}
	/**
	 * Devuelve el soporte de la obra
	 * @return Soporte
	 */
	public Soporte getSoporte() {
		return soporte;
	}
	/**
	 * Modifica el soporte.
	 * @param soporte
	 */
	public void setSoporte(Soporte soporte) {
		this.soporte = soporte;
	}

	@Override
	public double calcularPrecioRestauracion() {
		return getValor()+soporte.getPrecio()+ getEstadoConservacion().getPrecio();
	}


}
