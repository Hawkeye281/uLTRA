/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

package gamegrid;

public class LightSource implements CellContent {
	private int capacity = 0;
	private int remainingCapacity = 0;	
	
	public LightSource(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException();
		
		capacity = initialCapacity;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int newCapacity) {
		capacity = newCapacity;
	}
	
	public int getRemainingCapacity() {
		return remainingCapacity;
	}
	
	public void setRemainingCapacity(int newRemainingCapacity) {
		remainingCapacity = newRemainingCapacity;
	}
}
