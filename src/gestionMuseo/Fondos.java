package gestionMuseo;

/**
 * @author Nieves María Borrero Barea.
 */

import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.enumeraciones.TipoDeGrabado;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.ObraNoExisteException;
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

public class Fondos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ObraDeArte> fondos;

	public Fondos() {

		fondos = new ArrayList<ObraDeArte>();
	}

	/**
	 * Devuelve una instancia de fondos siempre que no exista ya una.
	 * 
	 * @return
	 */

//	public static Fondos getInstance() {
//		if (fondos == null)
//			return new Fondos();
//		return null;
//	}

	/**
	 * A�ade una escultura al ArrayList de fondos.
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
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void aniadirEscultura(String nombre, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TipoEscultura tipoEscultura, MaterialEscultura materialEscultura,
			double ancho, double alto, double profundidad)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinMaterialException, SinTipoEsculturaException {

		Escultura escultura = new Escultura(nombre, autor, localizacion,
				estiloArtistico, donada, personaEntidad, fama, valor,
				tipoEscultura, materialEscultura, alto, ancho, profundidad);

		fondos.add(escultura);
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
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void aniadirPintura(String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			Soporte soporte, MaterialPintura material, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinMaterialException, SinSoporteException {

		Pintura pintura = new Pintura(titulo, autor, localizacion,
				estiloArtistico, donada, personaEntidad, fama, valor, soporte,
				material, alto, ancho);
		fondos.add(pintura);
	}

	/**
	 * Devuelve la posición de una obra en el ArrayList.
	 * 
	 * @param obra
	 * @return
	 */
	public int indexOf(ObraDeArte obra) {
		return fondos.indexOf(obra);
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
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */

	public void aniadirGrabado(String nombre, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TipoDeGrabado tipoDeGrabado, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinTipoGrabadoException {
		Grabado grabado = new Grabado(nombre, autor, localizacion,
				estiloArtistico, donada, personaEntidad, fama, valor,
				tipoDeGrabado, alto, ancho);
		fondos.add(grabado);
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
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void aniadirDibujo(String nombre, String autor, String localizacion,
			EstiloArtistico estiloArtistico, boolean donada,
			String personaEntidad, double fama, double valor,
			TecnicaDeDibujo tecnica, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			SinTecnicaException {

		Dibujo dibujo = new Dibujo(nombre, autor, localizacion,
				estiloArtistico, donada, personaEntidad, fama, valor, tecnica,
				alto, ancho);
		fondos.add(dibujo);
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

	public void eliminarPintura(int codigo) throws ObraNoExisteException {
		if (!fondos.remove(new Pintura(codigo)))
			throw new ObraNoExisteException("Error, la obra no existe");
	}

	public void eliminarDibujo(int codigo) throws ObraNoExisteException {
		if (!fondos.remove(new Dibujo(codigo)))
			throw new ObraNoExisteException("Error, la obra no existe");
	}

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
	public void eliminarObra(int indice) throws ObraNoExisteException,
			NoHayFondosException {
		if (fondos.isEmpty())
			throw new NoHayFondosException(
					"Imposible eliminar, no hay fondos en el museo");
		if (fondos.remove(indice) == null)
			throw new ObraNoExisteException(
					"Imposible eliminar, la obra no existe");
	}

	// /**
	// * Elimina por título
	// * @param obra
	// */
	// public void eliminar(String titulo){
	// fondos.remove(new Grabado(titulo));
	// }

	/**
	 * Ordena por Titulo
	 */
	public void ordenarPorTitulo() {
		Collections.sort(fondos, new CompareTitulo());
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
	 * @throws NoHayFondosException
	 */
	public ObraDeArte devolverPorCodigo(int cod) {

		ObraDeArte obra;
		ListIterator<ObraDeArte> it = fondos.listIterator();

		while (it.hasNext()) {
			obra = (ObraDeArte) it.next();
			if (obra.getCodigo() == cod) {
				return obra;
			}
		}
		return null;
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
	 * Permite modificar los datos de una pintura.
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
	 * @throws SinMaterialException 
	 * @throws SinSoporteException 
	 * @throws personaEntidadNoValidaException
	 * @throws ObraYaExisteException
	 */
	public void modificarPintura(int indice, String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			Soporte soporte, MaterialPintura material, double alto, double ancho)
			throws AutorNoValidoException, EstiloNoValidoException,
			ObraNoExisteException, SinMaterialException, SinSoporteException {

		fondos.get(indice).setTitulo(titulo);
		fondos.get(indice).setAutor(autor);
		fondos.get(indice).setAncho(ancho);
		fondos.get(indice).setAlto(alto);
		fondos.get(indice).setEstiloArtistico(estiloArtistico);
		fondos.get(indice).setLocalizacion(localizacion);
		fondos.get(indice).setDonada(donada);
		fondos.get(indice).setPersonaEntidad(personaEntidad);
		fondos.get(indice).setFama(fama);
		fondos.get(indice).setValor(valor);
		((Pintura) fondos.get(indice)).setSoporte(soporte);
		((Pintura) fondos.get(indice)).setMaterial(material);
	}

	/**
	 * Permite modificar los datos de una escultura.
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
	 * @throws SinTipoEsculturaException 
	 * @throws SinMaterialException 
	 */
	public void modificarEscultura(int indice, String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TipoEscultura tipoEscultura, MaterialEscultura materialEscultura,
			double ancho, double alto, double profundidad)
			throws AutorNoValidoException,
			EstiloNoValidoException, SinTipoEsculturaException, SinMaterialException {

		fondos.get(indice).setTitulo(titulo);
		fondos.get(indice).setAutor(autor);
		fondos.get(indice).setAncho(ancho);
		fondos.get(indice).setAlto(alto);
		fondos.get(indice).setEstiloArtistico(estiloArtistico);
		fondos.get(indice).setLocalizacion(localizacion);
		fondos.get(indice).setDonada(donada);
		fondos.get(indice).setPersonaEntidad(personaEntidad);
		fondos.get(indice).setFama(fama);
		fondos.get(indice).setValor(valor);
		((Escultura) fondos.get(indice)).setProfundidad(profundidad);
		((Escultura) fondos.get(indice)).setTipoEscultura(tipoEscultura);
		((Escultura) fondos.get(indice))
				.setMaterialEscultura(materialEscultura);
	}

	/**
	 * Permite modificar los datos de un grabado
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
	 * @throws EstiloNoValidoException
	 * @throws SinTipoGrabadoException 
	 */
	public void modificarGrabado(int indice, String titulo, String autor,
			String localizacion, EstiloArtistico estiloArtistico,
			boolean donada, String personaEntidad, double fama, double valor,
			TipoDeGrabado tipoDeGrabado, double alto, double ancho)
			throws AutorNoValidoException,
			EstiloNoValidoException, SinTipoGrabadoException {
		fondos.get(indice).setTitulo(titulo);
		fondos.get(indice).setAutor(autor);
		fondos.get(indice).setAncho(ancho);
		fondos.get(indice).setAlto(alto);
		fondos.get(indice).setEstiloArtistico(estiloArtistico);
		fondos.get(indice).setLocalizacion(localizacion);
		fondos.get(indice).setDonada(donada);
		fondos.get(indice).setPersonaEntidad(personaEntidad);
		fondos.get(indice).setFama(fama);
		fondos.get(indice).setValor(valor);
		((Grabado) fondos.get(indice)).setTipoDeGrabado(tipoDeGrabado);
	}

	/**
	 * Permite modificar los datos de un dibujo.
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

		fondos.get(indice).setTitulo(titulo);
		fondos.get(indice).setAutor(autor);
		fondos.get(indice).setAncho(ancho);
		fondos.get(indice).setAlto(alto);
		fondos.get(indice).setEstiloArtistico(estiloArtistico);
		fondos.get(indice).setLocalizacion(localizacion);
		fondos.get(indice).setDonada(donada);
		fondos.get(indice).setPersonaEntidad(personaEntidad);
		fondos.get(indice).setFama(fama);
		fondos.get(indice).setValor(valor);
		((Dibujo) fondos.get(indice)).setTecnica(tecnica);
	}

	// /**
	// * Permite contar las pinturas que hay en el ArrayList de fondos.
	// *
	// * @return nº de pinturas.
	// * @throws NoHayFondosException
	// */
	// public int contarPinturas() throws NoHayFondosException {
	// if (fondos.isEmpty())
	// throw new NoHayFondosException(
	// "Imposible contar pinturas, no hay fondos en el museo");
	//
	// int n_pinturas = 0;
	// ListIterator<ObraDeArte> it = fondos.listIterator();
	//
	// while (it.hasNext()) {
	// if (it.next() instanceof Pintura) {
	// n_pinturas++;
	// }
	// }
	// return n_pinturas;
	//
	// }
	//
	// /**
	// * Permite contar las esculturas que hay en el ArrayList de fondos.
	// *
	// * @return nº de esculturas.
	// * @throws NoHayFondosException
	// */
	// public int contarEsculturas() throws NoHayFondosException {
	// if (fondos.isEmpty())
	// throw new NoHayFondosException(
	// "Imposible contar esculturas, no hay fondos en el museo");
	//
	// int n_esculturas = 0;
	// ListIterator<ObraDeArte> it = fondos.listIterator();
	//
	// while (it.hasNext()) {
	// if (it.next() instanceof Pintura) {
	// n_esculturas++;
	// }
	// }
	// return n_esculturas;
	//
	// }
	//
	// /**
	// * Permite contar los dibujos que hay en el ArrayList de fondos.
	// *
	// * @return nº de dibujos.
	// * @throws NoHayFondosException
	// */
	// public int contarDibujos() throws NoHayFondosException {
	// if (fondos.isEmpty())
	// throw new NoHayFondosException(
	// "Imposible contar esculturas, no hay fondos en el museo");
	//
	// int n_dibujos = 0;
	// ListIterator<ObraDeArte> it = fondos.listIterator();
	//
	// while (it.hasNext()) {
	// if (it.next() instanceof Pintura) {
	// n_dibujos++;
	// }
	// }
	// return n_dibujos;
	//
	// }
	//
	// /**
	// * Permite contar los grabados que hay en el ArrayList de fondos.
	// *
	// * @return nº de grabados.
	// * @throws NoHayFondosException
	// */
	// public int contarGrabados() throws NoHayFondosException {
	// if (fondos.isEmpty())
	// throw new NoHayFondosException(
	// "Imposible contar grabados, no hay fondos en el museo");
	//
	// int n_grabados = 0;
	// ListIterator<ObraDeArte> it = fondos.listIterator();
	//
	// while (it.hasNext()) {
	// if (it.next() instanceof Pintura) {
	// n_grabados++;
	// }
	// }
	// return n_grabados;
	//
	// }

	// public String[] generarOpcionesMenu(){
	// String[] obras= new String[fondos.size()];
	// String obra;
	// for (int i = 0; i < obras.length; i++) {
	// if(fondos.get(i).isExpuesta())
	// i--;
	// obra= fondos.get(i).getTitulo();
	// obras[i] = obra;
	// }
	// return obras;
	// }

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
			throw new NoHayFondosException("no hay Dibujos");

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
			throw new NoHayFondosException("no hay Esculturas");
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
			throw new NoHayFondosException("no hay Dibujos");
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

	public boolean isEmpty() {
		return fondos.isEmpty();
	}

}
