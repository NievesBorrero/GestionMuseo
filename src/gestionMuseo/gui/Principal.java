package gestionMuseo.gui;

import gestionMuseo.GestionMuseo;
import gestionMuseo.Ficheros.Fichero;
import gestionMuseo.Ficheros.Filtro;
import gestionMuseo.excepciones.NoHayFondosException;
import gestionMuseo.jerarquia.ObraDeArte;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ListIterator;
import java.awt.Color;
import java.awt.Toolkit;

/**
 * Ventana principal del programa.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */
public class Principal {

	private static final String SIN_TITULO_MUSEO = "Sin titulo: Museo";
	private JFrame frame;
	static GestionMuseo museo = new GestionMuseo();
	private JFileChooser fileChooser = new JFileChooser();
	private Filtro filtro = new Filtro(".obj", "obj");
	private String nombreMuseo;

	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenuItem mntmAbrirExpo;
	private JMenuItem mntmNu;
	private JMenuItem mntmGuardar;
	private JMenuItem mntmGuardarComo;
	private JSeparator separator_1;
	private JSeparator separator;
	private JMenuItem mntmSalir;
	private JMenu mnObras;
	private JMenuItem mntmIngresarObra;
	private JMenu mnMostrarObras;
	private JMenuItem mntmOrdenadasPorTtulo;
	private JMenuItem mntmOrdenadasPorCdigo;
	private JMenuItem mntmOrdenadasPorCoste;
	private JMenuItem mntmModificarDatosDe;
	private JMenu mnEliminarObra;
	private JMenuItem mntmPorCdigo;
	private JMenuItem mntmMostrarObrasY;
	private JMenu mnBuscar;
	private JMenuItem mntmBuscarPorNombre;
	private JMenu mnBuscarPorTipo;
	private JMenuItem mntmPintura_1;
	private JMenuItem mntmGrabado_1;
	private JMenuItem mntmEscultura_1;
	private JMenuItem mntmDibujo_1;
	private JMenu mnExposicion;
	private JMenuItem mntmVisitarExposicion;
	private JMenuItem mntmOrganizarExposicion;
	private JMenuItem mntmExponerObras;
	private JMenuItem mntmDatosDeLa;
	private JMenuItem mntmClausurarExposicin;
	private JMenu mnDptoRestauracin;
	private JMenuItem mntmRestaurarObra;
	private JMenu mnAyuda;
	private JMenuItem mntmAcercaDe;
	private JMenuItem mntmAyuda;
	

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


	public Principal() {
		initialize();
	}

