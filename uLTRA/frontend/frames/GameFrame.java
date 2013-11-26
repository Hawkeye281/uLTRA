/**
 * 
 */
package frames;

import javax.swing.JInternalFrame;

import panels.GamePanel;

public class GameFrame extends JInternalFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Spielfenster - hier werden alle Spielfunktionalitäten über das GamePanel bereitgestellt
	 * Es wird im MainFrame geladen
	 * @param mainFrame
	 * @author Sebastian Kiepert
	 * @version 1.0
	 * @see frames.MainFrame#MainFrame()
	 * @see panels.GamePanel#GamePanel(MainFrame)
	 */
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
