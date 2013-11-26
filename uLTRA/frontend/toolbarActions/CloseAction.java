/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

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
	
	public CloseAction(){
		putValue(Action.NAME, "Beenden");
		putValue(Action.SHORT_DESCRIPTION, "Programm schlieﬂen");
	}

	/** 
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
