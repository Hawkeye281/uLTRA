/**
 * 
 */
package dialogs;

import javax.swing.JDialog;

import frames.MainFrame;

import panels.FieldSizePanel;


public class FieldSizeDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static FieldSizeDialog _fsd;
	
	/**
	 * Der Diolog, in dem die Spielfeldgröße eingestellt wird
	 * 
	 * @author Sebastian Kiepert
	 * @version 1.0
	 * @param editPanel
	 * @see panels.FieldSizePanel
	 */
	public FieldSizeDialog(){
		MainFrame mf = MainFrame.getMainFrame();
		FieldSizeDialog._fsd = this;
//		setUndecorated(true);
		setLocationRelativeTo(null);
		setLocation((mf.getBounds().x+3), (mf.getBounds().y+74));
		setSize(300,180);
		setResizable(true);
//		setModal(true);
		add(new FieldSizePanel());
		setVisible(true);
	}
	
	public static FieldSizeDialog getFSD(){
		return FieldSizeDialog._fsd;
	}
	
}
