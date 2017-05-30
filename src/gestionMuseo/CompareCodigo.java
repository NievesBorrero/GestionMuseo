package gestionMuseo;

import java.util.Comparator;

import gestionMuseo.jerarquia.ObraDeArte;

public class CompareCodigo implements Comparator<ObraDeArte>{

	@Override
	public int compare(ObraDeArte obra1, ObraDeArte obra2) {
		if(obra1.getCodigo()>obra2.getCodigo())
			return 1;
		else if(obra2.getCodigo()>obra1.getCodigo())
			return -1;
		else
			return 0;
	}

}
