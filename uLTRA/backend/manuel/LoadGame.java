package manuel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import gamegrid.GameGrid;

public class LoadGame {
	GameGrid spielfeld = null;
	
	/**
	 * 
	 * @param spielname
	 * @return
	 */
	public GameGrid spielLaden(String spielname){
		InputStream fis = null;
		GameGrid spiel = null;
		
		try {
			fis = new FileInputStream(spielname);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			spiel = (GameGrid)ois.readObject();
			
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return spiel;
	}
}
