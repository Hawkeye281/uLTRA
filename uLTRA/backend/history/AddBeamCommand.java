/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

package history;

import java.io.Serializable;

import panels.GamePanel;
import gamegrid.*;

public class AddBeamCommand implements Command, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4183981092337765649L;

	private final int xStart, yStart;
	private final int xEnd, yEnd;
	private final BeamDirections direction;
	private final LightSource lightSource;
	private final int length;
	
	private boolean undone = false;
	private boolean executed = false;
	private boolean lastCommand = false;
	
	private GameGrid gameGrid;
	
	public AddBeamCommand(int xS, int yS, int xE, int yE, BeamDirections dir) throws Exception
	{
		gameGrid = GameGrid.getInstance();
		
		lightSource = (LightSource)gameGrid.getCell(xS,yS).getContent();
		direction = dir;
		
		length = xS==xE ? Math.abs(yS-yE) : Math.abs(xS-xE);
		
		// set start coordinates as first cell containing a beam element (neighbour of lightsource, depending on direction)
		switch(direction)
		{
		case BEAM_UP:
			xStart = xS;
			yStart = yS-1; // first beam element is above lightsource
			break;
		case BEAM_DOWN:
			xStart = xS;
			yStart = yS+1; // first beam element is below lightsource
			break;
		case BEAM_LEFT:
			xStart = xS-1; // first beam element is left of lightsource
			yStart = yS;
			break;
		case BEAM_RIGHT:
			xStart = xS+1; // first beam element is right of lightsource
			yStart = yS;
			break;
		default: // to avoid compiler errors, never reached since all directions are touched by case
			xStart = -1;
			yStart = -1;
			break;
		}

		xEnd = xE;
		yEnd = yE;
	}
	
	@Override
	public void execute()
	{
		executed = true;
		undone = false;
		
		// draw beam
		int activeCoordinate;
		switch(direction)
		{
		case BEAM_UP:
			for(activeCoordinate = yStart; activeCoordinate > yEnd; activeCoordinate--) // set "body" of beam
				gameGrid.getCell(xStart, activeCoordinate).setContent(new Beam(direction, false, lightSource));
			break;
		case BEAM_DOWN:
			for(activeCoordinate = yStart; activeCoordinate < yEnd; activeCoordinate++) // set "body" of beam
				gameGrid.getCell(xStart, activeCoordinate).setContent(new Beam(direction, false, lightSource));
			break;
		case BEAM_LEFT:
			for(activeCoordinate = xStart; activeCoordinate > xEnd; activeCoordinate--) // set "body" of beam
				gameGrid.getCell(activeCoordinate, yStart).setContent(new Beam(direction, false, lightSource));
			break;
		case BEAM_RIGHT:
			for(activeCoordinate = xStart; activeCoordinate < xEnd; activeCoordinate++) // set "body" of beam
				gameGrid.getCell(activeCoordinate, yStart).setContent(new Beam(direction, false, lightSource));
		}
		// set end of beam
		gameGrid.getCell(xEnd, yEnd).setContent(new Beam(direction, true, lightSource));
		
		// update remaining capacity of lightsource
		lightSource.setRemainingCapacity(lightSource.getRemainingCapacity()-length);
		
		// update gui
		GamePanel.getGamePanel().getGridPanel().resetLayout();
		GamePanel.getGamePanel().refresh();
	}

	@Override
	public void undo() {
		undone = true;
		executed = false;
		
		// remove beam
		int activeCoordinate;
		switch(direction)
		{
		case BEAM_UP:
			for(activeCoordinate = yStart; activeCoordinate >= yEnd; activeCoordinate--)
				gameGrid.getCell(xStart, activeCoordinate).setContent(new EmptyContent());
			break;
		case BEAM_DOWN:
			for(activeCoordinate = yStart; activeCoordinate <= yEnd; activeCoordinate++)
				gameGrid.getCell(xStart, activeCoordinate).setContent(new EmptyContent());
			break;
		case BEAM_LEFT:
			for(activeCoordinate = xStart; activeCoordinate >= xEnd; activeCoordinate--)
				gameGrid.getCell(activeCoordinate, yStart).setContent(new EmptyContent());
			break;
		case BEAM_RIGHT:
			for(activeCoordinate = xStart; activeCoordinate <= xEnd; activeCoordinate++)
				gameGrid.getCell(activeCoordinate, yStart).setContent(new EmptyContent());
		}
		
		// update remaining capacity of lightsource
		lightSource.setRemainingCapacity(lightSource.getRemainingCapacity()+length);
		
		// update gui
		GamePanel.getGamePanel().getGridPanel().resetLayout();
		GamePanel.getGamePanel().refresh();
	}
	
	public String toString()
	{
		return "add " + xStart + "/" + yStart + " to " + xEnd + "/" + yEnd;
	}
	
	public boolean lastCommand()
	{
		return lastCommand;
	}
	
	public boolean undone()
	{
		return undone;
	}
	
	public boolean executed()
	{
		return executed;
	}
	
	public void setLastCommand(boolean isLast)
	{
		lastCommand = isLast;
	}
}