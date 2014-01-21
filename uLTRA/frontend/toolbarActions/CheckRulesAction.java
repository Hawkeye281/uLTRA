/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import Controller.EditorController;

import panels.GamePanel;

/**
 * @author v095376
 *
 */
public class CheckRulesAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GamePanel gamePanel;
	private EditorController editCont;

	/**
	 * 
	 */
	public CheckRulesAction() {
		this.gamePanel = GamePanel.getGamePanel();
		this.editCont = gamePanel.getEditorController();
		this.putValue(SHORT_DESCRIPTION, "Feld auf Fehler prüfen");
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (editCont.gridIsSet()){
			gamePanel.checkRules();
			gamePanel.setChecked();
		}
	}

}
