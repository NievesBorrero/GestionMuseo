package gestionMuseo.enumeraciones;

	public enum EstiloArtistico {
	EGIPCIO (PeriodoHistorico.EDAD_ANTIGUA),
	GRIEGO (PeriodoHistorico.EDAD_ANTIGUA),
	ROMANO (PeriodoHistorico.EDAD_ANTIGUA),
	ROMANICO (PeriodoHistorico.EDAD_MEDIA),
	ISLAMICO (PeriodoHistorico.EDAD_MEDIA),
	GOTICO (PeriodoHistorico.EDAD_MEDIA),
	RENACIMIENTO (PeriodoHistorico.EDAD_MODERNA),
	BARROCO (PeriodoHistorico.EDAD_MODERNA),
	NEOCLASICISTA (PeriodoHistorico.EDAD_CONTEMPORANEA),
	REALISTA (PeriodoHistorico.EDAD_CONTEMPORANEA),
	IMPRESIONISTA (PeriodoHistorico.EDAD_CONTEMPORANEA),
	CUBISTA (PeriodoHistorico.EDAD_CONTEMPORANEA);
	
	private PeriodoHistorico epoca;
	
	EstiloArtistico(PeriodoHistorico epoca){
		this.epoca= epoca;
	}

	public PeriodoHistorico getEpoca() {
		return epoca;
	}
	/**
	 * Devuelve la constante como una cadena
	 */
	@Override
	public String toString() {
		return name();

	}

	public PeriodoHistorico getPeridoHistorico() {
		// TODO Auto-generated method stub
		return this.epoca;
	}
	
}
