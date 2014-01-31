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
		ImageIcon icon = new ImageIcon("../uLTRA/Documents/images/icons/warning.png");
		int save = 1;
		if(GridController.getGameGrid() != null){
			String subfolder = GamePanel.getGamePanel().getPanelMode() != Mode.EDIT? "SaveGames" : "Editor";
			if (GamePanel.getGamePanel().getPanelMode() == Mode.EDIT){
				boolean playable = CheckEditRules.playable();
				GamePanel.getGamePanel().getEditorController().setPlayable(playable);
				if (!playable)
					save = JOptionPane.showConfirmDialog(null,
							"Das Spielfeld ist unvollständig. Trotzdem speichern?",
							"Unvollständiges Spielfeld",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE,
							icon);
			}
			if (save == 0){
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
									"Möchten Sie das vorhandene Puzzle überschreiben?",
									"Puzzle existiert",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.WARNING_MESSAGE,
									icon);
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
		}
		else{
			icon = new ImageIcon("../uLTRA/Documents/images/icons/error.png");
			JOptionPane.showMessageDialog(null, "Nichts zu speichern...",
					":-(",
					JOptionPane.ERROR_MESSAGE,
					icon);
		}
	}
}
