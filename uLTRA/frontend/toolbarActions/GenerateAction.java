/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import panels.EditorPanel;

import dialogs.FieldSizeDialog;

/**
 * @author basti
 *
 */
public class GenerateAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EditorPanel editPanel;
	
	public GenerateAction(EditorPanel editPanel){
		this.editPanel = editPanel;
		putValue(Action.NAME, "Feld erstellen");
		putValue(Action.SHORT_DESCRIPTION, "Spielfeld erstellen");
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		FieldSizeDialog fsd = new FieldSizeDialog(editPanel);
	}

}
