/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

package gamegrid;

import java.io.Serializable;

public class Beam implements CellContent, Serializable {
	private static final long serialVersionUID = -7518155070908255527L;
	private BeamDirections direction;
	
	public Beam(BeamDirections initialDirection) {
		direction = initialDirection;
	}
	
	public BeamDirections getDirection() {
		return direction;
	}
	
	public void setDirection(BeamDirections newDirection) {
		direction = newDirection;
	}
}