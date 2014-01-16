package Controller;

import panels.GamePanel;
import sebastian.Mode;
import sebastian.PanelMode;
import frames.MainFrame;

public class MenuController {
	static MainFrame mainFrame = MainFrame.getMainFrame();
	
	
	public void newGame(){
		mainFrame.removeFromDesktop(0);
		mainFrame.addToDesktop(new GamePanel(new PanelMode(Mode.GAME)));
	}
	
	public void editor(){
		mainFrame.removeFromDesktop(0);
		mainFrame.addToDesktop(new GamePanel(new PanelMode(Mode.EDIT)));
	}
	
//	public static void loadGame(GamePanel _gamePanel){
//		mainFrame.removeFromDesktop(0);
//		_gamePanel = new GamePanel(mainFrame);
//		mainFrame.addToDesktop(_gamePanel);
//		_gamePanel.loadGame();
//	}
	
	public void exit(){
		System.exit(0);
	}
}
