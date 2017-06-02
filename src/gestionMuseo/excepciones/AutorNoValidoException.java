package gestionMuseo.excepciones;

public class AutorNoValidoException extends Exception {
 
	private static final long serialVersionUID = 1L;

	public AutorNoValidoException(String msj){
		super(msj);
	}
}
