package gestionMuseo.gui;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextArea;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * Jdialog que muestra la ayuda del programa.
 * @author Nieves María Borrero Barea
 * @version 1.0
 */

public class Ayuda extends JDialog {

	private static final long serialVersionUID = 1L;
	private static Ayuda ayuda;
	private final JPanel contentPanel = new JPanel();

	Ayuda() {
		setResizable(false);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Ayuda");
		setModal(false);
		setBounds(100, 100, 414, 444);

		getContentPane().setLayout(null);

		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		
		textArea.setText(
				"Gestión de Museo\n"
						+ "\nBienvenid@ a la guía de ayuda del programa.\n"
						+ "\n**Consideraciones previas:\n"
						+ "\nPara comodidad del usuario existe un archivo de prueba"
						+ "\ncon obras ya cargadas en la carpeta del proyecto deno-"
						+ "\nminado \"museo.obj\" que podrá abrir desde el menú de"
						+ "\narchivo.\n"
						+ "\n**Menú archivo:\n"
						+ "\nEn él se contienen las acciones relativas a los archivos:"
						+ "\ncrear un nuevo museo, guardarlo y abrirlo; además de la "
						+ "\nacción salir.\n"
						+ "\n**Menú obras:\n"
						+ "\nDesde este menú tendremos opción a ingresar varias o-"
						+ "\nbras, modificarlas, mostrarlas y eliminarlas.\n"
						+ "\nLa eliminación de obras se podrá realizar mientras estas"
						+ "\nse muestran para mayor comodidad del usuario. También "
						+ "\nexiste la posibilidad de eliminarlas por código.\n"
						+ "\nAl mostrar las obras se podrán listar ordenadas por uno"
						+ "\nde tres campos: por título, por código y por coste de res-"
						+ "\ntauración.\n"
						+ "\n**Menú buscar:\n"
						+ "\nPermite buscar obras de arte, bien por tipo de obra o bien"
						+ "\nintroduciendo el título de la misma.\n"
						+ "\n**Menú exposición:\n"
						+ "\nDesde el menú exposición se podrá realizar lo siguiente:\n"
						+ "\n-Consultar el presupuesto del museo.\n"
						+ "\n-Exponer obras: Para exponer las obras será necesario in-"
						+ "\ndicar la sala de exposición.\n"
						+ "\n-Organizar una exposición: Para organizar una exposición "
						+ "\nes necesario exponer obras antes. Si ya existe una expo-"
						+ "\nsición no se podrá organizar otra, será necesario clau-"
						+ "\nsurarla primero.\n"
						+ "\n-Visitar exposición: Se mostrarán las obras expuestas.\n"
						+ "\n-Datos de la exposición: Muestra los datos de la exposición "
						+ "\nsi es que esta existe.\n"
						+ "\n-Clausurar exposición: Si hay una exposición, la clausurará,"
						+ "\nrecogiendo las obras y guardándolas en el almacén. Se "
						+ "\ncalculará el presupuesto resultante de los ingresos y gas-"
						+ "\ntos de la exponsición organizada. En este presupuesto"
						+ "\ntambién se tendrán en cuenta las obras que, a pesar de"
						+ "\nno haber sido expuestas, se hayan restaurado.\n"
						+ "\n**Menú Dpto.Restauración:\n"
						+ "\nPermite restaurar las obras. Solo se podrán restaurar"
						+ "\naquellas cuyo estado de conservación es regular o malo.\n"
						+ "\n**Menú de ayuda:\n"
						+ "\nPermite consultar esta ayuda e información acerca del pro-"
						+ "\ngrama."
						);
		textArea.setBounds(0, 0, 403, 434);
		getContentPane().add(textArea);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
	}
	/**
	 * Crea una instancia de ayuda.
	 * @return new Ayuda
	 */
	public static Ayuda getInstance() {
		if (ayuda == null)
			ayuda = new Ayuda();
		return ayuda;

	}
}
	
	


