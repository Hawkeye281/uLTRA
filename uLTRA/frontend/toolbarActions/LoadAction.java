/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;


import Controller.GridController;
import Controller.MenuController;

import panels.EditorPanel;
import panels.GamePanel;

/**
 * 
 * Schaltfl�che zum Laden von Spielfeldern
 * 
 * @author basti
 * @version 1.0
 */
public class LoadAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private GamePanel gamePanel = null;
	private EditorPanel editorPanel = null;
	
	public LoadAction(GamePanel gamePanel){
		this.gamePanel = gamePanel;
		putValue(Action.NAME, "Laden");
		putValue(Action.SHORT_DESCRIPTION, "Spiel laden");
	}
	
	public LoadAction(EditorPanel editorPanel){
		this.editorPanel = editorPanel;
		putValue(Action.NAME, "Laden");
		putValue(Action.SHORT_DESCRIPTION, "Spiel laden");
	}

	/**
	 * Verkn�pfung zur Load-Methode
	 */
	@Override
	public void actionPerformed(ActionEvent e) { 
		
		if (gamePanel!=null) {
			GridController.loadGame(JOptionPane.showOptionDialog(null, "W�hlen Sie ein Spiel aus:", "Spiel laden", 1, 1, null,
					GridController.getAllSavedGames(), null));
		}
		else {
//			MenuController.loadGame(editorPanel);
		}

	}

}
