/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import panels.GamePanel;
import panels.MenuPanel;

import frames.MainFrame;

/**
 * Toolbar-Schaltfl�che zum Zur�ckkehren ins Hauptmen�
 * 
 * @author basti
 * @see folgt
 * @version 1.0
 */

public class MainMenuAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame = MainFrame.getMainFrame();
	private GamePanel gamePanel = GamePanel.getGamePanel();
	
	public MainMenuAction(){
		putValue(Action.NAME, "Hauptmen�");
		putValue(Action.SHORT_DESCRIPTION, "Zum Hauptmen� zur�ckkehren");
	}

	/**
	 * schaltet auf das Hauptmen� um
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.gamePanel.resetPanel();
		mainFrame.removeFromDesktop(0);
		mainFrame.addToDesktop(new MenuPanel());
		mainFrame.refreshDesktop();
	}

}
