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
	
	public static void check(EditorController editCont, GamePanel gamePanel){
		int failures = 0;
		for (int x=0; x < EditorController.getGridWidth();x++){
			for (int y=0; y < EditorController.getGridHeight(); y++){
				Cell cell = editCont.getCell(x, y);
				if (!cell.isEmpty()){
					if (cell.isLightSource()){
						LightSource lightSource = (LightSource) cell.getContent();
						if (lightSource.getCapacity() == 0){
							gamePanel.getTurnList().addTurn(new Turn("[" + (x+1) + " links; " + (y+1) + " runter]: Lichtquelle = 0"));
							failures++;
						}
						
					}
				}
				else{
					gamePanel.getTurnList().addTurn(new Turn("[" + (x+1) + " links; " + (y+1) + " runter]: Leere Zelle"));
					failures++;
				}
			}
		}
		if (failures>0){
			gamePanel.getTurnList().addTurn(new Turn(failures + " Fehler gefunden"));
		}
		else{
			gamePanel.getTurnList().addTurn(new Turn("Keine Fehler gefunden"));
		}
	}

}
