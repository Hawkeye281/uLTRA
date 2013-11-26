/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * 
 * Schaltfläche zum Laden von Spielfeldern
 * 
 * @author basti
 * @version 1.0
 */
public class LoadAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public LoadAction(){
		putValue(Action.NAME, "Laden");
		putValue(Action.SHORT_DESCRIPTION, "Spiel laden");
	}

	/**
	 * Verknüpfung zur Load-Methode
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Load-Methode in Toolbar implementieren

	}

}
