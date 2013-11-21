/**
 * 
 */
package frames;

import javax.swing.JInternalFrame;

import panels.GamePanel;

/**
 * @author Sebastian Kiepert
 *
 */
public class GameFrame extends JInternalFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GameFrame(MainFrame mainFrame){
		closable = false;
		maximizable = false;
		resizable = false;
		isMaximum = true;
		setTitle("Spielfeld");
		add(new GamePanel(mainFrame));
		setVisible(false);
	}

}
