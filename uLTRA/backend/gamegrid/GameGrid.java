/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */


package gamegrid;

import java.awt.Point;
import java.util.Observable;

public class GameGrid extends Observable {
	private Cell[][] gameGrid;
	private int width, height = 0;
	
	public GameGrid(int width, int height) {
		if (width < 0 || height < 0) 
			throw new IllegalArgumentException();
		
		this.width = width;
		this.height = height;
		
		gameGrid = new Cell[width][height];
		
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				gameGrid[x][y] = new Cell();
	}
	
//	public void solve(SolveAlgorithm pSolveAlgorithm) {
//		
//	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Cell getCell(int x, int y) {
		if (!isInGrid(x, y))
			throw new IllegalArgumentException();

		return gameGrid[x][y];
	}
	
	public Cell getCell(Point pPoint)
	{
		return getCell(pPoint.x, pPoint.y);
	}
	
	public void setCell(Cell pCell, int x, int y) {
		if (!isInGrid(x, y))
			throw new IllegalArgumentException();
		
		gameGrid[x][y] = pCell;
		
		setChanged();
		notifyObservers();
	}
	
	private boolean isInGrid(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height)
			return false;
		return true;
	}
}
