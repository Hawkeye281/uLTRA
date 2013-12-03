package listener;


import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import components.RayGrid;
import frames.MainFrame;

import Controller.EditorController;
import Controller.GridController;

public abstract class AbstractMousePositionListener implements MouseListener
{
	protected Point _startPoint;
	protected Point _endPoint;
	protected Point _cell;
	
	private double _fieldHeight = (float)RayGrid.getGridSize().height / (float)GridController.getGameGrid().getHeight();
	private double _fieldWidth = (float)RayGrid.getGridSize().width / (float)GridController.getGameGrid().getWidth();
	
	@Override
	public void mouseClicked(MouseEvent pEvent)
	{
		if (pEvent.getClickCount()==2){
			double cellHeight = (float)MainFrame.getDesktopSize().getHeight() / (float) EditorController.getGridHeight();
			double cellWidth = (float)MainFrame.getDesktopSize().getWidth() / (float) EditorController.getGridWidth();
			int posX = pEvent.getX();
			int posY = pEvent.getY();
			int x=0, y=0;
//			System.out.println(posX + ", " + posY);
//			System.out.println(cellWidth + ", " + cellHeight);
			if(posY > (int)cellHeight){
//				System.out.println("1");
				for (int i=1;;i++){
//					System.out.println("i: " + i);
					cellHeight *= i;
					if ((int) cellHeight > posY) {
						y=i-1;
						break;
					}
				}
			}
			if(posX > (int)cellWidth){
				for (int i=1;;i++){
					cellWidth*=i;
					if ((int) cellWidth > posX) {
						x=i-1;
						break;
					}
				}
			}
//			System.out.println(y + ", " + x);
			_cell = new Point(x, y);
//			System.out.println((int)_cell.getX() + ", " + (int)_cell.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent pEvent)
	{
		
	}

	@Override
	public void mouseExited(MouseEvent pEvent)
	{

	}

	@Override
	public void mousePressed(MouseEvent pEvent)
	{
		int startX = pEvent.getX();
		int startY = pEvent.getY();
		boolean inField = true;
		
		if(startX > 0 && startX < RayGrid.getGridSize().width)
		{
			startX /= _fieldWidth;
		}
		else
		{
			inField = false;
		}
		
		if(startY > 0 && startY < RayGrid.getGridSize().height)
		{
			startY /= _fieldHeight;
		}
		else
		{
			inField = false;
		}
		
		if(inField)
		{
			_startPoint = new Point(startX, startY);
		}
	}

	@Override
	public void mouseReleased(MouseEvent pEvent)
	{
		int endX = pEvent.getX();
		int endY = pEvent.getY();
		boolean inField = true;
		
		if(endX > 0 && endX < RayGrid.getGridSize().width)
		{
			endX /= _fieldWidth;
		}
		else
		{
			inField = false;
		}
		
		if(endX > 0 && endY < RayGrid.getGridSize().height)
		{
			endY /= _fieldHeight;
		}
		else
		{
			inField = false;
		}
		
		if(inField)
		{
			_endPoint = new Point(endX, endY);
		}
		else
		{
			System.out.println("Not in Field");
		}
	}
}