package Controller;

import components.RayGrid;

import gamegrid.GameGrid;
import gamegrid.LightSource;

public class EditorController {

	private GameGrid editorGrid = null;
	
	/**
	 * Erstellt ein neues Grid nach Anwendervorgaben und gibt 'true' zurück
	 * 
	 * @param height = Anzahl der Felder an der x-Achse
	 * @param width = Anzahl der Felder an der y-Achse
	 * @author Sebastian Kiepert
	 */
	public void setGrid(int height, int width){
		editorGrid = new GameGrid(height, width);	
	}
	
	/**
	 * gibt das aktuell aktive Grid zurück
	 * @return editorGrid als RayGrid
	 * @author Sebastian Kiepert
	 */
	public RayGrid getActivGrid(){
		return new RayGrid(editorGrid);
	}
	
	public void removeGrid(){
		editorGrid = null;
	}
	
	/**
	 * prüft, ob ein editorGrid vorhanden ist 
	 * @return true = Grid ist vorhanden; false = kein Grid vorhanden
	 * @author Sebastian Kiepert
	 */
	public boolean isSet(){
		return (editorGrid!=null)? true : false;
	}
	
	/**
	 * erstellt in der aktivierten Zelle eine Lichtquelle nach
	 * Anwendervorgaben.
	 *  
	 * @param x = Position der Zelle an der x-Achse;
	 * @param y = Position der Zelle an der y-Achse;
	 * @param value = Wert (Stärke) der Lichtquelle;
	 * @author Sebastian Kiepert
	 */
	public void setLightSource(int x, int y, int value){
		editorGrid.getCell(x, y).setContent(new LightSource(value));
	}
	
	/**
	 * 
	 * @param x = Position der Lichtquelle an der x-Achse
	 * @param y = Position der Lichquelle an der y-Achse
	 * @author Sebastian Kiepert
	 */
	public void removeLightSource(int x, int y){
		editorGrid.getCell(x, y).setContent(null);
	}
	
	/**
	 * Liefert die Anzahl der Zellen an der x-Achse
	 * @return 
	 * @author Sebastian Kiepert
	 */
	public int getGridHeight(){
		return editorGrid.getHeight();
	}
	/**
	 * Liefert die Anzahl der Zellen an der y-Achse
	 * @return
	 * @author Sebastian Kiepert
	 */
	public int getGridWidth(){
		return editorGrid.getWidth();
	}

}
