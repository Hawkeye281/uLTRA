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
 * @author basti
 *
 */
public class FieldSizePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSpinner spinHeight;
	private JSpinner spinWidth;
	
	public FieldSizePanel(FieldSizeDialog fsd, EditorPanel editPanel){
		super(new GridLayout(3,2));
		SpinnerNumberModel spinNumberH = new SpinnerNumberModel(editPanel.getHeight(),3,20,1);
		SpinnerNumberModel spinNumberW = new SpinnerNumberModel(editPanel.getWidth(),3,20,1);
		spinHeight = new JSpinner(spinNumberH);
		spinWidth = new JSpinner(spinNumberW);
		JButton gen = new JButton("Feld generieren");
		gen.addActionListener(new ActionHandler(fsd, editPanel));
		add(new JLabel("Feldhöhe"));
		add(spinHeight);
		add(new JLabel("Feldbreite"));
		add(spinWidth);
		add(new JLabel("Feld generieren"));
		add(gen);
	}
	
	class ActionHandler implements ActionListener{
		
		FieldSizeDialog fsd;
		EditorPanel editPanel;
		
		public ActionHandler(FieldSizeDialog fsd, EditorPanel editPanel){
			this.fsd = fsd;
			this.editPanel = editPanel;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int height = (int) spinHeight.getValue();
			int width = (int) spinWidth.getValue();
			this.editPanel.generateField(height, width);
			this.fsd.dispose();
			
		}
	}
}
