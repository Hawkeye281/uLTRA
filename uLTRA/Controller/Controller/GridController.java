package Controller;

import java.io.File;

import manuel.LoadGame;
import manuel.SaveGame;
import frames.MainFrame;
import gamegrid.Cell;
import gamegrid.GameGrid;

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
	
	/* Derzeit nicht benötigt */
//	public GridController(){
//		
//	}

	/**
	 * Das Spiel initialisieren
	 */
	public void initializeGame(){
		MainFrame frame = new MainFrame();
		frame.init();
	}
	
	public static GameGrid getGameGrid(){
		spielfeld = speicher.spielLaden();
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
		speicher.spielSpeichern(spielfeld, spielname);
	}
	
	public static GameGrid loadGame(int spielindex){
		spielfeld = loader.spielLaden(getGameNameByIndex(spielindex));
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
	
	public static String getGameNameByIndex(int spielindex){
		String[] spiele = getAllSavedGames();
		
		System.out.println("INDEX: " + spielindex);
		System.out.println("SPIEL: " + spiele[spielindex]);
		
		return spiele[spielindex];
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