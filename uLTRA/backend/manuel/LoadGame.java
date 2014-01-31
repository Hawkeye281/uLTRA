package manuel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import gamegrid.GameGrid;
import help.SaveContainer;

public class LoadGame {
	GameGrid spielfeld = null;
	
	/**
	 * 
	 * @param spielname
	 * @return
	 */
	public SaveContainer spielLaden(String spielname){
		InputStream fis = null;
		SaveContainer spiel = null;
		
		try {
			fis = new FileInputStream(spielname);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			spiel = (SaveContainer) ois.readObject();
			
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
