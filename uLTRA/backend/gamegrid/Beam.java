/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

package gamegrid;

public class Beam implements CellContent {
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
