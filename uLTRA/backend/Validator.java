/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

import java.io.Serializable;
import java.util.ArrayList;
import history.*;
import gamegrid.*;

public class Validator implements Serializable{
	
	private static Validator _instance = null;
	private TurnList _reference;
	
	private Validator(TurnList pReference) {
		_reference = new TurnList(pReference);
	}
	
	public static Validator getInstance() throws NullPointerException {
		if (null == _instance)
			throw new NullPointerException("Validator not initialised");

		return _instance;		
	}
	
	public static Validator getInstance(TurnList pReference) {
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
	
	public boolean isValid(CommandAddBeam pCommand) {
		Turn turn = pCommand.getTurn();
		BeamDirections direction = pCommand.getDirection();
		ArrayList<Command> history = _reference.getHistory();
		
		for (Command _command : history) {
			if (!(_command instanceof CommandAddBeam))
				continue;

			CommandAddBeam _currentCommand = (CommandAddBeam) _command;
			
			if (direction != _currentCommand.getDirection())
				continue;
			
			Turn _currentTurn = _currentCommand.getTurn();
			
			if (direction == BeamDirections.BEAM_DOWN ||
				direction == BeamDirections.BEAM_UP)
			{
				if (turn.getStart().x != _currentTurn.getStart().x)
					continue;
			}
			else
			{
				if (turn.getStart().y != _currentTurn.getStart().y)
					continue;
			}
		}
	}
}