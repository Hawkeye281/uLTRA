/**
 * 
 */
package listener;

import gamegrid.BeamDirections;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Controller.EditorController;

import panels.GamePanel;

/**
 * @author Sebastian Kiepert
 *
 */
public class EditorMouseListener extends AbstractMousePositionListener {

	private GamePanel gamePanelNew;
//	private static EditorPanel editorPanel;
	private EditorController editGridCont;
	
	public EditorMouseListener(){
		this.gamePanelNew = GamePanel.getGamePanel();
		this.editGridCont = gamePanelNew.getEditorController();
	}
	
	@Override
	public void mouseClicked(MouseEvent pEvent){
		super.mouseClicked(pEvent);
		
//		System.out.println(super._startPoint + " : " + super._endPoint);
		Point clickLoc = getStartPoint();
		if (pEvent.getClickCount()==2 && !pEvent.isMetaDown()){
//			System.out.println(getStartPoint());
			if (!editGridCont.isLightSource(clickLoc.x, clickLoc.y))
				editGridCont.setLightSource(clickLoc.x, clickLoc.y);
			this.editGridCont.recreateEditGrid();
		}
		else if (pEvent.isMetaDown()){
			if (editGridCont.isLightSource(clickLoc.x, clickLoc.y)){
				if (editGridCont.getLightValue(clickLoc.x, clickLoc.y) == 0){
					editGridCont.removeLightSource(clickLoc.x, clickLoc.y);
					this.editGridCont.recreateEditGrid();
				}
				else {
					removeLightSourceAndBeams(clickLoc.x, clickLoc.y);
					this.editGridCont.recreateEditGrid();
				}
			}
			else if (editGridCont.isBeam(clickLoc.x, clickLoc.y)){
				removeBeam(clickLoc.x, clickLoc.y, checkBeamDirection(clickLoc.x, clickLoc.y));
				this.editGridCont.recreateEditGrid();
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
		
		if (pEvent.getClickCount()==2 || pEvent.isMetaDown() || getStartPoint()==getEndPoint()){
			mouseClicked(pEvent);
		}
		else {
			if (getStartPoint() != getEndPoint()){
				//Wir befinden uns in der gleichen Spalte
				if (getStartPoint().x == getEndPoint().x){
					int column = getStartPoint().x;
					if (getStartPoint().y < getEndPoint().y){
						System.out.println("down");
						y_start = getStartPoint().y;
						y_end = getEndPoint().y;
						_direction = BeamDirections.BEAM_DOWN;
					}
					else {
						System.out.println("up");
						y_start = getEndPoint().y;
						y_end = getStartPoint().y;
						_direction = BeamDirections.BEAM_UP;
					}
					if (editGridCont.isLightSource(getStartPoint().x, getStartPoint().y)){
						lightValue = editGridCont.getLightValue(getStartPoint().x, getStartPoint().y);
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
						editGridCont.setLightValue(getStartPoint().x, getStartPoint().y, lightValue);
					}
				}
				//Wir befinden uns in der gleichen Zeile
				else if (getStartPoint().y == getEndPoint().y){
					int row = getStartPoint().y;
					if (getStartPoint().x < getEndPoint().x){
						System.out.println("right");
						x_start = getStartPoint().x;
						x_end = getEndPoint().x;
						_direction = BeamDirections.BEAM_RIGHT;
					}
					else {
						System.out.println("left");
						x_start = getEndPoint().x;
						x_end = getStartPoint().x;
						_direction = BeamDirections.BEAM_LEFT;
					}
					if (editGridCont.isLightSource(getStartPoint().x, getStartPoint().y)){
						lightValue = editGridCont.getLightValue(getStartPoint().x, getStartPoint().y);
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
						editGridCont.setLightValue(getStartPoint().x, getStartPoint().y, lightValue);
					}
				}
//				System.out.println(lightValue);	
				this.editGridCont.recreateEditGrid();
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
		if (editGridCont.isBeam(x-1, y)){
			String[] check = checkBeamDirection(x-1, y);
			if (check[0].equals("LEFT"))
				checkList.add(check);
		}
		if (editGridCont.isBeam(x+1, y)){
			String[] check = checkBeamDirection(x+1, y);
			if (check[0].equals("RIGHT"))
				checkList.add(check);
		}
		if (editGridCont.isBeam(x, y-1)){
			String[] check = checkBeamDirection(x, y-1);
			if (check[0].equals("UP"))
				checkList.add(check);
		}
		if (editGridCont.isBeam(x, y+1)){
			String[] check = checkBeamDirection(x, y+1);
			if (check[0].equals("DOWN"))
				checkList.add(check);
		}
//		int i=0;
//		System.out.println(checkList.size());
		for (String[] check : checkList){
//			i++;
//			System.out.println("Liste " + i + ": " + check[0] + " : " + check[1]);
			if (check[0].equals("LEFT") || check[0].equals("RIGHT")){
				int x_start = x + (Integer.valueOf(check[1])*-1);
				BeamDirections beam = editGridCont.getBeam(x_start, y).getDirection();
				do{
					if (editGridCont.getBeam(x_start, y).getDirection()==beam)
						editGridCont.removeBeam(x_start, y);
					x_start += (Integer.valueOf(check[1])*-1);
				}while(editGridCont.isInGrid(x_start, y) && editGridCont.isBeam(x_start, y));
			}
			else if (check[0].equals("UP") || check[0].equals("DOWN")){
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
			if (check[0].equals("LEFT") || check[0].equals("RIGHT")){
				x += Integer.valueOf(check[1]);
			}
			else if (check[0].equals("UP") || check[0].equals("DOWN")){
				y += Integer.valueOf(check[1]);
			}
		}while(!editGridCont.isLightSource(x, y));
		return new Point(x,y);
	}
	
	private String[] checkBeamDirection(int x, int y){
		BeamDirections beam = editGridCont.getBeam(x, y).getDirection();
		String[] result = {null, null};
		if (beam == BeamDirections.BEAM_LEFT){
			result[0] = "LEFT";
			result[1] = "1";
		}
		else if (beam == BeamDirections.BEAM_RIGHT){
			result[0] = "RIGHT";
			result[1] = "-1";
		}
		else if (beam == BeamDirections.BEAM_UP){
			result[0] = "UP";
			result[1] = "1";
		}
		else if (beam == BeamDirections.BEAM_DOWN){
			result[0] = "DOWN";
			result[1] = "-1";
		}
		return result;
	}
}