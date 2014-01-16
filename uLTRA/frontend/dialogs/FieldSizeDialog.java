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
		FieldSizeDialog._fsd = this;
		setUndecorated(true);
		setLocation((int)MainFrame.getFrameLocation().getX()+125, (int)MainFrame.getFrameLocation().getY()+50);
		setSize(200,120);
		setModal(true);
		add(new FieldSizePanel());
		setVisible(true);
	}
	
	public static FieldSizeDialog getFSD(){
		return FieldSizeDialog._fsd;
	}
	
}
