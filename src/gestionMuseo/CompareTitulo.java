package gestionMuseo;

import gestionMuseo.jerarquia.ObraDeArte;

import java.util.Comparator;
/**
 * Clase que implementa la interfaz comparator, comparando por título y
 * permitiendo ordenar las obras por este campo.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */
public class CompareTitulo implements Comparator<ObraDeArte>{

	@Override
	public int compare(ObraDeArte o1, ObraDeArte o2) {
		
		return o1.getTitulo().toLowerCase().compareTo(o2.getTitulo().toLowerCase());
	}
	
}
