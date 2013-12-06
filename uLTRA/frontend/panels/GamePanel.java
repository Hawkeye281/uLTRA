/**
 * 
 */
package panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import listener.MouseTurnListener;

import Controller.GridController;

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
	
	private static GridPanel _gridPanel; 
	private static GridController _gridController = new GridController();
	

	/**
	 * Spielbrett der Anwendung. Hier werden alle zum Spielen benötigten Funktionen geladen
	 * (ggfs. bitte umformulieren)
	 * 
	 * @author Sebastian Kiepert & Stephan Humme
	 * @version 1.0
	 * @todo Controller uebernimmt in Zukunft die Initialisierung
	 * @see panels.GridPanel
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
	
	public static GridPanel getGridPanel()
	{
		return _gridPanel;
	}
	
	public static GridController getGridController()
	{
		return _gridController;
	}
	
	public void initGridDesigner(GridPanel GridDesigner){
		remove(_gridPanel);
		_gridPanel = GridDesigner;
		_gridPanel.addMouseListener(new MouseTurnListener());
		add(_gridPanel, BorderLayout.CENTER);
		add(_turnList, BorderLayout.EAST);
		refresh();
	}
	
	public void loadGame(){
		_gridPanel = new GridPanel(_gridController);
		initGridDesigner(_gridPanel);
	}
	private void refresh(){
		setVisible(false);
		setVisible(true);
	}
	
}
