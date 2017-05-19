package gestionMuseo.Ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Pattern;


public class Fichero{

	private static final long serialVersionUID = 1L;

	private static final Pattern patron = Pattern.compile("^((\\w)+(\\.obj))$");

	public static File FICHERO= new File("SinTitulo");

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
	public static void nuevo() {
		setFichero("SinTitulo.obj");
	}

	/**
	 * Guarda el fichero con el nombre dado.
	 * 
	 * @param objeto
	 * @param nombre
	 * @throws IOException
	 */
	public static void guardarComo(Object objeto, File nombre)
			throws IOException {
		FICHERO = comprobarNombre(nombre);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream(FICHERO))) {
			out.writeObject(objeto);
		}
	}

	/**
	 * guarda el fichero en el directorio en el que se encontraba.
	 * 
	 * @param objeto
	 * @throws IOException
	 */
	public static void guardar(Object objeto) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream(FICHERO))) {
			out.writeObject(objeto);
		}
	}

	/**
	 * abre un fichero.
	 * 
	 * @param archivo
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object abrir(File archivo) throws IOException,
			ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				archivo))) {
			return in.readObject();
		}
	}

	/**
	 * Comprueba que el nombre del fichero cumple el patrón y le añade la
	 * extensión
	 * 
	 * @param fichero2
	 * @return
	 */
	public static File comprobarNombre(File fichero) {
		if (patron.matcher(fichero.getName()).matches()) {
			return fichero;
		} else {
			setFichero(fichero.getAbsolutePath() + ".obj");
			return fichero;
		}

	}

}
