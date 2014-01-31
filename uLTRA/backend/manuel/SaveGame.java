package manuel;

import gamegrid.GameGrid;
import gamegrid.LightSource;
import gamegrid.Validator;
import help.SaveContainer;

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
	
	public void spielSpeichern(GameGrid spielfeld, String spielname, Validator pValidator)
	{
		OutputStream fos = null;
		try
		{
			fos = new FileOutputStream(spielname + ".puzzle");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			SaveContainer saveContainer = new SaveContainer(spielfeld, pValidator);
			
			oos.writeObject(saveContainer);
						
			oos.close();
			fos.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
