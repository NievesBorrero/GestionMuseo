package gestionMuseo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ListIterator;

import gestionMuseo.enumeraciones.EstadoDeConservacion;
import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.Sala;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.enumeraciones.TipoDeGrabado;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.EstadoNoAdecuadoException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.ExposicionNoOrganizadaException;
import gestionMuseo.excepciones.FechaFinException;
import gestionMuseo.excepciones.FechaPasadaException;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.ObraExpuestaException;
import gestionMuseo.excepciones.ObraNoDaniadaException;
import gestionMuseo.excepciones.ObraNoExisteException;
import gestionMuseo.excepciones.ObraNoExpuestaException;
import gestionMuseo.excepciones.TituloNoValidoException;
import gestionMuseo.excepciones.YaHayExposicionException;
import gestionMuseo.jerarquia.Dibujo;
import gestionMuseo.jerarquia.Escultura;
import gestionMuseo.jerarquia.Grabado;
import gestionMuseo.jerarquia.ObraDeArte;
import gestionMuseo.jerarquia.Pintura;

/**
 * 
 * @author Nieves María Borrero Barea.
 *
 */

public class Exposicion implements Presupuestable, Serializable{
	private Fondos museo = Fondos.getInstance(); // Fondos que hay en el museo.
	private double presupuesto = 1000; // Presupuesto que hay en el museo.
	private String nombreExposicion;
	private String descripcionExposicion;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private double costeExposicion;
	private double precioExposicion;
	private boolean organizada = false;
	private double gastos = 0.0;
	private double ingresos = 0.0;
	private boolean modificada; // MIRAR MODIFICABLE
	private int entradas;
	

	public Exposicion() {

	}
	

	/**
	 * Añade una pintura al museo.
	 * 
	 * @param titulo
	 * @param autor
	 * @param localizacion
	 * @param estiloArtistico
	 * @param donada
	 * @param personaEntidad
	 * @param fama
	 * @param valor
	 * @param soporte
	 * @param material
	 * @param alto
	 * @param ancho
	 * @throws AutorNoValidoException
	 * @throws LocalizacionNoValidaException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void ingresarPintura(String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			Soporte soporte, MaterialPintura material, double alto, double ancho)
			throws AutorNoValidoException, TituloNoValidoException,
			EstiloNoValidoException {

		museo.aniadirPintura(titulo, autor, localizacion, estiloArtistico,
				donada, personaEntidad, fama, valor, soporte, material, alto,
				ancho);
	}

	/**
	 * 
	 * @param titulo
	 * @param autor
	 * @param localizacion
	 * @param estiloArtistico
	 * @param donada
	 * @param personaEntidad
	 * @param fama
	 * @param valor
	 * @param tipoEscultura
	 * @param materialEscultura
	 * @param ancho
	 * @param alto
	 * @param profundidad
	 * @throws AutorNoValidoException
	 * @throws LocalizacionNoValidaException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	/**
	 * Añade una escultura al museo.
	 * 
	 * @param titulo
	 * @param autor
	 * @param localizacion
	 * @param estiloArtistico
	 * @param donada
	 * @param personaEntidad
	 * @param fama
	 * @param valor
	 * @param tipoEscultura
	 * @param materialEscultura
	 * @param ancho
	 * @param alto
	 * @param profundidad
	 * @throws AutorNoValidoException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException
	 */
	public void ingresarEscultura(String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TipoEscultura tipoEscultura, MaterialEscultura materialEscultura,
			double ancho, double alto, double profundidad)
			throws AutorNoValidoException, TituloNoValidoException,
			EstiloNoValidoException {

		museo.aniadirEscultura(titulo, autor, localizacion, estiloArtistico,
				donada, personaEntidad, fama, valor, tipoEscultura,
				materialEscultura, ancho, alto, profundidad);
	}

