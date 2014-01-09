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
			editGridCont.setLightSource(super._startPoint.x, super._startPoint.y);
			editorPanel.reloadEditor();
		}
		else if (pEvent.isMetaDown()){
			if (editGridCont.contentIsLightSource(super._startPoint.x, super._startPoint.y)){
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
		int stayRow = 0, stayColumn = 0;
		BeamDirections _direction = null;
		
		if (pEvent.getClickCount()==2 || super._startPoint==super._endPoint){
			mouseClicked(pEvent);
		}
		else {
		if (super._startPoint!=super._endPoint){
			//Wir befinden uns in der gleichen Spalte
			if (super._startPoint.x == super._endPoint.x){
				x_start = super._startPoint.x;
				x_end = super._endPoint.x;
				if (super._startPoint.y < super._endPoint.y){
					System.out.println("down and upper cell from start");
					stayColumn = 0;
					stayRow = 1;
					y_start = super._startPoint.y;
					y_end = super._endPoint.y;
					_direction = BeamDirections.BEAM_DOWN;
				}
				else {
					System.out.println("up and down cell from start");
					stayColumn = 0;
					stayRow = 1;
					y_start = super._endPoint.y;
					y_end = super._startPoint.y;
					_direction = BeamDirections.BEAM_UP;
				}
			}
			//Wir befinden uns in der gleichen Zeile
			else if (super._startPoint.y == super._endPoint.y){
				y_start = super._startPoint.y;
				y_end = super._endPoint.y;
				if (super._startPoint.x < super._endPoint.x){
					System.out.println("right and left cell from start");
					stayColumn = 1;
					stayRow = 0;
					x_start = super._startPoint.x;
					x_end = super._endPoint.x;
					_direction = BeamDirections.BEAM_RIGHT;
				}
				else {
					System.out.println("left and right cell from start");
					stayColumn = 0;
					stayRow = 1;
					x_start = super._endPoint.x;
					x_end = super._startPoint.x;
					_direction = BeamDirections.BEAM_LEFT;
				}
			}
			System.out.println("["+x_start+":"+y_start+"]"+"["+x_end+":"+y_end+"]");
		
//		if(!(editGrid.getCell(super._startPoint).getContent() instanceof LightSource))
//		{
//			if(super._startPoint.y < super._endPoint.y)
//			{
//				y_start = super._startPoint.y + 1;
//				y_end = super._endPoint.y;
//				_direction = BeamDirections.BEAM_DOWN;
//				System.out.println("down");
//			}
//			else if(super._startPoint.y == super._endPoint.y)
//			{
//				y_start = super._startPoint.y;
//				y_end = super._endPoint.y;
//			}
//			else
//			{
//				y_start = super._endPoint.y;
//				y_end = super._startPoint.y;
//				_direction = BeamDirections.BEAM_UP;
//			}
//			
//			for(; y_start <= y_end; y_start++)
//			{
//				if(super._startPoint.x < super._endPoint.x)
//				{
//					x_start = super._startPoint.x + 1;
//					x_end = super._endPoint.x;
//					_direction = BeamDirections.BEAM_RIGHT;
//				}
//				else if(super._startPoint.x == super._endPoint.x)
//				{
//					x_start = super._startPoint.x;
//					x_end = super._endPoint.x;
//				}
//				else
//				{
//					x_start = super._endPoint.x;
//					x_end = super._startPoint.x;
//					_direction = BeamDirections.BEAM_LEFT;
//				}
//				
//				for(; x_start <= x_end; x_start++)
//				{
//					Cell c = editGrid.getCell(x_start, y_start);
//					
//					if(c.getContent() != null)
//					{
//						if(!(c.getContent() instanceof LightSource) && c.getContent() instanceof Beam)
//						{
//							//TODO Command-Pattern undo einfügen
//						}
//					}
//					else
//					{
//						c.setContent(new Beam(_direction));
//					}
//				}
//			}

//			if(!super._startPoint.equals(super._endPoint))
//			{
//				GamePanel.getGridPanel().resetLayout();
//			}
//			editorPanel.reloadEditor();
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
