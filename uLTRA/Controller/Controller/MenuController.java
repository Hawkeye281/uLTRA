package Controller;

import panels.EditorPanel;
import panels.GamePanel;
import frames.MainFrame;

public class MenuController {
	static MainFrame mainFrame = new MainFrame();
	
	
	public void newGame(MainFrame mainFrame){
		mainFrame.removeFromDesktop(0);
		mainFrame.addToDesktop(new GamePanel(mainFrame));
	}
	
	public void editor(MainFrame mainFrame){
		mainFrame.removeFromDesktop(0);
		mainFrame.addToDesktop(new EditorPanel(mainFrame));
	}
	
	public static void loadGame(GamePanel _gamePanel){
		mainFrame.removeFromDesktop(0);
		_gamePanel = new GamePanel(mainFrame);
		mainFrame.addToDesktop(_gamePanel);
		_gamePanel.loadGame();
	}
	
	public void exit(){
		System.exit(0);
	}
}
