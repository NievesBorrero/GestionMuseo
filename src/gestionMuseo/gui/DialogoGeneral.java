package gestionMuseo.gui;

import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.PeriodoHistorico;
import gestionMuseo.enumeraciones.Sala;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.enumeraciones.TipoDeGrabado;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.jerarquia.Dibujo;
import gestionMuseo.jerarquia.Escultura;
import gestionMuseo.jerarquia.Grabado;
import gestionMuseo.jerarquia.ObraDeArte;
import gestionMuseo.jerarquia.Pintura;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import gestionMuseo.enumeraciones.EstiloArtistico;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;

/**
 * JDialog padre con los campos de cada obra a partir del cual heredan el resto
 * de diálogos que comparten la misma estructura.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */

public class DialogoGeneral extends JDialog {

	private static final long serialVersionUID = 1L;

	final JPanel contentPanel = new JPanel();
	JTextField textTitulo;
	JTextField textLocal;
	JTextField textAutor;
	JTextField textPersona;
	JComboBox<PeriodoHistorico> cbPH;
	JComboBox<EstiloArtistico> cbEA;
	JRadioButton rbDonada;
	JRadioButton rbComprada;
	JButton btnAnterior;
	JButton btnIzquierda;
	JButton btnDerecha;
	JSpinner spAlto;
	JSpinner spAncho;
	JSpinner spCosteExp;
	JSpinner spCostRest;
	JSpinner spValor;
	JSpinner spFama;
	JLabel lblCombobox1;
	JLabel lblCombobox2;
	@SuppressWarnings("rawtypes")
	JComboBox cb1;
	@SuppressWarnings("rawtypes")
	JComboBox cb2;
	JLabel lblTipoDeObra;
	JButton btnSiguiente;
	JLabel lblProfundidad;
	JSpinner spprof;
	JLabel lblCosteDeExposicion;
	JLabel lblCosteRestauracin;
	JLabel lblValor;
	JLabel lblFama;
	JLabel lblLocal;
	JLabel lblAutor;
	JLabel lblPersonaentidad;
	JLabel lblPeriodoHistrico;
	JLabel lblestado;
	JLabel lblTtulo;
	JLabel lblFechaDeIngreso;
	JLabel lblAncho;
	JLabel lblAlto;
	JRadioButton rbSala_1;
	JRadioButton rbSala_2;
	JRadioButton rbSala_3;
	JPanel ingresosYgastos;
	JPanel panel_salas;
	JPanel panel_medidas;
	JTextField textcod;
	JLabel lblcod;
	@SuppressWarnings("rawtypes")
	JComboBox cbTipo;
	JPanel panel_adquisicion;
	JRadioButton rbAlmacen;
	JTextField textFecha;
	JTextField textEstado;
	JPanel panel_2;
	JSeparator separator;
	private final ButtonGroup buttonGroup_ad = new ButtonGroup();
	private final ButtonGroup buttonGroup_salas = new ButtonGroup();
	ObraDeArte obraMostrada;

