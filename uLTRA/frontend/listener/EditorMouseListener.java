/**
 * 
 */
package listener;

import gamegrid.Beam;
import gamegrid.BeamDirections;
import gamegrid.Cell;
import gamegrid.LightSource;

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
	private EditorController editGridCont;
	
	public EditorMouseListener(){
		this.gamePanelNew = GamePanel.getGamePanel();
		this.editGridCont = gamePanelNew.getEditorController();
	}
	
	@Override
	public void mouseClicked(MouseEvent pEvent){
		super.mouseClicked(pEvent);
		
		Point clickLoc = getStartPoint();
		if (pEvent.getClickCount()==2 && !pEvent.isMetaDown()){
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
		
		int lightValue = 0;
		int move = 0;
		int moves = 0;
		BeamDirections _direction = null;
		
		if (pointsAreValid(getStartPoint(), getEndPoint())){
			if (pEvent.getClickCount()==2 || pEvent.isMetaDown() || getStartPoint()==getEndPoint()){
				mouseClicked(pEvent);
			}
			else if(getStartPoint() != getEndPoint() && editGridCont.isLightSource(getStartPoint().x, getStartPoint().y)) {
				Point startPoint = getStartPoint();
				Point endPoint = getEndPoint();
				LightSource lightSource = editGridCont.getLightSource(getStartPoint().x, getStartPoint().y);
				lightValue = lightSource.getCapacity(); 
//				System.out.println(moves = getMoveCount());
				if (inRow(startPoint, endPoint)){
					if(isLeftMove(startPoint, endPoint)){
//						System.out.println("left");
						_direction = BeamDirections.BEAM_LEFT;
						move -= 1;
					}
					else if (isRightMove(startPoint, endPoint)){
//						System.out.println("right");
						_direction = BeamDirections.BEAM_RIGHT;
						move = 1;
					}
					startPoint.x += move;
					while(moves >= 0 && continueLightRay(startPoint, _direction)){
						if (!editGridCont.isBeam(startPoint.x, startPoint.y)){
							editGridCont.setBeam(startPoint.x, startPoint.y, _direction);
							lightValue++;
						}
						startPoint.x += move;
						moves--;
					};
				}
				else if (inColumn(startPoint, endPoint)){
					if (isUpMove(startPoint, endPoint)){
						_direction = BeamDirections.BEAM_UP;
						move -= 1;
					}
					else if (isDownMove(startPoint, endPoint)){
						_direction = BeamDirections.BEAM_DOWN;
						move = 1;
					}
					startPoint.y += move;
					while(moves >= 0 && continueLightRay(startPoint, _direction)){
						if (!editGridCont.isBeam(startPoint.x, startPoint.y)){
							editGridCont.setBeam(startPoint.x, startPoint.y, _direction);
							lightValue++;
						}
						startPoint.y += move;
						moves--;
					};
				}
				lightSource.setCapacity(lightValue);
			}
			this.editGridCont.recreateEditGrid();
		}
	}
	
	private void removeBeam(int x, int y, String[] check){
		if (checkPosition(x,y)){
			int x_start = x, y_start = y;		
			if (check[0]!=null){
				LightSource lightSource = searchLightSource(x_start, y_start, check);
				lightSource.setCapacity(lightSource.getCapacity()-1);
				editGridCont.removeBeam(x_start, y_start);
			}
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
		for (String[] check : checkList){
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
	
	private LightSource searchLightSource(int x, int y, String[] check){
		do {
			if (check[0].equals("LEFT") || check[0].equals("RIGHT")){
				x += Integer.valueOf(check[1]);
			}
			else if (check[0].equals("UP") || check[0].equals("DOWN")){
				y += Integer.valueOf(check[1]);
			}
		}while(!editGridCont.isLightSource(x, y));
		return editGridCont.getLightSource(x, y);
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
	
	private boolean checkPosition(int x, int y){
		BeamDirections beam = editGridCont.getBeam(x, y).getDirection(), nextBeam=null;
		if (beam == BeamDirections.BEAM_LEFT){
			Beam test = editGridCont.getBeam(x-1, y);
			nextBeam = (test!=null)? test.getDirection() : null;
		}
		else if (beam == BeamDirections.BEAM_RIGHT){
			Beam test = editGridCont.getBeam(x+1, y);
			nextBeam = (test!=null)? test.getDirection() : null;
		}
		else if (beam == BeamDirections.BEAM_UP){
			Beam test = editGridCont.getBeam(x, y-1);
			nextBeam = (test!=null)? test.getDirection() : null;
		}
		else if (beam == BeamDirections.BEAM_DOWN){
			Beam test = editGridCont.getBeam(x, y+1);
			nextBeam = (test!=null)? test.getDirection() : null;
		}
		return (beam==nextBeam)? false : true;
	}
	
	private int getMoveCount(){
		int row = (getStartPoint().x < getEndPoint().x)? 
				getEndPoint().x - getStartPoint().x : getStartPoint().x - getEndPoint().x;
		int column = (getStartPoint().y < getEndPoint().y)?
				getEndPoint().y - getStartPoint().y : getStartPoint().y - getEndPoint().y;
		return (inRow(getStartPoint(), getEndPoint()))? row-1 : column-1;
	}
	
	private boolean isLeftMove(Point start, Point end){
		return (start.x > end.x)? true : false;
	}
	
	private boolean isRightMove(Point start, Point end){
		return (start.x < end.x)? true : false;
	}
	
	private boolean isUpMove(Point start, Point end){
		return (start.y > end.y)? true : false;
	}
	
	private boolean isDownMove(Point start, Point end){
		return (start.y < end.y)? true : false;
	}
	
	private boolean inRow(Point start, Point end){
		return (start.y == end.y)? true : false;
	}
	
	private boolean inColumn(Point start, Point end){
		return (start.x == end.x)? true : false;
	}
	
	private boolean continueLightRay(Point pos, BeamDirections direction){
		boolean result = false;
		if (editGridCont.isInGrid(pos.x, pos.y)){
			Cell cell = EditorController.getCell(pos.x, pos.y);
			result = cell.isEmpty();
			result = (!result)? isAlreadySameBeam(pos, direction) : result;
		}
//		System.out.println("print: "+result);
		return result;
	}
	
	private boolean isAlreadySameBeam(Point pos, BeamDirections direction){
		boolean result = false;
		if (editGridCont.isBeam(pos.x, pos.y)){
			result = (editGridCont.getBeam(pos.x, pos.y).getDirection() == direction)? true : false;
		}
//		System.out.println("beamcheck: "+result);
		return result;
	}
	
	private boolean pointsAreValid(Point start, Point end){
		return (start != null && end != null)? true : false;
	}
}