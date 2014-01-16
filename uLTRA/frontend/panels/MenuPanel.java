/**
 * 
 */
package panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.MenuController;

import gamegrid.GameGrid;

/**
 * @author Sebastian Kiepert
 *
 */
public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Hauptmenü der Anwendung, das im MenuPanel geladen
	 * @author Sebastian Kiepert
	 * @version 1.0
	 * @param MainFrame mainFrame
	 * @see panels.MenuPanel.ActionHandler#actionPerformed(ActionEvent)
	 */
	public MenuPanel(){
		super(new GridLayout(5,1));
		setSize(200, 300);
		setLocation(300,150);
		setBorder(BorderFactory.createTitledBorder("Hauptmenü"));
		add(setButton("Neues Spiel", "start", true, new ImageIcon("../uLTRA/Documents/images/icons/new.png")));
		add(setButton("Editor starten", "editor", true, new ImageIcon("../uLTRA/Documents/images/icons/editor.png")));
		add(setButton("Optionen", "options", false, new ImageIcon("../uLTRA/Documents/images/icons/options.png")));
		add(setButton("Spiel beenden", "exit", true, new ImageIcon("../uLTRA/Documents/images/icons/exit.png")));
		setVisible(true);
	}
	
	private JButton setButton(String title, String name, boolean enable, Icon icon){
		JButton button = new JButton(title);
		button.setName(name);
		button.addActionListener(new ActionHandler());
		button.setEnabled(enable);
		button.setIcon(icon);
		button.setBackground(Color.WHITE);
		button.setIconTextGap(30);
		button.setHorizontalAlignment(SwingConstants.LEFT);
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
				menuController.newGame();
			}
			else if (clicked.getName().equals("editor")){
				GameGrid.deleteInstance();
				menuController.editor();
			}
			else if(clicked.getName().equals("exit")){
				menuController.exit();
			}
			
		}
		
	}
}
