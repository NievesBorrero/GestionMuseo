package gestionMuseo.excepciones;

public class PeriodoNoValidoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public PeriodoNoValidoException(String msj){
		super(msj);
	}
}
