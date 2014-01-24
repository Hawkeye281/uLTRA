/**
 * 
 */
package panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import Controller.EditorController;

import dialogs.FieldSizeDialog;

/**
 * @author Sebastian Kiepert
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
	
	public JPanel validatePanel(){
		JPanel valPanel = new JPanel();
		valPanel.setLayout(new GridLayout(5,0));
		valPanel.add(new JLabel("Ihre Änderungen gehen verloren."));
		valPanel.add(new JLabel("Möchten Sie trotzdem ein neues"));
		valPanel.add(new JLabel("Feld generieren?"));
		JButton val = new JButton("ok");
		val.addActionListener(new ActionHandler());
		JButton abort = new JButton("abbrechen");
		abort.addActionListener(new ActionHandler());
		valPanel.add(val);
		valPanel.add(abort);
		this.setVisible(false);
		valPanel.setVisible(true);
		return valPanel;
	}
	
	public static FieldSizePanel getFieldSizePanel(){
		return fsp;
	}
	
	private JSpinner setSpinner(int value){
		return new JSpinner(new SpinnerNumberModel(value,3,20,1));
	}
	
	class ActionHandler implements ActionListener{
		
		private FieldSizeDialog fsd;
		private FieldSizePanel fsp;
		
		public ActionHandler(){
			this.fsd = FieldSizeDialog.getFSD();
			this.fsp = FieldSizePanel.getFieldSizePanel();
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
			if (b.getText().equals("ok")){
				generateField();
			}
			else if (b.getText().equals("start")){
				if (_editCont.gridIsSet()){
					this.fsd.add(fsp.validatePanel());
				}
				else {
					generateField();
				}
			}
			else {
				this.fsd.dispose();
			}
			
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
	}
}
