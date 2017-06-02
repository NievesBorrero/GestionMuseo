package gestionMuseo;

import gestionMuseo.jerarquia.ObraDeArte;

import java.util.Comparator;

/**
 * Clase que implementa la interfaz comparator, comparando por coste de 
 * restauración y permitiendo ordenar las obras por este campo.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */
public class CompareCosteRestauracion implements Comparator<ObraDeArte> {

	@Override
	public int compare(ObraDeArte obra1, ObraDeArte obra2) {
		if(obra1.getCosteRestauracion()>obra2.getCosteRestauracion())
			return 1;
		else if(obra2.getCosteRestauracion()>obra1.getCosteRestauracion())
			return -1;
		else
			return 0;
	}
	
}
