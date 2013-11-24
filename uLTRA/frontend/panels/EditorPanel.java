/**
 * 
 */
package panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import components.RayGrid;

import toolbar.CommonToolbar;

import frames.MainFrame;

/**
 * @author Sebastian Kiepert
 *
 */
public class EditorPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EditorPanel(MainFrame mainFrame){
		super(new BorderLayout());
		add(new CommonToolbar(mainFrame, this, "editor"), BorderLayout.PAGE_START);
		setMaximumSize(mainFrame.getMaximumSize());
	}
	
	public void generateField(int height, int width){
		int[][] _numbers = new int[height][width];
		this.add(new RayGrid(_numbers, null), BorderLayout.CENTER);
		this.setVisible(false);
		this.setVisible(true);
		System.out.println("ja hier:" + height + ", " + width);
	}

}
