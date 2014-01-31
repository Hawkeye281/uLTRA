/**
 * @author Carsten Strauch
 * @copyright 2014 TASACDWS
 */

package gamegrid;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import history.*;

public class Validator implements Serializable{
	
	private static Validator _instance = null;
	private GameGrid _reference = null;
	
	private Validator(GameGrid pReference) {
		_reference = new GameGrid(pReference);
	}
	
	public static Validator getInstance() throws NullPointerException {
		if (null == _instance)
			throw new NullPointerException("Validator not initialised");

		return _instance;		
	}
	
	public static Validator getInstance(GameGrid pReference) {
		if (null == _instance)
			_instance = new Validator(pReference);
		
		return _instance;
	}
	
	public static void deleteInstance() {
		_instance = null;
	}
	
	public static void setInstance(Validator pValidator) {
		_instance = pValidator;
	}
	
	public boolean isValid(int xStart, int yStart, int xEnd, int yEnd) {
		Point start = new Point(xStart, yStart);
		Point end = new Point(xEnd, yEnd);
		BeamDirections direction = null;
		
		if (start.x == end.x) {
			if (start.y < end.y)
				direction = BeamDirections.BEAM_UP;
			else if (start.y > end.y)
				direction = BeamDirections.BEAM_DOWN;
			
			int lowerY = Math.min(start.y, end.y);
			int greaterY = Math.max(start.y, end.y);
			
			for (int currentY = lowerY; currentY <= greaterY; currentY++) {
				Cell currentCell = _reference.getCell(start.x, currentY);
				CellContent currentContent = currentCell.getContent();
				
				if(currentContent instanceof Beam)
					if (((Beam)currentContent).getDirection() != direction)
						return false;
			}
			
			return true;
			
		} else if (start.y == end.y) {
			if (start.x < end.x)
				direction = BeamDirections.BEAM_RIGHT;
			else if (start.x > end.x)
				direction = BeamDirections.BEAM_LEFT;
			
			int lowerX = Math.min(start.x, end.x);
			int greaterX = Math.max(start.x, end.x);
			
			for (int currentX = lowerX; currentX <= greaterX; currentX++) {
				Cell currentCell = _reference.getCell(currentX, start.y);
				CellContent currentContent = currentCell.getContent();
				
				if(currentContent instanceof Beam)
					if (((Beam)currentContent).getDirection() != direction)
						return false;
			}
			
			return true;
		}
		
		return false;
	}
}