package gestionMuseo.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Acceso extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Acceso dialog = new Acceso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Acceso() {
		setTitle("Acceso al Museo");
		setBounds(100, 100, 445, 186);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnVisitantes = new JButton("VISITANTES");
		btnVisitantes.setBounds(71, 84, 117, 25);
		contentPanel.add(btnVisitantes);
		
		JButton btnGestin = new JButton("GESTION");
		btnGestin.setBounds(240, 84, 117, 25);
		contentPanel.add(btnGestin);
		
		JLabel lblBienvenidoAlMuseo = new JLabel("Bienvenido al Museo: Por favor, indica si vas ");
		lblBienvenidoAlMuseo.setBounds(61, 12, 344, 57);
		contentPanel.add(lblBienvenidoAlMuseo);
		
		JLabel lblAVisitarLa = new JLabel("a visitar la exposición o a gestionar las obras.");
		lblAVisitarLa.setBounds(61, 49, 372, 15);
		contentPanel.add(lblAVisitarLa);
	}
}
