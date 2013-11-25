/**
 * @author Carsten Strauch
 * @copyright 2013 TASACDWS
 */


package gamegrid;

public class Cell {

	private Cell topCell, bottomCell, leftCell, rightCell = null;
	private CellContent content;
	
	public Cell() {
		
	}
	
	public Cell(CellContent initialContent) {
		content = initialContent;
	}
	
	public CellContent getContent() {
		return content;
	}
	
	public void setContent(CellContent newContent) {
		content = newContent;
	}
	
	public Cell getTopCell() {
		return topCell;
	}
	
	public void setTopCell(Cell newTopCell) {
		topCell = newTopCell;
	}
	
	public Cell getBottomCell() {
		return bottomCell;
	}
	
	public void setBottomCell(Cell newBottomCell) {
		bottomCell = newBottomCell;
	}
	
	public Cell getLeftCell() {
		return leftCell;
	}
	
	public void setLeftCell(Cell newLeftCell) {
		leftCell = newLeftCell;
	}
	
	public Cell getRightCell() {
		return rightCell;
	}
	
	public void setRightCell(Cell newRightCell) {
		rightCell = newRightCell;
	}
}
