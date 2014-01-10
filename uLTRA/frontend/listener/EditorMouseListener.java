/**
 * 
 */
package listener;

import gamegrid.Beam;
import gamegrid.BeamDirections;
import gamegrid.Cell;
import gamegrid.GameGrid;
import gamegrid.LightSource;

import java.awt.Point;
import java.awt.event.MouseEvent;

import Controller.EditorController;

import panels.EditorPanel;

/**
 * @author Sebastian Kiepert
 *
 */
public class EditorMouseListener extends AbstractMousePositionListener {

	private static Point loc;
	private static EditorPanel editorPanel;
	private static EditorController editGridCont = EditorPanel.getController();
	private static GameGrid editGrid = EditorController.getEditGrid();
	
	public EditorMouseListener(EditorPanel editorPanel){
		EditorMouseListener.editorPanel = editorPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent pEvent){
		
		if (pEvent.getClickCount()==2 && !pEvent.isMetaDown()){
			loc = new Point(pEvent.getXOnScreen(), pEvent.getYOnScreen());
			System.out.println(super._startPoint);
			if (!editGridCont.isLightSource(super._startPoint.x, super._startPoint.y))
				editGridCont.setLightSource(super._startPoint.x, super._startPoint.y);
			editorPanel.reloadEditor();
		}
		else if (pEvent.isMetaDown()){
			if (editGridCont.isLightSource(super._startPoint.x, super._startPoint.y)){
				editGridCont.removeLightSource(super._startPoint.x, super._startPoint.y);
				editorPanel.reloadEditor();
			}
		}
		else {
			mouseReleased(pEvent);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent pEvent)
	{	
		super.mouseReleased(pEvent);
		
		int x_start = 0, x_end = 0;
		int y_start = 0, y_end = 0;
		int lightValue = 0;
		BeamDirections _direction = null;
		
		if (pEvent.getClickCount()==2 || super._startPoint==super._endPoint || pEvent.isMetaDown()){
			mouseClicked(pEvent);
		}
		else {
		if (super._startPoint!=super._endPoint){
			//Wir befinden uns in der gleichen Spalte
			if (super._startPoint.x == super._endPoint.x){
				int column = super._startPoint.x;
				if (super._startPoint.y < super._endPoint.y){
					System.out.println("down");
					y_start = super._startPoint.y;
					y_end = super._endPoint.y;
					_direction = BeamDirections.BEAM_DOWN;
				}
				else {
					System.out.println("up");
					y_start = super._endPoint.y;
					y_end = super._startPoint.y;
					_direction = BeamDirections.BEAM_UP;
				}
				if (editGridCont.isLightSource(super._startPoint.x, super._startPoint.y)){
					lightValue = editGridCont.getLightValue(super._startPoint.x, super._startPoint.y);
					int start = (_direction == BeamDirections.BEAM_DOWN)? y_start + 1 : y_start;
					for (;start <= y_end; start++){
						if (!editGridCont.isLightSource(column, start) && !editGridCont.isBeam(column, start)){
							editGridCont.setBeam(column, start, _direction);
							lightValue++;
						}
						else if (editGridCont.isLightSource(column, start)){
							start += y_end;
						}
					}
					editGridCont.setLightValue(super._startPoint.x, super._startPoint.y, lightValue);
				}
			}
			//Wir befinden uns in der gleichen Zeile
			else if (super._startPoint.y == super._endPoint.y){
				int row = super._startPoint.y;
				if (super._startPoint.x < super._endPoint.x){
					System.out.println("right");
					x_start = super._startPoint.x;
					x_end = super._endPoint.x;
					_direction = BeamDirections.BEAM_RIGHT;
				}
				else {
					System.out.println("left");
					x_start = super._endPoint.x;
					x_end = super._startPoint.x;
					_direction = BeamDirections.BEAM_LEFT;
				}
				if (editGridCont.isLightSource(super._startPoint.x, super._startPoint.y)){
					lightValue = editGridCont.getLightValue(super._startPoint.x, super._startPoint.y);
					int start = (_direction == BeamDirections.BEAM_RIGHT)? x_start + 1: x_start;
					for (;start <= x_end;start++){
						if (!editGridCont.isLightSource(start, row) && !editGridCont.isBeam(start, row)){
							editGridCont.setBeam(start, row, _direction);
							lightValue++;
						}
						else if (editGridCont.isLightSource(start, row)){
							start += x_end;
						}
					}
					editGridCont.setLightValue(super._startPoint.x, super._startPoint.y, lightValue);
				}
			}
			System.out.println(lightValue);
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