	/**
	 * Añade un grabado al museo.
	 * 
	 * @param titulo
	 * @param autor
	 * @param localizacion
	 * @param estiloArtistico
	 * @param donada
	 * @param personaEntidad
	 * @param fama
	 * @param valor
	 * @param tipoDeGrabado
	 * @param alto
	 * @param ancho
	 * @throws AutorNoValidoException
	 * @throws LocalizacionNoValidaException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void ingresarGrabado(String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TipoDeGrabado tipoDeGrabado, double alto, double ancho)
			throws AutorNoValidoException, TituloNoValidoException,
			EstiloNoValidoException {

		museo.aniadirGrabado(titulo, autor, localizacion, estiloArtistico,
				donada, personaEntidad, fama, valor, tipoDeGrabado, alto, ancho);

	}

	/**
	 * Añade un dibujo al museo.
	 * 
	 * @param titulo
	 * @param autor
	 * @param localizacion
	 * @param estiloArtistico
	 * @param donada
	 * @param personaEntidad
	 * @param fama
	 * @param valor
	 * @param tecnica
	 * @param alto
	 * @param ancho
	 * @throws AutorNoValidoException
	 * @throws LocalizacionNoValidaException
	 * @throws TituloNoValidoException
	 * @throws EstiloNoValidoException
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void ingresarDibujo(String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TecnicaDeDibujo tecnica, double alto, double ancho)
			throws AutorNoValidoException, TituloNoValidoException,
			EstiloNoValidoException {

		museo.aniadirDibujo(titulo, autor, localizacion, estiloArtistico,
				donada, personaEntidad, fama, valor, tecnica, alto, ancho);
	}
	
	/**
	 * Devuelve la obra indicada.
	 * 
	 * @param indice
	 * @return
	 * @throws NoHayFondosException
	 */

	public ObraDeArte getObra(int indice) throws NoHayFondosException {
		return museo.getObra(indice);
	}

	/**
	 * Devuelve el tamaño del arrayList museo.
	 * 
	 * @return
	 */
	public int size() {
		return museo.size();
	}

	/**
	 * Elimina una obra del museo a partir de su código.
	 * 
	 * @param codigo
	 * @throws ObraNoExisteException
	 * @throws NoHayFondosException
	 */
	public void eliminar(int codigo){

		museo.eliminar(codigo);
	}

	public int contarPinturas() throws NoHayFondosException {
		return museo.contarPinturas();
	}

	public int contarDibujos() throws NoHayFondosException {
		return museo.contarDibujos();
	}

	public int contarEsculturas() throws NoHayFondosException {
		return museo.contarEsculturas();
	}

	public int contarGrabados() throws NoHayFondosException {
		return museo.contarGrabados();
	}
	
	/**
	 * Devuelve una lista iterable de las obras del museo.
	 * 
	 * @return ListIterator
	 * @throws NoHayFondosException 
	 */
	public ListIterator<ObraDeArte> getList() throws NoHayFondosException{
		if (museo.size()==0)
			throw new NoHayFondosException("No hay fondos en el museo");
		return museo.listIterator();
	}

	/**
	 * Devuelve una lista iterable de las pinturas del museo.
	 * 
	 * @return ListIterator
	 * @throws NoHayFondosException 
	 */
	public ListIterator<ObraDeArte> getListPintura() throws NoHayFondosException {
		if (museo.size()==0)
			throw new NoHayFondosException("No hay fondos en el museo");
		return museo.listPinturas();
	}

	/**
	 * Devuelve una lista iterable de las esculturas del museo.
	 * 
	 * @return ListIterator
	 * @throws NoHayFondosException 
	 */
	public ListIterator<ObraDeArte> gestListEscultura() throws NoHayFondosException {
		if (museo.size()==0)
			throw new NoHayFondosException("No hay fondos en el museo");
		return museo.listEsculturas();
	}

	/**
	 * Devuelve una lista iterable de los dibujos del museo.
	 * 
	 * @return ListIterator
	 * @throws NoHayFondosException 
	 */
	public ListIterator<ObraDeArte> gestListDibujo() throws NoHayFondosException {
		if (museo.size()==0)
			throw new NoHayFondosException("No hay fondos en el museo");
		return museo.listDibujos();
	}

