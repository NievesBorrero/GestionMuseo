package gestionMuseo;
/**
 * Interface que implementan las obras de arte sujetas a generar un presupuesto
 * @author Nieves Borrero
 */
public interface Presupuestable {
	
	/**
	 * Calcula el presupuesto resultante de organizar una exposición.
	 * @param gasto
	 * @param ingreso
	 * @return decimal
	 */
	double calcularPresupuesto(double gasto, double ingreso);

}
