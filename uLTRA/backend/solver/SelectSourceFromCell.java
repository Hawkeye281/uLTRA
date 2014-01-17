package solver;

import Controller.GameController;
import gamegrid.BeamDirections;
import gamegrid.Cell;
import gamegrid.CellContent;
import gamegrid.GameGrid;
import gamegrid.Beam;
import gamegrid.LightSource;

public class SelectSourceFromCell implements SolveAlgorithm {

	int gridHeight, gridWidth; // grid dimensions
	GameController gc;
	
	public SelectSourceFromCell()
	{
		gc = new GameController();
	}
	
	@Override
	public void solve(GameGrid g)
	{	
		gridHeight = g.getHeight();
		gridWidth = g.getWidth();
		
		Cell lightSource;
		Cell currentCell;
		for(int h = 0; h <= gridHeight; h++)
		{
			for(int w = 0; w <= gridWidth; w++)
			{
				currentCell = g.getCell(w,h);
				
				if(currentCell.isEmpty())
				{
					Reachable r = findLightSources(currentCell);
					
					switch(r)
					{
					case ZERO: // Cell is not reached by any lightsource, puzzle cannot be solved
						throw new IllegalStateException("Cell at [" + w + "," + h + "] cannot be reached by any lightsource.");
					case GREATER_ONE: // reaching lightsource cannot be determined (yet)
						break;
					default:
						lightSource = getReachingLightSource(currentCell, r);
						if(canReachCell(currentCell, lightSource, r))
							gc.addTurn(lightSource.getCoordinates(), currentCell.getCoordinates());
					}
				}
			}
		}		
	}
	
	private boolean canReachCell(Cell target, Cell lightSource, Reachable lightSourcePosition)
	{
		// compute distance between cells
		int distance;
		switch(lightSourcePosition)
		{
		case TOP:
		case BOTTOM:
			distance = Math.abs(target.getY() - lightSource.getY());
			break;
		default:
			distance = Math.abs(target.getX() - lightSource.getX());
			break;
		}
		
		// walk from lightsource to cell, count number of beam cells emitted from light source and empty cells
		// determine whether lightsource has enough remaining capacity to reach cell
		Cell current = lightSource;
		int emptyCells = 0;
		while(current != target)
			if(!current.isBeam())
				emptyCells++;
		
		if(emptyCells > ((LightSource)lightSource.getContent()).getRemainingCapacity())
			return true;
		return false;
	}
	
	private Reachable findLightSources(Cell c)
	{
		Cell origin = c;
		Cell currentCell;
		int reaching = 0;
		Reachable result = Reachable.ZERO;
		
		currentCell = origin;
		
//		System.out.println("Cell: " + origin);
//		System.out.println("hasTopCell: " + currentCell.hasTopCell());
//		System.out.println("hasBottomCell: " + currentCell.hasBottomCell());
//		System.out.println("hasLeftCell: " + currentCell.hasLeftCell());
//		System.out.println("hasRightCell: " + currentCell.hasRightCell());
		
		while(currentCell.hasTopCell())
		{
			currentCell = currentCell.getTopCell();
			
			if(currentCell.isLightSource())
			{
				reaching++;
				result = Reachable.TOP;
				break;
			}
			
			if(currentCell.isBeam() && ((Beam)currentCell.getContent()).getDirection() != BeamDirections.BEAM_DOWN)
				break;
		}
		
		currentCell = origin;
		
		while(currentCell.hasLeftCell())
		{
			currentCell = currentCell.getLeftCell();
			
			if(currentCell.isLightSource())
			{
				reaching++;
				result = Reachable.LEFT;
				break;
			}
			
			if(currentCell.isBeam() && ((Beam)currentCell.getContent()).getDirection() != BeamDirections.BEAM_RIGHT)
				break;
		}
		
		if(reaching > 1)
			return Reachable.GREATER_ONE;
		
		currentCell = origin;
		
		while(currentCell.hasBottomCell())
		{
			currentCell = currentCell.getBottomCell();
			if(currentCell.isLightSource())
			{
				reaching++;
				result = Reachable.BOTTOM;
				break;
			}
			
			if(currentCell.isBeam() && ((Beam)currentCell.getContent()).getDirection() != BeamDirections.BEAM_UP)
				break;
		}
		
		if(reaching > 1)
			return Reachable.GREATER_ONE;
		
		currentCell = origin;
		
		while(currentCell.hasRightCell())
		{
			currentCell = currentCell.getRightCell();
			
			if(currentCell.isLightSource())
			{
				reaching++;
				result = Reachable.RIGHT;
				break;
			}
			
			if(currentCell.isBeam() && ((Beam)currentCell.getContent()).getDirection() != BeamDirections.BEAM_LEFT)
				break;
		}
		
		if(reaching > 1)
			return Reachable.GREATER_ONE;
		
		return result;
	}
	
	private Cell getReachingLightSource(Cell c, Reachable direction)
	{	
		switch(direction)
		{
		case TOP:
			while(c.hasTopCell())
				c = c.getTopCell();
			break;
		case LEFT:
			while(c.hasLeftCell())
				c = c.getLeftCell();
			break;
		case BOTTOM:
			while(c.hasBottomCell())
				c = c.getBottomCell();
			break;
		case RIGHT:
			while(c.hasRightCell())
				c = c.getRightCell();
			break;
		default:
			return null;
		}
		return c;
	}

	private enum Reachable
	{
		ZERO,
		GREATER_ONE,
		TOP,
		BOTTOM,
		LEFT,
		RIGHT
	}
}
