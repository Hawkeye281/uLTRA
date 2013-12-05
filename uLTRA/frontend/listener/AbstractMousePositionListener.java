package listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import panels.GridPanel;

import frames.MainFrame;

import Controller.GridController;

/**
 * 
 * @author Stephan Humme
 *
 */
public abstract class AbstractMousePositionListener implements MouseListener
{
	protected Point _startPoint;
	protected Point _endPoint;
	protected Point _cell;
	protected GridController gridCont = new GridController();
	protected MainFrame mainFrame = new MainFrame();
	
	private double _cellHeight = (float)GridPanel.getGridSize().height / (float)gridCont.getGameGrid().getHeight();
	private double _cellWidth = (float)GridPanel.getGridSize().width / (float)gridCont.getGameGrid().getWidth();
	
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
		
		if(startX > 0 && startX < GridPanel.getGridSize().width)
		{
			startX /= _cellWidth;
		}
		else
		{
			inField = false;
		}
		
		if(startY > 0 && startY < GridPanel.getGridSize().height)
		{
			startY /= _cellHeight;
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
		
		if(endX >= 0 && endX < GridPanel.getGridSize().width)
		{
			endX /= _cellWidth;
		}
		else
		{
			inField = false;
		}
		
		if(endY >= 0 && endY < GridPanel.getGridSize().height)
		{
			endY /= _cellHeight;
		}
		else
		{
			inField = false;
		}
		
		if(inField)
		{
			_endPoint = new Point(endX, endY);
		}
	}
}