	/**
	 * Create the dialog.
	 */
	public DialogoGeneral() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 649, 582);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		crearDatosGenerales();

		crearPanelAdquisicion();

		crearComboBoxes();

		crearPanelIngresosGastos();

		crearBotones();

		crearPanelMedidas();

		crearPanelSalas();

		
	}
	/**
	 * Crea el panel relativo a los posibles gastos e ingresos de la obra
	 */
	protected void crearPanelIngresosGastos() {
		ingresosYgastos = new JPanel();
		ingresosYgastos.setBorder(new TitledBorder(new LineBorder(new Color(
				184, 207, 229)), "Ingresos y gastos", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		ingresosYgastos.setBounds(318, 333, 308, 116);
		contentPanel.add(ingresosYgastos);
		ingresosYgastos.setLayout(null);

		lblCosteDeExposicion = new JLabel("Coste de Exposici\u00F3n");
		lblCosteDeExposicion.setBounds(12, 25, 156, 15);
		ingresosYgastos.add(lblCosteDeExposicion);

		lblCosteRestauracin = new JLabel("Coste restauraci\u00F3n");
		lblCosteRestauracin.setBounds(12, 52, 156, 15);
		ingresosYgastos.add(lblCosteRestauracin);

		lblValor = new JLabel("Valor");
		lblValor.setBounds(12, 89, 70, 15);
		ingresosYgastos.add(lblValor);

		lblFama = new JLabel("Fama");
		lblFama.setBounds(139, 89, 70, 15);
		ingresosYgastos.add(lblFama);

		spCosteExp = new JSpinner();
		spCosteExp.setEnabled(false);
		spCosteExp.setBounds(186, 23, 64, 20);
		spCosteExp.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
		ingresosYgastos.add(spCosteExp);

		spCostRest = new JSpinner();
		spCostRest.setEnabled(false);
		spCostRest.setBounds(186, 50, 64, 20);
		spCostRest.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
		ingresosYgastos.add(spCostRest);

		spValor = new JSpinner();
		spValor.setBounds(63, 87, 58, 20);
		spValor.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
		ingresosYgastos.add(spValor);

		spFama = new JSpinner();
		spFama.setBounds(186, 87, 64, 20);
		spFama.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
		ingresosYgastos.add(spFama);
	}
	/**
	 * crea los comboboxes de selección/visión de periodo histórico, estilo artístico y los 
	 * particulares del tipo de material y soporte de las obras
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void crearComboBoxes() {
		lblPeriodoHistrico = new JLabel("Periodo hist\u00F3rico");
		lblPeriodoHistrico.setBounds(309, 54, 129, 15);
		contentPanel.add(lblPeriodoHistrico);

		cbPH = new JComboBox<PeriodoHistorico>();
		cbPH.setSelectedItem(null);
		cbPH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cbEA.setModel(new DefaultComboBoxModel(getEstilo(cbPH)));
			}
		});

		cbPH.setModel(new DefaultComboBoxModel(PeriodoHistorico.values()));
		cbPH.setBounds(459, 51, 167, 24);
		contentPanel.add(cbPH);

		JLabel lblEstiloArtstico = new JLabel("Estilo art\u00EDstico");
		lblEstiloArtstico.setBounds(309, 82, 129, 15);
		contentPanel.add(lblEstiloArtstico);

		cbEA = new JComboBox<EstiloArtistico>();
		cbEA.setModel(new DefaultComboBoxModel(getEstilo(cbPH)));
		cbEA.setBounds(459, 79, 167, 24);
		contentPanel.add(cbEA);

		lblCombobox1 = new JLabel("ComboBox1");
		lblCombobox1.setBounds(309, 111, 129, 15);
		contentPanel.add(lblCombobox1);

		cb1 = new JComboBox();
		cb1.setSelectedItem(null);
		cb1.setBounds(459, 106, 167, 24);
		contentPanel.add(cb1);

		lblCombobox2 = new JLabel("ComboBox2");
		lblCombobox2.setBounds(309, 138, 114, 15);
		contentPanel.add(lblCombobox2);

		cb2 = new JComboBox();
		cb2.setSelectedItem(null);
		cb2.setBounds(459, 133, 167, 24);
		contentPanel.add(cb2);
		
		lblTipoDeObra = new JLabel("Tipo de obra");
		lblTipoDeObra.setBounds(12, 6, 97, 15);
		contentPanel.add(lblTipoDeObra);

		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] { "Pintura",
				"Escultura", "Dibujo", "Grabado" }));
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarTipoDeObra();
			}
		});
		cbTipo.setBounds(127, 1, 114, 24);
		contentPanel.add(cbTipo);

	}
	/**
	 * crea las etiquetas y campos de texto generales de la obra
	 */
	protected void crearDatosGenerales() {
		lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(22, 56, 70, 17);
		contentPanel.add(lblTtulo);

		textTitulo = new JTextField();
		textTitulo.setBounds(127, 56, 164, 19);
		contentPanel.add(textTitulo);
		textTitulo.setColumns(10);

		textLocal = new JTextField();
		textLocal.setColumns(10);
		textLocal.setBounds(127, 111, 164, 19);
		contentPanel.add(textLocal);

		lblLocal = new JLabel("Localizaci\u00F3n");
		lblLocal.setBounds(22, 112, 97, 15);
		contentPanel.add(lblLocal);

		textAutor = new JTextField();
		textAutor.setColumns(10);
		textAutor.setBounds(127, 80, 164, 19);
		contentPanel.add(textAutor);

		lblAutor = new JLabel("Autor");
		lblAutor.setBounds(22, 85, 70, 15);
		contentPanel.add(lblAutor);
		
		lblcod = new JLabel("Código");
		lblcod.setBounds(524, 6, 59, 15);
		contentPanel.add(lblcod);

		textcod = new JTextField();
		textcod.setBounds(578, 4, 59, 19);
		contentPanel.add(textcod);
		textcod.setColumns(10);


		lblFechaDeIngreso = new JLabel("Fecha de ingreso:");
		lblFechaDeIngreso.setBounds(22, 139, 127, 15);
		contentPanel.add(lblFechaDeIngreso);

		textFecha = new JTextField();
		textFecha.setBounds(155, 138, 86, 19);
		contentPanel.add(textFecha);
		textFecha.setColumns(10);
		textFecha.setEnabled(false);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(Color.WHITE);
		separator.setBounds(0, 478, 649, 13);
		contentPanel.add(separator);

		lblestado = new JLabel("Estado de conservación: ");
		lblestado.setBounds(309, 284, 177, 15);
		contentPanel.add(lblestado);

		textEstado = new JTextField();
		textEstado.setBounds(499, 284, 114, 19);
		contentPanel.add(textEstado);
		textEstado.setColumns(10);
	}
	/**
	 * crea el panel referente al tipo de adquisición de la obra
	 */
	protected void crearPanelAdquisicion() {
		panel_adquisicion = new JPanel();
		panel_adquisicion.setBorder(new TitledBorder(null, "Adquisici\u00F3n",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		panel_adquisicion.setBounds(22, 344, 229, 105);
		contentPanel.add(panel_adquisicion);
		panel_adquisicion.setLayout(null);

		rbDonada = new JRadioButton("Donada");
		buttonGroup_ad.add(rbDonada);
		rbDonada.setBounds(8, 19, 98, 23);
		panel_adquisicion.add(rbDonada);

		rbComprada = new JRadioButton("Comprada");
		buttonGroup_ad.add(rbComprada);
		rbComprada.setBounds(110, 19, 111, 23);
		panel_adquisicion.add(rbComprada);

		lblPersonaentidad = new JLabel("Persona/Entidad");
		lblPersonaentidad.setBounds(8, 50, 135, 15);
		panel_adquisicion.add(lblPersonaentidad);

		textPersona = new JTextField();
		textPersona.setBounds(8, 77, 134, 19);
		panel_adquisicion.add(textPersona);
		textPersona.setColumns(10);
	}
	/**
	 * crea el panel referente a la sala en que se encuentra la obra
	 */
	protected void crearPanelSalas() {
		panel_salas = new JPanel();
		panel_salas.setBorder(new TitledBorder(null, "Salas",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_salas.setBounds(306, 174, 287, 86);
		contentPanel.add(panel_salas);
		panel_salas.setLayout(null);

		rbSala_2 = new JRadioButton("Sala 2");
		buttonGroup_salas.add(rbSala_2);
		rbSala_2.setBounds(107, 23, 89, 23);
		panel_salas.add(rbSala_2);

		rbSala_3 = new JRadioButton("Sala 3");
		buttonGroup_salas.add(rbSala_3);
		rbSala_3.setBounds(200, 23, 79, 23);
		panel_salas.add(rbSala_3);

		rbSala_1 = new JRadioButton("Sala 1");
		buttonGroup_salas.add(rbSala_1);
		rbSala_1.setBounds(8, 23, 107, 23);
		panel_salas.add(rbSala_1);

		rbAlmacen = new JRadioButton("Almacén");
		buttonGroup_salas.add(rbAlmacen);
		rbAlmacen.setBounds(91, 51, 149, 23);
		panel_salas.add(rbAlmacen);
	}
	/**
	 * crea el panel referido a las medidas de la obra
	 */
	protected void crearPanelMedidas() {
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Medidas (m)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_2.setBounds(22, 174, 198, 116);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		lblProfundidad = new JLabel("Profundidad");
		lblProfundidad.setBounds(12, 87, 97, 15);
		panel_2.add(lblProfundidad);

		spprof = new JSpinner();
		spprof.setBounds(107, 85, 70, 20);
		panel_2.add(spprof);
		spprof.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));

		lblAncho = new JLabel("Ancho");
		lblAncho.setBounds(12, 56, 70, 15);
		panel_2.add(lblAncho);

		spAncho = new JSpinner();
		spAncho.setBounds(107, 53, 70, 20);
		panel_2.add(spAncho);
		spAncho.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));

		lblAlto = new JLabel("Alto");
		lblAlto.setBounds(12, 28, 70, 15);
		panel_2.add(lblAlto);

		spAlto = new JSpinner();
		spAlto.setBounds(107, 28, 70, 20);
		panel_2.add(spAlto);
		spAlto.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
	}
	/**
	 * crea los botones de acción de la ventana
	 */
	protected void crearBotones() {
		btnAnterior = new JButton("        ");

		btnAnterior.setBounds(22, 503, 117, 25);
		contentPanel.add(btnAnterior);

		btnIzquierda = new JButton("      ");
		btnIzquierda.setBounds(391, 503, 117, 25);
		contentPanel.add(btnIzquierda);

		btnDerecha = new JButton("       ");
		btnDerecha.setBounds(520, 503, 117, 25);
		contentPanel.add(btnDerecha);

		btnSiguiente = new JButton("");
		btnSiguiente.setBounds(151, 503, 117, 25);
		contentPanel.add(btnSiguiente);
	}

	/**
	 * Recorre un array de la enum de modelos, comprobando si la marca
	 * seleccionada se corresponde con la marca de cada modelo, si es así, la
	 * añade a un arrayList de modelos que luego devuelve convertido en array.
	 * 
	 * @param comboBoxMarca
	 * @return array con los modelos de esa marca.
	 */
	@SuppressWarnings("rawtypes")
	private Object[] getEstilo(JComboBox cbPH) {
		PeriodoHistorico periodo = (PeriodoHistorico) cbPH.getSelectedItem();
		ArrayList<EstiloArtistico> estilos = new ArrayList<EstiloArtistico>();
		for (EstiloArtistico est : EstiloArtistico.values())
			if (est.getPeridoHistorico() == periodo) // Creo un ArrayList con
														// los estilos a partir
														// del ArrayList
														// de la enum
				estilos.add(est);
		return estilos.toArray();
	}

	/**
	 * Muestra una obra de arte.
	 * 
	 * @param obra
	 */
	protected void mostrar(ObraDeArte obra) {

		rellenarCamposComunes(obra);

		if (obra instanceof Pintura) {
			rellenarCamposPintura(obra);
		}

		else if (obra instanceof Escultura) {
			rellenarCamposEscultura(obra);
		}

		else if (obra instanceof Grabado) {
			rellenarCamposGrabado(obra);
		}

		else if (obra instanceof Dibujo) {
			rellenarCamposDibujo(obra);
		}

		setObraMostrada(obra);

	}

	/**
	 * Rellena los campos de un dibujo.
	 * 
	 * @param obra
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void rellenarCamposDibujo(ObraDeArte obra) {
		String codigo;
		cbTipo.setSelectedItem("Dibujo");
		Dibujo dibujo = (Dibujo) obra;
		lblCombobox1.setText("Tecnica");
		cb1.setModel(new DefaultComboBoxModel(TecnicaDeDibujo.values()));
		cb1.setSelectedItem(dibujo.getTecnica());
		cb2.setModel(new DefaultComboBoxModel(Soporte.values()));
		cb2.setSelectedItem(dibujo.getSoporte());
		codigo = String.valueOf((dibujo.getCodigo()));
		textcod.setText(codigo);
	}

	/**
	 * Rellena los campos de un grabado.
	 * 
	 * @param obra
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void rellenarCamposGrabado(ObraDeArte obra) {
		String codigo;
		cbTipo.setSelectedItem("Grabado");
		Grabado grabado = (Grabado) obra;
		lblCombobox1.setText("Tipo");
		cb1.setModel(new DefaultComboBoxModel(TipoDeGrabado.values()));
		cb1.setSelectedItem(grabado.getTipoDeGrabado());
		codigo = String.valueOf((grabado.getCodigo()));
		textcod.setText(codigo);
	}

	/**
	 * Rellena los campos de una escultura.
	 * 
	 * @param obra
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void rellenarCamposEscultura(ObraDeArte obra) {
		String codigo;
		cbTipo.setSelectedItem("Escultura");
		Escultura escultura = (Escultura) obra;
		lblCombobox1.setText("Tipo");
		lblCombobox2.setText("Material");
		cb2.setEnabled(false);
		cb1.setModel(new DefaultComboBoxModel(TipoEscultura.values()));
		cb2.setModel(new DefaultComboBoxModel(MaterialEscultura.values()));
		cb1.setSelectedItem(escultura.getTipoEscultura());
		cb2.setSelectedItem(escultura.getMaterialEscultura());
		codigo = String.valueOf((escultura.getCodigo()));
		textcod.setText(codigo);
		spprof.setValue(((Escultura) obra).getProfundidad());
	}

	/**
	 * Rellena los campos de una pintura.
	 * 
	 * @param obra
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void rellenarCamposPintura(ObraDeArte obra) {
		String codigo;
		cbTipo.setSelectedItem("Pintura");
		Pintura pintura = (Pintura) obra;
		cb1.setModel(new DefaultComboBoxModel(Soporte.values()));
		cb2.setModel(new DefaultComboBoxModel(MaterialPintura.values()));
		cb1.setSelectedItem(pintura.getSoporte());
		cb2.setSelectedItem(pintura.getMaterial());
		cb2.setEnabled(false);
		codigo = String.valueOf((pintura.getCodigo()));
		textcod.setText(codigo);
		textFecha.setText(pintura.formatearFecha(pintura.getFechaIngreso()));
	}

	/**
	 * Rellena los campos que todas las obras de arte tienen en común.
	 * 
	 * @param obra
	 */
	private void rellenarCamposComunes(ObraDeArte obra) {
		textTitulo.setText(obra.getTitulo());
		textAutor.setText(obra.getAutor());
		textLocal.setText(obra.getLocalizacion());
		textPersona.setText(obra.getPersonaEntidad());
		cbPH.setSelectedItem(obra.getEstiloArtistico().getPeridoHistorico());
		cbEA.setSelectedItem(obra.getEstiloArtistico());
		textFecha.setText(obra.formatearFecha(obra.getFechaIngreso()));
		spCosteExp.setValue(obra.getCosteExposicion());
		spCostRest.setValue(obra.getCosteRestauracion());
		spFama.setValue(obra.getFama());
		spValor.setValue(obra.getValor());
		spAlto.setValue(obra.getAlto());
		spAncho.setValue(obra.getAncho());
		textEstado.setText(obra.getEstadoConservacion().toString()
				.toLowerCase());
		seleccionarSala(obra);
		seleccionarAdquisicion(obra);
	}

	/**
	 * Selecciona si la obra fue donada o comprada.
	 * 
	 * @param obra
	 */
	private void seleccionarAdquisicion(ObraDeArte obra) {
		if (obra.isDonada())
			rbDonada.setSelected(true);
		else
			rbComprada.setSelected(true);
	}

	/**
	 * Selecciona la sala en la que se encuentre la obra de arte.
	 * 
	 * @param obra
	 */
	void seleccionarSala(ObraDeArte obra) {
		if (obra.getSala() == Sala.SALA1)
			rbSala_1.setSelected(true);
		else if (obra.getSala() == Sala.SALA2)
			rbSala_2.setSelected(true);
		else if (obra.getSala() == Sala.SALA3)
			rbSala_3.setSelected(true);
		else
			rbAlmacen.setSelected(true);

	}

	/**
	 * Limpia el diálogo eliminando todo lo que se ha rellenado previamente.
	 */
	void limpiar() {

		lblCombobox1.setVisible(false);
		cb1.setVisible(false);
		lblCombobox2.setVisible(false);
		cb2.setVisible(false);
		textTitulo.setText(null);
		textAutor.setText(null);
		textLocal.setText(null);
		textPersona.setText(null);
		buttonGroup_ad.clearSelection();
		cbPH.setSelectedItem(null);
		cbEA.setSelectedItem(null);
		cb1.setSelectedItem(null);
		cb2.setSelectedItem(null);
		cbTipo.setSelectedItem(null);
		textcod.setText(null);
		spCosteExp.setValue(0);
		spCostRest.setValue(0);
		spFama.setValue(0);
		spValor.setValue(0);
		spAncho.setValue(0);
		spAlto.setValue(0);
		spprof.setValue(0);
	}

	/**
	 * Muestra/oculta componentes según el tipo de obra.
	 */
	void habilitarTipoDeObra() {

		if (cbTipo.getSelectedItem() == "Pintura")
			habilitarPintura();

		else if (cbTipo.getSelectedItem() == "Escultura")
			habilitarEscultura();

		else if (cbTipo.getSelectedItem() == "Grabado")
			habilitarGrabado();

		else if (cbTipo.getSelectedItem() == "Dibujo")
			habilitarDibujo();

		else
			habilitarSinTipoDeObra();

	}

	/**
	 * Oculta los componentes específicos cuando aún no se ha seleccionado el
	 * tipo de obra.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void habilitarSinTipoDeObra() {
		spprof.setVisible(false);
		lblProfundidad.setVisible(false);
		lblCombobox2.setVisible(false);
		lblCombobox1.setVisible(false);
		cb1.setVisible(false);
		cb2.setVisible(false);
		lblCombobox1.setText("");
		cb1.setModel(new DefaultComboBoxModel());
	}

	/**
	 * Muestra/oculta los componentes para un dibujo.
	 */
	private void habilitarDibujo() {
		spprof.setVisible(false);
		lblProfundidad.setVisible(false);
		lblCombobox2.setVisible(false);
		lblCombobox1.setVisible(true);
		cb1.setVisible(true);
		cb2.setVisible(true);
		lblCombobox1.setText("Tecnica");
		lblCombobox2.setVisible(true);
		lblCombobox2.setText("Soporte");
		cb1.setModel(new DefaultComboBoxModel(TecnicaDeDibujo.values()));
		cb1.setSelectedItem(null);
		cb2.setModel(new DefaultComboBoxModel(Soporte.values()));
		cb2.setSelectedItem(Soporte.PAPEL);
		cb2.setEnabled(false);
	}

	/**
	 * Muestra/oculta los componentes para un grabado.
	 */
	private void habilitarGrabado() {
		spprof.setVisible(false);
		lblProfundidad.setVisible(false);
		lblCombobox1.setText("Tipo");
		lblCombobox2.setVisible(false);
		lblCombobox1.setVisible(true);
		cb1.setVisible(true);
		cb2.setVisible(false);
		cb1.setModel(new DefaultComboBoxModel(TipoDeGrabado.values()));
		cb1.setSelectedItem(null);
	}

	/**
	 * Muestra/oculta los componentes para una escultura.
	 */
	private void habilitarEscultura() {
		spprof.setVisible(true);
		lblProfundidad.setVisible(true);
		lblCombobox1.setText("Tipo");
		lblCombobox2.setText("Material");
		lblCombobox1.setVisible(true);
		lblCombobox2.setVisible(true);
		cb1.setVisible(true);
		cb2.setVisible(true);
		cb1.setModel(new DefaultComboBoxModel(TipoEscultura.values()));
		cb2.setModel(new DefaultComboBoxModel(MaterialEscultura.values()));
		cb2.setEnabled(true);
		cb1.setSelectedItem(null);
		cb2.setSelectedItem(null);
	}

	/**
	 * Muestra/oculta los componentes para una pintura.
	 */
	private void habilitarPintura() {
		spprof.setVisible(false);
		lblProfundidad.setVisible(false);
		lblCombobox1.setText("Soporte");
		lblCombobox2.setText("Material");
		cb1.setModel(new DefaultComboBoxModel(Soporte.values()));
		cb2.setModel(new DefaultComboBoxModel(MaterialPintura.values()));
		lblCombobox1.setVisible(true);
		lblCombobox2.setVisible(true);
		cb1.setVisible(true);
		cb2.setVisible(true);
		cb2.setEnabled(true);
		cb1.setSelectedItem(null);
		cb2.setSelectedItem(null);
	}
	
	/**
	 * Transforma el contenido de un JText a un entero.
	 * @return
	 */
	int pasarTextAEntero(String cod) {
		return Integer.parseInt(cod);
	}

	/**
	 * Permite modificar la obra mostrada
	 * 
	 * @param obra
	 */
	void setObraMostrada(ObraDeArte obra) {
		this.obraMostrada = obra;
	}

	/**
	 * Devuelve la obra mostrada
	 * 
	 * @return obra
	 */

	protected ObraDeArte getObraMostrada() {
		return obraMostrada;
	}
}
