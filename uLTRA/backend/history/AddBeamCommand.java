/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

package history;

import java.io.Serializable;
import gamegrid.*;

public class AddBeamCommand implements Command, Serializable {

	private Turn _turn;
	private BeamDirections _direction;
	
	public AddBeamCommand(Turn pTurn) {
		_turn = pTurn;
	}
	
	@Override
	public void execute() {
		GameGrid _gameGrid = null;
		
		try {
			_gameGrid = GameGrid.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		int startX = _turn.getStart().x;
		int startY = _turn.getStart().y;
		int endX = _turn.getEnd().x;
		int endY = _turn.getEnd().y;	
		
		if(startX == endX) {
			if(startY < endY) {
				_direction = BeamDirections.BEAM_DOWN;
				
				for (int i = startY; i <= endY; i++) {
					Cell cell = _gameGrid.getCell(startX, i);
					cell.setContent(new Beam(_direction));
				}
			}
			else {
				_direction = BeamDirections.BEAM_UP;
				
				for (int i = startY; i >= endY; i--) {
					Cell cell = _gameGrid.getCell(startX, i);
					cell.setContent(new Beam(_direction));
				}
			}
		}
		else {
			if(startX < endX) {
				_direction = BeamDirections.BEAM_RIGHT;
				
				for (int j = startX; j <= endX; j++) {
					Cell cell = _gameGrid.getCell(j, startY);
					cell.setContent(new Beam(_direction));
				}
			}
			else {
				_direction = BeamDirections.BEAM_LEFT;
				
				for (int j = startX; j >= endX; j--) {
					Cell cell = _gameGrid.getCell(j, startY);
					cell.setContent(new Beam(_direction));
				}
			}
		}
	}

	@Override
	public void undo() {
		GameGrid _gameGrid = null;
		
		try {
			_gameGrid = GameGrid.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		int startX = _turn.getStart().x;
		int startY = _turn.getStart().y;
		int endX = _turn.getEnd().x;
		int endY = _turn.getEnd().y;	
		
		if(startX == endX) {
			if(startY < endY) {				
				for (int i = startY; i <= endY; i++) {
					Cell cell = _gameGrid.getCell(startX, i);
					cell.setContent(new EmptyContent());
				}
					
			}
			else {				
				for (int i = startY; i >= endY; i--) {
					Cell cell = _gameGrid.getCell(startX, i);
					cell.setContent(new EmptyContent());
				}
			}
		}
		else {
			if(startX < endX) {				
				for (int j = startX; j <= endX; j++) {
					Cell cell = _gameGrid.getCell(j, startY);
					cell.setContent(new EmptyContent());
				}
			}
			else {				
				for (int j = startX; j >= endX; j--) {
					Cell cell = _gameGrid.getCell(j, startY);
					cell.setContent(new EmptyContent());
				}
			}
		}
	}
	
	public BeamDirections getDirection() {
		return _direction;
	}
	
	public Turn getTurn() {
		return _turn;
	}
}