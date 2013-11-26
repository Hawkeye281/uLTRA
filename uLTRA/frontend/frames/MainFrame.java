/**
 * 
 */
package frames;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

/**
 * @author Sebastian Kiepert
 *
 */
public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktop;
	private MenuFrame menuFrame = new MenuFrame(this);
	private GameFrame gameFrame = new GameFrame(this);
	private EditorFrame editorFrame = new EditorFrame(this);
	private Image img;
	
	/**
	 * erzeugt ein rahmenloses Fenster der Gr��e 800*600
	 * @author Sebastian Kiepert
	 * @version 2.0
	 * @see frames.MainFrame#init()
	 */
	public MainFrame(){
		setSize(800,600);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
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
		File imgFile = new File("../uLTRA/Documents/images/light.jpg");
		try {
			img = ImageIO.read(imgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		desktop = new JDesktopPane()
		{
	        /**
	         * erzeugt das Hintergrundbild des Hauptfensters, das im Hauptmen� zu sehen ist
			 *
			 */
			private static final long serialVersionUID = 1L;			
			Image scaledImg = img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
			public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(scaledImg,0,0,this.getWidth(),this.getHeight(),this);
	        }
		};
		desktop.add(menuFrame);
		desktop.add(gameFrame);
		desktop.add(editorFrame);
		desktop.setVisible(true);
		this.add(this.desktop);
	}
	
	/**
	 * De-/Aktiviert das Hauptmen�
	 * @param visible
	 */
	public void setMenuVisibility(boolean visible){
		menuFrame.setVisible(visible);
	}
	
	/**
	 * De-/Aktiviert das Spielfenster
	 * @param visible
	 */
	public void setGameVisibility(boolean visible){
		gameFrame.setVisible(visible);
	}
	
	/**
	 * De-/Aktiviert das Editorfenster
	 * @param visible
	 */
	public void setEditorVisibility(boolean visible){
		editorFrame.setVisible(visible);
	}
	
}