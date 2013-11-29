package Controller;

import manuel.SaveGame;
import frames.MainFrame;
import gamegrid.GameGrid;

/**
 * Der Controller h�lt alle aktuellen Informationen zum angezeigten Spielfeld vor.
 * Er dient au�erdem dazu, das Spielfeld von der GUI-Seite an die Backend-Seite
 * und umgekehrt zu geben (z.B. zum Speichern/Laden).
 * 
 * @author Martin Dickau, Manuel Buhr
 *
 */
public class GridController {
	SaveGame speicher = new SaveGame();
	
	/* Derzeit nicht ben�tigt */
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
	
	/**
	 * Die Methode �bergibt das aktuelle Spielfeld an die Speicher-Methode
	 * 
	 * @param spielfeld
	 * 				Das aktuelle Spielfeld
	 */
	public void saveGame(GameGrid spielfeld){
		speicher.spielSpeichern(spielfeld);
	}
	
	
}
