package manuel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class SaveLoader {
	
	
	/*
	 * Das Spielfeld wird als serialisiertes Objekt gespeichert und daraus 
	 * auch wiederhergestellt
	 * siehe: http://openbook.galileocomputing.de/javainsel9/javainsel_17_010.htm#mjfbe8cb1105d7dfaf6adbc23f31c81b93
	 */
	public void spielSpeichern(){
		OutputStream fos = null;
		
		try {
			fos = new FileOutputStream("Spiel1");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				fos.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void spielLaden(){
		
	}
}
