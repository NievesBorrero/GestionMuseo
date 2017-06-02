package gestionMuseo.jerarquia;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import gestionMuseo.enumeraciones.EstadoDeConservacion;
import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.Sala;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.DimensionNoValidaException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.ObraExpuestaException;
import gestionMuseo.excepciones.ObraNoDaniadaException;
import gestionMuseo.excepciones.PeriodoNoValidoException;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * Clase abstracta de la que heredan los distintos tipos de obras de arte.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */

public abstract class ObraDeArte implements Serializable {

	private static final long serialVersionUID = 1L;
	private String titulo;
	private String autor;
	private static DateTimeFormatter FORMATTER = DateTimeFormatter
			.ofPattern("dd/MM/yyyy");
	private static Pattern patternAutor = Pattern
			.compile("([\\-´,a-zA-ZáéíóúñÑ0-9]{2,}\\s?)+");
	private static DecimalFormat DFORMAT = new java.text.DecimalFormat("0.00");

	private LocalDate fechaIngreso = LocalDate.now();// La fecha de ingreso será
														// la misma en la que se
														// cree la obra de arte.
	private String localizacion;
	private EstiloArtistico estiloArtistico;
	private int codigo;
	private static int Id = 1;
	private EstadoDeConservacion estadoConservacion = EstadoDeConservacion.BUENO;
	int danio = 0;
	private boolean donada;
	private String personaEntidad; // que dona o a la que se compra la obra.
	private Sala sala = Sala.ALMACEN;
	double costeExposicion = 2; // Suma en gastos
	private double costeRestauracion;
	private double fama; // Suma en ingresos
	double valor; // Suma en gastos de restauración
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
	 * @throws DimensionNoValidaException
	 * @throws PeriodoNoValidoException
	 * @throws LocalizacionNoValidaException
	 */

	ObraDeArte(String titulo, String autor, String localizacion,
			EstiloArtistico estiloArtistico, boolean donada,
			String personaEntidad, double fama, double valor, double alto,
			double ancho) throws AutorNoValidoException,
			EstiloNoValidoException, DimensionNoValidaException {
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

	/**
	 * Constructor de obra de arte a partir del título introducido por
	 * parámetro.
	 *
	 * @param Titulo
	 */
	ObraDeArte(String Titulo) {
		setTitulo(Titulo);
	}

	/**
	 * Comprueba si el autor es un nombre válido.
	 * 
	 * @param autor
	 * @return cadena
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

	/**
	 * Devuelve el daño de una obra de arte.
	 * 
	 * @return
	 */
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
		else
			setEstadoConservacion(EstadoDeConservacion.MALO);
	}

	/**
	 * Cambia el estado de la obra que estaba en el almacén a una de las salas,
	 * así la obra está expuesta, modificando el valor de expuesta a true. Si la
	 * obra ya está expuesta, lanza una excepción.
	 * 
	 * @param sala
	 * @throws ObraExpuestaException
	 */
	public void exponerObra(Sala sala) throws ObraExpuestaException {

		if (isExpuesta() == true)
			throw new ObraExpuestaException("Error, la obra ya esta expuesta");
		else {
			daniarObra();
			expuesta = true;
			setSala(sala);
		}

	}

	/**
	 * Devuelve si la obra está expuesta o no.
	 * 
	 * @return true o false.
	 */
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
	 * Cambia el valor de daño de la obra a cero.
	 * 
	 * @throws ObraNoDaniadaException .
	 */
	public void restaurar() throws ObraNoDaniadaException {
		if (getEstadoConservacion() == EstadoDeConservacion.BUENO)
			throw new ObraNoDaniadaException(
					"No se puede restaurar, la obra no est\u00e1 da\u00f1ada");

		setRestaurada(true);
		this.danio = 0;
		comprobarEstadoConservacion();

	}

	/**
	 * Modifica el estilo artístico. Si el estilo artístico está a null, lanza
	 * una excepción.
	 * 
	 * @param estiloArtistico
	 * @throws EstiloNoValidoException
	 */
	public void setEstiloArtistico(EstiloArtistico estiloArtistico)
			throws EstiloNoValidoException {
		if (estiloArtistico != null)
			this.estiloArtistico = estiloArtistico;
		else
			throw new EstiloNoValidoException(
					"Debe introducir un estilo art\u00edstico");
	}

	/**
	 * Calcula el precio de la restauración según distintas variables de cada
	 * obra.
	 * 
	 * @return precioRestauracion (double)
	 */
	public abstract double calcularPrecioRestauracion();

