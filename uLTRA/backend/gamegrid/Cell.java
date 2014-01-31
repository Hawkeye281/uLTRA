/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */


package gamegrid;

import java.awt.Point;
import java.io.Serializable;

public class Cell implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2979456417318764696L;
	private Cell topCell, bottomCell, leftCell, rightCell = null;
	private CellContent content;
	private Point coordinates;
	private boolean hasTop, hasBottom, hasRight, hasLeft;
	
	public Cell(Cell pCell) {
		topCell = (null == pCell.topCell ? null : new Cell(pCell.topCell));
		bottomCell = (null == pCell.bottomCell ? null : new Cell(pCell.bottomCell));
		leftCell = (null == pCell.leftCell ? null : new Cell(pCell.leftCell));
		rightCell = (null == pCell.rightCell ? null : new Cell(pCell.rightCell));
		content = pCell.content.clone();
		coordinates = new Point(pCell.coordinates);
		hasTop = pCell.hasTop;
		hasBottom = pCell.hasBottom;
		hasRight = pCell.hasRight;
		hasLeft = pCell.hasLeft;
	}
	
	public Cell(int x, int y) {
		content = new EmptyContent();
		coordinates = new Point(x,y);
	}
	
	public Cell(int x, int y, CellContent initialContent) {
		if(initialContent == null)
			throw new IllegalArgumentException("Content may not be null.");
		content = initialContent;
		coordinates = new Point(x,y);
		
		// set neighbor cells
		GameGrid gg;
		try {
			gg = GameGrid.getInstance();
			if(x > 0)
				leftCell = gg.getCell(x-1,y);
			if(y > 0)
				topCell = gg.getCell(x,y-1);
			if(x < gg.getWidth())
				rightCell = gg.getCell(x+1,y);
			if(y < gg.getHeight())
				bottomCell = gg.getCell(x,y+1);
		} catch (Exception e) {
			e.printStackTrace();
		}

			
	}
	
	public CellContent getContent() {
		return content;
	}
	
	public Point getCoordinates() {
		return coordinates;
	}
	
	public int getX() {
		return (int)coordinates.getX();
	}
	
	public int getY() {
		return (int)coordinates.getY();
	}
	
	public void setContent(CellContent newContent) {
		content = newContent;
	}
	
	public boolean hasTopCell()	{
		return topCell != null;
	}
	
	public Cell getTopCell() {
		return topCell;
	}
	
	public void setTopCell(Cell newTopCell) {
		topCell = newTopCell;
	}
	
	public boolean hasBottomCell() {
		return bottomCell != null;
	}
	
	public Cell getBottomCell() {
		return bottomCell;
	}
	
	public void setBottomCell(Cell newBottomCell) {
		bottomCell = newBottomCell;
	}
	
	public boolean hasLeftCell() {
		return leftCell != null;
	}
	
	public Cell getLeftCell() {
		return leftCell;
	}
	
	public void setLeftCell(Cell newLeftCell) {
		leftCell = newLeftCell;
	}
	
	public boolean hasRightCell() {
		return rightCell != null;
	}
	
	public Cell getRightCell() {
		return rightCell;
	}
	
	public void setRightCell(Cell newRightCell) {
		rightCell = newRightCell;
	}
	
	public boolean isEmpty() {
		return content instanceof EmptyContent;
	}
	
	public boolean isLightSource() {
		return content instanceof LightSource; 
	}
	
	public boolean isBeam() {
		return content instanceof Beam;
	}	
	
	public void removeContent(){
		content = new EmptyContent();
	}
	
	public String toString()
	{
		return "["+coordinates.getX()+","+coordinates.getY()+"]: " + content.getClass();
	}
}