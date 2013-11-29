package Controller;

import manuel.SaveGame;
import frames.MainFrame;
import gamegrid.GameGrid;
import gamegrid.LightSource;

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
		spielfeld = new GameGrid(12, 13);
		
		/* TODO   !!!! NUR TESTDATEN !!!! */
		// erste Zahl ist x, zweite ist y
		spielfeld.getCell(4, 0).setContent(new LightSource(5));
		spielfeld.getCell(9, 0).setContent(new LightSource(2));
		spielfeld.getCell(1, 1).setContent(new LightSource(6));
		spielfeld.getCell(6, 1).setContent(new LightSource(8));
		spielfeld.getCell(4, 2).setContent(new LightSource(3));
		spielfeld.getCell(11, 2).setContent(new LightSource(3));
		spielfeld.getCell(0, 3).setContent(new LightSource(4));
		spielfeld.getCell(7, 3).setContent(new LightSource(2));
		spielfeld.getCell(2, 4).setContent(new LightSource(6));
		spielfeld.getCell(5, 4).setContent(new LightSource(4));
		spielfeld.getCell(11, 4).setContent(new LightSource(4));
		spielfeld.getCell(9, 5).setContent(new LightSource(4));
		spielfeld.getCell(1, 6).setContent(new LightSource(6));
		spielfeld.getCell(4, 6).setContent(new LightSource(6));
		spielfeld.getCell(7, 6).setContent(new LightSource(5));
		spielfeld.getCell(8, 7).setContent(new LightSource(6));
		spielfeld.getCell(3, 8).setContent(new LightSource(4));
		spielfeld.getCell(10, 8).setContent(new LightSource(9));
		spielfeld.getCell(0, 9).setContent(new LightSource(6));
		spielfeld.getCell(5, 9).setContent(new LightSource(5));
		spielfeld.getCell(7, 9).setContent(new LightSource(4));
		spielfeld.getCell(2, 10).setContent(new LightSource(2));
		spielfeld.getCell(11, 10).setContent(new LightSource(6));
		spielfeld.getCell(4, 11).setContent(new LightSource(6));
		spielfeld.getCell(9, 11).setContent(new LightSource(2));
		
		
		frame.init();
	}
	
	public static GameGrid getGameGrid(){
		System.out.println(spielfeld);
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
}