package gestionMuseo.gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JSeparator;

public class Ayuda extends JDialog {
	private static Ayuda ayuda;

	/**
	 * Create the dialog.
	 */
	private Ayuda() {
		setResizable(false);
		setModal(false);
		setBounds(100, 100, 450, 334);
		getContentPane().setLayout(null);
		
		JTextArea txtGestion = new JTextArea();
		txtGestion.setBounds(12, 12, 426, 310);
		getContentPane().add(txtGestion);
		txtGestion.setText("Gestión de Museo");
	}
	
	public static Ayuda getInstance(){
		if(ayuda==null)
			ayuda=new Ayuda();
		return ayuda;
		
	}
}
