package gestionMuseo.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ClausurarExposicion extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public ClausurarExposicion() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPresupuesto = new JLabel("Presupuesto");
		lblPresupuesto.setBounds(31, 12, 160, 15);
		contentPanel.add(lblPresupuesto);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(31, 230, 117, 25);
		contentPanel.add(btnAceptar);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("a");
		textArea.setBounds(403, 64, -371, 152);
		contentPanel.add(textArea);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(172, 230, 117, 25);
		
//		contentPanel.add(btnCancelar);
//		JScrollPane scroll = new JScrollPane (textArea); 
//		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
//		//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
//		contentPanel.add(scroll); 
//		
		
	}
}