	/**
	 * Inicializa los contenidos de la ventana.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/imagenes/icon.jpeg")));
		frame.getContentPane().setForeground(Color.BLACK);

		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setResizable(false);

		frame.setTitle(SIN_TITULO_MUSEO);

		crearBarraDeMenu();

		try {
			BufferedImage imagen = null;
			imagen = ImageIO.read(new File(getClass().getResource(
					"/imagenes/museo.jpeg").getFile()));
			Fondo fondo = new Fondo(imagen);
			((JComponent) frame.getContentPane()).setBorder(fondo);
			frame.getContentPane().setLayout(null);
		} catch (IOException e) {
		}

		generarMenus();

		/*
		 * En caso de que se cierre la ventana sin guardar, se preguntará si se
		 * desea guardar antes.
		 */
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				salir();
			}
		});
	}

	/**
	 * Genera los distintos menús de que se compone la barra de menú.
	 */
	private void generarMenus() {
		menuArchivo();
		menuObras();
		menuBuscar();
		menuExposicion();
		menuRestauración();
		menuAyuda();
	}

	/**
	 * Crea el menú de ayuda.
	 */
	private void menuAyuda() {
		mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		mnAyuda.setMnemonic('Y');

		acercaDe();
		ayuda();
	}

	/**
	 * Crea un JMenuItem que abre un diálogo de ayuda al usuario.
	 */
	private void ayuda() {
		mntmAyuda = new JMenuItem("Ayuda");
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
	 * Crea un JMenuItem que abre un diálogo que informa del programa.
	 */
	private void acercaDe() {
		mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcercaDe acerca = new AcercaDe();
				acerca.setVisible(true);
			}
		});
		mnAyuda.add(mntmAcercaDe);
	}

	/**
	 * Crea el menú de restauración con todos sus elementos y eventos.
	 */
	private void menuRestauración() {
		mnDptoRestauracin = new JMenu("Dpto. Restauraci\u00F3n");
		mnDptoRestauracin.setMnemonic('R');
		menuBar.add(mnDptoRestauracin);

		restaurar();
	}

	/**
	 * Crea un JMenuItem para restaurar obras.
	 */
	private void restaurar() {
		mntmRestaurarObra = new JMenuItem("Restaurar obra");
		mntmRestaurarObra.setMnemonic(KeyEvent.VK_R);
		mntmRestaurarObra.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mntmRestaurarObra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (museo.comprobarObrasDaniadas()) {
					try {
						museo.ordenarPorCodigo();
						Restaurar obrasDaniadas = new Restaurar(museo.getList());
						obrasDaniadas.setVisible(true);
					} catch (NoHayFondosException exc) {
						JOptionPane.showMessageDialog(frame, exc.getMessage(),
								"Error", JOptionPane.ERROR_MESSAGE);
					}

				} else
					JOptionPane.showMessageDialog(frame,
							"No hay obras dañadas", "Error",
							JOptionPane.ERROR_MESSAGE);

			}
		});
		mnDptoRestauracin.add(mntmRestaurarObra);
	}

	/**
	 * Crea el menú de exposición con todos sus elementos y eventos.
	 */
	private void menuExposicion() {
		mnExposicion = new JMenu("Exposicion");
		mnExposicion.setMnemonic('E');
		menuBar.add(mnExposicion);

		organizarExposicion();
		exponerObras();
		consultarPresupuesto();
		visitarExposicion();
		datos();
		clausurarExposicion();
	}

	/**
	 * Crea un JMenuItem para clausurar la exposición.
	 */
	private void clausurarExposicion() {
		mntmClausurarExposicin = new JMenuItem("Clausurar exposición");
		mntmClausurarExposicin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (museo.isOrganizada()) {
					ClausurarExposicion clausurar = new ClausurarExposicion();
					clausurar.setVisible(true);
				} else
					JOptionPane.showMessageDialog(frame,
							"No hay ninguna exposición organizada", "Error",
							JOptionPane.ERROR_MESSAGE);

			}
		});
		mnExposicion.add(mntmClausurarExposicin);
	}

	/**
	 * Crea un JMenuItem que abre un diálogo que muestra los datos de la
	 * exposición.
	 */
	private void datos() {
		mntmDatosDeLa = new JMenuItem("Datos de la exposici\u00F3n");
		mntmDatosDeLa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (museo.isOrganizada()) {
					Datos datos = new Datos();
					datos.setVisible(true);
				} else
					JOptionPane.showMessageDialog(frame,
							"No hay ninguna exposición organizada", "Error",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		mnExposicion.add(mntmDatosDeLa);
	}

	/**
	 * Crea un JMenuItem para visitar la exposición.
	 */
	private void visitarExposicion() {
		mntmVisitarExposicion = new JMenuItem("Visitar exposici\u00F3n");
		mntmVisitarExposicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarObrasMuseo visitarExposicion;
				try {
					if (museo.isOrganizada()) {
						visitarExposicion = new MostrarObrasMuseo(museo
								.listExpuestas());
						visitarExposicion.setVisible(true);
						visitarExposicion.setTitle("visitando...");
					} else
						JOptionPane.showMessageDialog(frame,
								"No hay ninguna exposición organizada",
								"Error", JOptionPane.ERROR_MESSAGE);
				} catch (NoHayFondosException e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		mnExposicion.add(mntmVisitarExposicion);
	}

	/**
	 * Crea un JMenuItem para consultar el presupuesto.
	 */
	private void consultarPresupuesto() {
	}

	/**
	 * Crea un JMenuItem para exponer las obras.
	 */
	private void exponerObras() {
	}

	/**
	 * Crea un JMenuItem para organizar una exposición.
	 */
	private void organizarExposicion() {
		mntmOrganizarExposicion = new JMenuItem("Organizar exposición");
		mntmOrganizarExposicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (museo.isOrganizada()) {
					JOptionPane.showMessageDialog(frame,
							"Ya hay una exposici\u00f3n organizada", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (museo.comprobarSiObrasExpuestas()) {
					OrganizarExposicion organizar = new OrganizarExposicion(
							museo);
					organizar.setVisible(true);
				} else
					JOptionPane.showMessageDialog(frame,
							"No hay obras expuestas,\n ¡debe exponerlas antes!",
							"Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		JMenuItem mntmConsultarPresupuesto = new JMenuItem(
				"Consultar presupuesto");
		mntmConsultarPresupuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame, "Presupuesto del museo: "
						+ (double)Math.round(Principal.museo.getPresupuesto()*100)/100, "Presupuesto",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnExposicion.add(mntmConsultarPresupuesto);
		mntmExponerObras = new JMenuItem("Exponer obras");
		mntmExponerObras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					museo.ordenarPorCodigo();
					ExponerObras exponer = new ExponerObras(museo.getList());
					exponer.setVisible(true);
				} catch (NoHayFondosException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		mnExposicion.add(mntmExponerObras);
		mnExposicion.add(mntmOrganizarExposicion);
	}

	/**
	 * Crea el menú de buscar con todos sus elementos y eventos.
	 */
	private void menuBuscar() {
		mnBuscar = new JMenu("Buscar");
		mnBuscar.setMnemonic('B');
		menuBar.add(mnBuscar);

		buscarPorNombre();
		buscarPorTipo();
	}

	/**
	 * Crea un menú para buscar por tipo.
	 */
	private void buscarPorTipo() {
		mnBuscarPorTipo = new JMenu("Buscar por tipo de obra");
		mnBuscar.add(mnBuscarPorTipo);

		buscarPinturas();
		buscarEsculturas();
		buscarGrabados();
		buscarDibujos();
	}

	/**
	 * Crea un JMenuItem para buscar dibujos.
	 */
	private void buscarDibujos() {
		mntmDibujo_1 = new JMenuItem("Dibujo");
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
	}

	/**
	 * Crea un JMenuItem para buscar grabados.
	 */
	private void buscarGrabados() {
		mntmGrabado_1 = new JMenuItem("Grabado");
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
	}

	/**
	 * Crea un JMenuItem para buscar esculturas.
	 */
	private void buscarEsculturas() {
		mntmEscultura_1 = new JMenuItem("Escultura");
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
	}

	/**
	 * Crea un JMenuItem para buscar pinturas.
	 */
	private void buscarPinturas() {
		mntmPintura_1 = new JMenuItem("Pintura");
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
	}

	/**
	 * Crea un JMenuItem para buscar por nombre.
	 */
	private void buscarPorNombre() {
		mntmBuscarPorNombre = new JMenuItem("Buscar por título");
		mntmBuscarPorNombre.setMnemonic(KeyEvent.VK_B);
		mntmBuscarPorNombre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mntmBuscarPorNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarPorTitulo buscar = new BuscarPorTitulo();
				buscar.setVisible(true);
			}
		});
		mnBuscar.add(mntmBuscarPorNombre);
	}

	/**
	 * Crea el menú de obras con todos sus elementos y eventos.
	 */
	private void menuObras() {
		mnObras = new JMenu("Obras");
		mnObras.setMnemonic('O');
		menuBar.add(mnObras);

		menuIngresarObras();
		menuMostrar();
		menuModificar();
		menuEliminar();
	}

	/**
	 * Crea un menú para eliminar obras del museo.
	 */
	private void menuEliminar() {
		mnEliminarObra = new JMenu("Eliminar obra");
		mnObras.add(mnEliminarObra);

		EliminarPorCodigo();
		EliminarMostrar();
	}
	
	/**
	 * Crea un JMenuItem para eliminar mostrando las obras.
	 */
	private void EliminarMostrar() {
		mntmMostrarObrasY = new JMenuItem("Mostrando obras");
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
	}
	
	/**
	 * Crea un JMenuItem para eliminar por código.
	 */
	private void EliminarPorCodigo() {
		mntmPorCdigo = new JMenuItem("Por código");
		mntmPorCdigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EliminarPorCodigo eliminarPorCodigo = new EliminarPorCodigo();
				eliminarPorCodigo.setVisible(true);
			}
		});
		mnEliminarObra.add(mntmPorCdigo);
	}

	/**
	 * Crea un JMenuItem para modificar las obras del museo.
	 */
	private void menuModificar() {
		mntmModificarDatosDe = new JMenuItem("Modificar datos");
		mntmModificarDatosDe.setMnemonic(KeyEvent.VK_D);
		mntmModificarDatosDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		mntmModificarDatosDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ModificarObra modificar = new ModificarObra(museo.getList());
					modificar.setVisible(true);
				} catch (NoHayFondosException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnObras.add(mntmModificarDatosDe);
	}

	/**
	 * Crea un menú para mostrar las obras del museo.
	 */
	private void menuMostrar() {
		mnMostrarObras = new JMenu("Mostrar obras");
		mnObras.add(mnMostrarObras);

		ordenarPorTitulo();
		ordenarPorCodigo();
		ordenarPorCosteDeRestauracion();
	}

	/**
	 * Crea un JMenuItem para mostrar las obras ordenadas por coste de
	 * restauración.
	 */
	private void ordenarPorCosteDeRestauracion() {
		mntmOrdenadasPorCoste = new JMenuItem(
				"Ordenadas por coste de restauración");
		mntmOrdenadasPorCoste.setMnemonic(KeyEvent.VK_R);
		mntmOrdenadasPorCoste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_MASK));
		mntmOrdenadasPorCoste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				museo.ordenarPorcosteRestauracion();
				try {
					MostrarObrasMuseo mostrar = new MostrarObrasMuseo(museo
							.getList());
					mostrar.setVisible(true);
				} catch (NoHayFondosException e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnMostrarObras.add(mntmOrdenadasPorCoste);
	}
	
	/**
	 * Crea un JMenuItem para mostrar las obras ordenadas por código.
	 */
	private void ordenarPorCodigo() {
		mntmOrdenadasPorCdigo = new JMenuItem("Ordenadas por código ");
		mntmOrdenadasPorCdigo.setMnemonic(KeyEvent.VK_C);
		mntmOrdenadasPorCdigo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.SHIFT_MASK));
		mntmOrdenadasPorCdigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				museo.ordenarPorCodigo();
				try {
					MostrarObrasMuseo mostrar = new MostrarObrasMuseo(museo
							.getList());
					mostrar.setVisible(true);
				} catch (NoHayFondosException e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		mnMostrarObras.add(mntmOrdenadasPorCdigo);
	}
	
	/**
	 * Crea un JMenuItem para mostrar las obras ordenadas por título.
	 */
	private void ordenarPorTitulo() {
		mntmOrdenadasPorTtulo = new JMenuItem("Ordenadas por título");
		mntmOrdenadasPorTtulo.setMnemonic(KeyEvent.VK_T);
		mntmOrdenadasPorTtulo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.SHIFT_MASK));
		mntmOrdenadasPorTtulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				museo.ordenarPorTitulo();
				try {
					MostrarObrasMuseo mostrar = new MostrarObrasMuseo(museo
							.getList());
					mostrar.setVisible(true);
				} catch (NoHayFondosException e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnMostrarObras.add(mntmOrdenadasPorTtulo);
	}
	
	/**
	 * Crea un JMenuItem ingresar nuevas obras.
	 */
	private void menuIngresarObras() {
		mntmIngresarObra = new JMenuItem("Ingresar obra");
		mntmIngresarObra.setMnemonic(KeyEvent.VK_I);
		mntmIngresarObra.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mntmIngresarObra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IngresarObras ingresos = new IngresarObras();
				ingresos.setVisible(true);
			}
		});
		mnObras.add(mntmIngresarObra);
	}

	/**
	 * Crea el menú de archivo con todos sus elementos y eventos.
	 */
	private void menuArchivo() {
		mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('A');
		menuBar.add(mnArchivo);
		mnArchivo.setToolTipText("Archivo");

		menuAbrir();
		menuNuevo();

		separator = new JSeparator();
		mnArchivo.add(separator);

		menuGuardar();
		menuGuardarComo();

		separator_1 = new JSeparator();
		mnArchivo.add(separator_1);

		menuSalir();
	}

	/**
	 * Crea un JMenuItem para salir del museo.
	 */

	private void menuSalir() {
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salir();
			}
		});
		mnArchivo.add(mntmSalir);
	}

	/**
	 * Crea un JMenuItem para guardar un museo con el nombre indicado.
	 */
	private void menuGuardarComo() {
		mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarComo();
			}
		});
		mnArchivo.add(mntmGuardarComo);
	}

	/**
	 * Crea un JMenuItem para guardar un museo.
	 */
	private void menuGuardar() {
		mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.setMnemonic(KeyEvent.VK_G);
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		mnArchivo.add(mntmGuardar);
	}

	/**
	 * Crea un JMenuItem para crear un nuevo museo.
	 */
	private void menuNuevo() {
		mntmNu = new JMenuItem("Nuevo Museo");
		mntmNu.setMnemonic(KeyEvent.VK_N);
		mntmNu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmNu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevoMuseo();
			}
		});
		mnArchivo.add(mntmNu);
		mnArchivo.add(mntmAbrirExpo);
	}

	/**
	 * Crea un JMenuItem para abrir un museo existente.
	 */
	private void menuAbrir() {
		mntmAbrirExpo = new JMenuItem("Abrir museo");
		mntmAbrirExpo.setMnemonic(KeyEvent.VK_A);
		mntmAbrirExpo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK));
		mntmAbrirExpo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
	}

	/**
	 * Crea la barra de menú y la añade al contentPane.
	 */
	private void crearBarraDeMenu() {
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 538, 21);
		frame.getContentPane().add(menuBar);
	}

	/**
	 * Crea un nuevo museo vacio.
	 */
	private void nuevoMuseo() {
		if (museo.isModificado()) {
			if (JOptionPane
					.showConfirmDialog(
							null,
							"Ha hecho cambios... \n¿seguro que quiere abrir un nuevo fichero?",
							"Museo modificado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				museo = new GestionMuseo();
				frame.setTitle(SIN_TITULO_MUSEO);
			}
		} else {
			museo = new GestionMuseo();
			frame.setTitle(SIN_TITULO_MUSEO);
		}
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
			museo.setModificado(false);
		} else {
			try {
				Fichero.guardar(museo);
				museo.setModificado(false);
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
				
				int respuesta = JOptionPane.showOptionDialog(null,
						"El archivo ya existe, ¿deseas sobreescribirlo?",
						"Guardando", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE, null,null,null);
				if (respuesta == JOptionPane.YES_OPTION) {
					try {
						Fichero.guardarComo(museo,
								Fichero.getFichero());
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
			museo.setModificado(false);
		}
	}

	/**
	 * Abre un archivo existente
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void abrir() {
		if (museo.isModificado()) {
			switch (JOptionPane.showOptionDialog(null, "¿Desea Guardar?",
					"Archivo sin guardar", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, null, null, null)) {
			case JOptionPane.YES_OPTION:
				guardarComo();
				break;
			case JOptionPane.CANCEL_OPTION:
				return;
			default:
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
	 * Método que crea un FileChooser
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
			museo = Fichero.abrir(fileChooser.getSelectedFile());
			actualizarUltimoCodigo();
		}
	}
	/**
	 * Actualiza el codigo identificador al cargar los fondos
	 */
	private void actualizarUltimoCodigo() {
		
		for (ObraDeArte obra: museo.getListaMuseo()){
			if (obra.getCodigo()>museo.getMuseo().getUltimoCodigo())
				museo.getMuseo().setUltimoCodigo(obra.getCodigo()+1);
		}			
	}
		


	/**
	 * Permite salir del programa comprobando si hay archivos modificados, si es
	 * así pregunta si se desea guardar antes de salir.
	 */
	private void salir() {
		if (museo.isModificado()) {
			switch(JOptionPane.showOptionDialog(null,
					"No ha guardado, ¿Desea Guardar?", "Archivo sin guardar",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, null, null, null)){
					case JOptionPane.YES_OPTION:
						guardarComo();
					case JOptionPane.NO_OPTION:
						System.exit(0);
					case JOptionPane.CANCEL_OPTION:
						break;
			}
		} 
		else {
			System.exit(0);
		}
	}
}
