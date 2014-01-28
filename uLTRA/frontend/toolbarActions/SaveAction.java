/**
 * 
 */
package toolbarActions;



import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;




import panels.GamePanel;
import sebastian.CheckEditRules;
import sebastian.Mode;
import Controller.GridController;

/**
 * Toolbar-Schaltfläche zum Speichern des Spielstandes oder Editorfeldes
 * 
 * @author basti
 * @see folgt
 * @version 1.0
 */
public class SaveAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public SaveAction(){
		putValue(Action.NAME, "Speichern");
		putValue(Action.SHORT_DESCRIPTION, "Spiel speichern");
	}

	/** 
	 * durch anklicken der Schaltfläche wird die Save-Methode ausgeführt
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(GridController.getGameGrid() != null){
			String subfolder = GamePanel.getGamePanel().getPanelMode() != Mode.EDIT? "SaveGames" : "Editor";
			if (GamePanel.getGamePanel().getPanelMode() == Mode.EDIT)
				GamePanel.getGamePanel().getEditorController().setPlayable(CheckEditRules.playable());
			int w = 0;
			JFileChooser jfc = new JFileChooser();
			FileFilter ff = new FileNameExtensionFilter("Lichtstrahl-Puzzle", "puzzle");
			File dirfile = new File("Documents/Spiele/" + subfolder);
			File tempfile = null;
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jfc.setMultiSelectionEnabled(false);
			jfc.setFileHidingEnabled(true);
			jfc.setFileFilter(ff);
			jfc.setCurrentDirectory(dirfile.getAbsoluteFile());
			do {
				w = 0;
				if (jfc.showSaveDialog(jfc) == JFileChooser.APPROVE_OPTION) {
					String spielname = jfc.getSelectedFile().getAbsolutePath();
					tempfile = new File(spielname);
					if(tempfile.exists()){
						int auswahl = JOptionPane.showConfirmDialog(null,
								"Wollen Sie das Puzzle überschreiben?",
								"Puzzle existiert",
								JOptionPane.YES_NO_CANCEL_OPTION);
						switch (auswahl) {
						case 0:
							File spiel = new File(spielname);
							spiel.delete();
							if(spielname.endsWith(".puzzle")){
								spielname = spielname.replace(".puzzle", "");
							}
							GridController.saveGame(GridController.getGameGrid(), spielname);
							w = 0;
							break;
						case 1:
							w = 1;
							break;
						case 2:
							w = 0;
							break;
						}
					}
					else {
						if(spielname.endsWith(".puzzle")){
							spielname = spielname.replace(".puzzle", "");
						}
						GridController.saveGame(GridController.getGameGrid(), spielname);
					}
				}
			} while (w == 1);
		}
		else{
			JOptionPane.showMessageDialog(null, "Nichts zu speichern...", ":-(", JOptionPane.ERROR_MESSAGE);
		}
	}
}
