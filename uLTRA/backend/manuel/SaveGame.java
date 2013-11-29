package manuel;

import gamegrid.GameGrid;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class SaveGame {
	
	
	/*
	 * Das Spielfeld wird als serialisiertes Objekt gespeichert und daraus 
	 * auch wiederhergestellt
	 * siehe: http://openbook.galileocomputing.de/javainsel9/javainsel_17_010.htm#mjfbe8cb1105d7dfaf6adbc23f31c81b93
	 */
	
	/**
	 * @author Manuel Buhr
	 */
	public void spielSpeichern(GameGrid spielfeld){
		OutputStream fos = null;
		
		try {
			fos = new FileOutputStream("Spiel1");
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
	
	public void spielLaden(){
		
	}
}