	/**
	 * Devuelve una lista iterable de los dibujos del museo.
	 * 
	 * @return ListIterator
	 * @throws NoHayFondosException 
	 */
	public ListIterator<ObraDeArte> gestListGrabado() throws NoHayFondosException {
		if (museo.size()==0)
			throw new NoHayFondosException("No hay fondos en el museo");
		return museo.listGrabados();
	}
	
	public ListIterator<ObraDeArte> listExpuestas(){
		return museo.listExpuestas();
	}

	/**
	 * Expone una obra en la sala indicada.
	 * 
	 * @param codigo
	 * @param opcionSala
	 * @throws NoHayFondosException
	 * @throws ObraExpuestaException
	 * @throws EstadoNoAdecuadoException
	 * @throws ObraNoExpuestaException
	 */
	void exponerObra(int indice, Sala sala) throws NoHayFondosException,
			ObraExpuestaException, EstadoNoAdecuadoException,
			ObraNoExpuestaException {

		ObraDeArte obra = museo.getObra(indice);
		obra.exponerObra(sala);
		setIngresos(getIngresos() + obra.getFama());
		setGastos(getGastos() + obra.getCosteExposicion());

	}
	
	public ObraDeArte devolverPorCodigo(int cod){
		return museo.devolverPorCodigo(cod);
	}

	/**
	 * Restaura una obra y el precio de restauración se suma a gastos.
	 * 
	 * @param indice
	 * @throws NoHayFondosException
	 * @throws ObraNoDaniadaException
	 */
	public void restaurar(int indice) throws NoHayFondosException,
			ObraNoDaniadaException {

		setGastos(getGastos() + museo.getObra(indice).restaurar());
	}

	/**
	 * Introduce datos a la exposición.
	 * 
	 * @param titulo
	 * @param descripcion
	 * @param inicio
	 * @param fin
	 * @throws YaHayExposicionException
	 * @throws FechaFinException
	 * @throws FechaPasadaException
	 */
	public void organizarExposicion(String titulo, String descripcion,
			LocalDate inicio, LocalDate fin) throws YaHayExposicionException,
			FechaFinException, FechaPasadaException {
		if (isOrganizada() == true)
			throw new YaHayExposicionException(
					"Ya hay una exposicion organizada");
		if (fin.isAfter(inicio))
			throw new FechaFinException(
					"La fecha de fin no puede ser menor que la fecha de inicio");
		if (fin.isAfter(LocalDate.now()) || inicio.isAfter(LocalDate.now()))
			throw new FechaPasadaException("La fecha ya ha pasado");

		setNombreExposicion(titulo);
		setDescripcionExposicion(descripcion);
		setFechaInicio(inicio);
		setFechaFin(fin);
		setOrganizada(true);
	}

	/**
	 * Genera un número de entradas de forma aleatoria.
	 * 
	 * @return
	 */
	public int generarEntradas() {
		setEntradas((int) Math.round(Math.random() * 100));
		return getEntradas();
	}

