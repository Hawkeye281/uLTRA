package Controller;

import java.awt.Point;
import java.util.ArrayList;

import listener.EditorMouseListener;
import manuel.LoadGame;
import panels.GamePanel;
import panels.GridPanel;

import gamegrid.Beam;
import gamegrid.BeamDirections;
import gamegrid.Cell;
import gamegrid.GameGrid;
import gamegrid.LightSource;

public class EditorController {

	private static GameGrid editorGrid;
	private static GamePanel gamePanel;
	private ArrayList<Cell> cellList;
	static LoadGame loader = new LoadGame();
	
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
	
	public void setEditGrid(int height, int width) throws Exception{
		EditorController.gamePanel = GamePanel.getGamePanel();
		GameGrid.deleteInstance();
		editorGrid = GameGrid.getInstance(height, width);
		EditorController.gamePanel.setGroundPanel();
		EditorController.gamePanel.refresh();
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
	
	public void resetGrid(){
		if (gridIsSet())
			removeGrid();
	}
	
	public void recreateEditGrid(){
		EditorController.gamePanel.setGroundPanel();
		EditorController.gamePanel.refresh();
	}
	
	public static Cell getCell(int x, int y){
		return editorGrid.getCell(x, y);
	}
	
	public static Cell getCell(Point pos){
		return editorGrid.getCell(pos);
	}
	
	public void setCellList(int newWidth, int newHeight){
		this.cellList = new ArrayList<Cell>();
		for (int x =0; x<editorGrid.getWidth();x++){
			for (int y=0; y<editorGrid.getHeight();y++){
				if (x < newWidth && y < newHeight){
					System.out.println(x + ", " + y + ": " + editorGrid.getCell(x, y).getContent());
					this.cellList.add(editorGrid.getCell(x, y));
				}
			}
		}
	}
	
	public boolean gridIsEmpty(){
		for (int x = 0; x < editorGrid.getWidth(); x++){
			for (int y = 0; y < editorGrid.getHeight(); y++){
				if (!editorGrid.getCell(x, y).isEmpty()) return false;
			}
		}
		return true;
	}
	
	public void setCellsFromList(){
		for (Cell cell : this.cellList){
			editorGrid.setCell(cell, cell.getX(), cell.getY());
		}
		this.cellList = null;
		recreateEditGrid();
	}
	
	public void checkCellsToChange(int newWidth, int newHeight){
		for (int x = (editorGrid.getWidth() -1); x >= 0; x--){
			for (int y = (editorGrid.getHeight()-1); y >= 0; y--){
				if (x >= newWidth || y >= newHeight){
					System.out.println(x + ", " + y);
					Cell cell = editorGrid.getCell(x, y);
					if (cell.getContent() instanceof LightSource){
						removeLightSourceAndBeams(x, y);
					}
					else if (cell.getContent() instanceof Beam){
						System.out.println(cell.getX() + ", " + cell.getY() + ": Beam gelöscht");
						deleteBeam(x, y);
					}
				}
			}
		}
	}
	
	/**
	 * prüft, ob ein editorGrid vorhanden ist 
	 * @return true = Grid ist vorhanden; false = kein Grid vorhanden
	 * @author Sebastian Kiepert
	 */
	public boolean gridIsSet(){
		return (editorGrid!=null)? true : false;
	}
	
	public boolean isInGrid(int x, int y){
		return (editorGrid.isInGrid(x, y))? true : false;
	}
	
	public static GameGrid loadGame(String spielname){
		EditorController.gamePanel = GamePanel.getGamePanel();
		try
		{
			GameGrid.setInstance(loader.spielLaden(spielname).getGameGrid());
			EditorController.editorGrid = GameGrid.getInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return EditorController.editorGrid;
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
	
	/**
	 * 
	 * @param x = Position der Lichtquelle an der x-Achse
	 * @param y = Position der Lichquelle an der y-Achse
	 * @author Sebastian Kiepert
	 */
	public void removeLightSource(int x, int y){
		editorGrid.getCell(x, y).removeContent();
	}
	
	public void setBeam(int x, int y, BeamDirections direction, boolean endBeam, LightSource lightSource){
		if (!isLightSource(x, y)){
			editorGrid.getCell(x, y).setContent(new Beam(direction, endBeam, lightSource));
			lightSource.addBeamToList((Beam) editorGrid.getCell(x, y).getContent());
		}
	}
	
	public Beam getBeam(int x, int y){
		 return (editorGrid.isInGrid(x, y) && isBeam(x, y))? (Beam) editorGrid.getCell(x, y).getContent() : null;
	}
	
	public boolean isBeam(int x, int y){
		boolean beam = false;
		if (isInGrid(x, y))
			beam = (editorGrid.getCell(x, y).getContent() instanceof Beam)? true : false;
		return beam;
	}
	
	public void removeBeam(int x, int y){
		if (isBeam(x, y)){
			Beam beam = (Beam) editorGrid.getCell(x, y).getContent();
			LightSource lightSource = beam.getRemLightSource();
			lightSource.removeBeamFromList(beam);
			editorGrid.getCell(x, y).removeContent();
		}
	}
	
	public void deleteBeam(int x, int y){
		int x_start = x, y_start = y;
		Beam beam = getBeam(x_start, y_start);
		if (beam.isBeamEnd()){
			LightSource lightSource = beam.getRemLightSource();
			lightSource.setCapacity(lightSource.getCapacity()-1);
			EditorMouseListener.setNewLastBeam(EditorController.getCell(x_start, y_start));
			removeBeam(x_start, y_start);
		}
	}
	
	public void setPlayable(boolean playable){
		editorGrid.setPlayable(playable);
	}
	
	public void removeLightSourceAndBeams(int click_x, int click_y){
		LightSource lightSource = getLightSource(click_x, click_y);
		for (int x=0; x <= EditorController.getGridWidth(); x++){
			for (int y=0; y <= EditorController.getGridHeight(); y++){
				if (isBeam(x, y)){
					Beam beam = getBeam(x, y);
					LightSource vglLightSource = beam.getRemLightSource();
					if (lightSource == vglLightSource){
						removeBeam(x, y);
					}
				}
			}
		}
		removeLightSource(click_x, click_y);
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
