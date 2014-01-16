package solver;

import gamegrid.GameGrid;

public class SmartSolver {

	public static void solve(GameGrid g)
	{	
		SolveAlgorithm solver; // reference to currently used solver
		
		// different solve algorithms
		SolveAlgorithm selectSourceFromCellSolver = new SelectSourceFromCell();
		
		while(!gridSolved(g))
		{
			solver = selectSourceFromCellSolver;
			solver.solve(g);
			
			if(gridSolved(g))
				break;
			
			
			
		}
		
	}
	
	/**
	 * checks if the provided GameGrid is solved by looking for empty cells
	 * if any empty cells are found, the grid has not been solved yet
	 * @param g
	 * @return
	 */
	private static boolean gridSolved(GameGrid g)
	{
		int width = g.getWidth();
		int height = g.getHeight();
		
		for(int w = 0; w < width; w++)
			for(int h = 0; h < height; h++)
				if(g.getCell(w, h).isEmpty())
					return false;
		return true;
	}
}