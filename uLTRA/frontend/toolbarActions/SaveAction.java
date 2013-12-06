/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controller.GridController;

/**
 * Toolbar-Schaltfl�che zum Speichern des Spielstandes oder Editorfeldes
 * 
 * @author basti
 * @see folgt
 * @version 1.0
 */
public class SaveAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public SaveAction(){
		putValue(Action.NAME, "Speichern");
		putValue(Action.SHORT_DESCRIPTION, "Spiel speichern");
	}

	/** 
	 * durch anklicken der Schaltfl�che wird die Save-Methode ausgef�hrt
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		GridController.saveGame(GridController.getGameGrid(), JOptionPane.showInputDialog
				(null, "Geben Sie hier den Spielnamen ein:", "Spielname", 1));
	}
}
