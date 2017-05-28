package gestionMuseo.jerarquia;

/**
 * @author Nieves Borrero.
 */

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import gestionMuseo.enumeraciones.EstadoDeConservacion;
import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.Sala;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.EstadoNoAdecuadoException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.ObraExpuestaException;
import gestionMuseo.excepciones.ObraNoDaniadaException;
import gestionMuseo.excepciones.ObraNoExpuestaException;
import gestionMuseo.excepciones.TituloNoValidoException;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ObraDeArte implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titulo;
	private String autor;

	private static DateTimeFormatter FORMATTER = DateTimeFormatter
			.ofPattern("dd/MM/yyyy");
	private static Pattern patternAutor = Pattern
			.compile("([\\-´,a-zA-ZáéíóúñÑ0-9]{2,}\\s?)+");


	protected static DecimalFormat DFORMAT = new java.text.DecimalFormat("0.00");

	private LocalDate fechaIngreso = LocalDate.now();// La fecha de ingreso será
														// la misma en la que se
														// cree la obra de arte.

	private String localizacion;

	private EstiloArtistico estiloArtistico;

	private int codigo;
	private static int ID = 1;

	protected EstadoDeConservacion estadoConservacion = EstadoDeConservacion.BUENO;

	protected int danio = 0;
	private boolean donada;
	private String personaEntidad; // que dona o a la que se compra la obra.
	private Sala sala = Sala.ALMACEN;
	protected double costeExposicion = 2; // Suma en gastos
	protected double costeRestauracion;
	private double fama; // Suma en ingresos
	protected double valor; // Suma en gastos de restauración
	private boolean expuesta;
	private boolean restaurada;
	private double alto;
	private double ancho;

	/**
	 * Constructor de la clase padre.
	 * 
	 * @param nombre
	 * @param autor
	 * @param localizacion
	 * @param estiloArtistico
	 * @param adquisicion
	 * @param personaEntidad
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @throws AutorNoValidoException
	 * @throws personaEntidadNoValidaException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException 
	 * @throws LocalizacionNoValidaException 
	 */

	ObraDeArte(String titulo, String autor, String localizacion,
			EstiloArtistico estiloArtistico, boolean donada,
			String personaEntidad, double fama, double valor, double alto,
			double ancho) throws AutorNoValidoException, TituloNoValidoException, EstiloNoValidoException {
		setTitulo(titulo);
		setAutor(autor);
		setLocalizacion(localizacion);
		setEstiloArtistico(estiloArtistico);
		setDonada(donada);
		setPersonaEntidad(personaEntidad);
		setFama(fama);
		setValor(valor);
		setCodigo(codigo);
		setAlto(alto);
		setAncho(ancho);
	}

	/**
	 * Constructor de obra de arte a partir del código introducido por
	 * parámetro.
	 * 
	 * @param codigo
	 */
	ObraDeArte(int codigo) {
		this.codigo = codigo;
	}
	
