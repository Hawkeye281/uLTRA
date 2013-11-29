/**
 * 
 */
package panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
		super(new GridLayout(5,1));
		this.mainFrame = mainFrame;
		setSize(200, 300);
		setLocation(300,150);
		setBorder(BorderFactory.createTitledBorder("Hauptmenü"));
		add(setButton("Neues Spiel", "start", true));
		add(setButton("Spiel laden", "load", false));
		add(setButton("Editor starten", "editor", true));
		add(setButton("Optionen", "options", false));
		add(setButton("Spiel beenden", "exit", true));
		setVisible(true);
	}
	
	private JButton setButton(String title, String name, boolean enable){
		JButton button = new JButton(title);
		button.setName(name);
		button.addActionListener(new ActionHandler());
		button.setEnabled(enable);
		return button;
	}
	
	class ActionHandler implements ActionListener{

		private MenuController menuController = new MenuController();
		
		/**
		 * implementiert die von den Schaltflächen auszuführenden Funktionen
		 * @see Controller.GameController
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clicked = (JButton) e.getSource();
			if (clicked.getName().equals("start")){
				menuController.newGame(mainFrame);
			}
			else if (clicked.getName().equals("load")){
				// TODO Load-Methode im Hauptmenü implementieren
			}
			else if (clicked.getName().equals("editor")){
				menuController.editor(mainFrame);
			}
			else if(clicked.getName().equals("exit")){
				menuController.exit();
			}
			
		}
		
	}
}
