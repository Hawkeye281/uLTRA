/**
 * 
 */
package toolbarActions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.GridController;

import panels.EditorPanel;
import panels.GamePanel;

/**
 * 
 * Schaltfläche zum Laden von Spielfeldern
 * 
 * @author basti
 * @version 1.0
 */
public class LoadAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private GamePanel gamePanel = null;
	private EditorPanel editorPanel = null;
	
	public LoadAction(GamePanel gamePanel){
		this.gamePanel = gamePanel;
		putValue(Action.NAME, "Laden");
		putValue(Action.SHORT_DESCRIPTION, "Spiel laden");
	}
	
	public LoadAction(EditorPanel editorPanel){
		this.editorPanel = editorPanel;
		putValue(Action.NAME, "Laden");
		putValue(Action.SHORT_DESCRIPTION, "Spiel laden");
	}

	/**
	 * Verknüpfung zur Load-Methode
	 */
	@Override
	public void actionPerformed(ActionEvent e) { 
		if (gamePanel != null) {
			JFileChooser jfc = new JFileChooser();
			FileFilter ff = new FileNameExtensionFilter("Lichtstrahl-Puzzle", "puzzle");
			File dirfile = new File("Documents/Spiele");
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jfc.setMultiSelectionEnabled(false);
			jfc.setFileHidingEnabled(true);
			jfc.setFileFilter(ff);
			jfc.setCurrentDirectory(dirfile.getAbsoluteFile());
			if(jfc.showOpenDialog(jfc) == 0){
				GridController.loadGame(jfc.getSelectedFile().getName());
			}
		}
		else {
//			MenuController.loadGame(editorPanel);
		}

		gamePanel.loadGame();
	}

}
