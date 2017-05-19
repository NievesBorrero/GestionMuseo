package gestionMuseo.enumeraciones;
/**
 * Según el material de la pintura, costará más o menos restaurar una pintura.
 * @author nieves
 *
 */
public enum MaterialPintura {
	OLEO(20),
	ACRILICO(7),
	PASTEL(10),
	ACUARELA(8);
	
	double precio;
	
	MaterialPintura(double precio){
		this.precio=precio;
	}

	public double getPrecio() {
		return precio;
	}

}
