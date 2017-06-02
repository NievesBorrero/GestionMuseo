package gestionMuseo.enumeraciones;
/**
 * Según el material de la pintura, costará más o menos restaurar una pintura.
 * @author Nieves Borrero
 *
 */
public enum MaterialPintura {
	OLEO(20),
	ACRILICO(7),
	PASTEL(10),
	ACUARELA(8);
	
	double precio;
	/**
	 * 
	 * @param precio
	 */
	MaterialPintura(double precio){
		this.precio=precio;
	}
	/**
	 * 
	 * @return decimal
	 */
	public double getPrecio() {
		return precio;
	}

}
