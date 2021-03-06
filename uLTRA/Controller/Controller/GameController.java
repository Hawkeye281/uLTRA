package Controller;

import gamegrid.Beam;
import gamegrid.BeamDirections;
import gamegrid.Cell;
import gamegrid.LightSource;
import gamegrid.Turn;

import java.awt.Point;

import panels.GamePanel;

public class GameController {
	
	/**
	 * f�gt einen neuen Strahl hinzu
	 * @param start Koordinaten (in Zellen) der Lichtquelle von der der Strahl ausgeht
	 * @param end Koordinaten (in Zellen) des Endpunkts des Strahls
	 */
	public void addTurn(Point start, Point end)
	{
		int startX, startY, endX, endY;
		startX = start.x;
		startY = start.y;
		endX = end.x;
		endY = end.y;
		BeamDirections d;
		
		if(!(startX==endX) && !(startY==endY)) // pr�ft ob der neue Strahl eine gerade Linie ist
		{
			// bestimme auf welcher Achse der Strahl l�nger ist, diese Achse wird als Richtung angenommen
			// wenn der Strahl auf beiden Achsen gleich lang ist, nehme horizontal als Standardausrichtung
			int xDiff = Math.abs(startX-endX);
			int yDiff = Math.abs(startY-endY);
			if(yDiff > xDiff) // l�nger auf vertikaler Achse
				endX = startX;
			else
				endY = startY;
		}
		if(startX == endX)
			if(startY < endY)
				d = BeamDirections.BEAM_DOWN;
			else
				d = BeamDirections.BEAM_UP;
		else
			if(startX < endX)
				d = BeamDirections.BEAM_RIGHT;
			else
				d = BeamDirections.BEAM_LEFT;
		
		// bestimme:
		// - H�he und Breite des Spielfelds
		// - verbleibende Kapazit�t der Lichtquelle
		
		GridController gridCtrl = GamePanel.getGamePanel().getGridController();
		int maxX, maxY;
		maxX = gridCtrl.getWidth();
		maxY = gridCtrl.getHeight();
		Cell lightCell = gridCtrl.getCell(startX, startY);
		LightSource light = (LightSource)lightCell.getContent();
		int remCap = light.getRemainingCapacity();
		int tempEnd = startY;
		
//		CellContent cellCont = null;
		
		switch(d)
		{
		case BEAM_DOWN:
/*
			tempEnd = startY + 1; 
			
			while(tempEnd < endY)
			{
				cellCont = gridCtrl.getCell(startX, tempEnd).getContent();
				
				if(!(cellCont instanceof LightSource) && !(cellCont instanceof Beam))
				{
					if(remCap > 0)
					{
						tempEnd++;
						remCap--;
					}
				}
			}
*/			
			tempEnd = startY;
			while(
			tempEnd+1 < endY && // Strahl nicht l�nger als vom Spieler geklickt
			tempEnd+1 <= maxY && // n�chste Zelle innerhalb des Spielfelds 
			!(gridCtrl.getCell(startX, tempEnd+1).getContent() instanceof LightSource) && // n�chste Zelle enth�lt keine Lichtquelle 
			!(gridCtrl.getCell(startX, tempEnd+1).getContent() instanceof Beam) &&  // n�chste Zelle enth�lt keinen Strahl
			remCap > 0) // Lichtquelle hat gen�gend Restkapazit�t um Strahl in n�chste Zelle zu zeichnen
			{
				tempEnd++;
				remCap--;
			}
//			endY = tempEnd;

			break;
		case BEAM_UP:
			tempEnd = startY;
			while(
			tempEnd-1 > endX && // Strahl nicht l�nger als vom Spieler geklickt
			tempEnd-1 > 0 && // n�chste Zelle innerhalb des Spielfelds 
			!(gridCtrl.getCell(startX, tempEnd-1).getContent() instanceof LightSource) && // n�chste Zelle enth�lt keine Lichtquelle 
			!(gridCtrl.getCell(startX, tempEnd-1).getContent() instanceof Beam) &&  // n�chste Zelle enth�lt keinen Strahl
			remCap > 0) // Lichtquelle hat gen�gend Restkapazit�t um Strahl in n�chste Zelle zu zeichnen
			{
				tempEnd--; // n�chste Zelle als Ende des Strahls ausw�hlen
				remCap--; // verbleibende Kapazit�t anpassen
			}
//			endY = tempEnd;
			break;
		case BEAM_LEFT:
			tempEnd = startX;
			while(
			tempEnd-1 > endX && // Strahl nicht l�nger als vom Spieler geklickt
			tempEnd-1 > 0 && // n�chste Zelle innerhalb des Spielfelds 
			!(gridCtrl.getCell(startX, tempEnd-1).getContent() instanceof LightSource) && // n�chste Zelle enth�lt keine Lichtquelle 
			!(gridCtrl.getCell(startX, tempEnd-1).getContent() instanceof Beam) &&  // n�chste Zelle enth�lt keinen Strahl
			remCap > 0) // Lichtquelle hat gen�gend Restkapazit�t um Strahl in n�chste Zelle zu zeichnen
			{
				tempEnd--;
				remCap--;
			}
//			endX = tempEnd;
			break;
		case BEAM_RIGHT:
			tempEnd = startX;
			while(
			tempEnd+1 < endX && // Strahl nicht l�nger als vom Spieler geklickt
			tempEnd+1 < maxX && // n�chste Zelle innerhalb des Spielfelds 
			!(gridCtrl.getCell(startX, tempEnd+1).getContent() instanceof LightSource) && // n�chste Zelle enth�lt keine Lichtquelle 
			!(gridCtrl.getCell(startX, tempEnd+1).getContent() instanceof Beam) &&  // n�chste Zelle enth�lt keinen Strahl
			remCap > 0) // Lichtquelle hat gen�gend Restkapazit�t um Strahl in n�chste Zelle zu zeichnen
			{
				tempEnd++;
				remCap--;
			}
//			endX = tempEnd;
			break;
		}
		light.setRemainingCapacity(remCap);
		
		// turnList : irgendwas...
//		Turn t = new Turn(new Point(startX, startY), new Point(endX, endY));
//		GamePanel.getGamePanel().getTurnList().addTurn(t);
	}
	
	public void pushHistory(Turn turn)
	{
		
	}
}
