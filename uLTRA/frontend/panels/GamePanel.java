/**
 * 
 */
package panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import components.RayGrid;
import components.TurnList;

import toolbar.CommonToolbar;

import frames.MainFrame;

/**
 * @author Sebastian Kiepert
 *
 */
public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Spielbrett der Anwendung. Hier werden alle zum Spielen benötigten Funktionen geladen
	 * (ggfs. bitte umformulieren)
	 * 
	 * @author Sebastian Kiepert & Stephan Humme
	 * @version 1.0
	 * @todo Controller uebernimmt in Zukunft die Initialisierung
	 * @see components.RayGrid
	 * @see components.TurnList
	 */
	public GamePanel(MainFrame mainFrame){
		super(new BorderLayout());
		add(new CommonToolbar(mainFrame, null, "game"), BorderLayout.PAGE_START);
		setMaximumSize(mainFrame.getMaximumSize());
		
		//TODO Nur zu Testzwecken. Sind die Zahlen vom vierten Rätsel vom Beispielblatt 
		int[][] _numbers = new int[12][12];
		
		_numbers[0][4] = 5;
		_numbers[0][9] = 2;
		_numbers[1][1] = 6;
		_numbers[1][6] = 8;
		_numbers[2][4] = 3;
		_numbers[2][11] = 3;
		_numbers[3][0] = 4;
		_numbers[3][7] = 2;
		_numbers[4][2] = 6;
		_numbers[4][5] = 4;
		_numbers[4][11] = 4;
		_numbers[5][9] = 4;
		_numbers[6][1] = 6;
		_numbers[6][4] = 6;
		_numbers[7][6] = 5;
		_numbers[7][8] = 6;
		_numbers[8][3] = 4;
		_numbers[8][10] = 9;
		_numbers[9][0] = 6;
		_numbers[9][5] = 5;
		_numbers[9][7] = 4;
		_numbers[10][2] = 2;
		_numbers[10][11] = 6;
		_numbers[11][4] = 6;
		_numbers[11][9] = 2;
		
		add(new RayGrid(_numbers, null), BorderLayout.CENTER);
		add(new TurnList(), BorderLayout.EAST);
	}

}
