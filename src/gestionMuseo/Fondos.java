package gestionMuseo;

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.enumeraciones.TipoDeGrabado;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.DimensionNoValidaException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.ObraNoExisteException;
import gestionMuseo.excepciones.PeriodoNoValidoException;
import gestionMuseo.excepciones.SinMaterialException;
import gestionMuseo.excepciones.SinSoporteException;
import gestionMuseo.excepciones.SinTecnicaException;
import gestionMuseo.excepciones.SinTipoEsculturaException;
import gestionMuseo.excepciones.SinTipoGrabadoException;
import gestionMuseo.jerarquia.Dibujo;
import gestionMuseo.jerarquia.Escultura;
import gestionMuseo.jerarquia.Grabado;
import gestionMuseo.jerarquia.ObraDeArte;
import gestionMuseo.jerarquia.Pintura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

/**
 * Clase envoltorio del arrayList de obras de arte.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */
public class Fondos implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<ObraDeArte> fondos;
	private int ultimoCodigo=0;

	public Fondos() {

		fondos = new ArrayList<ObraDeArte>();
	}

	/**
	 * Añade una escultura al ArrayList de fondos.
	 * 
	 * @param nombre
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
	 * @throws EstiloNoValidoException
	 * @throws SinTipoEsculturaException
	 * @throws SinMaterialException
	 * @throws DimensionNoValidaException
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void aniadirEscultura(String nombre, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TipoEscultura tipoEscultura, MaterialEscultura materialEscultura,
			double ancho, double alto, double profundidad)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinMaterialException, SinTipoEsculturaException,
			DimensionNoValidaException{

		Escultura escultura = new Escultura(nombre, autor, localizacion,
				estiloArtistico, donada, personaEntidad, fama, valor,
				tipoEscultura, materialEscultura, alto, ancho, profundidad);

		fondos.add(escultura);
		
		comprobarUltimoCodigo(escultura);
	}
	
	/**
	 * Añade una pintura al ArrayList de fondos.
	 * 
	 * @param nombre
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
	 * @throws DimensionNoValidaException
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void aniadirPintura(String titulo, String autor,
			String localizacion,
			EstiloArtistico estiloArtistico, boolean donada,
			String personaEntidad, double fama, double valor, Soporte soporte,
			MaterialPintura material, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinMaterialException, SinSoporteException,
			DimensionNoValidaException {

		Pintura pintura = new Pintura(titulo, autor, localizacion,
				estiloArtistico, donada, personaEntidad, fama, valor, soporte,
				material, alto, ancho);
		fondos.add(pintura);
		comprobarUltimoCodigo(pintura);
	}


	/**
	 * Añade un grabado al ArrayList de fondos.
	 * 
	 * @param nombre
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
	 * @throws DimensionNoValidaException
	 * @throws PeriodoNoValidoException
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */

	public void aniadirGrabado(String nombre, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TipoDeGrabado tipoDeGrabado, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinTipoGrabadoException, DimensionNoValidaException {
		Grabado grabado = new Grabado(nombre, autor, localizacion,
				estiloArtistico, donada, personaEntidad, fama, valor,
				tipoDeGrabado, alto, ancho);
		fondos.add(grabado);
		comprobarUltimoCodigo(grabado);
	}

	/**
	 * Añade un dibujo al ArrayList de fondos.
	 * 
	 * @param nombre
	 * @param autor
	 * @param localizacion
	 * @param estiloArtistico
	 * @param donada
	 * @param personaEntidad
	 * @param fama
	 * @param valor
	 * @param alto
	 * @param ancho
	 * @param tecnica
	 * @throws AutorNoValidoException
	 * @throws LocalizacionNoValidaException
	 * @throws EstiloNoValidoException
	 * @throws SinTecnicaException
	 * @throws DimensionNoValidaException
	 * @throws PeriodoNoValidoException
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void aniadirDibujo(String nombre, String autor, String localizacion,
			EstiloArtistico estiloArtistico, boolean donada,
			String personaEntidad, double fama, double valor,
			TecnicaDeDibujo tecnica, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinTecnicaException, DimensionNoValidaException {

		Dibujo dibujo = new Dibujo(nombre, autor, localizacion,
				estiloArtistico, donada, personaEntidad, fama, valor, tecnica,
				alto, ancho);
		fondos.add(dibujo);
		comprobarUltimoCodigo(dibujo);
	}
	

	/**
	 * Comprueba el último código de las obras almacenadas en el arrayList.
	 * @param obra
	 */
	protected void comprobarUltimoCodigo(ObraDeArte obra) {
		int index = fondos.indexOf(obra);
		System.out.println("ultimo codigo antes de anadir: "+getUltimoCodigo());
		if (fondos.get(index).getCodigo()<getUltimoCodigo())

			fondos.get(index).setCodigo(getUltimoCodigo()+1);
		setUltimoCodigo(fondos.get(index).getCodigo());
		System.out.println("ultimo codigo despues de anadir: "+getUltimoCodigo());
	}
	
	/**
	 * Devuelve el último código de las obras almacenadas en el arrayList.
	 * @return entero
	 */
	public int getUltimoCodigo() {
		return ultimoCodigo;
	}

	/**
	 * Modifica el último código de las obras almacenadas en el arrayList.
	 * @param ultimoCodigo
	 */
	public void setUltimoCodigo(int ultimoCodigo) {
		this.ultimoCodigo = ultimoCodigo;
	}

	/**
	 * Elimina un grabado por código
	 * 
	 * @param codigo
	 * @throws ObraNoExisteException
	 * @throws NoHayFondosException
	 */
	public void eliminarGrabado(int codigo) throws ObraNoExisteException {
		if (!fondos.remove(new Grabado(codigo)))
			throw new ObraNoExisteException("Error, la obra no existe");
	}
	
	/**
	 * Elimina una pintura por código
	 * @param codigo
	 * @throws ObraNoExisteException
	 */
	public void eliminarPintura(int codigo) throws ObraNoExisteException {
		if (!fondos.remove(new Pintura(codigo)))
			throw new ObraNoExisteException("Error, la obra no existe");
	}
	
	/**
	 * Elimina un dibujo por código.
	 * @param codigo
	 * @throws ObraNoExisteException
	 */
	public void eliminarDibujo(int codigo) throws ObraNoExisteException {
		if (!fondos.remove(new Dibujo(codigo)))
			throw new ObraNoExisteException("Error, la obra no existe");
	}
	
	/**
	 * Elimina una escultura por código
	 * @param codigo
	 * @throws ObraNoExisteException
	 */
	public void eliminarEscultura(int codigo) throws ObraNoExisteException {
		if (!fondos.remove(new Escultura(codigo)))
			throw new ObraNoExisteException("Error, la obra no existe");
	}

	/**
	 * Elimina una obra de arte por indice
	 * 
	 * @param indice
	 * @throws ObraNoExisteException
	 * @throws NoHayFondosException
	 */
	public void eliminar(int indice) throws ObraNoExisteException,
			NoHayFondosException {
		if (fondos.isEmpty())
			throw new NoHayFondosException(
					"Imposible eliminar, no hay fondos en el museo");
		if (fondos.remove(indice) == null)
			throw new ObraNoExisteException(
					"Imposible eliminar, la obra no existe");
	}


	/**
	 * Ordena por Titulo
	 */
	public void ordenarPorTitulo() {
		Collections.sort(fondos, new CompareTitulo());
	}
	/**
	 * Ordena por Codigo
	 */
	public void ordenarPorCodigo() {
		Collections.sort(fondos, new CompareCodigo());
	}

	/**
	 * Ordena por nombre
	 */
	public void ordenarPorcosteRestauracion() {
		Collections.sort(fondos, new CompareCosteRestauracion());
	}

	/**
	 * 
	 * @param cod
	 * @return
	 * @throws ObraNoExisteException
	 * @throws NoHayFondosException
	 */
	public ObraDeArte devolverPorCodigo(int cod) throws ObraNoExisteException {

		ObraDeArte obra;
		ListIterator<ObraDeArte> it = fondos.listIterator();

		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra.getCodigo() == cod) {
				return obra;
			}
		}
		throw new ObraNoExisteException("La obra que buscas no existe");
	}

	/**
	 * Devuelve una obra por título.
	 * @param cod
	 * @return obra
	 * @throws ObraNoExisteException
	 * @throws NoHayFondosException
	 */
	public ObraDeArte devolverPorTitulo(String titulo)
			throws ObraNoExisteException {

		ObraDeArte obra;
		ListIterator<ObraDeArte> it = fondos.listIterator();

		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra.getTitulo().equalsIgnoreCase(titulo)) {
				return obra;
			}
		}
		throw new ObraNoExisteException("La obra que buscas no existe");
	}

	/**
	 * Devuelve el tamaño del ArrayList de fondos
	 * 
	 * @return int
	 */
	public int size() {
		return fondos.size();
	}

	/**
	 * Devuelve una obra de arte.
	 * 
	 * @param index
	 * @return ObraDeArte
	 * @throws NoHayFondosException
	 */
	public ObraDeArte getObra(int index) throws NoHayFondosException {
		if (fondos.isEmpty())
			throw new NoHayFondosException(
					"Imposible devolver alguna obra, no hay fondos en el museo");
		return fondos.get(index);
	}

	
	/**
	 * Devuelve un lisIterator de con los fondos.
	 * 
	 * @return
	 */
	public ListIterator<ObraDeArte> listIterator() {
		return fondos.listIterator();
	}

	/**
	 * Devuelve un listIterator con las pinturas.
	 * 
	 * @return
	 * @throws NoHayFondosException
	 */
	public ListIterator<ObraDeArte> listPinturas() throws NoHayFondosException {
		ArrayList<ObraDeArte> pinturas = new ArrayList<ObraDeArte>();
		ObraDeArte obra;
		ListIterator<ObraDeArte> it = fondos.listIterator();
		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra instanceof Pintura) {
				pinturas.add((Pintura) obra);
			}
		}
		if (!pinturas.listIterator().hasNext())
			throw new NoHayFondosException("No hay dibujos");

		return pinturas.listIterator();
	}

	/**
	 * Devuelve un listIterator con las esculturas.
	 * 
	 * @return
	 * @throws NoHayFondosException
	 */
	public ListIterator<ObraDeArte> listEsculturas()
			throws NoHayFondosException {
		ArrayList<ObraDeArte> esculturas = new ArrayList<ObraDeArte>();
		ObraDeArte obra;
		ListIterator<ObraDeArte> it = fondos.listIterator();
		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra instanceof Escultura) {
				esculturas.add((Escultura) obra);
			}
		}
		if (!esculturas.listIterator().hasNext())
			throw new NoHayFondosException("No hay esculturas");
		return esculturas.listIterator();
	}

	/**
	 * Devuelve un listIterator con los dibujos.
	 * 
	 * @return
	 * @throws NoHayFondosException
	 */
	public ListIterator<ObraDeArte> listDibujos() throws NoHayFondosException {
		ArrayList<ObraDeArte> dibujos = new ArrayList<ObraDeArte>();
		ObraDeArte obra;
		ListIterator<ObraDeArte> it = fondos.listIterator();
		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra instanceof Dibujo) {
				dibujos.add((Dibujo) obra);
			}
		}
		if (!dibujos.listIterator().hasNext())
			throw new NoHayFondosException("No hay dibujos");
		return dibujos.listIterator();
	}

	/**
	 * Devuelve un listIterator con los grabados.
	 * 
	 * @return
	 * @throws NoHayFondosException
	 */
	public ListIterator<ObraDeArte> listGrabados() throws NoHayFondosException {
		ArrayList<ObraDeArte> grabados = new ArrayList<ObraDeArte>();
		ObraDeArte obra;
		ListIterator<ObraDeArte> it = fondos.listIterator();
		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra instanceof Grabado) {
				grabados.add((Grabado) obra);
			}
		}
		if (!grabados.listIterator().hasNext())
			throw new NoHayFondosException("no hay Grabados");

		return grabados.listIterator();
	}

	/**
	 * Devuelve un lisIterator de con las obras expuestas.
	 * 
	 * @return
	 */
	public ListIterator<ObraDeArte> listExpuestas() {
		ArrayList<ObraDeArte> expuestas = new ArrayList<ObraDeArte>();
		ObraDeArte obra;
		ListIterator<ObraDeArte> it = fondos.listIterator();
		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra.isExpuesta()) {
				expuestas.add((ObraDeArte) obra);
			}
		}
		return expuestas.listIterator();
	}
	
	/**
	 * Devuelve si el arraylist está vacío o no.
	 * @return
	 */
	public boolean isEmpty() {
		return fondos.isEmpty();
	}

	public ArrayList<ObraDeArte> getObras() {
		return fondos;
	}

}
