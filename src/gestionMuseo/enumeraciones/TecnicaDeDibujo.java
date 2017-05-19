package gestionMuseo.enumeraciones;

public enum TecnicaDeDibujo {
	GRAFITO(3),
	CARBONCILLO(5),
	MIXTAS (7),
	SANGUINA(8);
	
	private double precio;
	
	TecnicaDeDibujo(double precio){
		this.precio=precio;
	}

	public double getPrecio() {
		return precio;
	}
}
