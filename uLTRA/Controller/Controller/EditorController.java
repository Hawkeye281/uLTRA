package Controller;

import components.RayGrid;

import gamegrid.GameGrid;
import gamegrid.LightSource;

public class EditorController {

	private static GameGrid editorGrid = null;
	
	/**
	 * Erstellt ein neues Grid nach Anwendervorgaben und gibt 'true' zur�ck
	 * 
	 * @param height = Anzahl der Felder an der x-Achse
	 * @param width = Anzahl der Felder an der y-Achse
	 * @author Sebastian Kiepert
	 */
	public void setGrid(int height, int width){
		editorGrid = new GameGrid(height, width);
	}
	
	/**
	 * gibt das aktuell aktive Grid zur�ck
	 * @return editorGrid als RayGrid
	 * @author Sebastian Kiepert
	 */
	public RayGrid getActivGrid(){
		GridController gridCont = new GridController();
		return new RayGrid(gridCont);
	}
	
	/**
	 * l�scht das erstellte Grid
	 */
	public void removeGrid(){
		editorGrid = null;
	}
	
	/**
	 * pr�ft, ob ein editorGrid vorhanden ist 
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
	 * @param value = Wert (St�rke) der Lichtquelle;
	 * @author Sebastian Kiepert
	 */
	public void setLightSource(int x, int y, int value){
		editorGrid.getCell(x, y).setContent(new LightSource(value));
	}
	
	/**
	 * Gibt true zur�ck, wenn die Zelle bereits eine Lichtquelle enth�lt, sonst false
	 * @param x = Koordinate an der x-Achse
	 * @param y = Koordinate an der y-Achse
	 * @return
	 */
	public boolean contentIsLightSource(int x, int y){
		return (editorGrid.getCell(x,y).getContent() instanceof LightSource) ? true : false;
	}
	
	/**
	 * Ist in der gew�hlten Zelle eine Lichtquelle vorhanden, wird die Strahlst�rke zur�ckgegeben
	 * @param x = Koordinate an der x-Achse
	 * @param y = Koordinate an der y-Achse
	 * @return
	 */
	public int getLightValue(int x, int y){
		if (contentIsLightSource(x, y)){
			LightSource light = (LightSource) editorGrid.getCell(x, y).getContent();
			return light.getCapacity();
		}
		else{
			return 0;
		}
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
	public static int getGridHeight(){
		return editorGrid.getHeight();
	}
	/**
	 * Liefert die Anzahl der Zellen an der y-Achse
	 * @return
	 * @author Sebastian Kiepert
	 */
	public static int getGridWidth(){
		return editorGrid.getWidth();
	}

}
