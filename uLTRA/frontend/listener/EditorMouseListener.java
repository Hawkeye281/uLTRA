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
				removeBeam(clickLoc.x, clickLoc.y);
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
				LightSource lightSource = editGridCont.getLightSource(getStartPoint().x, getStartPoint().y);
				lightValue = lightSource.getCapacity(); 
				moves = getMoveCount();
				if (inRow(startPoint, endPoint)){
					if(isLeftMove(startPoint, endPoint)){
						_direction = BeamDirections.BEAM_LEFT;
						move -= 1;
					}
					else if (isRightMove(startPoint, endPoint)){
						_direction = BeamDirections.BEAM_RIGHT;
						move = 1;
					}
					startPoint.x += move;
					while(moves >= 0 && continueLightRay(startPoint, _direction)){
						if (!editGridCont.isBeam(startPoint.x, startPoint.y)){
							if(moves > 0){
								editGridCont.setBeam(startPoint.x, startPoint.y, _direction, false, lightSource);
							}
							else {
								editGridCont.setBeam(startPoint.x, startPoint.y, _direction, true, lightSource);
							}
							lightValue++;
						}
						else if (editGridCont.isBeam(startPoint.x, startPoint.y)){
							Beam beam = editGridCont.getBeam(startPoint.x, startPoint.y);
							if (beam.isBeamEnd() && isAlreadySameBeam(new Point(startPoint.x, startPoint.y), _direction)){
								beam.changeBeamEnd();
							}
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
							if (moves > 0){
								editGridCont.setBeam(startPoint.x, startPoint.y, _direction, false, lightSource);
							}
							else{
								editGridCont.setBeam(startPoint.x, startPoint.y, _direction, true, lightSource);
							}
							lightValue++;
						}
						else if (editGridCont.isBeam(startPoint.x, startPoint.y)){
							Beam beam = editGridCont.getBeam(startPoint.x, startPoint.y);
							if (beam.isBeamEnd() && isAlreadySameBeam(new Point(startPoint.x, startPoint.y), _direction)){
								beam.changeBeamEnd();
							}
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
	
	private void removeBeam(int x, int y){
		int x_start = x, y_start = y;
		Beam beam = editGridCont.getBeam(x_start, y_start);
		if (beam.isBeamEnd()){
			LightSource lightSource = beam.getRemLightSource();
			lightSource.setCapacity(lightSource.getCapacity()-1);
			setNewLastBeam(EditorController.getCell(x_start, y_start));
			editGridCont.removeBeam(x_start, y_start);
		}
	}
	
	private void removeLightSourceAndBeams(int click_x, int click_y){
		LightSource lightSource = editGridCont.getLightSource(click_x, click_y);
		for (int x=0; x <= EditorController.getGridWidth(); x++){
			for (int y=0; y <= EditorController.getGridHeight(); y++){
				if (editGridCont.isBeam(x, y)){
					Beam beam = editGridCont.getBeam(x, y);
					LightSource vglLightSource = beam.getRemLightSource();
					if (isSameLightSource(lightSource, vglLightSource)){
						editGridCont.removeBeam(x, y);
					}
				}
			}
		}
		editGridCont.removeLightSource(click_x, click_y);
	}
	
	private boolean isSameLightSource(LightSource lightSource, LightSource vglLightSource){
		return (lightSource == vglLightSource)? true : false;
	}
	
	private void setNewLastBeam(Cell changed){
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