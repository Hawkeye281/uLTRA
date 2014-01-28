/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */

package gamegrid;

import java.awt.Point;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Observable;
import history.TurnList;

public class GameGrid extends Observable implements Serializable {

	private static GameGrid _instance = null;
	private static final long serialVersionUID = -2537055812502261249L;
	private Cell[][] gameGrid;
	private int width, height = 0;
	private TurnList _turnList = null;
	private boolean playable = true;
	
	/**
	 * 
	 * Copy constructor!
	 * 
	 * @param GameGrid pReference
	 * 
	 */
	public GameGrid(GameGrid pReference) {
		gameGrid = new Cell[pReference.height][pReference.width];
		
		for (int x = 0; x < pReference.gameGrid.length; x++)
			for (int y = 0; y < pReference.gameGrid[x].length; y++)
				gameGrid[x][y] = new Cell(pReference.gameGrid[x][y]);
		
		width = pReference.width;
		height = pReference.height;
	}
	
	/**
	 * 
	 * @param int height
	 * @param int width
	 * 
	 */
	private GameGrid(int height, int width) {
		if (width < 0 || height < 0) 
			throw new IllegalArgumentException();

		this.width = width;
		this.height = height;

		gameGrid = new Cell[height][width];

		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				gameGrid[y][x] = new Cell(x,y);
	}

	/**
	 * 
	 * @return GameGrid
	 * @throws Exception
	 * 
	 */
	public static GameGrid getInstance() throws Exception {
		if (null == _instance)
			throw new NullPointerException("Gamegrid not initialised");
//		System.out.println("return: " + _instance);
		return _instance;		
	}
	
	/**
	 * 
	 * @param int height
	 * @param int width
	 * @return GameGrid
	 * 
	 */
	public static GameGrid getInstance(int height, int width) {
		if (null == _instance)
			_instance = new GameGrid(height, width);

		return _instance;
	}
	
	public static void deleteInstance() {
		_instance = null;
	}
	
	public static void setInstance(GameGrid pGameGrid) {
		_instance = pGameGrid;
	}
	
	/**
	 * 
	 * @return int
	 * 
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * 
	 * @return int
	 * 
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @param int x
	 * @param int y
	 * @return Cell
	 * 
	 */
	public Cell getCell(int x, int y) {
		if (!isInGrid(x, y))
			throw new IllegalArgumentException();

		return gameGrid[y][x];
	}
	
	/**
	 * 
	 * @param point pPoint
	 * @return Cell
	 * 
	 */
	public Cell getCell(Point pPoint) {
		return getCell(pPoint.x, pPoint.y);
	}
	
	/**
	 * 
	 * @param Cell pCell
	 * @param int x
	 * @param int y
	 */
	public void setCell(Cell pCell, int x, int y) {
		if (!isInGrid(x, y))
			throw new IllegalArgumentException();
		
		gameGrid[y][x] = pCell;
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * 
	 * @param int x
	 * @param int y
	 * @return boolean
	 * 
	 */
	public boolean isInGrid(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height)
			return false;
		return true;
	}
	
	public TurnList getTurnList() {
		return _turnList;
	}
	
	public void setTurnList(TurnList pTurnList) {
		_turnList = pTurnList;
	}
	
	public boolean getPlayable(){
		return this.playable;
	}
	
	public void setPlayable(boolean playable){
		this.playable = playable;
	}
}
