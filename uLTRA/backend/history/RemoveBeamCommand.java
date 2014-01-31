/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

package history;

import java.io.Serializable;

import panels.GamePanel;
import gamegrid.*;

public class RemoveBeamCommand implements Command, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7982168976797896874L;
	
	private final int xStart, yStart;
	private final int xEnd, yEnd;
	private final LightSource lightSource;
	private final BeamDirections direction;
	
	private boolean executed = false;
	private boolean undone = false;
	private boolean lastCommand = false;

	private GameGrid gameGrid;
	private int length = 0;
	
	public RemoveBeamCommand(int xS, int yS, int xE, int yE, LightSource ls, BeamDirections dir) throws Exception
	{
		xStart = xS;
		yStart = yS;
		xEnd = xE;
		yEnd = yE;
		lightSource = ls;
		direction = dir;
		
		gameGrid = GameGrid.getInstance();
	}

	@Override
	public void execute()
	{
		executed = true;
		undone = false;
		
		switch(direction)
		{
		case BEAM_UP:
			length = yStart - yEnd +1;
			for(int activeCoordinate = yStart; activeCoordinate >= yEnd; activeCoordinate--)
				gameGrid.getCell(xStart, activeCoordinate).setContent(new EmptyContent());
			break;
		case BEAM_DOWN:
			length = yEnd - yStart +1;
			for(int activeCoordinate = yStart; activeCoordinate <= yEnd; activeCoordinate++)
				gameGrid.getCell(xStart, activeCoordinate).setContent(new EmptyContent());
			break;
		case BEAM_LEFT:
			length = xStart - xEnd +1;
			for(int activeCoordinate = xStart; activeCoordinate >= xEnd; activeCoordinate--)
				gameGrid.getCell(activeCoordinate, yStart).setContent(new EmptyContent());
			break;
		case BEAM_RIGHT:
			length = xEnd - xStart +1;
			for(int activeCoordinate = xStart; activeCoordinate <= xEnd; activeCoordinate++)
				gameGrid.getCell(activeCoordinate, yStart).setContent(new EmptyContent());
			break;	
		}
		lightSource.setRemainingCapacity(lightSource.getRemainingCapacity()+length);
		
		GamePanel.getGamePanel().getGridPanel().resetLayout();
		GamePanel.getGamePanel().refresh();
	}
	
	@Override
	public void undo() {
		undone = true;
		executed = false;
		
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
	
	public String toString()
	{
		return "remove " + xStart + "/" + yStart + " to " + xEnd + "/" + yEnd;
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

	@Override
	public boolean isCorrect()
	{
		return true;
	}
}