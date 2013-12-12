/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

package history;

public interface Command {

	public void execute();
	
	public void undo();
}