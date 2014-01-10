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
	 * fügt einen neuen Strahl hinzu
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
		
		if(!(startX==endX) && !(startY==endY)) // prüft ob der neue Strahl eine gerade Linie ist
		{
			// bestimme auf welcher Achse der Strahl länger ist, diese Achse wird als Richtung angenommen
			// wenn der Strahl auf beiden Achsen gleich lang ist, nehme horizontal als Standardausrichtung
			int xDiff = Math.abs(startX-endX);
			int yDiff = Math.abs(startY-endY);
			if(yDiff > xDiff) // länger auf vertikaler Achse
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
		// - Höhe und Breite des Spielfelds
		// - verbleibende Kapazität der Lichtquelle
		
		GridController gridCtrl = GamePanel.getGridController();
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
			
			System.out.println("startX: " + startX + "| start.x: " + start.x);
			System.out.println("tempEnd: " + tempEnd + " < " + endY);
			System.out.print(startY);
			
			while(tempEnd < endY)
			{
				cellCont = gridCtrl.getCell(startX, tempEnd).getContent();
				
				if(!(cellCont instanceof LightSource) && !(cellCont instanceof Beam))
				{
					if(remCap > 0)
					{
						tempEnd++;
						remCap--;
						System.out.println(" -> " + tempEnd);
					}
				}
			}
			System.out.println();
*/			
			tempEnd = startY;
			while(
			tempEnd+1 < endY && // Strahl nicht länger als vom Spieler geklickt
			tempEnd+1 <= maxY && // nächste Zelle innerhalb des Spielfelds 
			!(gridCtrl.getCell(startX, tempEnd+1).getContent() instanceof LightSource) && // nächste Zelle enthält keine Lichtquelle 
			!(gridCtrl.getCell(startX, tempEnd+1).getContent() instanceof Beam) &&  // nächste Zelle enthält keinen Strahl
			remCap > 0) // Lichtquelle hat genügend Restkapazität um Strahl in nächste Zelle zu zeichnen
			{
				tempEnd++;
				remCap--;
			}
//			endY = tempEnd;

			break;
		case BEAM_UP:
			tempEnd = startY;
			while(
			tempEnd-1 > endX && // Strahl nicht länger als vom Spieler geklickt
			tempEnd-1 > 0 && // nächste Zelle innerhalb des Spielfelds 
			!(gridCtrl.getCell(startX, tempEnd-1).getContent() instanceof LightSource) && // nächste Zelle enthält keine Lichtquelle 
			!(gridCtrl.getCell(startX, tempEnd-1).getContent() instanceof Beam) &&  // nächste Zelle enthält keinen Strahl
			remCap > 0) // Lichtquelle hat genügend Restkapazität um Strahl in nächste Zelle zu zeichnen
			{
				tempEnd--; // nächste Zelle als Ende des Strahls auswählen
				remCap--; // verbleibende Kapazität anpassen
			}
//			endY = tempEnd;
			break;
		case BEAM_LEFT:
			tempEnd = startX;
			while(
			tempEnd-1 > endX && // Strahl nicht länger als vom Spieler geklickt
			tempEnd-1 > 0 && // nächste Zelle innerhalb des Spielfelds 
			!(gridCtrl.getCell(startX, tempEnd-1).getContent() instanceof LightSource) && // nächste Zelle enthält keine Lichtquelle 
			!(gridCtrl.getCell(startX, tempEnd-1).getContent() instanceof Beam) &&  // nächste Zelle enthält keinen Strahl
			remCap > 0) // Lichtquelle hat genügend Restkapazität um Strahl in nächste Zelle zu zeichnen
			{
				tempEnd--;
				remCap--;
			}
//			endX = tempEnd;
			break;
		case BEAM_RIGHT:
			tempEnd = startX;
			while(
			tempEnd+1 < endX && // Strahl nicht länger als vom Spieler geklickt
			tempEnd+1 < maxX && // nächste Zelle innerhalb des Spielfelds 
			!(gridCtrl.getCell(startX, tempEnd+1).getContent() instanceof LightSource) && // nächste Zelle enthält keine Lichtquelle 
			!(gridCtrl.getCell(startX, tempEnd+1).getContent() instanceof Beam) &&  // nächste Zelle enthält keinen Strahl
			remCap > 0) // Lichtquelle hat genügend Restkapazität um Strahl in nächste Zelle zu zeichnen
			{
				tempEnd++;
				remCap--;
			}
//			endX = tempEnd;
			break;
		}
		light.setRemainingCapacity(remCap);
		
		System.out.println("Start [" + start.x + ":" + start.y + "] wird zu [" + startX + ":" + startY + "]");
		System.out.println("Ende  [" + end.x + ":" + end.y + "] wird zu [" + endX + ":" + endY + "]");
		
		Turn t = new Turn(new Point(startX, startY), new Point(endX, endY));
//		System.out.println(t);
		GamePanel.getTurnList().addTurn(t);
		
	}
	
	public void pushHistory(Turn turn)
	{
		
	}
}
