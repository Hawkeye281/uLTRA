/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import panels.GamePanel;

/**
 * Toolbar-Schaltfläche zum Zurücksetzen des Editorfeldes
 * 
 * @author basti
 * @see EditorPanel.resetRay()
 * @version 1.0
 */

public class ResetAction extends AbstractAction{

	private static final long serialVersionUID = 1L;
	private GamePanel gamePanel;
	
	public ResetAction(){
		this.gamePanel = GamePanel.getGamePanel();
		putValue(Action.NAME, "Reset");
		putValue(Action.SHORT_DESCRIPTION, "Spielfeld zurücksetzen");
	}
	
	public static int openResetDialog(){
		ImageIcon icon = new ImageIcon("../uLTRA/Documents/images/icons/warning.png");
		int choice = JOptionPane.showConfirmDialog(null, "Das gesamte Spielfeld wird verworfen!\n" + 
						"Möchten Sie das wirklich?",
						"Spielfeld verwerfen?",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE,
						icon);
		return choice;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(gamePanel.getEditorController().gridIsSet() && openResetDialog() == 0 )
			this.gamePanel.resetGrid();
	}

}
