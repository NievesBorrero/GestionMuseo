package gestionMuseo.gui;

import gestionMuseo.GestionMuseo;
import gestionMuseo.excepciones.FechaFinException;
import gestionMuseo.excepciones.FechaPasadaException;
import gestionMuseo.excepciones.YaHayExposicionException;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrganizarExposicion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Create the dialog.
	 */
	public OrganizarExposicion(GestionMuseo exposicion) {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("T\u00EDtulo de la Exposici\u00F3n");
		lblNewLabel.setBounds(12, 12, 198, 15);
		contentPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(198, 10, 216, 19);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setBounds(12, 39, 125, 15);
		contentPanel.add(lblDescripcion);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(12, 66, 402, 84);
		contentPanel.add(textPane);

		JLabel lblFe = new JLabel("Fecha de inicio");
		lblFe.setBounds(12, 180, 105, 15);
		contentPanel.add(lblFe);

		JLabel lblFechaDeFin = new JLabel("Fecha de fin");
		lblFechaDeFin.setBounds(12, 207, 95, 15);
		contentPanel.add(lblFechaDeFin);

		JSpinner spinicio = new JSpinner();
		spinicio.setBounds(134, 205, 95, 20);
		spinicio.setModel(new SpinnerDateModel());
		spinicio.setEditor(new JSpinner.DateEditor(spinicio, "dd/MM/yyyy"));
		contentPanel.add(spinicio);

		JSpinner spfin = new JSpinner();
		spfin.setBounds(135, 178, 94, 20);
		spfin.setModel(new SpinnerDateModel());
		spfin.setEditor(new JSpinner.DateEditor(spfin, "dd/MM/yyyy"));
		contentPanel.add(spfin);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LocalDate fechaInicio = getFecha(spinicio);
					LocalDate fechaFin = getFecha(spfin);
					exposicion.organizarExposicion(textField.getText(), textPane.getText(), fechaInicio, fechaFin);
					setVisible(false);
					exposicion.setModificada(true);
				} catch (YaHayExposicionException e) {
					JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (FechaFinException e) {
					JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}catch (FechaPasadaException e) {
					JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			}
		});
		btnAceptar.setBounds(12, 247, 95, 25);
		contentPanel.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(144, 247, 105, 25);
		contentPanel.add(btnCancelar);
	}

	public LocalDate getFecha(JSpinner spinner) {
		Calendar calendar = Calendar.getInstance(); // toma el día de hoy
		calendar.setTime((Date) spinner.getModel().getValue()); // date que me devuelve.
		LocalDate fecha = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH)); // A partir de date creo una localdate.
		return fecha;
	}
}
