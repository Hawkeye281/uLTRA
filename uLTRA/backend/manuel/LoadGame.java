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
			fis = new FileInputStream("../uLTRA/Documents/Spiele/" + spielname);
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
	
	
//	public static String[] getAllSavedGames(){
//		final File verzeichnis = new File("../uLTRA/Documents/Spiele");
//		String[] spiele = new String[verzeichnis.listFiles().length];
//		int iterator = 0;
//		for(final File dateien : verzeichnis.listFiles()){
//			if(dateien.isFile())
//				spiele[iterator] = dateien.getName();
//			iterator++;
//		}
//		
//		return spiele;
//	}
	
	
	
	
	
//	public void listFilesForFolder(final File folder) {
//	    for (final File fileEntry : folder.listFiles()) {
//	        if (fileEntry.isDirectory()) {
//	            listFilesForFolder(fileEntry);
//	        } else {
//	            System.out.println(fileEntry.getName());
//	        }
//	    }
//	}
//
//	final File folder = new File("/home/you/Desktop");
//	listFilesForFolder(folder);



}
