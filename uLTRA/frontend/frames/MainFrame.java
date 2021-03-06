/**
 * 
 */
package frames;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import panels.*;

/**
 * @author Sebastian Kiepert
 *
 */
public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MainFrame _mainFrame;
	private static JDesktopPane desktop;
	private static Point location;
	private Image img;
	private boolean imgLoad = true;
	
	/**
	 * erzeugt ein rahmenloses Fenster der Gr��e 800*600
	 * @author Sebastian Kiepert
	 * @version 2.0
	 * @see frames.MainFrame#init()
	 */
	public MainFrame(){
		ImageIcon icon = new ImageIcon("../uLTRA/Documents/images/icons/lightning.png");
		MainFrame._mainFrame = this;
		this.setSize(900,680);
		this.setIconImage(icon.getImage());
		this.setTitle("uLTRA - The Lightbeam Game");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		location = this.getLocation();
	}
	
	
	/**
	 * initialisiert das Anwendungsfenster und f�gt einen Desktop hinzu, auf dem weiterhin gearbeitet wird.
	 * Es werden alle internen Fenster initialisiert und dem Desktop hinzugef�gt. Zus�tzlich wird ein
	 * Hintergrundbild geladen
	 * @author Sebastian Kiepert
	 * @see frames.MenuFrame#MenuFrame(MainFrame)
	 * @see frames.GameFrame#GameFrame(MainFrame)
	 * @see frames.EditorFrame#EditorFrame(MainFrame)
	 * 
	 */
	public void init(){		
		desktop = setDesktopBackground();
		desktop.add(new MenuPanel());
		desktop.setVisible(true);
		this.add(desktop);
		this.setVisible(true);
	}
	
	/**
     * erzeugt das Hintergrundbild des Hauptfensters, das im Hauptmen� zu sehen ist
	 *
	 */
	private JDesktopPane setDesktopBackground(){
		File imgFile = new File("../uLTRA/Documents/images/light.jpg");
		try {
			img = ImageIO.read(imgFile);
		} catch (IOException e) {
//			e.printStackTrace();
			imgLoad = false;
		}
		JDesktopPane desk = new JDesktopPane()
		{
	        
			private static final long serialVersionUID = 1L;	
			Image scaledImg = (imgLoad)? img.getScaledInstance(800, 600, Image.SCALE_SMOOTH) : null;
			public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            if (scaledImg != null) g.drawImage(scaledImg,0,0,this.getWidth(),this.getHeight(),this);
	        }
		};
		return desk;
	}
	
	public static MainFrame getMainFrame(){
		return MainFrame._mainFrame;
	}
	
	public void addToDesktop(Component comp){
		desktop.add(comp);
	}
	
	public void removeFromDesktop(int index){
		desktop.remove(index);
	}
	
	public void refreshDesktop(){
		desktop.setVisible(false);
		desktop.setVisible(true);
	}
	
	public static Dimension getDesktopSize(){
		return desktop.getSize();
	}
	
	public static JDesktopPane getDesktop(){
		return desktop;
	}
	
	public static Point getFrameLocation(){
		return location;
	}
}