//	/**
//	 * Constructor de obra de arte a partir del título introducido por
//	 * parámetro.
//	 * 
//	 * @param codigo
//	 */
//	ObraDeArte(String Titulo) {
//		this.titulo = titulo;
//	}


	public ObraDeArte() {
	}

	public EstiloArtistico getEstiloArtistico() {
		return estiloArtistico;
	}

	public void setEstiloArtistico(EstiloArtistico estiloArtistico) throws EstiloNoValidoException {
		if (estiloArtistico != null)
			this.estiloArtistico = estiloArtistico;
		else
			throw new EstiloNoValidoException("Debes introducir un estilo artistico");
	}
	
	/**
	 * Comprueba si el autor es un nombre válido.
	 * @param autor
	 * @return
	 */
	private static boolean esValido(String autor) {
		return patternAutor.matcher(autor).matches();
	}

	/**
	 * Da formato a una fecha
	 * 
	 * @param fecha
	 * @return cadena con la fecha formateada.
	 */
	public String formatearFecha(LocalDate fecha) {
		return FORMATTER.format(fecha);
	}

	public double getDanio() {
		return this.danio;
	}

	/**
	 * Comprueba el estado de conservación de la obra a partir del daño sufrido
	 * por la misma.
	 */
	protected void comprobarEstadoConservacion() {
		if (danio <= 4)
			setEstadoConservacion(EstadoDeConservacion.BUENO);
		else if (danio > 4 && danio < 7)
			setEstadoConservacion(EstadoDeConservacion.REGULAR);
		else if (danio > 7 && danio < 10)
			setEstadoConservacion(EstadoDeConservacion.MALO);
		else
			setEstadoConservacion(EstadoDeConservacion.MUYMALO);
	}

	/**
	 * Cambia el estado de la obra que estaba en el almacen a una de las salas,
	 * así la obra está expuesta, modificando el valor de expuesta a true. Si la
	 * obra ya está expuesta o no tiene un estado de conservación adecuado para
	 * exponerla, lanza una excepción.
	 * 
	 * @param sala
	 * @throws ObraExpuestaException
	 * @throws EstadoNoAdecuadoException
	 * @throws ObraNoExpuestaException 
	 */
	public void exponerObra(Sala sala) throws ObraExpuestaException,
			EstadoNoAdecuadoException{

		if (isExpuesta() == true)
			throw new ObraExpuestaException("Error, la obra ya esta expuesta");
		else if (getEstadoConservacion() == EstadoDeConservacion.MUYMALO)
			throw new EstadoNoAdecuadoException(
					"Imposible exponer, el estado de conservacion de la obra es pesimo");
		else {
			daniarObra();
			expuesta = true;
			setSala(sala);
		}
			
	}

	public boolean isExpuesta() {
		return expuesta;
	}

	/**
	 * Incrementa el daño sufrido por la obra
	 */
	protected void daniarObra() {
		this.danio = danio + 1;
		comprobarEstadoConservacion();
	}

	/**
	 * Cambia el valor de daño de la obra a cero y devuelve el precio de restauración.
	 * @return precio de la restauración.
	 * @throws ObraNoDaniadaException .
	 */
	public double restaurar() throws ObraNoDaniadaException {
		if(getEstadoConservacion()==EstadoDeConservacion.BUENO)
			throw new ObraNoDaniadaException("No se puede restaurar, la obra no esta daniada");
		
		this.restaurada= true;
		this.danio = 0;
		setCosteRestauracion(getCosteRestauracion()+getValor());
		return getCosteRestauracion();
	}

	/**
	 * Calcula el precio de la restauración según distintas variables de cada
	 * obra.
	 * 
	 * @return precioRestauracion (double)
	 */
	abstract double calcularPrecioRestauracion();

	/**
	 * Cambia la sala a almacén y cambia el valor de expuesta a false, si la
	 * obra no está expuesta, lanza una excepción.
	 * 
	 * @throws ObraNoExpuestaException
	 */
	public void recogerObra() throws ObraNoExpuestaException {
		if (getSala() != Sala.ALMACEN) {
			setSala(Sala.ALMACEN);
			expuesta = false;
		} else
			throw new ObraNoExpuestaException("No puedes recoger una obra que no esta expuesta");

	}

	/**
	 * Obtiene las dimensiones de la obra,que varían según el tipo de obra.
	 * 
	 * @return dimensiones (double)
	 */
	public double obtenerDimensiones() {
		return getAncho() * getAlto();
	}

	/**
	 * Calcula cuánto tiempo lleva la obra en el museo.
	 * 
	 * @return cadena.
	 */
	public String calcularAntiguedad() {
		Period periodo = Period.between(fechaIngreso, LocalDate.now());

		return "La obra lleva " + periodo.getDays() + " dias, "
				+ periodo.getMonths() + " meses y " + periodo.getYears()
				+ " anios en el museo";
	}

	/**
	 * Identifica cada figura con un código único autoincrementable.
	 * 
	 * @param codigo
	 */
	private void setCodigo(int codigo) {
		this.codigo = ID++;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) throws TituloNoValidoException {
		if(titulo=="")
			this.titulo= "Sin titulo";
		this.titulo = titulo;
	}

	public EstadoDeConservacion getEstadoConservacion() {
		return estadoConservacion;
	}

	protected void setEstadoConservacion(EstadoDeConservacion estadoConservacion) {
		this.estadoConservacion = estadoConservacion;
	}
	
	

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}


	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) throws AutorNoValidoException {
		if(!esValido(autor))
			throw new AutorNoValidoException("El autor no es valido");
		this.autor = autor;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion){
		this.localizacion = localizacion;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getPersonaEntidad() {
		return personaEntidad;
	}

	public void setPersonaEntidad(String personaEntidad) {
		this.personaEntidad = personaEntidad;
	}

	public Sala getSala() {
		return sala;
	}

	/**
	 * Cambia de sala a la obra.
	 * 
	 * @param sala
	 */
	void setSala(Sala sala) {
		this.sala = sala;
	}

	public double getCosteExposicion() {
		return costeExposicion;
	}

	protected void setCosteExposicion(double costeExposicion) {
		this.costeExposicion = costeExposicion;
	}

	public double getCosteRestauracion() {
		return costeRestauracion;
	}

	protected void setCosteRestauracion(double costeRestauracion) {
		this.costeRestauracion = costeRestauracion;
	}

	public double getFama() {
		return fama;
	}

	public void setFama(double fama) {
		this.fama = fama;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getAlto() {
		return alto;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}
	
	public boolean isDonada() {
		return this.donada;
	}
	
	public void setDonada(boolean donada){
		this.donada= donada;
	}
	
	/**
	 * Indica si la obra ha sido restaurada.
	 * @return
	 */
	public boolean isRestaurada() {
		return restaurada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObraDeArte other = (ObraDeArte) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n\n" + getClass().getSimpleName() + " [nombre=" + titulo
				+ ", autor=" + autor + "codigo=" + codigo + " fechaIngreso="
				+ formatearFecha(fechaIngreso) + ", localizacion="
				+ localizacion + ", estiloArtistico=" + estiloArtistico
				+ ",\nestadoConservacion=" + estadoConservacion + ", danio="
				+ danio + ", donada=" + donada + ", personaEntidad="
				+ personaEntidad + ",\n sala= " + sala + ", costeExposicion="
				+ getCosteExposicion() + ", costeRestauracion="
				+ getCosteRestauracion() + ", fama=" + fama + ", valor="
				+ valor + " Expuesta= " + expuesta + ", ";
	}


	
}
