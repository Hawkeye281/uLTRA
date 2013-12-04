/**
 * 
 */
package dialogs;

import java.awt.Point;

import javax.swing.JDialog;

import panels.LightSourcePanel;

import listener.EditorMouseListener;

/**
 * @author Sebastian Kiepert
 *
 */
public class LightSourceDialog extends JDialog {

	private static int x;
	private static int y;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LightSourceDialog(Point cell){
		LightSourceDialog.x = cell.x;
		LightSourceDialog.y = cell.y;
		setTitle("Lichtquelle einfügen");
		setLocation(EditorMouseListener.getMouseLocation());
		setSize(200,80);
		setModal(true);
		add(new LightSourcePanel(this));
		setVisible(true);
	}
	
	public static int getCellX(){
		return x;
	}
	
	public static int getCellY(){
		return y;
	}
}
