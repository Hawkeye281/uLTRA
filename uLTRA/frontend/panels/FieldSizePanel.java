/**
 * 
 */
package panels;

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
		setBorder(BorderFactory.createTitledBorder("Spielfeld erzeugen"));
		spinHeight = (EditorPanel.getController().isSet())? setSpinner(EditorController.getGridHeight()): setSpinner(3);
		EditorPanel.getController();
		spinWidth = (EditorPanel.getController().isSet())? setSpinner(EditorController.getGridWidth()): setSpinner(3);
		JButton gen = new JButton("start");
		gen.addActionListener(new ActionHandler(fsd, editPanel));
		JButton abort = new JButton("abbrechen");
		abort.addActionListener(new ActionHandler(fsd, editPanel));
		add(new JLabel("Feldhöhe"));
		add(spinHeight);
		add(new JLabel("Feldbreite"));
		add(spinWidth);
		add(abort);
		add(gen);
	}
	
	private JSpinner setSpinner(int value){
		return new JSpinner(new SpinnerNumberModel(value,3,26,1));
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
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("start")){
				int height = (int) spinHeight.getValue();
				int width = (int) spinWidth.getValue();
				try {
					this.editPanel.generateField(height, width);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.fsd.dispose();
			}
			else {
				this.fsd.dispose();
			}
			
		}
	}
}
