/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */


package gamegrid;

import java.util.*;

public class GameGrid {
	private ArrayList<ArrayList<Cell>> gameGrid = new ArrayList<ArrayList<Cell>>();
	private int width, height = 0;
	
	public GameGrid(int width, int height) {
		if (width < 0 || height < 0) 
			throw new IllegalArgumentException();
		
		this.width = width;
		this.height = height;
		
		for (int i = 0; i < height; i++) {
			gameGrid.add(i, new ArrayList<Cell>());

			for (int j = 0; j < width; j++) {
				gameGrid.get(i).add(j, new Cell());
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int height() {
		return height;
	}
	
	public Cell getCell(int x, int y) {
		if (!isInGrid(x, y))
			throw new IllegalArgumentException();

		return gameGrid.get(x).get(y);
	}
	
	public void setCell(Cell pCell, int x, int y) {
		if (!isInGrid(x, y))
			throw new IllegalArgumentException();
		
		gameGrid.get(x).set(y, pCell);
	}
	
	private boolean isInGrid(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height)
			return false;
		return true;
	}
}
