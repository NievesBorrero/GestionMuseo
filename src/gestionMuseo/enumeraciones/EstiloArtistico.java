package gestionMuseo.enumeraciones;

/**
 * Estilos artísticos a los que puede pertenecer una obra de arte y que se
 * corresponden con uno u otro periodo.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */
public enum EstiloArtistico {
	EGIPCIO(PeriodoHistorico.EDAD_ANTIGUA), GRIEGO(
			PeriodoHistorico.EDAD_ANTIGUA), ROMANO(
			PeriodoHistorico.EDAD_ANTIGUA), ROMANICO(
			PeriodoHistorico.EDAD_MEDIA), ISLAMICO(PeriodoHistorico.EDAD_MEDIA), GOTICO(
			PeriodoHistorico.EDAD_MEDIA), RENACIMIENTO(
			PeriodoHistorico.EDAD_MODERNA), BARROCO(
			PeriodoHistorico.EDAD_MODERNA), NEOCLASICISTA(
			PeriodoHistorico.EDAD_CONTEMPORANEA), REALISTA(
			PeriodoHistorico.EDAD_CONTEMPORANEA), IMPRESIONISTA(
			PeriodoHistorico.EDAD_CONTEMPORANEA), CUBISTA(
			PeriodoHistorico.EDAD_CONTEMPORANEA);

	private PeriodoHistorico epoca;
	/**
	 * 
	 * @param epoca
	 */
	EstiloArtistico(PeriodoHistorico epoca) {
		this.epoca = epoca;
	}
	
	/**
	 * 
	 * @return Periodo Historico
	 */
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
	
	/**
	 * Devuelve el periodo histórico en el que se da cada estilo.
	 * @return PeriodoHistorico.
	 */
	public PeriodoHistorico getPeridoHistorico() {
		return this.epoca;
	}

}
