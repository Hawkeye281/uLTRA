package gamegrid;

import java.awt.Point;
import java.util.Observable;

/**
 * 
 * @author Stephan
 *
 */
public class Turn extends Observable
{
	private Point _start;
	private Point _end;
	
	public Turn(Point pStart, Point pEnd)
	{
		_start = pStart;
		_end = pEnd;
		
		insertIntoTurnList();
	}
	
	public Point getStart()
	{
		return _start;
	}
	
	public Point getEnd()
	{
		return _end;
	}
	
	public void insertIntoTurnList()
	{
		setChanged();
		notifyObservers(this);		
	}
	
	@Override
	public String toString()
	{
		return "[" + _start.x + ":" + _start.y + "] -> [" + _end.x + ":" + _end.y + "]";
	}
}