	/**
	 * Comprueba si hay obras expuestas
	 * 
	 * @return
	 */
	public boolean comprobarSiObrasExpuestas() {
		int i = 0;
		ObraDeArte obra;
		ListIterator<ObraDeArte> it = museo.listIterator();

		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra.isExpuesta()) {
				i++;
			}
		}
		if (i > 0)
			return true;
		return false;
	}

	public int getEntradas() {
		return entradas;
	}

	public void setEntradas(int entradas) {
		this.entradas = entradas;
	}

	public boolean isOrganizada() {
		return organizada;
	}

	public void setOrganizada(boolean organizada) {
		this.organizada = organizada;
	}

	public String getNombreExposicion() {
		return nombreExposicion;
	}

	public double getGastos() {
		return gastos;
	}

	public Fondos getMuseo() {
		return museo;
	}


	public void setMuseo(Fondos museo) {
		this.museo = museo;
	}


	public void setGastos(double gastos) {
		this.gastos = gastos;
	}

	public double getIngresos() {
		return ingresos;
	}

	public void setIngresos(double ingresos) {
		this.ingresos = ingresos;
	}

	public void setNombreExposicion(String nombreExposicion) {
		this.nombreExposicion = nombreExposicion;
	}

	public String getDescripcionExposicion() {
		return descripcionExposicion;
	}

	public void setDescripcionExposicion(String descripcionExposicion) {
		this.descripcionExposicion = descripcionExposicion;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	

//	/**
//	 * Calcula el ingreso de la exposición
//	 * 
//	 * @return
//	 */
	// @Override
	// public double calcularIngreso() {
	// ListIterator<ObraDeArte> it = museo.listIterator();
	// ObraDeArte obra;
	// double fama = 0.0;
	// double valor = 0.0;
	//
	// while (it.hasNext()) {
	// obra = (ObraDeArte) it.next();
	// if (obra.isExpuesta()) {
	// fama += obra.getFama();
	// }
	// }
	// return (fama + valor) * calcularDiasExposicion();
	// }

	// /**
	// * Calcula el gasto de la exposición
	// *
	// * @return
	// */
	// @Override
	// public double calcularGasto() {
	// ListIterator<ObraDeArte> it = museo.listIterator();
	// ObraDeArte obra;
	// double precioSala1 = 0.0;
	// double precioSala2 = 0.0;
	// double precioSala3 = 0.0;
	// double gastoObras = 0.0;
	//
	// while (it.hasNext()) {
	// if (it.next().isExpuesta()) {
	// obra = (ObraDeArte) it.next();
	// if (obra.getSala() == Sala.SALA1)
	// precioSala1 = Sala.SALA1.getGasto();
	// if (obra.getSala() == Sala.SALA2)
	// precioSala2 = Sala.SALA2.getGasto();
	// if (obra.getSala() == Sala.SALA3)
	// precioSala3 = Sala.SALA3.getGasto();
	// gastoObras += obra.getCosteExposicion();
	// }
	// }
	// return (precioSala1 + precioSala2 + precioSala3 + gastoObras) *
	// calcularDiasExposicion();
	// }
	/**
	 * Calcula el presupuesto después de una exposición.
	 */
	@Override
	public double calcularPresupuesto(double gasto, double ingreso) {
		this.presupuesto = (presupuesto + (ingreso*getEntradas()*calcularDiasExposicion()))-gasto;
		
		return getPresupuesto();
	}

	public long calcularDiasExposicion() {
		return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
	}

	public String[] generarOpcionesMenu() {
		return museo.generarOpcionesMenu();
	}

	public double getPresupuesto() {
		return this.presupuesto;
	}

	public boolean isModificada() {
		return modificada;
	}

	public void setModificada(boolean modificada) {
		this.modificada = modificada;
	}

	/**
	 * Recoge las obras y calcula el presupuesto resultante de la exposición.
	 * 
	 * @throws ObraNoExpuestaException
	 */
	public void clausurarExposicion() throws ObraNoExpuestaException {
		ListIterator<ObraDeArte> it = museo.listIterator();
		ObraDeArte obra;

		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra.isExpuesta()) {
				obra.recogerObra();
			}
		}
		calcularPresupuesto(getGastos(), getIngresos());
	}

	/**
	 * Comprueba si hay obras dañadas.
	 * 
	 * @return
	 */
	public boolean comprobarObrasDaniadas() {
		int daniadas = 0;
		ObraDeArte obra;
		ListIterator<ObraDeArte> it = museo.listIterator();
		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra.getEstadoConservacion() != EstadoDeConservacion.BUENO) {
				daniadas++;
			}
		}
		if (daniadas < 0)
			return true;
		return false;
	}

	/**
	 * Muestra informaaci�n sobre la exosici�n.
	 */
	@Override
	public String toString() {

		return "Exposicion [museo=" + museo + ", presupuesto="
				+ getPresupuesto() + ", nombreExposicion=" + nombreExposicion
				+ ", descripcionExposicion=" + descripcionExposicion
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", costeExposicion=" + costeExposicion
				+ ", precioExposicion=" + precioExposicion + "]";

	}


}
