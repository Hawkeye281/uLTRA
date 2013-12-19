package manuel;

import gamegrid.GameGrid;
import gamegrid.LightSource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * @author Manuel Buhr
 */

public class SaveGame {
	
	GameGrid spielfeld = null;
	/*
	 * Das Spielfeld wird als serialisiertes Objekt gespeichert und daraus 
	 * auch wiederhergestellt
	 * siehe: http://openbook.galileocomputing.de/javainsel9/javainsel_17_010.htm#mjfbe8cb1105d7dfaf6adbc23f31c81b93
	 */
	
	public void spielSpeichern(GameGrid spielfeld, String spielname){
		OutputStream fos = null;
		
		try {
			fos = new FileOutputStream("../uLTRA/Documents/Spiele/" + spielname);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(spielfeld);
						
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Deprecated
	public GameGrid spielLaden(){
		GameGrid.deleteInstance();
		spielfeld = GameGrid.getInstance(12, 12);
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
		return spielfeld;
	}
}
