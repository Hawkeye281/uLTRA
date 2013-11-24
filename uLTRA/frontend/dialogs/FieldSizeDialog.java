/**
 * 
 */
package dialogs;

import javax.swing.JDialog;

import panels.EditorPanel;
import panels.FieldSizePanel;

/**
 * @author Sebastian Kiepert
 *
 */
public class FieldSizeDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FieldSizeDialog(EditorPanel editPanel){
		setTitle("Spielfeld generieren - Gr��e eingeben");
		setLocationRelativeTo(null);
		setSize(200,120);
		setModal(true);
		add(new FieldSizePanel(this, editPanel));
		setVisible(true);
	}
}
