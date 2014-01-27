/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

package gamegrid;

import java.io.Serializable;

public class Beam implements CellContent, Serializable {
	private static final long serialVersionUID = -7518155070908255527L;
	private BeamDirections direction;
	private LightSource lightSource;
	private boolean beamEnd;
	
	public Beam(BeamDirections initialDirection) {
		direction = initialDirection;
		beamEnd = false;
	} 
	
	public Beam (BeamDirections initialDirection, boolean pBeamEnd, LightSource remainingLightSource){
		this.direction = initialDirection;
		this.beamEnd = pBeamEnd;
		this.lightSource = remainingLightSource;
	}
	
	public Beam(BeamDirections initialDirection, boolean pBeamEnd) {
		direction = initialDirection;
		beamEnd = pBeamEnd;
	}
	
	public BeamDirections getDirection() {
		return direction;
	}
	
	public void setDirection(BeamDirections newDirection) {
		direction = newDirection;
	}
	
	public void setBeamEnd(boolean pBeamEnd){
		this.beamEnd = pBeamEnd;
	}
	
	public void changeBeamEnd(){
		this.beamEnd = (this.beamEnd)? false : true;
	}
	
	public boolean isBeamEnd()
	{
		return beamEnd;
	}
	
	public LightSource getRemLightSource(){
		return this.lightSource;
	}
	
	public Beam clone() {
		return new Beam(this.direction, this.beamEnd, this.lightSource);
	}
}