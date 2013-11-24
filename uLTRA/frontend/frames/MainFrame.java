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
	
	public MainFrame(){
		setTitle("uLTRA");
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void init(){		
		File imgFile = new File("../uLTRA/Documents/images/light.jpg");
		try {
			img = ImageIO.read(imgFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		desktop = new JDesktopPane()
		{
	        /**
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
	
	public void setMenuVisibility(boolean visible){
		menuFrame.setVisible(visible);
	}
	
	public void setGameVisibility(boolean visible){
		gameFrame.setVisible(visible);
	}
	
	public void setEditorVisibility(boolean visible){
		editorFrame.setVisible(visible);
	}
	
}