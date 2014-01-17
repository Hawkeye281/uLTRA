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
			System.out.println(x);
			for (int y=0; y < EditorController.getGridHeight(); y++){
				System.out.println(y);
				Cell cell = editCont.getCell(x, y);
				if (!cell.isEmpty()){
					if (cell.isLightSource()){
						LightSource lightSource = (LightSource) cell.getContent();
						if (lightSource.getCapacity() == 0){
							gamePanel.getTurnList().addTurn(new Turn("[" + x + ";" + y + "]: Lichtquelle ohne Wert"));
							failures++;
						}
						
					}
				}
				else{
					gamePanel.getTurnList().addTurn(new Turn("[" + x + ";" + y + "]: Leere Zelle"));
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
