/**
 * 
 */
package panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import dialogs.FieldSizeDialog;

/**
 * @author Sebastian Kiepert
 * @see panels.FieldSizePanel#FieldSizePanel(FieldSizeDialog, EditorPanel)
 *
 */
public class FieldSizePanel extends JPanel{


	private static final long serialVersionUID = 1L;
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
	public FieldSizePanel(FieldSizeDialog fsd, EditorPanel editPanel){
		super(new GridLayout(3,2));
		spinHeight = (editPanel.getController().isSet())? setSpinner(editPanel.getController().getGridHeight()): setSpinner(3);
		spinWidth = (editPanel.getController().isSet())? setSpinner(editPanel.getController().getGridWidth()): setSpinner(3);
		JButton gen = new JButton("start");
		gen.addActionListener(new ActionHandler(fsd, editPanel));
		add(new JLabel("Feldhöhe"));
		add(spinHeight);
		add(new JLabel("Feldbreite"));
		add(spinWidth);
		add(new JLabel("Feld generieren"));
		add(gen);
	}
	
	private JSpinner setSpinner(int value){
		return new JSpinner(new SpinnerNumberModel(value,3,20,1));
	}
	
	class ActionHandler implements ActionListener{
		
		FieldSizeDialog fsd;
		EditorPanel editPanel;
		
		public ActionHandler(FieldSizeDialog fsd, EditorPanel editPanel){
			this.fsd = fsd;
			this.editPanel = editPanel;
		}
		
		/**
		 * Übergabe der Eingabeparameter und Spielfeldgenerierung
		 * @see panels.EditorPanel#generateField(int, int)
		 * @author Sebastian Kiepert
		 * @version 1.0
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			int height = (int) spinHeight.getValue();
			int width = (int) spinWidth.getValue();
			this.editPanel.generateField(height, width);
			this.fsd.dispose();
			
		}
	}
}
