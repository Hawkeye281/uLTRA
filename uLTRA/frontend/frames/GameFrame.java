/**
 * 
 */
package frames;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import panels.GameMenu;

/**
 * @author Sebastian Kiepert
 *
 */
public class GameFrame extends JInternalFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktop;
	private MainFrame mainFrame;
	
	public GameFrame(JDesktopPane desktop, MainFrame mainFrame){
		this.desktop = desktop;
		this.mainFrame = mainFrame;
		closable = false;
		maximizable = false;
		resizable = false;
		isMaximum = true;
		setTitle("Spielfeld");
		setVisible(true);
	}

}
