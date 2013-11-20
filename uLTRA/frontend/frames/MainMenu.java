package frames;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import panels.GameMenu;

public class MainMenu extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;
	
	public MainMenu(JFrame frame){
		mainFrame = frame;
		this.closable = false;
		this.maximizable = false;
		this.resizable = false;
		this.setSize(200, 100);
		this.title = "Hauptmenü";
		this.add(new GameMenu());
		this.setVisible(true);
	}
	

}
