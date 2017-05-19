package gestionMuseo;

import gestionMuseo.jerarquia.ObraDeArte;

import java.util.Comparator;

public class CompareTitulo implements Comparator<ObraDeArte>{

	@Override
	public int compare(ObraDeArte o1, ObraDeArte o2) {
		
		return o1.getTitulo().toLowerCase().compareTo(o2.getTitulo().toLowerCase());
	}
	
}
