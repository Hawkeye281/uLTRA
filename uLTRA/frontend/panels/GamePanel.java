/**
 * 
 */
package panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import Controller.GridController;

import components.RayGrid;
import components.TurnList;

import toolbar.CommonToolbar;

import frames.MainFrame;
import gamegrid.GameGrid;
import gamegrid.LightSource;

/**
 * @author Sebastian Kiepert
 *
 */
public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static TurnList _turnList = new TurnList();
	

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
		setSize(800, 600);
		setLocation(0,0);
		
		//TODO Nur zu Testzwecken. Sind die Zahlen vom vierten Rätsel vom Beispielblatt 
		GameGrid testGrid = GridController.getGameGrid();
		
//		erste Zahl ist y, zweite ist x
//		testGrid.getCell(0, 4).setContent(new LightSource(5));
//		testGrid.getCell(0, 9).setContent(new LightSource(2));
//		testGrid.getCell(1, 1).setContent(new LightSource(6));
//		testGrid.getCell(1, 6).setContent(new LightSource(8));
//		testGrid.getCell(2, 4).setContent(new LightSource(3));
//		testGrid.getCell(2, 11).setContent(new LightSource(3));
//		testGrid.getCell(3, 0).setContent(new LightSource(4));
//		testGrid.getCell(3, 7).setContent(new LightSource(2));
//		testGrid.getCell(4, 2).setContent(new LightSource(6));
//		testGrid.getCell(4, 5).setContent(new LightSource(4));
//		testGrid.getCell(4, 11).setContent(new LightSource(4));
//		testGrid.getCell(5, 9).setContent(new LightSource(4));
//		testGrid.getCell(6, 1).setContent(new LightSource(6));
//		testGrid.getCell(6, 4).setContent(new LightSource(6));
//		testGrid.getCell(6, 7).setContent(new LightSource(5));
//		testGrid.getCell(7, 8).setContent(new LightSource(6));
//		testGrid.getCell(8, 3).setContent(new LightSource(4));
//		testGrid.getCell(8, 10).setContent(new LightSource(9));
//		testGrid.getCell(9, 0).setContent(new LightSource(6));
//		testGrid.getCell(9, 5).setContent(new LightSource(5));
//		testGrid.getCell(9, 7).setContent(new LightSource(4));
//		testGrid.getCell(10, 2).setContent(new LightSource(2));
//		testGrid.getCell(10, 11).setContent(new LightSource(6));
//		testGrid.getCell(11, 4).setContent(new LightSource(6));
//		testGrid.getCell(11, 9).setContent(new LightSource(2));
		
		RayGrid rg = new RayGrid(testGrid);
		
		add(rg, BorderLayout.CENTER);
		add(_turnList, BorderLayout.EAST);
	}

	public static TurnList getTurnList()
	{
		return _turnList;
	}
}
