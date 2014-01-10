/**
 * 
 */
package panels;

import java.awt.BorderLayout;
import java.awt.Color;

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
	
	private static TurnList _turnList;
	
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
		add(new CommonToolbar(mainFrame, this), BorderLayout.PAGE_START);
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
	
	public void refreshGridPanel(){
		JPanel helpPanel = new JPanel();
		if (getComponentCount()>1){
			remove(getComponentCount()-1);
			remove(getComponentCount()-1);
		}
		_turnList = new TurnList();
		_gridPanel = new GridPanel();
		_gridPanel.addMouseListener(new MouseTurnListener());
		_gridPanel.setBackground(Color.WHITE);
		helpPanel.add(_gridPanel);
		add(helpPanel, BorderLayout.CENTER);
		add(_turnList, BorderLayout.EAST);
		refresh();
	}
	
	public void loadGame(){
		refreshGridPanel();
	}
	private void refresh(){
		setVisible(false);
		setVisible(true);
	}
	
}
