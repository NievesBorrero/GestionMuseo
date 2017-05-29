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
import gestionMuseo.excepciones.FechaFinException;
import gestionMuseo.excepciones.FechaPasadaException;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.ObraExpuestaException;
import gestionMuseo.excepciones.ObraNoDaniadaException;
import gestionMuseo.excepciones.ObraNoExisteException;
import gestionMuseo.excepciones.ObraNoExpuestaException;
import gestionMuseo.excepciones.SinMaterialException;
import gestionMuseo.excepciones.SinSoporteException;
import gestionMuseo.excepciones.SinTecnicaException;
import gestionMuseo.excepciones.SinTipoEsculturaException;
import gestionMuseo.excepciones.SinTipoGrabadoException;
import gestionMuseo.excepciones.YaHayExposicionException;
import gestionMuseo.jerarquia.ObraDeArte;

/**
 * Clase que gestiona un museo.
 * 
 * @author Nieves María Borrero Barea.
 *
 */

public class GestionMuseo implements Serializable, Presupuestable {
	private static final long serialVersionUID = 1L;
	private Fondos museo = new Fondos(); // Fondos que hay en el museo.
	private double presupuesto = 1000; // dinero de que dispone el museo.
	private String nombreExposicion;
	private String descripcionExposicion;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private double costeExposicion;
	private double precioExposicion;
	private boolean organizada = false;
	private double gastos = 0.0;
	private double ingresos = 0.0;
	private boolean modificado;
	private int entradas;

