/**
 * 
 */
package panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import listener.MouseTurnListener;

import Controller.GridController;

import components.RayGrid;
import components.TurnList;

import toolbar.CommonToolbar;

import frames.MainFrame;
import gamegrid.GameGrid;

/**
 * @author Sebastian Kiepert
 *
 */
public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static TurnList _turnList = new TurnList();
	
	private static RayGrid _rayGrid; 
	

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
		setSize(MainFrame.getDesktopSize());
		setLocation(0,0);

		GameGrid testGrid = GridController.getGameGrid();
		
		_rayGrid = new RayGrid(testGrid);
		_rayGrid.addMouseListener(new MouseTurnListener());
		
		add(_rayGrid, BorderLayout.CENTER);
		add(_turnList, BorderLayout.EAST);
	}

	public static TurnList getTurnList()
	{
		return _turnList;
	}
	
	public static RayGrid getRayGrid()
	{
		return _rayGrid;
	}
	
	public void initRayGrid(RayGrid rayGrid){
		remove(_rayGrid);
		_rayGrid = rayGrid;
		_rayGrid.addMouseListener(new MouseTurnListener());
		add(rayGrid, BorderLayout.CENTER);
		refresh();
	}
	
	private void refresh(){
		setVisible(false);
		setVisible(true);
	}
	
}