	/**
	 * Cambia la sala a almacén y cambia el valor de expuesta a false.
	 */
	public void recogerObra() {
		setSala(Sala.ALMACEN);
		expuesta = false;
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
	public void setCodigo() {
		this.codigo = Id++;
	}
	
	/**
	 * Modifica el código
	 * @param codigo
	 */
	public void setCodigo(int codigo){
		this.codigo = codigo;
	}
	/**
	 * Devuelve el título de una obra.
	 * 
	 * @return
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Modifica el título de una obra.
	 * 
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		if (titulo.length() == 0)
			this.titulo = "Sin titulo";
		else
			this.titulo = titulo;
	}

	/**
	 * Devuelve el estado de conservación de una obra.
	 * 
	 * @return estado de conservación.
	 */
	public EstadoDeConservacion getEstadoConservacion() {
		return estadoConservacion;
	}

	/**
	 * Modifica el estado de conseración de una obra.
	 * 
	 * @param estadoConservacion
	 */
	protected void setEstadoConservacion(EstadoDeConservacion estadoConservacion) {
		this.estadoConservacion = estadoConservacion;
	}

	/**
	 * Modifica el campo "restaurada" de una obra
	 * 
	 * @param restaurada
	 */
	public void setRestaurada(boolean restaurada) {
		this.restaurada = restaurada;
	}

	/**
	 * Devuelve el estilo artístico.
	 * 
	 * @return
	 */
	public EstiloArtistico getEstiloArtistico() {
		return estiloArtistico;
	}

	/**
	 * Devuelve la fecha de ingreso de una obra.
	 * 
	 * @return
	 */
	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * Devuelve el autor de una obra.
	 * 
	 * @return autor.
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * Modifica el autor comprobando si es un nombre válido, en caso de no
	 * serlo, lanza una excepción.
	 * 
	 * @param autor
	 * @throws AutorNoValidoException
	 */
	public void setAutor(String autor) throws AutorNoValidoException {
		if (!esValido(autor))
			throw new AutorNoValidoException("El autor no es v\u00e1lido");
		this.autor = autor;
	}

	/**
	 * Devuelve el lugar al que perteneció o donde se encontró una obra.
	 * 
	 * @return localización.
	 */
	public String getLocalizacion() {
		return localizacion;
	}

	/**
	 * Modifica el lugar al que perteneció o donde se encontró una obra.
	 * 
	 * @param localizacion
	 */
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	/**
	 * Devuelve el código de la obra.
	 * 
	 * @return
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Devuelve la persona o entidad que donó o a la que se compró una obra.
	 * 
	 * @return cadena.
	 */
	public String getPersonaEntidad() {
		return personaEntidad;
	}

	/**
	 * Modifica la persona o entidad a la que se compró o donó una obra.
	 * 
	 * @param personaEntidad
	 */
	public void setPersonaEntidad(String personaEntidad) {
		this.personaEntidad = personaEntidad;
	}

	/**
	 * Devuelve la sala.
	 * @return sala.
	 */
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
	
	/**
	 * Devuelve el coste de exposición.
	 * @return decimal.
	 */
	public double getCosteExposicion() {
		return costeExposicion;
	}
	
	/**
	 * Modifica el coste de esposición de la obra.
	 * @param costeExposicion
	 */
	protected void setCosteExposicion(double costeExposicion) {
		this.costeExposicion = costeExposicion;
	}
	
	/**
	 * Devuelve el coste de restauración de la obra.
	 * @return
	 */
	public double getCosteRestauracion() {
		return costeRestauracion;
	}
	
	/**
	 * Modifica el coste de restauración de la obra.
	 * 
	 * @param costeRestauracion
	 */
	protected void setCosteRestauracion(double costeRestauracion) {
		this.costeRestauracion = costeRestauracion;
	}
	
	/**
	 * Devuelve la fama de la obra.
	 * @return
	 */
	public double getFama() {
		return fama;
	}

	/**
	 * Modifica la fama de la obra.
	 * @param fama
	 */
	public void setFama(double fama) {
		this.fama = fama;
	}
	
	/**
	 * Devuelve el valor de la obra.
	 * @return
	 */
	public double getValor() {
		return valor;
	}
	
	/**
	 * Modifica el valor de la obra.
	 * @param valor
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	/**
	 * Devuelve el alto de la obra.
	 * @return
	 */
	public double getAlto() {
		return alto;
	}
	
	/**
	 * Modifica la altura de la obra.
	 * @param alto
	 * @throws DimensionNoValidaException
	 */
	public void setAlto(double alto) throws DimensionNoValidaException {
		if (alto <= 0)
			throw new DimensionNoValidaException("La altura no es válida");
		this.alto = alto;
	}

	/**
	 * Devuelve el ancho de la obra.
	 * @return
	 */
	public double getAncho() {
		return ancho;
	}
	
	/**
	 * Modifica el ancho de la obra.
	 * @param ancho
	 * @throws DimensionNoValidaException
	 */
	public void setAncho(double ancho) throws DimensionNoValidaException {
		if (ancho <= 0)
			throw new DimensionNoValidaException("El ancho no es válido");
		this.ancho = ancho;
	}

	/**
	 * Devuelve si la obra ha sido donada o no.
	 * @return
	 */
	public boolean isDonada() {
		return this.donada;
	}
	
	/**
	 * Modifica el campo "donada" de la obra.
	 * @param donada
	 */
	public void setDonada(boolean donada) {
		this.donada = donada;
	}

	/**
	 * Indica si la obra ha sido restaurada.
	 * 
	 * @return true o false
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

}
