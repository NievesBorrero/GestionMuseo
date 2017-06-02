package gestionMuseo.Ficheros;

import gestionMuseo.GestionMuseo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;
/**
 * 
 * @author Nieves Borrero
 *
 */
public class Fichero {

	private static final Pattern patron = Pattern.compile("^((\\w)+(\\.obj))$");
	public static File FICHERO = new File("SinTitulo");

	/**
	 * Metodo set
	 * 
	 * @param fichero
	 */
	public static void setFichero(String fichero) {
		Fichero.FICHERO = new File(fichero);
	}

	/**
	 * Metodo get
	 * 
	 * @return
	 */
	public static File getFichero() {
		return FICHERO;
	}
	/**
	 * Crear un nuevo fichero
	 */
	public static void nuevo(){
		setFichero("SinTitulo.obj");
	}

	/**
	 * Guarda el fichero preguntando previamente donde y con que nombre
	 * 
	 * @param museo
	 * @param nombre
	 * @throws IOException
	 */
	public static void guardarComo(GestionMuseo museo, File nombre) throws IOException {
		FICHERO = comprobarNombre(nombre);
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FICHERO))) {
			out.writeObject(museo);
		}
	}

	/**
	 * guarda el fichero en la misma ubicacion y bajo el mismo nombre
	 * 
	 * @param objeto
	 * @throws IOException
	 */
	public static void guardar(GestionMuseo museo) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FICHERO))) {
			out.writeObject(museo);
		}
	}

	/**
	 * abre un Museo previamente guardado
	 * 
	 * @param archivo
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static GestionMuseo abrir(File archivo) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
			return (GestionMuseo) in.readObject();
		}
	}

	/**
	 * Comprueba que el nombre del fichero cumple el patron establecido y le anade la extension elegida (.obj)
	 * 
	 * @param ficheroUsuario
	 * @return FICHERO
	 */
	public static File comprobarNombre(File ficheroUsuario) {
		if (patron.matcher(ficheroUsuario.getName()).matches()) {
			return ficheroUsuario;
		} else {
			setFichero(ficheroUsuario.getAbsolutePath() + ".obj");
			return FICHERO;
		}

	}
}