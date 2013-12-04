/**
 * 
 */
package listener;

import java.awt.Point;
import java.awt.event.MouseEvent;

import panels.EditorPanel;

import dialogs.LightSourceDialog;

/**
 * @author Sebastian Kiepert
 *
 */
public class EditorMouseListener extends AbstractMousePositionListener {

	private static Point loc;
	private static EditorPanel editorPanel;
	
	public EditorMouseListener(EditorPanel editorPanel){
		EditorMouseListener.editorPanel = editorPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent pEvent){
		
		if (pEvent.getClickCount()==2 && !pEvent.isMetaDown()){
			loc = new Point(pEvent.getXOnScreen(), pEvent.getYOnScreen());
			System.out.println(super._startPoint);
			@SuppressWarnings("unused")
			LightSourceDialog lsd = new LightSourceDialog(super._startPoint);	
		}
		else if (pEvent.isMetaDown()){
			if (EditorPanel.getController().contentIsLightSource(super._startPoint.x, super._startPoint.y)){
				EditorPanel.getController().removeLightSource(super._startPoint.x, super._startPoint.y);
				editorPanel.reloadEditor();
			}
		}
	}
	
	public static Point getMouseLocation(){
		return loc;
	}
	
	public static EditorPanel getEditorPanel(){
		return editorPanel;
	}
}
