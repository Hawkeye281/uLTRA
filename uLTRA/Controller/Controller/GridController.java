package Controller;

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
	
	public GameGrid getGameGrid(){
		spielfeld = speicher.spielLaden();
		return spielfeld;
	}
	
	/**
	 * Die Methode übergibt das aktuelle Spielfeld an die Speicher-Methode
	 * 
	 * @param spielfeld
	 * 				Das aktuelle Spielfeld
	 */
	public static void saveGame(GameGrid spielfeld){
		speicher.spielSpeichern(spielfeld);
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