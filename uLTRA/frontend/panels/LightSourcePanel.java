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

import listener.EditorMouseListener;

import Controller.EditorController;

import dialogs.LightSourceDialog;

/**
 * @author Sebastian Kiepert
 *
 */
public class LightSourcePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JSpinner spinLight;
	private static EditorController editGrid = EditorPanel.getController();
	private static int x;
	private static int y;
	private static LightSourceDialog lsd;
	
	public LightSourcePanel(LightSourceDialog lsd){
		super(new GridLayout(2,2));
		LightSourcePanel.lsd = lsd;
		LightSourcePanel.x = LightSourceDialog.getCellX();
		LightSourcePanel.y = LightSourceDialog.getCellY();
		int max = (EditorController.getGridHeight()-1)+(EditorController.getGridWidth()-1);
		spinLight = setSpinner(max);
		JButton gen = new JButton("erstellen");
		gen.addActionListener(new ActionHandler());
		add(new JLabel("Lichtstärke"));
		add(spinLight);
		add(new JLabel(" "));
		add(gen);
	}
	
	private JSpinner setSpinner(int maxValue){
		if (editGrid.contentIsLightSource(LightSourcePanel.x, LightSourcePanel.y)){
			return new JSpinner(new SpinnerNumberModel(editGrid.getLightValue(LightSourcePanel.x, LightSourcePanel.y),1,maxValue,1));
		}
		else
			return new JSpinner(new SpinnerNumberModel(1,1,maxValue,1));
	}
	
	class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			EditorPanel editPanel = EditorMouseListener.getEditorPanel();
			editGrid.setLightSource(LightSourcePanel.x, LightSourcePanel.y, (int) spinLight.getValue());
			editPanel.reloadEditor();
			LightSourcePanel.lsd.dispose();
		}
		
	}

}
