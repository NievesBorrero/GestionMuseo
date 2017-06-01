package gestionMuseo.enumeraciones;

/**
 * Según el daño que hayan sufrido las obras de arte, tendrán 
 * un estado de conservación con un plus que se sumará al
 * precio de la restauración.
 * 
 * @author Nieves Borrero Barea.
 *
 */

public enum EstadoDeConservacion {
	
	BUENO (0), // Si es bueno no se restaura.
	REGULAR (50),
	MALO (100);

	private double precio;

	EstadoDeConservacion(double precio){
		this.precio=precio;
	}

	public double getPrecio() {
		return precio;
	}

}
