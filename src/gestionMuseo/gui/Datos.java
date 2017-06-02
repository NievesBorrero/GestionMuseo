package gestionMuseo.gui;

import gestionMuseo.GestionMuseo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * JDialog que muestra los datos de una exposición
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */
public class Datos extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textNombreExp;
	private JButton okButton;
	private JLabel lblDescripcion;
	private JLabel lblFechaDeFin;
	private JLabel lblFechaDeInicio;
	private JLabel lblNombreDeLa;
	private JTextField textInicio;
	private JTextField textFin;
	private JTextPane textPane;


	public Datos() {
		setBounds(100, 100, 450, 341);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setTitle("Datos de la exposici\u00F3n");
	
		
		lblNombreDeLa = new JLabel("Nombre de la exposición:");
		lblNombreDeLa.setBounds(12, 12, 189, 15);
		contentPanel.add(lblNombreDeLa);
		
		textNombreExp = new JTextField();
		textNombreExp.setBounds(202, 10, 223, 19);
		contentPanel.add(textNombreExp);
		textNombreExp.setColumns(10);
		
		lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(12, 40, 100, 15);
		contentPanel.add(lblDescripcion);
		
		lblFechaDeFin = new JLabel("Fecha de fin:");
		lblFechaDeFin.setBounds(12, 247, 123, 15);
		contentPanel.add(lblFechaDeFin);
		
		lblFechaDeInicio = new JLabel("Fecha de inicio:");
		lblFechaDeInicio.setBounds(12, 220, 123, 15);
		contentPanel.add(lblFechaDeInicio);
		
		textPane = new JTextPane();
		textPane.setBounds(12, 67, 413, 141);
		contentPanel.add(textPane);
		
		textInicio = new JTextField();
		textInicio.setBounds(130, 248, 114, 19);
		contentPanel.add(textInicio);
		textInicio.setColumns(10);
		
		textFin = new JTextField();
		textFin.setBounds(130, 218, 114, 19);
		contentPanel.add(textFin);
		textFin.setColumns(10);
		
		rellenarCampos(Principal.museo);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
	}
	
	/**
	 * Rellena los campos del JDialog con los datos de la exposición.
	 * @param museo
	 */
	private void rellenarCampos(GestionMuseo museo) {
		textNombreExp.setText(museo.getNombreExposicion());
		textPane.setText(museo.getDescripcionExposicion());
		textFin.setText(museo.formatearFecha(museo.getFechaInicio()));
		textInicio.setText(museo.formatearFecha(museo.getFechaFin()));
	}
}
