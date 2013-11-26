/**
 * 
 */
package panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controller.MenuController;

import frames.MainFrame;

/**
 * @author Sebastian Kiepert
 *
 */
public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	
	/**
	 * Hauptmenü der Anwendung, das im MenuPanel geladen
	 * @author Sebastian Kiepert
	 * @version 1.0
	 * @param MainFrame mainFrame
	 * @see panels.MenuPanel.ActionHandler#actionPerformed(ActionEvent)
	 */
	public MenuPanel(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		ActionHandler ah = new ActionHandler();
		setLayout(new GridLayout(4,1));
		JButton start = new JButton("Neues Spiel");
		start.setName("start");
		start.addActionListener(ah);
		JButton load = new JButton("Spiel laden");
		load.setName("load");
		JButton editor = new JButton("Editor starten");
		editor.setName("editor");
		editor.addActionListener(ah);
		JButton exitGame = new JButton("Spiel beenden");
		exitGame.setName("exit");
		exitGame.addActionListener(ah);
		add(start);
		add(load);
		add(editor);
		add(exitGame);
		setVisible(true);
	}
	
	class ActionHandler implements ActionListener{
		


		private MenuController gameController = new MenuController();
		
		/**
		 * implementiert die von den Schaltflächen auszuführenden Funktionen
		 * @see Controller.GameController
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clicked = (JButton) e.getSource();
			if (clicked.getName().equals("start")){
				gameController.newGame(mainFrame);
			}
			else if (clicked.getName().equals("load")){
				// TODO Load-Methode im Hauptmenü implementieren
			}
			else if (clicked.getName().equals("editor")){
				gameController.editor(mainFrame);
			}
			else if(clicked.getName().equals("exit")){
				gameController.exit();
			}
			
		}
		
	}
}
