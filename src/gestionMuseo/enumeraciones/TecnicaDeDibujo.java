package gestionMuseo.enumeraciones;
/**
 * Tecnica de Dibujo
 * @author Nieves Borrero
 *
 */
public enum TecnicaDeDibujo {
	GRAFITO(3),
	CARBONCILLO(5),
	MIXTAS (7),
	SANGUINA(8);
	
	private double precio;
	/**
	 * 
	 * @param precio
	 */
	TecnicaDeDibujo(double precio){
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
