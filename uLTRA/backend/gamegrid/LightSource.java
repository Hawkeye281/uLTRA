/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

package gamegrid;

import java.io.Serializable;

public class LightSource implements CellContent, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4174054452523873042L;
	private int capacity = 0;
	private int remainingCapacity = 0;	
	
	public LightSource(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Initialkapazität muss 0 oder höher sein.");
		
		capacity = initialCapacity;
		remainingCapacity = capacity;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int newCapacity) {
		if(newCapacity < 0)
			throw new IllegalArgumentException("Kapazität darf nicht unter 0 sein.");
		capacity = newCapacity;
	}
	
	public int getRemainingCapacity() {
		return remainingCapacity;
	}
	
	public void setRemainingCapacity(int newRemainingCapacity) {
		if(newRemainingCapacity < 0)
			throw new IllegalArgumentException("Kapazität darf nicht unter 0 sinken.");
		remainingCapacity = newRemainingCapacity;
	}
}
