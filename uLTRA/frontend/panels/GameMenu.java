/**
 * 
 */
package panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import frames.MainFrame;

/**
 * @author Sebastian Kiepert
 *
 */
public class GameMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;

	public GameMenu(MainFrame mainFrame){
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

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clicked = (JButton) e.getSource();
			if (clicked.getName()=="start"){
				mainFrame.getMenuFrame().setVisible(false);
				mainFrame.getGameFrame().setVisible(true);
			}
			else if (clicked.getName()=="load"){
				
			}
			else if (clicked.getName()=="editor"){
				
			}
			else if(clicked.getName()=="exit"){
				System.exit(0);
			}
			
		}
		
	}
}