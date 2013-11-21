/**
 * 
 */
package frames;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	
	public MainFrame(){
		setTitle("uLTRA");
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setContentPane(new BackGroundPane("../../Documents/images/light.jpg"));
		setVisible(true);
	}
	
	public void init(){
		desktop = new JDesktopPane();
		desktop.add(menuFrame);
		desktop.add(gameFrame);
		desktop.setVisible(true);
		this.add(this.desktop);
	}
	
	public MenuFrame getMenuFrame(){
		return menuFrame;
	}
	
	public GameFrame getGameFrame(){
		return gameFrame;
	}
	
    class BackGroundPane extends JPanel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Image img = null;
 
        BackGroundPane(String imagefile) {
            if (imagefile != null) {
                MediaTracker mt = new MediaTracker(this);
                img = Toolkit.getDefaultToolkit().getImage(imagefile);
                mt.addImage(img, 0);
                try {
                    mt.waitForAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
 
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);
        }
    }

}
