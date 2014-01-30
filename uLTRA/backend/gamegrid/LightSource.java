/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

package gamegrid;

import java.io.Serializable;
import java.util.ArrayList;

public class LightSource implements CellContent, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4174054452523873042L;
	private int capacity = 0;
	private int remainingCapacity = 0;	
	private ArrayList<Beam> beamList;
	
	public LightSource(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Initialkapazität muss 0 oder höher sein.");
		
		capacity = initialCapacity;
		remainingCapacity = capacity;
		beamList = new ArrayList<Beam>();
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
	
	public void addBeamToList(Beam beam){
		this.beamList.add(beam);
		setCapacity(this.beamList.size());
	}
	
	public void removeBeamFromList(Beam beam){
		this.beamList.remove(beam);
	}
	
	public ArrayList<Beam> getBeamList(){
		return this.beamList;
	}
	
	public LightSource clone() {
		LightSource newLightSource = new LightSource(this.capacity);
		newLightSource.setRemainingCapacity(this.remainingCapacity);
		
		return newLightSource;
	}
}
