/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;


import Controller.MenuController;

import panels.EditorPanel;
import panels.GamePanel;

/**
 * 
 * Schaltfläche zum Laden von Spielfeldern
 * 
 * @author basti
 * @version 1.0
 */
public class LoadAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JPanel _activPanel;

	
	public LoadAction(JPanel activPanel){
		this._activPanel = activPanel;
		putValue(Action.NAME, "Laden");
		putValue(Action.SHORT_DESCRIPTION, "Spiel laden");
	}

	/**
	 * Verknüpfung zur Load-Methode
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (_activPanel instanceof GamePanel) {
			MenuController.loadGame((GamePanel) _activPanel);
		}
		else {
//			MenuController.loadGame((EditorPanel) _activPanel);
		}

	}

}
