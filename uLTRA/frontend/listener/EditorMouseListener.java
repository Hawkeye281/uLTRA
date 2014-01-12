/**
 * 
 */
package listener;

import gamegrid.BeamDirections;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Controller.EditorController;

import panels.EditorPanel;

/**
 * @author Sebastian Kiepert
 *
 */
public class EditorMouseListener extends AbstractMousePositionListener {

	private static EditorPanel editorPanel;
	private static EditorController editGridCont = EditorPanel.getController();
	
	public EditorMouseListener(EditorPanel editorPanel){
		EditorMouseListener.editorPanel = editorPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent pEvent){
		Point clickLoc = new Point(super._startPoint.x, super._startPoint.y);
		if (pEvent.getClickCount()==2 && !pEvent.isMetaDown()){
			System.out.println(super._startPoint);
			if (!editGridCont.isLightSource(clickLoc.x, clickLoc.y))
				editGridCont.setLightSource(clickLoc.x, clickLoc.y);
			editorPanel.reloadEditor();
		}
		else if (pEvent.isMetaDown()){
			if (editGridCont.isLightSource(clickLoc.x, clickLoc.y)){
				if (editGridCont.getLightValue(clickLoc.x, clickLoc.y) == 0){
					editGridCont.removeLightSource(clickLoc.x, clickLoc.y);
					editorPanel.reloadEditor();
				}
				else {
					removeLightSourceAndBeams(clickLoc.x, clickLoc.y);
					editorPanel.reloadEditor();
				}
			}
			else if (editGridCont.isBeam(clickLoc.x, clickLoc.y)){
				removeBeam(clickLoc.x, clickLoc.y, checkBeamDirection(clickLoc.x, clickLoc.y));
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
	
	private void removeBeam(int x, int y, String[] check){
		int x_start = x, y_start = y;		
		if (check[0]!=null){
			Point lightSource = searchLightSource(x_start, y_start, check);
			editGridCont.setLightValue(lightSource.x, lightSource.y, editGridCont.getLightValue(lightSource.x, lightSource.y)-1);
			editGridCont.removeBeam(x_start, y_start);
		}
	}
	
	private void removeLightSourceAndBeams(int x, int y){
		ArrayList<String[]> checkList = new ArrayList<String[]>();
		if (editGridCont.isInGrid(x-1, y) && editGridCont.isBeam(x-1, y)){
			String[] check = checkBeamDirection(x-1, y);
			if (check[0].equals("x") && check[1].equals("1"))
				checkList.add(check);
		}
		if (editGridCont.isInGrid(x+1, y) && editGridCont.isBeam(x+1, y)){
			String[] check = checkBeamDirection(x+1, y);
			if (check[0].equals("x") && check[1].equals("-1"))
				checkList.add(check);
		}
		if (editGridCont.isInGrid(x, y-1) && editGridCont.isBeam(x, y-1)){
			String[] check = checkBeamDirection(x, y-1);
			if (check[0].equals("y") && check[1].equals("1"))
				checkList.add(check);
		}
		if (editGridCont.isInGrid(x, y+1) && editGridCont.isBeam(x, y+1)){
			String[] check = checkBeamDirection(x, y+1);
			if (check[0].equals("y") && check[1].equals("-1"))
				checkList.add(check);
		}
//		int i=0;
//		System.out.println(checkList.size());
		for (String[] check : checkList){
//			i++;
//			System.out.println("Liste " + i + ": " + check[0] + " : " + check[1]);
			if (check[0].equals("x")){
				int x_start = x + (Integer.valueOf(check[1])*-1);
				BeamDirections beam = editGridCont.getBeam(x_start, y).getDirection();
				do{
					if (editGridCont.getBeam(x_start, y).getDirection()==beam)
						editGridCont.removeBeam(x_start, y);
					x_start += (Integer.valueOf(check[1])*-1);
				}while(editGridCont.isInGrid(x_start, y) && editGridCont.isBeam(x_start, y));
			}
			else if (check[0].equals("y")){
				int y_start = y + (Integer.valueOf(check[1])*-1);
				BeamDirections beam = editGridCont.getBeam(x, y_start).getDirection();
				do{
					if (editGridCont.getBeam(x, y_start).getDirection() == beam)
						editGridCont.removeBeam(x, y_start);
					y_start += (Integer.valueOf(check[1])*-1);
				}while(editGridCont.isInGrid(x, y_start) && editGridCont.isBeam(x, y_start));
			}
		}
		editGridCont.removeLightSource(x, y);
	}
	
	private Point searchLightSource(int x, int y, String[] check){
		do {
			if (check[0].equals("x")){
				x += Integer.valueOf(check[1]);
			}
			else if (check[0].equals("y")){
				y += Integer.valueOf(check[1]);
			}
		}while(!editGridCont.isLightSource(x, y));
		return new Point(x,y);
	}
	
	private String[] checkBeamDirection(int x, int y){
		BeamDirections beam = editGridCont.getBeam(x, y).getDirection();
		String[] result = {null, null};
		if (beam == BeamDirections.BEAM_LEFT){
			result[0] = "x";
			result[1] = "1";
		}
		else if (beam == BeamDirections.BEAM_RIGHT){
			result[0] = "x";
			result[1] = "-1";
		}
		else if (beam == BeamDirections.BEAM_UP){
			result[0] = "y";
			result[1] = "1";
		}
		else if (beam == BeamDirections.BEAM_DOWN){
			result[0] = "y";
			result[1] = "-1";
		}
		return result;
	}
}