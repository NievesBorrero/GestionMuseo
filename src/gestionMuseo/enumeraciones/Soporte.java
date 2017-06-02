package gestionMuseo.enumeraciones;
/**
 * segun el soporte, costará más o menos la restauración.
 * @author Nieves Borrero.
 *
 */
public enum Soporte {
	LIENZO (20),
	TABLA (10),
	PAPEL (5);
	
	private double precio;
	/**
	 * 
	 * @param precio
	 */
	Soporte(double precio){
		this.precio= precio;
	}
	/**
	 * 
	 * @return decimal
	 */
	public double getPrecio() {
		return precio;
	}

}
