/**
 * 
 */
package main;

import Controller.GridController;
import Controller.LookAndFeelController;

/**
 * @author Sebastian Kiepert
 * @author Martin Dickau
 */
public class UltraExe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LookAndFeelController.initializeLookAndFeel();
		GridController game = new GridController();
		game.initializeGame();
	}

}
