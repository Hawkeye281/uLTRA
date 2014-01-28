/**
 * 
 */
package sebastian;

import java.util.ArrayList;

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
	
	private static ArrayList<String> failList = new ArrayList<String>();
	
	public static void check(GamePanel gamePanel){
		int failures = 0, cellFailures = 0, lightFailures = 0;
		for (int x=0; x < EditorController.getGridWidth();x++){
			for (int y=0; y < EditorController.getGridHeight(); y++){
				Cell cell = EditorController.getCell(x, y);
				if (!cell.isEmpty()){
					if (cell.isLightSource()){
						LightSource lightSource = (LightSource) cell.getContent();
						if (lightSource.getCapacity() == 0){
							failList.add("[" + (x+1) + " links; " + (y+1) + " runter]: Lichtquelle = 0");
//							gamePanel.getTurnList().addTurn(new Turn("[" + (x+1) + " links; " + (y+1) + " runter]: Lichtquelle = 0"));
							lightFailures++;
						}
						
					}
				}
				else{
					failList.add("[" + (x+1) + " links; " + (y+1) + " runter]: Leere Zelle");
//					gamePanel.getTurnList().addTurn(new Turn("[" + (x+1) + " links; " + (y+1) + " runter]: Leere Zelle"));
					cellFailures++;
				}
			}
		}
		failures = cellFailures + lightFailures;
		int i=0;
		for (String msg : failList){
			if (i<24) gamePanel.getTurnList().addTurn(new Turn(msg));
			i++;
		}
		if (failures>0 && failures <= 24){
			gamePanel.getTurnList().addTurn(new Turn(failures + " Fehler gefunden"));
			gamePanel.getTurnList().addTurn(new Turn("davon " + lightFailures + " leere Lichtquellen"));
			gamePanel.getTurnList().addTurn(new Turn("und " + cellFailures + " leere Zellen"));
		}
		else if (failures == 0){
			gamePanel.getTurnList().addTurn(new Turn("Keine Fehler gefunden"));
		}
		else {
			gamePanel.getTurnList().addTurn(new Turn((failList.size()-24)+" weitere Fehler gefunden"));
		}
		failList.removeAll(failList);
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
