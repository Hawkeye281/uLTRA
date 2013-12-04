package Controller;

import panels.EditorPanel;
import panels.GamePanel;
import frames.MainFrame;

public class MenuController {
	private GamePanel _gamePanel;
	public void newGame(MainFrame mainFrame){
		mainFrame.removeFromDesktop(0);
		mainFrame.addToDesktop(new GamePanel(mainFrame));
	}
	
	public void editor(MainFrame mainFrame){
		mainFrame.removeFromDesktop(0);
		mainFrame.addToDesktop(new EditorPanel(mainFrame));
	}
	
	public void loadGame(){
		_gamePanel.loadGame();
	}
	
	public void exit(){
		System.exit(0);
	}
}
