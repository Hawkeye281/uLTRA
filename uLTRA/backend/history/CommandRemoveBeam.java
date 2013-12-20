/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

package history;

import gamegrid.*;

public class CommandRemoveBeam implements Command {

	private Turn _turn;
	
	public CommandRemoveBeam(Turn pTurn) {
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
		BeamDirections direction = null;		
		
		if(startX == endX) {
			if(startY < endY) {
				direction = BeamDirections.BEAM_DOWN;
				
				for (int i = startY; i <= endY; i++) {
					Cell cell = _gameGrid.getCell(startX, i);
					cell.setContent(new Beam(direction));
				}
					
			}
			else {
				direction = BeamDirections.BEAM_UP;
				
				for (int i = startY; i >= endY; i--) {
					Cell cell = _gameGrid.getCell(startX, i);
					cell.setContent(new Beam(direction));
				}
			}
		}
		else {
			if(startX < endX) {
				direction = BeamDirections.BEAM_RIGHT;
				
				for (int j = startX; j <= endX; j++) {
					Cell cell = _gameGrid.getCell(j, startY);
					cell.setContent(new Beam(direction));
				}
			}
			else {
				direction = BeamDirections.BEAM_LEFT;
				
				for (int j = startX; j >= endX; j--) {
					Cell cell = _gameGrid.getCell(j, startY);
					cell.setContent(new Beam(direction));
				}
			}
		}
	}
}