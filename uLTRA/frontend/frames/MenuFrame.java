package frames;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import panels.GameMenu;

public class MenuFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MenuFrame(JDesktopPane desktop, MainFrame mainFrame){
		closable = false;
		maximizable = false;
		resizable = false;
		setSize(200, 300);
		setLocation(300,150);
		title = "Hauptmenü";
		add(new GameMenu(desktop, mainFrame, this));
		setVisible(true);
	}
}
