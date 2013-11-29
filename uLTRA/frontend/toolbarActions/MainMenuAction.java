/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

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
	private MainFrame mainFrame;
	
	public MainMenuAction(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		putValue(Action.NAME, "Hauptmenü");
		putValue(Action.SHORT_DESCRIPTION, "Zum Hauptmenü zurückkehren");
	}

	/**
	 * schaltet auf das Hauptmenü um
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		mainFrame.removeFromDesktop(0);
		mainFrame.addToDesktop(new MenuPanel(mainFrame));
		mainFrame.refreshDesktop();
	}

}
