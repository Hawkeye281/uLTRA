/**
 * 
 */
package sebastian;

import panels.GamePanel;
import Controller.EditorController;
import gamegrid.Cell;
import gamegrid.LightSource;
import gamegrid.Turn;

/**
 * @author Sebastian Kiepert
 *
 */
public class CheckEditRules {
	
	public static void check(GamePanel gamePanel){
		int failures = 0, cellFailures = 0, lightFailures = 0;
		for (int x=0; x < EditorController.getGridWidth();x++){
			for (int y=0; y < EditorController.getGridHeight(); y++){
				Cell cell = EditorController.getCell(x, y);
				if (!cell.isEmpty()){
					if (cell.isLightSource()){
						LightSource lightSource = (LightSource) cell.getContent();
						if (lightSource.getCapacity() == 0){
							gamePanel.getTurnList().addTurn(new Turn("[" + (x+1) + " links; " + (y+1) + " runter]: Lichtquelle = 0"));
							lightFailures++;
						}
						
					}
				}
				else{
					gamePanel.getTurnList().addTurn(new Turn("[" + (x+1) + " links; " + (y+1) + " runter]: Leere Zelle"));
					cellFailures++;
				}
			}
		}
		failures = cellFailures + lightFailures;
		if (failures>0){
			gamePanel.getTurnList().addTurn(new Turn(failures + " Fehler gefunden"));
			gamePanel.getTurnList().addTurn(new Turn("davon " + lightFailures + " leere Lichtquellen"));
			gamePanel.getTurnList().addTurn(new Turn("und " + cellFailures + " leere Zellen"));
		}
		else{
			gamePanel.getTurnList().addTurn(new Turn("Keine Fehler gefunden"));
		}
	}
	
	public static int lightSourceCount(){
		int count = 0;
		for (int x = 0; x < EditorController.getGridWidth(); x++){
			for (int y = 0; y < EditorController.getGridHeight(); y++){
				Cell cell = EditorController.getCell(x, y);
				if (!cell.isEmpty())
					if (cell.isLightSource())
						count++;
			}
		}
		return count;
	}
	
	public static int lightCapacityCount(){
		int count = 0;
		for (int x = 0; x < EditorController.getGridWidth(); x++){
			for (int y = 0; y < EditorController.getGridHeight(); y++){
				Cell cell = EditorController.getCell(x, y);
				if (!cell.isEmpty())
					if (cell.isLightSource()){
						LightSource lSource = (LightSource) cell.getContent();
						count += lSource.getCapacity();
					}
			}
		}
		return count;
	}
	
	public static int emptyCellCount(){
		int count = 0;
		for (int x = 0; x < EditorController.getGridWidth(); x++){
			for (int y = 0; y < EditorController.getGridHeight(); y++){
				Cell cell = EditorController.getCell(x, y);
				if (cell.isEmpty())
					count++;
			}
		}
		return count;
	}
	
	public static int beamCount(){
		int count = 0;
		for (int x = 0; x < EditorController.getGridWidth(); x++){
			for (int y = 0; y < EditorController.getGridHeight(); y++){
				Cell cell = EditorController.getCell(x, y);
				if (!cell.isEmpty())
					if (cell.isBeam())
						count++;
			}
		}
		return count;
	}

}
