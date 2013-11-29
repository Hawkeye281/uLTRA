package gamegrid;

import java.awt.Point;

/**
 * 
 * @author Stephan
 *
 */
public class Turn 
{
	private Point _start;
	private Point _end;
	
	public Turn(Point pStart, Point pEnd)
	{
		_start = pStart;
		_end = pEnd;
		
	}
	
	public Point getStart()
	{
		return _start;
	}
	
	public Point getEnd()
	{
		return _end;
	}
	
	@Override
	public String toString()
	{
		return "[" + _start.x + ":" + _start.y + "] -> [" + _end.x + ":" + _end.y + "]";
	}
}
