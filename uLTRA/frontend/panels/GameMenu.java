/**
 * 
 */
package panels;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Sebastian Kiepert
 *
 */
public class GameMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameMenu(){
		JButton start = new JButton("Neues Spiel");
		start.setName("start");
		JButton load = new JButton("Spiel laden");
		load.setName("load");
		JButton editor = new JButton("Editor starten");
		editor.setName("editor");
		JButton exitGame = new JButton("Spiel beenden");
		exitGame.setName("exit");
		this.add(start);
		this.add(load);
		this.add(editor);
		this.add(exitGame);
		this.setVisible(true);
	}
}