	public GestionMuseo() {// BORRARRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR

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
	 * @throws EstiloNoValidoException
	 * @throws SinSoporteException
	 * @throws SinMaterialException
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void ingresarPintura(String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			Soporte soporte, MaterialPintura material, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinMaterialException, SinSoporteException {

		museo.aniadirPintura(titulo, autor, localizacion, estiloArtistico,
				donada, personaEntidad, fama, valor, soporte, material, alto,
				ancho);
	}

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
	 * @throws EstiloNoValidoException
	 * @throws SinTipoEsculturaException
	 * @throws SinMaterialException
	 */
	public void ingresarEscultura(String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TipoEscultura tipoEscultura, MaterialEscultura materialEscultura,
			double ancho, double alto, double profundidad)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinMaterialException, SinTipoEsculturaException {

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
	 * @throws EstiloNoValidoException
	 * @throws SinTipoGrabadoException
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void ingresarGrabado(String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TipoDeGrabado tipoDeGrabado, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinTipoGrabadoException {

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
	 * @throws EstiloNoValidoException
	 * @throws SinTecnicaException
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void ingresarDibujo(String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TecnicaDeDibujo tecnica, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinTecnicaException {

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

	public void ordenarPorTitulo() {
		museo.ordenarPorTitulo();
	}

	/**
	 * Ordena por nombre
	 */
	public void ordenarPorcosteRestauracion() {
		museo.ordenarPorcosteRestauracion();
	}

	/**
	 * Elimina una obra del museo a partir de su código.
	 * 
	 * @param codigo
	 * @throws ObraNoExisteException
	 * @throws NoHayFondosException
	 */
	public void eliminarGrabado(int codigo) throws ObraNoExisteException {

		museo.eliminarGrabado(codigo);
	}

	public void eliminarPintura(int codigo) throws ObraNoExisteException {
		museo.eliminarPintura(codigo);
	}

	public void eliminarEscultura(int codigo) throws ObraNoExisteException {
		museo.eliminarEscultura(codigo);
	}

	public void eliminarDibujo(int codigo) throws ObraNoExisteException {
		museo.eliminarDibujo(codigo);
	}

	// /**
	// * Elimina una obra del museo.
	// * @param titulo
	// */
	// public void eliminar(String titulo){
	// museo.eliminar(titulo);
	// }

	// public int contarPinturas() throws NoHayFondosException {
	// return museo.contarPinturas();
	// }
	//
	// public int contarDibujos() throws NoHayFondosException {
	// return museo.contarDibujos();
	// }
	//
	// public int contarEsculturas() throws NoHayFondosException {
	// return museo.contarEsculturas();
	// }
	//
	// public int contarGrabados() throws NoHayFondosException {
	// return museo.contarGrabados();
	// }

	/**
	 * Devuelve una lista iterable de las obras del museo.
	 * 
	 * @return ListIterator
	 * @throws NoHayFondosException
	 */
	public ListIterator<ObraDeArte> getList() throws NoHayFondosException {
		if (museo.size() == 0)
			throw new NoHayFondosException("No hay fondos en el museo");
		return museo.listIterator();
	}

	/**
	 * Devuelve una lista iterable de las pinturas del museo.
	 * 
	 * @return ListIterator
	 * @throws NoHayFondosException
	 */
	public ListIterator<ObraDeArte> getListPintura()
			throws NoHayFondosException {
		if (museo.size() == 0)
			throw new NoHayFondosException("No hay fondos en el museo");
		return museo.listPinturas();
	}

	/**
	 * Devuelve una lista iterable de las esculturas del museo.
	 * 
	 * @return ListIterator
	 * @throws NoHayFondosException
	 */
	public ListIterator<ObraDeArte> getListEscultura()
			throws NoHayFondosException {
		if (museo.size() == 0)
			throw new NoHayFondosException("No hay fondos en el museo");
		return museo.listEsculturas();
	}

	/**
	 * Devuelve una lista iterable de los dibujos del museo.
	 * 
	 * @return ListIterator
	 * @throws NoHayFondosException
	 */
	public ListIterator<ObraDeArte> getListDibujo() throws NoHayFondosException {
		if (museo.size() == 0)
			throw new NoHayFondosException("No hay fondos en el museo");
		return museo.listDibujos();
	}

	/**
	 * Devuelve una lista iterable de los dibujos del museo.
	 * 
	 * @return ListIterator
	 * @throws NoHayFondosException
	 */
	public ListIterator<ObraDeArte> getListGrabado()
			throws NoHayFondosException {
		if (museo.size() == 0)
			throw new NoHayFondosException("No hay fondos en el museo");
		return museo.listGrabados();
	}

	public ListIterator<ObraDeArte> listExpuestas() {
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

	public ObraDeArte devolverPorCodigo(int cod) {
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
	 * Devuelve la posición de una obra en el museo.
	 * 
	 * @param obra
	 * @return
	 */
	public int indexOf(ObraDeArte obra) {
		return museo.indexOf(obra);
	}

	/**
	 * Permite modificar los datos de una pintura del museo.
	 * 
	 * @param indice
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
	 * @throws EstiloNoValidoException
	 * @throws ObraNoExisteException
	 * @throws SinSoporteException 
	 * @throws SinMaterialException 
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void modificarPintura(int indice, String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			Soporte soporte, MaterialPintura material, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			ObraNoExisteException, SinMaterialException, SinSoporteException {

		museo.modificarPintura(indice, titulo, autor, localizacion,
				estiloArtistico, donada, personaEntidad, fama, valor, soporte,
				material, alto, ancho);

	}

	/**
	 * Permite modificar los datos de una escultura del museo.
	 * 
	 * @param indice
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
	 * @throws EstiloNoValidoException
	 * @throws SinMaterialException 
	 * @throws SinTipoEsculturaException 
	 */
	public void modificarEscultura(int indice, String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TipoEscultura tipoEscultura, MaterialEscultura materialEscultura,
			double ancho, double alto, double profundidad)
			throws AutorNoValidoException,
			EstiloNoValidoException, SinTipoEsculturaException, SinMaterialException {

		museo.modificarEscultura(indice, titulo, autor, localizacion,
				estiloArtistico, donada, personaEntidad, fama, valor,
				tipoEscultura, materialEscultura, ancho, alto, profundidad);
	}

	/**
	 * Permite modificar los datos de un grabado del museo.
	 * 
	 * @param indice
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
	 * @throws EstiloNoValidoException
	 * @throws SinTipoGrabadoException 
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void modificarGrabado(int indice, String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TipoDeGrabado tipoDeGrabado, double alto, double ancho)
			throws AutorNoValidoException,
			EstiloNoValidoException, SinTipoGrabadoException {
		museo.modificarGrabado(indice, titulo, autor, localizacion,
				estiloArtistico, donada, personaEntidad, fama, valor,
				tipoDeGrabado, alto, ancho);
	}

	/**
	 * Permite modificar los datos de un dibujo del museo
	 * 
	 * @param indice
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
	 * @throws EstiloNoValidoException
	 * @throws SinTecnicaException 
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void modificarDibujo(int indice, String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TecnicaDeDibujo tecnica, double alto, double ancho)
			throws AutorNoValidoException,
			EstiloNoValidoException, SinTecnicaException {

		museo.modificarDibujo(indice, titulo, autor, localizacion,
				estiloArtistico, donada, personaEntidad, fama, valor, tecnica,
				alto, ancho);
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
	 * Devuelve los ingresos y gastos de las obras expuestas en un string.
	 * 
	 * @return cadena
	 */
	public String imprimirPresupuestoExpuestas() {
		ObraDeArte obra;
		ListIterator<ObraDeArte> it = museo.listIterator();
		StringBuilder expuestas = new StringBuilder(
				"Exposicion:\nObra\tGastos\tIngresos");

		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra.isExpuesta()) {
				expuestas.append("\n" + obra.getTitulo() + "\t-"
						+ obra.getCosteExposicion() + "\t+" + obra.getFama());

			}
		}
		expuestas.append("\nTOTAL:\t" + "-" + Math.rint(calcularGasto() * 100)
				/ 100 + "\t" + "+" + Math.rint(calcularIngreso() * 1000) / 1000
				+ "\n");
		return expuestas.toString();
	}

	/**
	 * Devuelve el gasto de restauración de las obras que han sido restauradas
	 * en una cadena.
	 * 
	 * @return cadena
	 */
	public String imprimirPresupuestoRestauradas() {
		ObraDeArte obra;
		ListIterator<ObraDeArte> it = museo.listIterator();
		StringBuilder restauradas = new StringBuilder(
				"Restauracion:\nObra\tprecio restauracion");
		double total = 0;

		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra.isRestaurada()) {
				restauradas.append("\n" + obra.getTitulo() + "\t-"
						+ obra.getCosteRestauracion());
				total += obra.getCosteRestauracion();

			}
		}
		restauradas
				.append("\nTOTAL:\t" + Math.rint(total * 1000) / 1000 + "\n");

		return restauradas.toString();
	}

	/**
	 * Añade el gasto de la sala a los gastos de la exposición y devulve una
	 * cadena con el precio de mantenimiento.
	 * 
	 * @return cadena
	 */
	public String calcularGastoSalas() {
		ObraDeArte obra;
		Sala sala;
		int sala1 = 0;
		int sala2 = 0;
		int sala3 = 0;
		double total = 0;
		ListIterator<ObraDeArte> it = museo.listIterator();
		StringBuilder salas = new StringBuilder("Salas:\nSala\tGasto");
		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra.isExpuesta()) {
				sala = obra.getSala();
				if (sala == Sala.SALA1)
					sala1++;
				else if (sala == Sala.SALA2)
					sala2++;
				else
					sala3++;
			}

		}

		if (sala1 > 0) {
			salas.append("\nSala1\t" + Sala.SALA1.getGasto());
			total += Sala.SALA1.getGasto();
			setGastos(getGastos() + Sala.SALA1.getGasto());
		}
		if (sala2 > 0) {
			salas.append("\nSala2\t" + Sala.SALA2.getGasto());
			total += Sala.SALA2.getGasto();
			setGastos(getGastos() + Sala.SALA2.getGasto());
		}
		if (sala3 > 0) {
			salas.append("\nSala3\t" + Sala.SALA3.getGasto());
			total += Sala.SALA3.getGasto();
			setGastos(getGastos() + Sala.SALA3.getGasto());
		}

		salas.append("\nTOTAL:\t" + total + "\n");

		return salas.toString();
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

	/**
	 * Calcula el ingreso de la exposición
	 *
	 * @return
	 */
	public double calcularIngreso() {
		ListIterator<ObraDeArte> it = museo.listIterator();
		ObraDeArte obra;
		double fama = 0.0;

		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra.isExpuesta()) {
				fama += obra.getFama();
			}
		}
		setIngresos(getIngresos() + fama);
		return fama;
	}

	/**
	 * Calcula el gasto de la exposición
	 *
	 * @return
	 */
	public double calcularGasto() {
		ListIterator<ObraDeArte> it = museo.listIterator();
		ObraDeArte obra;
		double gastoObras = 0.0;

		while (it.hasNext()) {
			if (it.next().isExpuesta()) {
				obra = (ObraDeArte) it.next();
				gastoObras += obra.getCosteExposicion();
			}
		}
		setGastos(getGastos() + gastoObras);
		return gastoObras;
	}

	/**
	 * Calcula el presupuesto después de una exposición.
	 */
	@Override
	public double calcularPresupuesto(double gasto, double ingreso) {
		return (presupuesto + (ingreso * getEntradas() * calcularDiasExposicion()))
				- gasto;
	}

	public long calcularDiasExposicion() {
		return ChronoUnit.DAYS.between(fechaFin, fechaInicio);
	}

	// public String[] generarOpcionesMenu() {
	// return museo.generarOpcionesMenu();
	// }

	public double getPresupuesto() {
		return this.presupuesto;
	}

	public boolean isModificado() {
		return modificado;
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
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
			if (obra.isRestaurada()){
				obra.setRestaurada(false);
			}
			if (obra.isExpuesta()) {
				obra.recogerObra();
			}
		}
		setPresupuesto(calcularPresupuesto(getGastos(), getIngresos()));
		setOrganizada(false);
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
			System.out.println("aqui en el comprobar arriba dice que el estado es: "+obra.getEstadoConservacion());
			if (obra.getEstadoConservacion() != EstadoDeConservacion.BUENO) {
				daniadas++;
			}
			
		}
		if (daniadas > 0)
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