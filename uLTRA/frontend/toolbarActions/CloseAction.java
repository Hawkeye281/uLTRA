/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import panels.FieldSizePanel;
import panels.GamePanel;

/**
 * 
 * Toolbar-Schaltfl‰che zum Schlieﬂen des Frames und Beenden der Anwendung
 * 
 * @author basti
 * @version 1.0
 *
 */
public class CloseAction extends AbstractAction {


	private static final long serialVersionUID = 1L;
	private GamePanel gamePanel = GamePanel.getGamePanel();
	
	public CloseAction(){
		putValue(Action.NAME, "Beenden");
		putValue(Action.SHORT_DESCRIPTION, "Programm schlieﬂen");
	}

	/** 
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(gamePanel.getGridController() == null && gamePanel.getEditorController().gridIsSet()){
			if(FieldSizePanel.openConfirmDialog() == 0)
				System.exit(0);
		}
		else if(gamePanel.getEditorController() == null && gamePanel.getGridController().gridIsSet()){
			if(FieldSizePanel.openConfirmDialog() == 0)
				System.exit(0);
		}
		else{
			System.exit(0);
		}
	}
}
