/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

package history;

public interface Command {

	public void execute();
	
	public void undo();
	
	public boolean undone();
	
	public boolean executed();
	
	public boolean lastCommand();
	
	public void setLastCommand(boolean isLast);
}