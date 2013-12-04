package Controller;

import panels.EditorPanel;
import panels.GamePanel;
import frames.MainFrame;

public class MenuController {
	
	public void newGame(MainFrame mainFrame){
		mainFrame.removeFromDesktop(0);
		mainFrame.addToDesktop(new GamePanel(mainFrame));
	}
	
	public void editor(MainFrame mainFrame){
		mainFrame.removeFromDesktop(0);
		mainFrame.addToDesktop(new EditorPanel(mainFrame));
	}
	
	public void loadGame(GamePanel _gamePanel){
		_gamePanel.loadGame();
	}
	
	public void exit(){
		System.exit(0);
	}
}
