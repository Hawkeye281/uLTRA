/**
 * 
 */
package panels;

import gamegrid.GameGrid;
import help.HelpWindow;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.MenuController;

import com.alee.laf.button.WebButtonUI;

/**
 * @author Sebastian Kiepert
 *
 */
public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Hauptmen� der Anwendung, das im MenuPanel geladen
	 * @author Sebastian Kiepert
	 * @version 1.0
	 * @param MainFrame mainFrame
	 * @see panels.MenuPanel.ActionHandler#actionPerformed(ActionEvent)
	 */
	public MenuPanel(){
		super(new GridLayout(5,1));
		setSize(200, 300);
		setLocation(300,150);
		setBackground(new Color(180,180,180));
		setBorder(BorderFactory.createTitledBorder("Hauptmen�"));
		add(setButton("Neues Spiel", "start", true, new ImageIcon("../uLTRA/Documents/images/icons/new.png")));
		add(setButton("Editor starten", "editor", true, new ImageIcon("../uLTRA/Documents/images/icons/editor.png")));
		add(setButton("Hilfe", "help", true, new ImageIcon("../uLTRA/Documents/images/icons/help.png")));
		add(setButton("Spiel beenden", "exit", true, new ImageIcon("../uLTRA/Documents/images/icons/exit.png")));
		add(setButton("�ber...", "about", true, new ImageIcon("../uLTRA/Documents/images/icons/about.png")));
		setVisible(true);
	}
	
	private JButton setButton(String title, String name, boolean enable, Icon icon){
		JButton button = new JButton(title);
		((WebButtonUI)button.getUI()).setBottomBgColor(Color.WHITE);
		((WebButtonUI)button.getUI()).setBottomSelectedBgColor(new Color(230,230,230));
		((WebButtonUI)button.getUI()).setRolloverShine(true);
		((WebButtonUI)button.getUI()).setShineColor(new Color(180, 180, 180));
		button.setName(name);
		button.addActionListener(new ActionHandler());
		button.setEnabled(enable);
		button.setIcon(icon);
		button.setBackground(Color.WHITE);
		button.setLayout(null);
		button.setIconTextGap(30);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		return button;
	}
	
	class ActionHandler implements ActionListener{

		private MenuController menuController = new MenuController();
		
		/**
		 * implementiert die von den Schaltfl�chen auszuf�hrenden Funktionen
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
			else if(clicked.getName().equals("help")){
				HelpWindow.getInstance();
			}
			else if(clicked.getName().equals("about")){
				ImageIcon icon = new ImageIcon("../uLTRA/Documents/images/icons/about.png");
				String message = "uLTRA - The Lightbeam Game \n\n" + 
						"Entwickelt von: \n" +
						"\t \t Buhr, Manuel\n" +
						"\t \t Dickau, Martin\n" +
						"\t \t Humme, Stephan\n" +
						"\t \t Kiepert, Sebastian\n" +
						"\t \t Strauch, Carsten\n" +
						"\t \t We�ler, Oliver\n\n";
				JOptionPane.showMessageDialog(null, message, "�ber...", JOptionPane.INFORMATION_MESSAGE, icon);
			}
		}
	}
}
