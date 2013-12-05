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

/**
 * @author Sebastian Kiepert
 *
 */
public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static TurnList _turnList = new TurnList();
	
	private static RayGrid _rayGrid; 
	private static GridController _gridController = new GridController();
	

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
		add(new CommonToolbar(mainFrame, this, "game"), BorderLayout.PAGE_START);
		setSize(MainFrame.getDesktopSize());
		setLocation(0,0);
		
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
		add(_rayGrid, BorderLayout.CENTER);
		add(_turnList, BorderLayout.EAST);
		refresh();
	}
	
	public void loadGame(){
		_rayGrid = new RayGrid(_gridController);
		initRayGrid(_rayGrid);
	}
	private void refresh(){
		setVisible(false);
		setVisible(true);
	}
	
}
