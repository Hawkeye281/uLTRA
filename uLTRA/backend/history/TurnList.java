/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

package history;

import java.io.Serializable;
import java.util.*;

public class TurnList implements Serializable {
	
	private ArrayList<Command> history = new ArrayList<Command>();
	
	public TurnList(TurnList pTurnlist) {
		history = pTurnlist.history;
	}
	
	public void exec(Command pCommand) {
		history.add(pCommand);
		pCommand.execute();
	}
	
	public void removeLastTurn() {
		history.remove(history.size() - 1);
	}
	
	public void replay() {
		for (Command _command : history)
			_command.execute();
	}
	
	public void reset() {
		history.clear();
	}
	
	public ArrayList<Command> getHistory() {
		return history;
	}
}