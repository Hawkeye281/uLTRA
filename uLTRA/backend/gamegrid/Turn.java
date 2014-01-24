package gamegrid;

import java.awt.Point;
import java.io.Serializable;

/**
 * 
 * @author Stephan
 *
 */
public class Turn implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5757315100515012398L;
	private Point _start;
	private Point _end;
	private String text = "";
	private boolean isValid = true;
	
	public Turn(Point pStart, Point pEnd)
	{
		_start = pStart;
		_end = pEnd;
		
	}
	
	public Turn(String failureString){
		text = failureString;
	}
	
	public Point getStart()
	{
		return _start;
	}
	
	public Point getEnd()
	{
		return _end;
	}
	
	public void setIsValid (boolean pIsValid) {
		isValid = pIsValid;
	}
	
	public boolean getIsValid () {
		return isValid;
	}
	
	@Override
	public String toString()
	{
		return (text.equals(""))? "[" + _start.x + ":" + _start.y + "] -> [" + _end.x + ":" + _end.y + "]" : text;
	}
}
