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
import Controller.EditorController;

import panels.GamePanel;

/**
 * @author Sebastian Kiepert
 *
 */
public class EditorMouseListener extends AbstractMousePositionListener {

	private GamePanel gamePanelNew;
	private EditorController editGridCont;
	private BeamDirections direction = null;
	
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
					this.editGridCont.removeLightSourceAndBeams(clickLoc.x, clickLoc.y);
					this.editGridCont.recreateEditGrid();
				}
			}
			else if (editGridCont.isBeam(clickLoc.x, clickLoc.y)){
				editGridCont.deleteBeam(clickLoc.x, clickLoc.y);
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
		
		int x_move = 0, y_move = 0;
		int moves = 0, moveCheck = 0;
		Beam beforeBeam = null;
		
		if (pointsAreValid(getStartPoint(), getEndPoint())){
			if (pEvent.getClickCount()==2 || pEvent.isMetaDown() || getStartPoint()==getEndPoint()){
				if (EditorController.getCell(getStartPoint()).isEmpty() && !pEvent.isMetaDown()) {
					mouseClicked(pEvent);
				}
				else if (pEvent.isMetaDown()) {
					mouseClicked(pEvent);
				}
			}
			else if(getStartPoint() != getEndPoint() && editGridCont.isLightSource(getStartPoint().x, getStartPoint().y)) {
				Point startPoint = getStartPoint();
				Point endPoint = getEndPoint();
				LightSource lightSource = editGridCont.getLightSource(startPoint.x, startPoint.y);
				moves = getMoveCount();
				moveCheck = moves;
				if (inRow(startPoint, endPoint)){
					if(isLeftMove(startPoint, endPoint)){
						this.direction = BeamDirections.BEAM_LEFT;
						x_move -= 1;
					}
					else if (isRightMove(startPoint, endPoint)){
						this.direction = BeamDirections.BEAM_RIGHT;
						x_move = 1;
					}
					startPoint.x += x_move;
				}
				else if (inColumn(startPoint, endPoint)){
					if (isUpMove(startPoint, endPoint)){
						this.direction = BeamDirections.BEAM_UP;
						y_move -= 1;
					}
					else if (isDownMove(startPoint, endPoint)){
						this.direction = BeamDirections.BEAM_DOWN;
						y_move = 1;
					}
					startPoint.y += y_move;
				}
				while(moves >= 0 && continueLightRay(startPoint, this.direction)){
					if (!editGridCont.isBeam(startPoint.x, startPoint.y)){
						if (moves == moveCheck){
							editGridCont.setBeam(startPoint.x, startPoint.y, this.direction, true, lightSource);
							beforeBeam = editGridCont.getBeam(startPoint.x, startPoint.y);
						}
						else if (moves > 0){
							beforeBeam.changeBeamEnd();
							editGridCont.setBeam(startPoint.x, startPoint.y, this.direction, true, lightSource);
							beforeBeam = editGridCont.getBeam(startPoint.x, startPoint.y);
						}
						else{
							beforeBeam.changeBeamEnd();
							editGridCont.setBeam(startPoint.x, startPoint.y, this.direction, true, lightSource);
						}
					}
					else if (editGridCont.isBeam(startPoint.x, startPoint.y)){
						beforeBeam = editGridCont.getBeam(startPoint.x, startPoint.y);
					}
					startPoint.x += x_move;
					startPoint.y += y_move;
					moves--;
				};
			}
			this.editGridCont.recreateEditGrid();
		}
	}
	
	public static void setNewLastBeam(Cell changed){
		Beam removed = (Beam) changed.getContent();
		BeamDirections direction = removed.getDirection();
		int x=0, y=0;
		switch(direction){
			case BEAM_UP: y=1;break;
			case BEAM_DOWN: y-=1;break;
			case BEAM_LEFT: x=1;break;
			case BEAM_RIGHT: x-=1;
		}
		Cell cell = EditorController.getCell(changed.getX()+x, changed.getY()+y);
		Beam change = (cell.getContent() instanceof Beam)? (Beam) cell.getContent() : null;
		if (change != null) change.changeBeamEnd();
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
		return result;
	}
	
	private boolean isAlreadySameBeam(Point pos, BeamDirections direction){
		boolean result = false;
		if (editGridCont.isBeam(pos.x, pos.y)){
			result = (editGridCont.getBeam(pos.x, pos.y).getDirection() == direction)? true : false;
		}
		return result;
	}
	
	private boolean pointsAreValid(Point start, Point end){
		return (start != null && end != null)? true : false;
	}
}