/**
 * 
 */
package toolbarActions;

import gamegrid.ContentConverter;
import gamegrid.GameGrid;
import gamegrid.Validator;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.EditorController;
import Controller.GridController;
import panels.FieldSizePanel;
import panels.GamePanel;
import sebastian.Mode;

/**
 * 
 * Schaltfläche zum Laden von Spielfeldern
 * 
 * @author basti
 * @version 1.0
 */
public class LoadAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private GamePanel gamePan = null;
	
	public LoadAction(){
		this.gamePan = GamePanel.getGamePanel();
		putValue(Action.NAME, "Laden");
		putValue(Action.SHORT_DESCRIPTION, "Spiel laden");
	}
	
	private void performAction(){
		String subfolder = GamePanel.getGamePanel().getPanelMode() != Mode.EDIT? "SaveGames" : "Editor";
		JFileChooser jfc = new JFileChooser();
		FileFilter ff = new FileNameExtensionFilter("Lichtstrahl-Puzzle", "puzzle");
		File dirfile = new File("Documents/Spiele/" + subfolder);
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setMultiSelectionEnabled(false);
		jfc.setFileHidingEnabled(true);
		jfc.setFileFilter(ff);
		jfc.setCurrentDirectory(dirfile.getAbsoluteFile());
		if(jfc.showOpenDialog(jfc) == 0){
			if (gamePan.getPanelMode()==Mode.GAME){
				GridController.loadGame(jfc.getSelectedFile().getAbsolutePath());
				
				// Falls das Grid ein aus dem Editor erstelltes Grid ist, soll er die Beams aus der Instanz entfernen
				if(jfc.getSelectedFile().getAbsolutePath().contains(System.getProperty("file.separator") + "Editor" + System.getProperty("file.separator")))
				{
					Validator.getInstance(GridController.getGameGrid());
					GameGrid.setInstance(ContentConverter.clearGame());
				}
				if (jfc.getSelectedFile().getName().contains("initial"))
					gamePan.getGridController().setPlayable(true);
			}
			if (gamePan.getPanelMode()==Mode.EDIT){
				EditorController.loadGame(jfc.getSelectedFile().getAbsolutePath());
			}
			this.gamePan.resetPanel();
			this.gamePan.setGroundPanel();
		}
	}
	
	/**
	 * Verknüpfung zur Load-Methode
	 */
	@Override
	public void actionPerformed(ActionEvent e) { 
		if(gamePan.getGridController() == null && gamePan.getEditorController().gridIsSet()){
			if(FieldSizePanel.openConfirmDialog() == 0)
				performAction();
		}
		else if(gamePan.getEditorController() == null && gamePan.getGridController().gridIsSet()){
			if(FieldSizePanel.openConfirmDialog() == 0)
				performAction();
		}
		else{
			performAction();
		}
	}

}
