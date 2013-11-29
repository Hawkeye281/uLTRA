package Controller;

import gamegrid.Turn;

import java.awt.Point;


public class GameController {
	
	public Turn addTurn(Point start, Point end){
		Turn t = new Turn(start, end);
		return t;
	}
	
}
