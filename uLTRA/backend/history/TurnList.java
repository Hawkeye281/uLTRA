package history;

import java.util.*;

public class TurnList {

	private ArrayList<Command> history = new ArrayList<Command>();
	
	public TurnList() {

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
}