package listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import panels.GridPanel;

import Controller.GridController;

/**
 * Klasse zur Positionsbestimmung im GridPanel.
 * 
 * @author Stephan Humme
 *
 */
public abstract class AbstractMousePositionListener implements MouseListener
{
	/** Startpunkt des Klicks (Position im Raster, keine Pixelangabe) */
	protected Point _startPoint;
	
	/** Endpunkt des Klicks (Position im Raster, keine Pixelangabe) */
	protected Point _endPoint;
	
	protected Point _cell;

	/** Spielfeldzellenhöhe */
	private double _cellHeight = (float)GridPanel.getGridSize().height / (float)GridController.getGameGrid().getHeight();
	
	/** Spielfeldzellenbreite */
	private double _cellWidth = (float)GridPanel.getGridSize().width / (float)GridController.getGameGrid().getWidth();
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent pEvent)
	{
		
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent pEvent)
	{
		
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent pEvent)
	{

	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
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

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent pEvent)
	{
		int endX = pEvent.getX();
		int endY = pEvent.getY();
		boolean inField = true;
		boolean _horizontal = false;
		boolean _vertical = false;
		
		if(endX >= 0 && endX < GridPanel.getGridSize().width)
		{
			endX /= _cellWidth;
			
			if(_startPoint.x == endX)
			{
				_horizontal = true;
			}
			else
			{
				_horizontal = false;
			}
		}
		else
		{
			inField = false;
		}
		
		if(endY >= 0 && endY < GridPanel.getGridSize().height)
		{
			endY /= _cellHeight;
			
			if(_startPoint.y == endY)
			{
				_vertical = true;
			}
			else
			{

				_vertical = false;
			}
		}
		else
		{
			inField = false;
		}
		
		if(inField)
		{
			System.out.println(endX + ":" + endY);
			System.out.println(_horizontal + " != " + _vertical);
			
			if(_horizontal != _vertical)
			{
				_endPoint = new Point(endX, endY);
			}
			else
			{
				System.out.println("Falscher Zug");
				_endPoint = new Point(_startPoint.x, _startPoint.y);
			}
		}
		System.out.println("---");
	}
}