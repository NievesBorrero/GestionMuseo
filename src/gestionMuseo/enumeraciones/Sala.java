package gestionMuseo.enumeraciones;

/**
 * Salas que tiene el museo, en las que pueden estar las obras.
 * Si están expuestan, estarán en alguna de las salas numeradas,
 * si no, estarán en el almacén.
 * 
 * Las salas abiertas tienen un gasto de mantenimiento, por tanto
 * si hay alguna obra expuesta en la sala, su gasto se sumará al
 * gasto de exposición. (No sé si por cada obra o una vez que se
 * exponga alguna).
 * 
 * @author Nieves Borrero
 *
 */
public enum Sala {
	
	ALMACEN(0),// No suma gasto al coste de exposición.
	SALA1(10),
	SALA2(10),
	SALA3 (10);
	
	private double gasto;
	private static final Sala[] VALUES = values(); //Almacena las posibles salas.
	
	Sala(double gasto){
		this.gasto= gasto;
	}
	
	/**
	 * Devuelve las posibles salas.
	 * @return
	 */
	public Sala[] getValues() {
		return VALUES;
	}
	
	/**
	 * Genera opciones para un menú con las posibles salas.
	 * @return
	 */
	public String[] generarOpcionesMenu() {
		String[] opcionesMenu = new String[VALUES.length-1];
		int i = 0;
		for (Sala sala : VALUES) {
			if(sala.ordinal()>0) // Para que no coja el almacén
			opcionesMenu[i++] = sala.name();
		}
		return opcionesMenu;
	}

	public double getGasto() {
		return gasto;
	}

}
