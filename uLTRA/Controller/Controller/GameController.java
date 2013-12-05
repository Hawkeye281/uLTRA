package Controller;

import gamegrid.Turn;

import java.awt.Point;

import panels.GamePanel;

public class GameController {
	
	public void addTurn(Point start, Point end)
	{
		Turn t = new Turn(start, end);
		GamePanel.getTurnList().addTurn(t);
	}
	
	public void pushHistory(Turn turn)
	{
		
	}
}
