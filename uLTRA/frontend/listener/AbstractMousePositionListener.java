package listener;


import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import components.RayGrid;
import frames.MainFrame;

import Controller.GridController;

public abstract class AbstractMousePositionListener implements MouseListener
{
	protected Point _startPoint;
	protected Point _endPoint;
	protected Point _cell;
	protected GridController gridCont = new GridController();
	protected MainFrame mainFrame = new MainFrame();
	
	private double _fieldHeight = (float)RayGrid.getGridSize().height / (float)gridCont.getGameGrid().getHeight();
	private double _fieldWidth = (float)RayGrid.getGridSize().width / (float)gridCont.getGameGrid().getWidth();
	
	@Override
	public void mouseClicked(MouseEvent pEvent)
	{
		
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