package gestionMuseo;

public interface Presupuestable {
	
	/**
	 * Calcula el presupuesto resultante de organizar una exposición.
	 * @param gasto
	 * @param ingreso
	 * @return
	 */
	double calcularPresupuesto(double gasto, double ingreso);
	
//	/**
//	 * Calcula el gasto al organizar una exposición.
//	 * @return
//	 */
//	public double calcularGasto();
//	
//	/**
//	 * Calcula el ingreso al organizar una exposición.
//	 * @return
//	 */
//	public double calcularIngreso();
}
