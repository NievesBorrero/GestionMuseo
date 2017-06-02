package gestionMuseo.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
/**
 * Informa acerca del proyecto.
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */
public class AcercaDe extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private URI web;
	private final JPanel contentPanel = new JPanel();
		
	private JLabel lblAutora;
	private JLabel lblAsignatura;
	private JLabel lblTitulo;
	private JButton btnirAllink;
	private JPanel buttonPane;
	private JButton aceptar;
	
	/**
	 * Permite crear el diálogo.
	 */
	public AcercaDe() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setModal(true);
		setResizable(false);
		setTitle("Acerca del proyecto");
		
		try {
			web = new URI ("https://github.com/NievesBorrero/GestionMuseo");
		} catch (URISyntaxException e) {
		}
		
		contentPanel.setLayout(null);
		lblAutora = new JLabel("Autor/a: Nieves Mar\u00EDa Borrero Barea");
		lblAutora.setHorizontalAlignment(SwingConstants.LEFT);
		lblAutora.setBounds(10, 87, 269, 14);
		contentPanel.add(lblAutora);
		
		lblAsignatura = new JLabel("Asignatura: Programaci\u00F3n");
		lblAsignatura.setHorizontalAlignment(SwingConstants.LEFT);
		lblAsignatura.setBounds(10, 144, 269, 14);
		contentPanel.add(lblAsignatura);
		
		lblTitulo = new JLabel("Título: Gestión Museo");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setBounds(10, 38, 269, 14);
		contentPanel.add(lblTitulo);
		btnirAllink = new JButton("repositorio");
		contentPanel.add(btnirAllink);
		btnirAllink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirRepositorio(web);
			}
		});
		btnirAllink.setBounds(10, 173, 116, 29);
		btnirAllink.setText("<HTML><FONT color=\"#000099\"><U>Repositorio</U></FONT></HTML>");
		btnirAllink.setHorizontalAlignment(SwingConstants.LEFT);
		btnirAllink.setBorderPainted(false);
		btnirAllink.setOpaque(false);
		btnirAllink.setBackground(Color.WHITE);
		btnirAllink.setToolTipText(web.toString());
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				aceptar = new JButton("Aceptar");
				aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				aceptar.setActionCommand("OK");
				buttonPane.add(aceptar);
				getRootPane().setDefaultButton(aceptar);
			}
		}
	}
	/**
	 * Lanza un enlace al repositorio en el navegador
	 * @param web2
	 */
	protected void abrirRepositorio(URI web2) {
		 if (Desktop.isDesktopSupported()) {
		      try {
		        Desktop.getDesktop().browse(web2);
		      } catch (IOException e) { 
		      }
		    } else {
		    	
	}
		
	}
}
