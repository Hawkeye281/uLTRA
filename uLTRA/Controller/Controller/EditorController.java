package Controller;

import panels.GridPanel;

import gamegrid.Beam;
import gamegrid.BeamDirections;
import gamegrid.GameGrid;
import gamegrid.LightSource;

public class EditorController {

	private static GameGrid editorGrid = null;
	
	/**
	 * Erstellt ein neues Grid nach Anwendervorgaben
	 * 
	 * @param height = Anzahl der Felder an der x-Achse
	 * @param width = Anzahl der Felder an der y-Achse
	 * @author Sebastian Kiepert
	 * @throws Exception 
	 */
	public void setGameGrid(int height, int width) throws Exception{
		GameGrid.deleteInstance();
		editorGrid = GameGrid.getInstance(height, width);
	}
	
	/**
	 * gibt das aktuell bzw erstellte aktive Grid zurück
	 * @return editorGrid als GridPanel
	 * @author Sebastian Kiepert
	 */
	public GridPanel getGridPanel(){
		return new GridPanel();
	}
	
	/**
	 * löscht das erstellte Grid
	 */
	public void removeGrid(){
		GameGrid.deleteInstance();
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
	 * erstellt in der aktivierten Zelle eine Lichtquelle 
	 * mit Initialwert 0
	 *  
	 * @param x = Position der Zelle an der x-Achse;
	 * @param y = Position der Zelle an der y-Achse;
	 * @param value = Wert (Stärke) der Lichtquelle;
	 * @author Sebastian Kiepert
	 */
	public void setLightSource(int x, int y){
		editorGrid.getCell(x, y).setContent(new LightSource(0));
	}
	
	public LightSource getLightSource(int x, int y){
		return (LightSource) editorGrid.getCell(x, y).getContent();
	}
	
	/**
	 * Gibt true zurück, wenn die Zelle bereits eine Lichtquelle enthält, sonst false
	 * @param x = Koordinate an der x-Achse
	 * @param y = Koordinate an der y-Achse
	 * @return
	 */
	public boolean isLightSource(int x, int y){
		return (editorGrid.getCell(x,y).getContent() instanceof LightSource) ? true : false;
	}
	
	/**
	 * Ist in der gewählten Zelle eine Lichtquelle vorhanden, wird die Strahlstärke zurückgegeben
	 * @param x = Koordinate an der x-Achse
	 * @param y = Koordinate an der y-Achse
	 * @return
	 */
	public int getLightValue(int x, int y){
		if (isLightSource(x, y)){
			LightSource light = (LightSource) editorGrid.getCell(x, y).getContent();
			return light.getCapacity();
		}
		else{
			return 0;
		}
	}
	
	public void setLightValue(int x, int y, int newCapacity){
		if (isLightSource(x,y)){
			LightSource light = (LightSource) editorGrid.getCell(x,y).getContent();
			light.setCapacity(newCapacity);
		}
	}
	
	public void setBeam(int x, int y, BeamDirections direction){
		if (!isLightSource(x, y))
			editorGrid.getCell(x, y).setContent(new Beam(direction));
	}
	
	public Beam getBeam(int x, int y){
		 return (editorGrid.isInGrid(x, y))? (Beam) editorGrid.getCell(x, y).getContent() : null;
	}
	
	public boolean isBeam(int x, int y){
		boolean beam = false;
		if (editorGrid.isInGrid(x, y))
			beam = (editorGrid.getCell(x, y).getContent() instanceof Beam)? true : false;
		return beam;
	}
	
	public void removeBeam(int x, int y){
		editorGrid.getCell(x, y).setContent(null);
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
	
	public static GameGrid getEditGrid(){
		return editorGrid;
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
