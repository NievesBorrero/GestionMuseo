package gestionMuseo.gui;

import gestionMuseo.excepciones.ObraNoExpuestaException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Rectangle;

import javax.swing.JScrollPane;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * JDialog que imprime el presupuesto tras una exposición y permite clausurarla,
 * devolviendo las obras expuestas al almacén.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */
public class ClausurarExposicion extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JTextPane textPane;
	private JLabel lblPresupuesto;
	private JScrollPane scrollPane;
	private JButton btnAceptar;

	ClausurarExposicion() {
		setResizable(false);
		setModal(true);
		setBounds(new Rectangle(65, 24, 400, 400));
		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 32, 360, 176);
		getContentPane().add(scrollPane);

		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setText(imprimirPresupuesto());

		lblPresupuesto = new JLabel("Presupuesto:");
		lblPresupuesto.setBounds(12, 0, 97, 15);
		getContentPane().add(lblPresupuesto);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(12, 220, 117, 25);
		getContentPane().add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Principal.museo.clausurarExposicion();
					Principal.museo.setModificado(true);
					JOptionPane.showMessageDialog(
							contentPanel,
							"Presupuesto del museo:"
									+ Principal.museo.getPresupuesto(),
							"Exposicion clausurada",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (ObraNoExpuestaException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					// No va a entrar, QUITAR ESTA EXCEPCIÓN??
				}
				setVisible(false);
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(255, 220, 117, 25);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		getContentPane().setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { scrollPane,
						textPane, lblPresupuesto, btnAceptar }));

	}

	/**
	 * Imprime gastos, ingresos y presupuesto resultante de haber organizado una
	 * exposición.
	 * 
	 * @return cadena.
	 */
	private String imprimirPresupuesto() {
		return Principal.museo.imprimirPresupuestoExpuestas()
				+ "\n"
				+ Principal.museo.calcularGastoSalas()
				+ "\n"
				+ Principal.museo.imprimirPresupuestoRestauradas()
				+ "\n"
				+ "\ndias de exposicion "
				+ Principal.museo.calcularDiasExposicion()
				+ "\n"
				+ "\nEntradas vendidas: "
				+ Principal.museo.generarEntradas()
				+ "\n"
				+ "\nPresupuesto final: "
				+ Principal.museo.calcularPresupuesto(
						Principal.museo.getGastos(),
						Principal.museo.getIngresos());
	}
}
