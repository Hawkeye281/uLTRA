/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import panels.FieldSizePanel;
import panels.GamePanel;
import panels.MenuPanel;

import frames.MainFrame;

/**
 * Toolbar-Schaltfläche zum Zurückkehren ins Hauptmenü
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
		putValue(Action.NAME, "Hauptmenü");
		putValue(Action.SHORT_DESCRIPTION, "Zum Hauptmenü zurückkehren");
	}
	
	private void performAction(){
		this.gamePanel.resetGrid();
		mainFrame.removeFromDesktop(0);
		mainFrame.addToDesktop(new MenuPanel());
		mainFrame.refreshDesktop();
	}

	/**
	 * schaltet auf das Hauptmenü um, sofern kein aktives Spiel-/Editorfeld besteht
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(gamePanel.getGridController() == null && gamePanel.getEditorController().gridIsSet()){
			if(FieldSizePanel.openConfirmDialog() == 0)
				performAction();
		}
		else if(gamePanel.getEditorController() == null && gamePanel.getGridController().gridIsSet()){
			if(FieldSizePanel.openConfirmDialog() == 0)
				performAction();
		}
		else{
			performAction();
		}
	}
}
