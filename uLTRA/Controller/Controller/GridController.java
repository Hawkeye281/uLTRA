package Controller;

import frames.MainFrame;

/**
 * 
 * @author Martin
 *
 */
public class MainController {
	
	public MainController(){
		
	}

	/**
	 * Das Spiel initialisieren
	 */
	public void initializeGame(){
		MainFrame frame = new MainFrame();
		frame.init();
	}
	
	
}
