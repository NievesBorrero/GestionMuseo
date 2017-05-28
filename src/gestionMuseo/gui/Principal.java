package gestionMuseo.gui;

import gestionMuseo.GestionMuseo;
import gestionMuseo.Fondos;
import gestionMuseo.Ficheros.Fichero;
import gestionMuseo.Ficheros.Filtro;
import gestionMuseo.enumeraciones.EstiloArtistico;
import gestionMuseo.enumeraciones.MaterialEscultura;
import gestionMuseo.enumeraciones.MaterialPintura;
import gestionMuseo.enumeraciones.Soporte;
import gestionMuseo.enumeraciones.TecnicaDeDibujo;
import gestionMuseo.enumeraciones.TipoDeGrabado;
import gestionMuseo.enumeraciones.TipoEscultura;
import gestionMuseo.excepciones.AutorNoValidoException;
import gestionMuseo.excepciones.EstiloNoValidoException;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.excepciones.TituloNoValidoException;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Principal {

	private static final String SIN_TITULO_MUSEO = "Sin titulo: Museo";
	private JFrame frame;
	static GestionMuseo museo = new GestionMuseo();
	static { // BORRARRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
		try {
			museo.ingresarPintura("Pintura1", "autor1", "sitio",
					EstiloArtistico.BARROCO, true, "persona", 0.5, 0.5,
					Soporte.LIENZO, MaterialPintura.OLEO, 0.20, 0.10);
			museo.ingresarPintura("Pintura2", "autor2", "sitio",
					EstiloArtistico.BARROCO, true, "persona", 0.5, 0.5,
					Soporte.LIENZO, MaterialPintura.OLEO, 0.20, 0.10);
			museo.ingresarPintura("Pintura3", "autor3", "sitio",
					EstiloArtistico.BARROCO, true, "persona", 0.5, 0.5,
					Soporte.LIENZO, MaterialPintura.OLEO, 0.20, 0.10);
			museo.ingresarEscultura("Escultura", "autor", "calle",
					EstiloArtistico.RENACIMIENTO, true, "persona", 0.5, 0.4,
					TipoEscultura.BULTO_REDONDO, MaterialEscultura.ARCILLA,
					0.3, 0.4, 0.5);
			museo.ingresarGrabado("grabado", "autor", "lala",
					EstiloArtistico.EGIPCIO, true, "persona", 0.3, 0.5,
					TipoDeGrabado.CALCOGRAFIA, 0.6, 0.3);
			 museo.ingresarGrabado("grabado2", "pepe", "ronda",
			 EstiloArtistico.ISLAMICO,
			 true, "persona", 0.3, 0.5, TipoDeGrabado.LITOGRAFIA, 0.6, 0.3);
			 museo.ingresarDibujo("dibujo", "autorr", "fuente",
			 EstiloArtistico.CUBISTA,
			 false, "personita", 0.6, 0.5, TecnicaDeDibujo.CARBONCILLO, 0.10,
			 0.5);

		}catch (AutorNoValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TituloNoValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EstiloNoValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private JFileChooser fileChooser = new JFileChooser();
	private Filtro filtro = new Filtro(".obj", "obj");
	private String nombreMuseo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 538, 21);
		frame.getContentPane().add(menuBar);
		frame.setTitle(SIN_TITULO_MUSEO);
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('A');
		menuBar.add(mnArchivo);
		mnArchivo.setToolTipText("Archivo");

		JMenuItem mntmAbrirExpo = new JMenuItem("Abrir museo");
		mntmAbrirExpo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});

		JMenuItem mntmNu = new JMenuItem("Nuevo Museo");
		mnArchivo.add(mntmNu);
		mnArchivo.add(mntmAbrirExpo);

		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarComo();
			}
		});
		mnArchivo.add(mntmGuardarComo);

		JSeparator separator_1 = new JSeparator();
		mnArchivo.add(separator_1);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// SALIR
			}
		});
		mnArchivo.add(mntmSalir);

		JMenu mnObras = new JMenu("Obras");
		mnObras.setMnemonic('M');
		menuBar.add(mnObras);

		JMenuItem mntmIngresarObra = new JMenuItem("Ingresar obra");
		mntmIngresarObra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IngresarObras ingresos = new IngresarObras(museo);
				ingresos.setVisible(true);
			}
		});
		
				JMenuItem mntmNewMenuItem = new JMenuItem("Mostrar obras");
				mntmNewMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							MostrarObrasMuseo mostrar = new MostrarObrasMuseo(museo.getList());
							mostrar.setVisible(true);
						} catch (NoHayFondosException e1) {
							JOptionPane.showMessageDialog(frame, e1.getMessage(),
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				mnObras.add(mntmNewMenuItem);
		mnObras.add(mntmIngresarObra);
		
		JMenuItem mntmModificarDatosDe = new JMenuItem("Modificar datos");
		mntmModificarDatosDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ModificarObra modificar= new ModificarObra(museo.getList());
					modificar.setVisible(true);
				} catch (NoHayFondosException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnObras.add(mntmModificarDatosDe);

		JMenu mnEliminarObra = new JMenu("Eliminar obra");
		mnObras.add(mnEliminarObra);

		JMenuItem mntmPorCdigo = new JMenuItem("Por código");
		mntmPorCdigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EliminarPorCodigo eliminarPorCodigo = new EliminarPorCodigo(
						museo);
				eliminarPorCodigo.setVisible(true);
			}
		});
		mnEliminarObra.add(mntmPorCdigo);

		JMenuItem mntmMostrarObrasY = new JMenuItem("Mostrando obras");
		mntmMostrarObrasY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MostrarEliminar eliminar;
				try {
					eliminar = new MostrarEliminar(museo.getList());
					eliminar.setVisible(true);
				} catch (NoHayFondosException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnEliminarObra.add(mntmMostrarObrasY);

		JMenu mnBuscar = new JMenu("Buscar");
		mnBuscar.setMnemonic('B');
		menuBar.add(mnBuscar);

		JMenuItem mntmBuscarPorNombre = new JMenuItem("Buscar por nombre");
		mnBuscar.add(mntmBuscarPorNombre);

		JMenu mnBuscarPorTipo = new JMenu("Buscar por tipo de obra");
		mnBuscar.add(mnBuscarPorTipo);

		JMenuItem mntmPintura_1 = new JMenuItem("Pintura");
		mntmPintura_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MostrarObrasMuseo mostrarPinturas = new MostrarObrasMuseo(
							museo.getListPintura());
					mostrarPinturas.setTitle("Mostrando Pinturas...");
					mostrarPinturas.setVisible(true);
				} catch (NoHayFondosException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		mnBuscarPorTipo.add(mntmPintura_1);

		JMenuItem mntmEscultura_1 = new JMenuItem("Escultura");
		mntmEscultura_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MostrarObrasMuseo mostrarEsculturas = new MostrarObrasMuseo(
							museo.getListEscultura());
					mostrarEsculturas.setTitle("Mostrando Esculturas...");
					mostrarEsculturas.setVisible(true);
				} catch (NoHayFondosException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnBuscarPorTipo.add(mntmEscultura_1);

		JMenuItem mntmGrabado_1 = new JMenuItem("Grabado");
		mntmGrabado_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MostrarObrasMuseo mostrarGrabados = new MostrarObrasMuseo(
							museo.getListGrabado());
					mostrarGrabados.setTitle("Mostrando Grabados...");
					mostrarGrabados.setVisible(true);
				} catch (NoHayFondosException e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnBuscarPorTipo.add(mntmGrabado_1);

		JMenuItem mntmDibujo_1 = new JMenuItem("Dibujo");
		mntmDibujo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MostrarObrasMuseo mostrarDibujos = new MostrarObrasMuseo(
							museo.getListDibujo());
					mostrarDibujos.setTitle("Mostrando Dibujos...");
					mostrarDibujos.setVisible(true);
				} catch (NoHayFondosException e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnBuscarPorTipo.add(mntmDibujo_1);

		JMenuItem mntmBuscarPorEstilo = new JMenuItem(
				"Buscar por estilo art\u00EDstico");
		mntmBuscarPorEstilo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}

		});
		mnBuscar.add(mntmBuscarPorEstilo);

		JMenu mnExposicion = new JMenu("Exposicion");
		mnExposicion.setMnemonic('E');
		menuBar.add(mnExposicion);

		JMenuItem mntmOrganizarExposicion = new JMenuItem(
				"Organizar exposicion");
		mntmOrganizarExposicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (museo.comprobarSiObrasExpuestas()) {
					OrganizarExposicion organizar = new OrganizarExposicion(
							museo);
					organizar.setVisible(true);
				} else
					JOptionPane.showMessageDialog(frame,
							"No hay obras expuestas, debes exponerlas antes!",
							"Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		JMenuItem mntmExponerObras = new JMenuItem("Exponer obras");
		mntmExponerObras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExponerObras exponer;
				try {
					exponer = new ExponerObras(museo.getList());
					exponer.setVisible(true);
				} catch (NoHayFondosException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		mnExposicion.add(mntmExponerObras);
		mnExposicion.add(mntmOrganizarExposicion);

		JMenuItem mntmVisitarExposicion = new JMenuItem("Visitar exposicion");
		mntmVisitarExposicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarObrasMuseo visitarExposicion;
				try {
					if (museo.isOrganizada()) {
						visitarExposicion = new MostrarObrasMuseo(museo.listExpuestas());
						visitarExposicion.setVisible(true);
					} else
						JOptionPane.showMessageDialog(frame,
								"No hay ninguna exposicion organizada",
								"Error", JOptionPane.ERROR_MESSAGE);
				} catch (NoHayFondosException e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		JMenuItem mntmDatosDeLa = new JMenuItem("Datos de la exposici\u00F3n");
		mnExposicion.add(mntmDatosDeLa);
		mnExposicion.add(mntmVisitarExposicion);

		JMenuItem mntmClausurarExposicin = new JMenuItem("Clausurar exposición");
		mntmClausurarExposicin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (museo.isOrganizada()) {
					ClausurarExposicion clausurar = new ClausurarExposicion();
					clausurar.setVisible(true);
				} else
					JOptionPane.showMessageDialog(frame,
							"No hay ninguna exposicion organizada",
							"Error", JOptionPane.ERROR_MESSAGE);
				
			}
		});
		mnExposicion.add(mntmClausurarExposicin);

		JMenu mnDptoRestauracin = new JMenu("Dpto. Restauraci\u00F3n");
		mnDptoRestauracin.setMnemonic('R');
		menuBar.add(mnDptoRestauracin);

		JMenuItem mntmRestaurarObra = new JMenuItem("Restaurar obra");
		mntmRestaurarObra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (museo.comprobarObrasDaniadas()) {
					RestaurarObras obrasDaniadas;
					try {
						obrasDaniadas = new RestaurarObras(museo.getList());
						obrasDaniadas.setVisible(true);
					} catch (NoHayFondosException exc) {
						JOptionPane.showMessageDialog(frame, exc.getMessage(),
								"Error", JOptionPane.ERROR_MESSAGE);
					}

				} else
					JOptionPane.showMessageDialog(frame,
							"No hay obras daniadas", "Error",
							JOptionPane.ERROR_MESSAGE);

			}
		});
		mnDptoRestauracin.add(mntmRestaurarObra);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		mnAyuda.setMnemonic('Y');

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcercaDe acerca = new AcercaDe();
				acerca.setVisible(true);
			}
		});
		mnAyuda.add(mntmAcercaDe);

		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ayuda ayuda = Ayuda.getInstance();
				ayuda.setVisible(true);
			}
		});
		mnAyuda.add(mntmAyuda);
	}

	/**
	 * Permite guardar un fichero
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void guardar() {
		if (Fichero.FICHERO.getName().equalsIgnoreCase(nombreMuseo)) {
			guardarComo();
			museo.setModificada(false);
		} else {
			try {
				Fichero.guardar(museo);
				museo.setModificada(false);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Error al guardar",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * Permite guardar como...
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void guardarComo() {
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(filtro);

		if (JFileChooser.APPROVE_OPTION == fileChooser.showDialog(fileChooser,
				"Guardar Archivo")) {
			fileChooser.setAcceptAllFileFilterUsed(false);
			Fichero.comprobarNombre(fileChooser.getSelectedFile());
			if (Fichero.getFichero().exists()) {
				Object[] options = { "Si", "No" };
				int respuesta = JOptionPane.showOptionDialog(null,
						"El archivo ya existe, ¿deseas sobreescribirlo?",
						"Guardando", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				if (respuesta == JOptionPane.YES_OPTION) {
					try {
						Fichero.guardarComo(museo, Fichero.getFichero());
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null,
								"Error al guardar el archivo", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			} else {
				guardar();
			}

			frame.setTitle(Fichero.getFichero().getName());
			museo.setModificada(false);
		}
	}

	/**
	 * Abre un archivo existente
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void abrir() {
		if (museo.isModificada()) {
			Object[] options = { "SI", "NO", "CANCELAR" };
			int respuesta = JOptionPane.showOptionDialog(null,
					"¿Deseas Guardar?", "Archivo sin guardar",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
			if (respuesta == JOptionPane.YES_OPTION)
				guardarComo();
			else {
				try {
					abrirFichero();
				} catch (IOException | ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null,
							"Error al abrir fichero", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					Fichero.nuevo();
				}
			}
		} else {
			try {
				abrirFichero();
			} catch (IOException | ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(null, "Error al abrir fichero",
						"ERROR", JOptionPane.ERROR_MESSAGE);
				Fichero.nuevo();
			}

		}
	}

	/**
	 * Metodo que crea un FileChooser
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void abrirFichero() throws FileNotFoundException,
			ClassNotFoundException, IOException {
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(filtro);
		if (fileChooser.showDialog(fileChooser, "Abrir Fichero") == JFileChooser.APPROVE_OPTION) {
			Fichero.FICHERO = fileChooser.getSelectedFile();
			museo = (GestionMuseo) Fichero.abrir(fileChooser.getSelectedFile());	
			frame.setTitle(Fichero.getFichero().getName());
			JOptionPane.showMessageDialog(null, "Cargado con exito");
}
		}
	}

