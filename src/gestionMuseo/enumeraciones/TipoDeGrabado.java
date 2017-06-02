package gestionMuseo.enumeraciones;
/**
 * Tipo de Grabado
 * @author Nieves Borrero
 *
 */
public enum TipoDeGrabado {
	XILOGRAFIA(5),
	LITOGRAFIA (7),
	CALCOGRAFIA (8);
	
	double precio;
	
	TipoDeGrabado(double precio){
		this.precio=precio;
	}

	public double getPrecio() {
		return precio;
	}
	
}
