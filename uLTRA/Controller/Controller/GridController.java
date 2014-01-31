package Controller;

import java.io.File;

import panels.GamePanel;
import sebastian.Mode;
import manuel.LoadGame;
import manuel.SaveGame;
import frames.MainFrame;
import gamegrid.Cell;
import gamegrid.GameGrid;
import gamegrid.Validator;
import help.SaveContainer;

/**
 * Der Controller hält alle aktuellen Informationen zum angezeigten Spielfeld vor.
 * Er dient außerdem dazu, das Spielfeld von der GUI-Seite an die Backend-Seite
 * und umgekehrt zu geben (z.B. zum Speichern/Laden).
 * 
 * @author Martin Dickau, Manuel Buhr
 *
 */
public class GridController {
	static SaveGame speicher = new SaveGame();
	static LoadGame loader = new LoadGame();
	static GameGrid spielfeld;

	/**
	 * Das Spiel initialisieren
	 */
	public void initializeGame(){
		MainFrame frame = new MainFrame();
		frame.init();
	}
	
	public static GameGrid initGameGrid()
	{
		try
		{
			spielfeld = GameGrid.getInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return spielfeld;
	}
	
	public boolean gridIsSet(){
		return (spielfeld!=null)? true : false;
	}
	
	/**
	 * löscht das erstellte Grid
	 */
	public void removeGrid(){
		GameGrid.deleteInstance();
		spielfeld = null;
	}
	
	public void resetGrid(){
		if (gridIsSet())
			removeGrid();
	}
	
	public static GameGrid getGameGrid(){
		return spielfeld;
	}
	
	/**
	 * Die Methode übergibt das aktuelle Spielfeld an die Speicher-Methode
	 * 
	 * @param spielfeld
	 * 				Das aktuelle Spielfeld
	 * @param spielname
	 * 				Der Name, unter dem das Spiel gespeichert wird
	 */
	public static void saveGame(GameGrid spielfeld, String spielname){
		speicher.spielSpeichern(spielfeld, spielname, (GamePanel.getGamePanel().getPanelMode() == Mode.EDIT? null : Validator.getInstance()));
	}
	
	public static GameGrid loadGame(String spielname){
		try
		{
			SaveContainer saveContainer = loader.spielLaden(spielname);
			
			GameGrid.setInstance(saveContainer.getGameGrid());
			Validator.setInstance(saveContainer.getValidator());

			spielfeld = GameGrid.getInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return spielfeld;
	}
	
	public static String[] getAllSavedGames(){
		final File verzeichnis = new File("../uLTRA/Documents/Spiele");
		String[] spiele = new String[verzeichnis.listFiles().length];
		int iterator = 0;
		for(final File dateien : verzeichnis.listFiles()){
			if(dateien.isFile())
				spiele[iterator] = dateien.getName();
			iterator++;
		}
		return spiele;
	}
	
	public boolean getPlayable(){
		return spielfeld.getPlayable();
	}
	
	public void setPlayable(boolean playable){
		spielfeld.setPlayable(playable);
	}
	
	public int getHeight(){
		return spielfeld.getHeight();
	}
	
	public int getWidth(){
		return spielfeld.getWidth();
	}
	
	public Cell getCell(int x, int y){
		Cell cell = spielfeld.getCell(x, y);
		return cell;
	}
}