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
	public StepBackAction(){
		GamePanel.getGamePanel();
		putValue(Action.NAME, "Schritt zurück");
		putValue(Action.SHORT_DESCRIPTION, "letzte Aktion löschen");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO einen Schritt zurück implementieren

	}

}
