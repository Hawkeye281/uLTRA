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
//		history.get(history.lastIndexOf(history));
	}
	
	public void replay() {
		for (Command _command : history)
			_command.execute();
	}
}