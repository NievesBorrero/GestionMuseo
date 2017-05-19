package gestionMuseo.enumeraciones;

public enum MaterialEscultura {
	MARMOL (60),
	MADERA (14),
	ARCILLA (10),
	PIEDRA (12),
	ESTUCO (20),
	PLATA (70),
	ORO (100),
	BRONCE (25),
	MARFIL(75);	

	private double precio;
	
	MaterialEscultura(double precio){
		this.precio=precio;
	}
	
	public double getPrecio() {
		return precio;
	}

}
