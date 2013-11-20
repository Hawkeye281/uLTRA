/**
 * 
 */
package frames;

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
	
	public MainFrame(){
		this.setTitle("uLTRA");
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void init(){
		desktop = new JDesktopPane();
		desktop.add(new MainMenu(this));
		desktop.setVisible(true);
		this.add(this.desktop);
	}

}
