/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import panels.EditorPanel;

/**
 * Toolbar-Schaltfl�che zum Zur�cksetzen des Editorfeldes
 * 
 * @author basti
 * @see EditorPanel.resetRay()
 * @version 1.0
 */

public class ResetAction extends AbstractAction{

	private static final long serialVersionUID = 1L;
	private EditorPanel editPanel;
	
	public ResetAction(EditorPanel editPanel){
		this.editPanel = editPanel;
		putValue(Action.NAME, "Reset");
		putValue(Action.SHORT_DESCRIPTION, "Spielfeld zur�cksetzen");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		editPanel.resetRay();
	}

}
