/**
 * 
 */
package panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import toolbar.CommonToolbar;

import frames.MainFrame;

/**
 * @author Sebastian Kiepert
 *
 */
public class GamePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GamePanel(MainFrame mainFrame){
		super(new BorderLayout());
		add(new CommonToolbar(mainFrame), BorderLayout.PAGE_START);
		setMaximumSize(mainFrame.getMaximumSize());
	}

}
