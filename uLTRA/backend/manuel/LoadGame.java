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
	
	
//	public static JFileChooser setFileChooser(){
//		JFileChooser jfc = new JFileChooser();
//		FileFilter ff = new FileNameExtensionFilter("Lichtstrahl-Puzzle", "puzzle");
//		File dirfile = new File("Documents/Spiele");
//		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
//		jfc.setMultiSelectionEnabled(false);
//		jfc.setFileHidingEnabled(true);
//		jfc.setFileFilter(ff);
//		jfc.setCurrentDirectory(dirfile.getAbsoluteFile());
//		
//		return jfc;
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
