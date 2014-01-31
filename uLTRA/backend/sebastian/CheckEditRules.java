/**
 * 
 */
package sebastian;

import gamegrid.Cell;
import gamegrid.LightSource;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import panels.GamePanel;
import Controller.EditorController;

/**
 * @author Sebastian Kiepert
 *
 */
public class CheckEditRules {
	
	private static ArrayList<String> failList = new ArrayList<String>();
	private static boolean playable;
	
	public static void check(GamePanel gamePanel){
		int failures = 0, cellFailures = 0, lightFailures = 0;
		for (int x=0; x < EditorController.getGridWidth();x++){
			for (int y=0; y < EditorController.getGridHeight(); y++){
				Cell cell = EditorController.getCell(x, y);
				if (!cell.isEmpty()){
					if (cell.isLightSource()){
						LightSource lightSource = (LightSource) cell.getContent();
						if (lightSource.getCapacity() == 0){
							failList.add("[" + (x+1) + " >; " + (y+1) + " v]: Lichtquelle = 0");
							lightFailures++;
						}
						
					}
				}
				else{
					failList.add("[" + (x+1) + " >; " + (y+1) + " v]: Leere Zelle");
					cellFailures++;
				}
			}
		}
		failures = cellFailures + lightFailures;
		playable = (failures == 0)? true : false;
		
		DefaultListModel<String> failModel = new DefaultListModel<String>();
		for(String msg : failList)
			failModel.addElement(msg);
		gamePanel.getErrorList().setModel(failModel);
		
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
	
	public static boolean playable(){
		CheckEditRules.check(GamePanel.getGamePanel());
		return CheckEditRules.playable;
	}

}
