/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import frames.MainFrame;

/**
 * Toolbar-Schaltfl�che zum Zur�ckkehren ins Hauptmen�
 * 
 * @author basti
 * @see folgt
 * @version 1.0
 */

public class MainMenuAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	private String whoYouAre;
	
	public MainMenuAction(MainFrame mainFrame, String whoYouAre){
		this.mainFrame = mainFrame;
		this.whoYouAre = whoYouAre;
		putValue(Action.NAME, "Hauptmen�");
		putValue(Action.SHORT_DESCRIPTION, "Zum Hauptmen� zur�ckkehren");
	}

	/**
	 * schaltet auf das Hauptmen� um
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(whoYouAre.equals("editor")){
			mainFrame.setEditorVisibility(false);
		}
		else{
			mainFrame.setGameVisibility(false);
		}
		mainFrame.setMenuVisibility(true);
	}

}
