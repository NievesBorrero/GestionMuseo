package gestionMuseo.gui;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Ayuda extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private static Ayuda ayuda;
	private JTextArea txtGestion;

	/**
	 * Permite crear el diálogo.
	 */
	private Ayuda() {
		setResizable(false);
		setModal(false);
		setBounds(100, 100, 400, 500);
		getContentPane().setLayout(null);
		JScrollPane areaScrollPane = new JScrollPane(txtGestion);
		areaScrollPane.setPreferredSize(new Dimension(380, 480));
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		txtGestion = new JTextArea();
		txtGestion.setBounds(12, 12, 380, 480);
		getContentPane().add(txtGestion);
		txtGestion.setEditable(false);
		txtGestion.setText("Gestión de Museo\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n");
	}
	
	public static Ayuda getInstance(){
		if(ayuda==null)
			ayuda=new Ayuda();
		return ayuda;
		
	}
}
