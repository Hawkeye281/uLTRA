package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import panels.GamePanel;

public class StepBackAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GamePanel gamePanel;
	
	public StepBackAction(GamePanel gamePanel){
		this.gamePanel = gamePanel;
		putValue(Action.NAME, "Schritt zurück");
		putValue(Action.SHORT_DESCRIPTION, "letzte Aktion löschen");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO einen Schritt zurück implementieren

	}

}
