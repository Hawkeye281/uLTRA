/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import panels.GamePanel;

import dialogs.FieldSizeDialog;

/**
 * Toolbar-Schaltfläche zum Generieren eines Spielfeldes
 * 
 * @author basti
 * @see folgt
 * @version 1.0
 */

public class GenerateAction extends AbstractAction {


	private static final long serialVersionUID = 1L;
	private GamePanel gamePanel;
	
	public GenerateAction(){
		this.gamePanel = GamePanel.getGamePanel();
		putValue(Action.NAME, "Feld erstellen");
		putValue(Action.SHORT_DESCRIPTION, "Spielfeld erstellen");
	}

	/** 
	 * öffnet einen Dialog, in dem die Spielfeldgröße eingestellt wird
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		FieldSizeDialog fsd = new FieldSizeDialog();
	}

}
