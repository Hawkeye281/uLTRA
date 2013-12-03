/**
 * 
 */
package dialogs;

import javax.swing.JDialog;

import frames.MainFrame;

import listener.EditorMouseListener;

import panels.FieldSizePanel;

/**
 * @author Sebastian Kiepert
 *
 */
public class LightSourceDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LightSourceDialog(){
		setTitle("Lichtquelle einfügen - Strahlstärke eingeben");
		setLocation(EditorMouseListener.getMouseLocation());
		setLocationRelativeTo(MainFrame.getDesktop());
		setSize(200,120);
		setModal(true);
		setVisible(true);
	}
}
