package solver;

import gamegrid.Cell;
import gamegrid.CellContent;
import gamegrid.GameGrid;

public class SelectSourceFromCell implements SolveAlgorithm {

	int gridHeight, gridWidth; // grid dimensions
	
	@Override
	public void solve(GameGrid g) {
		gridHeight = g.getHeight();
		gridWidth = g.getWidth();
		Cell lightSource;
		Cell currentCell;
		for(int h = 0; h <= gridHeight; h++)
		{
			for(int w = 0; w <= gridWidth; w++)
			{
				currentCell = g.getCell(w,h);
				
				if(g.getCell(w,h).isEmpty())
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
						int distance;
						switch(r)
						{
						case TOP:
						case BOTTOM:
							distance = Math.abs(currentCell.getY() - lightSource.getY());
							break;
						default:
							distance = Math.abs(currentCell.getX() - lightSource.getX());
							break;
						}
						
					}
				}
			}
		}		
	}
	
	private Reachable findLightSources(Cell c)
	{
		Cell origin = c;
		Cell currentCell;
		int reaching = 0;
		Reachable result = Reachable.ZERO;
		
		currentCell = origin;
		
		while(currentCell.hasTopCell())
		{
			currentCell = currentCell.getTopCell();
			
			if(currentCell.isLightSource())
			{
				reaching++;
				result = Reachable.TOP;
				break;
			}
			
			if(currentCell.isBeam())
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
			
			if(currentCell.isBeam())
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
			
			if(currentCell.isBeam())
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
			
			if(currentCell.isBeam())
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
