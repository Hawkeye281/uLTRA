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
 * Toolbar-Schaltfläche zum Generieren eines Spielfeldes
 * 
 * @author basti
 * @see folgt
 * @version 1.0
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

	/** 
	 * öffnet einen Dialog, in dem die Spielfeldgröße eingestellt wird
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		FieldSizeDialog fsd = new FieldSizeDialog(editPanel);
	}

}
