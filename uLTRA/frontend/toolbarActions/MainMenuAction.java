/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import frames.MainFrame;

/**
 * @author basti
 *
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
		putValue(Action.NAME, "Hauptmenü");
		putValue(Action.SHORT_DESCRIPTION, "Zum Hauptmenü zurückkehren");
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
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
