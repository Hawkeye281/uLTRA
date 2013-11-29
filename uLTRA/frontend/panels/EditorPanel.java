/**
 * 
 */
package panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import components.RayGrid;

import toolbar.CommonToolbar;

import frames.MainFrame;
import gamegrid.GameGrid;

/**
 * @author Sebastian Kiepert
 *
 */
public class EditorPanel extends JPanel{


	private static final long serialVersionUID = 1L;
	private boolean generated = false;
	private RayGrid ray;
	private int fieldHeight = 3;
	private int fieldWidth = 3;
	
	/**
	 * Hier wird das im EditorFrame eingebundene Panel editPanel erzeugt.
	 * @author Sebastian Kiepert
	 * @version 1.5
	 * @param mainFrame
	 * @see panels.EditorPanel#generateField(int, int)
	 * @see panels.EditorPanel#isGenerated()
	 * @see panels.EditorPanel#isGenerated()
	 * @see panels.EditorPanel#getFieldHeight()
	 * @see panels.EditorPanel#getFieldWidth()
	 * @see panels.EditorPanel#resetRay()
	 */
	public EditorPanel(MainFrame mainFrame){
		super(new BorderLayout());
		add(new CommonToolbar(mainFrame, this, "editor"), BorderLayout.PAGE_START);
		setSize(800, 600);
		setLocation(0, 0);
	}
	
	/**
	 * generiert mit den erhaltenen Parametern height (Höhe) und width (Breite) das zu bearbeitende Spielfeld
	 * @author Sebastian Kiepert
	 * @version 1.0
	 * @param height
	 * @param width
	 * @see panels.EditorPanel#EditorPanel(MainFrame)
	 */
	public void generateField(int height, int width){
		this.fieldHeight=height;
		this.fieldWidth=width;
		if (generated) remove(ray);
		add(createRay(new GameGrid(width, height)), BorderLayout.CENTER);
		setVisible(false);
		setVisible(true);
		setGenerated(true);
//		System.out.println("ja hier:" + height + ", " + width);
	}
	
	/**
	 * generiert die Spielfeldgrundlage, das RayGrid
	 * @see components.RayGrid
	 * @param _numbers
	 * @return ray
	 * @see panels.EditorPanel#EditorPanel(MainFrame)
	 */
	private RayGrid createRay(GameGrid pGrid){
		ray = new RayGrid(pGrid);
		return ray;
	}
	
	/**
	 * setzt, ob ein Spielfeld erzeugt wurde
	 * @param isGenerated
	 * @see panels.EditorPanel#EditorPanel(MainFrame)
	 */
	private void setGenerated(boolean isGenerated){
		generated = isGenerated;
	}
	
	/**
	 * gibt zurück, ob bereits ein Spielfeld erzeugt wurde
	 * @return boolean
	 * @see panels.EditorPanel#EditorPanel(MainFrame)
	 */
	public boolean isGenerated(){
		return generated;
	}
	
	/**
	 * gibt die Höhe des Spielfeldes zurück
	 * @return int
	 * @see panels.EditorPanel#EditorPanel(MainFrame)
	 */
	public int getFieldHeight(){
		return this.fieldHeight;
	}
	
	/**
	 * gibt die Breite des Spielfeldes zurück
	 * @return int
	 * @see panels.EditorPanel#EditorPanel(MainFrame)
	 */
	public int getFieldWidth(){
		return this.fieldWidth;
	}
	
	/**
	 * löscht das erstellte Spielfeld und aktualisiert das Panel
	 * @see panels.EditorPanel#EditorPanel(MainFrame)
	 */
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
