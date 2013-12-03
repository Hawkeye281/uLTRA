/**
 * 
 */
package listener;

import java.awt.Point;
import java.awt.event.MouseEvent;

import dialogs.LightSourceDialog;

/**
 * @author Sebastian Kiepert
 *
 */
public class EditorMouseListener extends AbstractMousePositionListener {

	private static Point loc;
	
	@Override
	public void mouseClicked(MouseEvent pEvent){
		super.mouseClicked(pEvent);
		if (pEvent.getClickCount()==2){
			loc = new Point(pEvent.getX(), pEvent.getY());
			LightSourceDialog lsd = new LightSourceDialog();			
		}
	}
	
	public static Point getMouseLocation(){
		return loc;
	}
}
