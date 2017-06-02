package gestionMuseo.gui;

	import java.awt.Component;
	import java.awt.Graphics;
	import java.awt.Insets;
	import java.awt.image.BufferedImage;

	import javax.swing.border.Border;
/**
 * Clase que permite crear una imagen de fondo.
 * 
 * @author Nieves María Borrero Barea.
 * @version 1.0
 */
	public class Fondo implements Border {
		
		private BufferedImage fondo = null;

		public Fondo(BufferedImage bImage) {
			fondo = bImage;
		}

		public void paintComponent(Graphics g) {
			
			
		}

		@Override
		public Insets getBorderInsets(Component arg0) {
			return new Insets(0, 0, 0, 0);
		}

		@Override
		public boolean isBorderOpaque() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void paintBorder(Component arg0, Graphics g, int arg2, int arg3, int width, int heigth) {
			
			if(fondo!= null){
				g.drawImage(fondo, 0, 0, width, heigth, null);
			}
			
	}
}
