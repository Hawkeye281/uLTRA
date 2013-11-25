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
	private boolean generated = false;
	private RayGrid ray;
	private int fieldHeight = 3;
	private int fieldWidth = 3;
	
	public EditorPanel(MainFrame mainFrame){
		super(new BorderLayout());
		add(new CommonToolbar(mainFrame, this, "editor"), BorderLayout.PAGE_START);
		setMaximumSize(mainFrame.getMaximumSize());
	}
	
	public void generateField(int height, int width){
		this.fieldHeight=height;
		this.fieldWidth=width;
		if (generated) remove(ray);
		add(createRay(new int[height][width]), BorderLayout.CENTER);
		setVisible(false);
		setVisible(true);
		setGenerated(true);
//		System.out.println("ja hier:" + height + ", " + width);
	}
	
	private RayGrid createRay(int[][] _numbers){
		ray = new RayGrid(_numbers, null);
		return ray;
	}
	
	private void setGenerated(boolean isGenerated){
		generated = isGenerated;
	}
	
	public boolean isGenerated(){
		return generated;
	}
	
	public int getFieldHeight(){
		return this.fieldHeight;
	}
	
	public int getFieldWidth(){
		return this.fieldWidth;
	}
	
	public void resetRay(){
		if (isGenerated()){
			this.fieldHeight = 3;
			this.fieldWidth = 3;
			remove(ray);
			setVisible(false);
			setVisible(true);
		}
	}

}
