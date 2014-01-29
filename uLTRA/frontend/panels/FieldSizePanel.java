/**
 * 
 */
package panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import Controller.EditorController;
import dialogs.FieldSizeDialog;

/**
 * @author Sebastian Kiepert, Manuel Buhr
 * @see panels.FieldSizePanel#FieldSizePanel(FieldSizeDialog, EditorPanel)
 *
 */
public class FieldSizePanel extends JPanel{


	private static final long serialVersionUID = 1L;
	private static FieldSizePanel fsp;
	private static GamePanel _gamePanel;
	private static EditorController _editCont;
	private JSpinner spinHeight;
	private JSpinner spinWidth;
	
	/**
	 * Das Panel zum FieldSizeDialog - hier sind die Auswahlboxen (JSpinner) zur Einstellung der Spielfeldgröße
	 * untergebracht. Nach dem Klick auf "start" zur Bestätigung der Eingaben werden diese an das Panel des Editors
	 * (--> editPanel) weitergegeben. editPanel erstellt anschließend das Spielfeld.
	 * @param fsd
	 * @param editPanel
	 * @see panels.FieldSizePanel.ActionHandler#actionPerformed(ActionEvent)
	 */
	public FieldSizePanel(){
		super(new GridLayout(3,2));
		_gamePanel = GamePanel.getGamePanel();
		_editCont = _gamePanel.getEditorController();
		fsp = this;
		setBorder(BorderFactory.createTitledBorder("Spielfeld erzeugen"));
		spinHeight = (_gamePanel.getEditorController().gridIsSet())? setSpinner(EditorController.getGridHeight()): setSpinner(3);
//		_gamePanel.getEditorController();
		spinWidth = (_gamePanel.getEditorController().gridIsSet())? setSpinner(EditorController.getGridWidth()): setSpinner(3);
		JButton gen = new JButton("start");
		gen.addActionListener(new ActionHandler());
		JButton abort = new JButton("abbrechen");
		abort.addActionListener(new ActionHandler());
		add(new JLabel("Feldhöhe"));
		add(spinHeight);
		add(new JLabel("Feldbreite"));
		add(spinWidth);
		add(abort);
		add(gen);
	}
	
	public static int openConfirmDialog(){
		ImageIcon icon = new ImageIcon("../uLTRA/Documents/images/icons/warning.png");
		int choice = JOptionPane.showConfirmDialog(null, "Alle ungespeicherten Änderungen werden verworfen!\n" + 
						"Möchten Sie das wirklich?",
						"Änderungen verwerfen?",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE,
						icon);
		return choice;
	}
	
	public static int openChangeConfirmDialog(){
		ImageIcon icon = new ImageIcon("../uLTRA/Documents/images/icons/help.png");
		int choice = JOptionPane.showConfirmDialog(null, "Möchten Sie die eingetragenen Lichtstraheln behalten? \n",
						"Änderungen behalten?",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						icon);
		return choice;
	}
	
	public static FieldSizePanel getFieldSizePanel(){
		return fsp;
	}
	
	private JSpinner setSpinner(int value){
		return new JSpinner(new SpinnerNumberModel(value,3,20,1));
	}
	
	class ActionHandler implements ActionListener{
		
		private FieldSizeDialog fsd;
		
		public ActionHandler(){
			this.fsd = FieldSizeDialog.getFSD();
		}
		
		/**
		 * Übergabe der Eingabeparameter und Spielfeldgenerierung
		 * @see panels.EditorPanel#generateField(int, int)
		 * @author Sebastian Kiepert
		 * @version 1.0
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if(!_editCont.gridIsSet() && b.getText().equals("start"))
				generateField();
			else if(_editCont.gridIsSet() && b.getText().equals("start")){
				int choice = 3, change = 3;
				if (((int) spinHeight.getValue() < EditorController.getGridHeight() ||
					(int) spinWidth.getValue() < EditorController.getGridWidth()) ||
					((int) spinHeight.getValue() == EditorController.getGridHeight() &&
					(int) spinWidth.getValue() == EditorController.getGridWidth()) ||
					_editCont.gridIsEmpty()){
					choice = openConfirmDialog();
				}
				else {
					change = openChangeConfirmDialog();
				}
				if(choice == 0 || change == 1){
					generateField();
				}
				else if (change == 0){
					expanField();
				}
				else {
					this.fsd.dispose();
				}
			}
			else
				this.fsd.dispose();
		}
		
		private void generateField(){
			int height = (int) spinHeight.getValue();
			int width = (int) spinWidth.getValue();
			try {
				_editCont.setEditGrid(height, width);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			this.fsd.dispose();
		}
		
		private void expanField(){
			_editCont.setCellList();
			generateField();
			_editCont.setCellsFromList();
		}
	}
}
