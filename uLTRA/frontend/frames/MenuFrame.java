package frames;

import javax.swing.JInternalFrame;

import panels.MenuPanel;

public class MenuFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * generiert das Hauptmen� der Anwendung und l�dt die Schaltfl�chen im eingebundenen Panel.
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
		title = "Hauptmen�";
		add(new MenuPanel(mainFrame));
		setVisible(true);
	}
	
}

