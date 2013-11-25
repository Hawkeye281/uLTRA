package Controller;

import frames.MainFrame;

public class MenuController {
	
	private MainFrame mainFrame;
	
	public void newGame(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		mainFrame.setMenuVisibility(false);
		mainFrame.setGameVisibility(true);
	}
	
	public void editor(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		mainFrame.setMenuVisibility(false);
		mainFrame.setEditorVisibility(true);
	}
	
	public void exit(){
		System.exit(0);
	}
}
