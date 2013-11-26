package frames;

import javax.swing.JInternalFrame;

import panels.MenuPanel;

public class MenuFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * generiert das Hauptmenü der Anwendung und lädt die Schaltflächen im eingebundenen Panel.
	 * Es wird im MainFrame geladen
	 * @author Sebastian Kiepert
	 * @see panels.MenuPanel#MenuPanel(MainFrame)
	 * @see frames.MainFrame#MainFrame()
	 * @param mainFrame
	 * @version 1.0
	 */
	public MenuFrame(MainFrame mainFrame){
		closable = false;
		maximizable = false;
		resizable = false;
		setSize(200, 300);
		setLocation(300,150);
		title = "Hauptmenü";
		add(new MenuPanel(mainFrame));
		setVisible(true);
	}
	
}

