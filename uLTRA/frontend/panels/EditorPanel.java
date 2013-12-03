/**
 * 
 */
package panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import listener.EditorMouseListener;

import Controller.EditorController;

import components.RayGrid;

import toolbar.CommonToolbar;

import frames.MainFrame;

/**
 * @author Sebastian Kiepert
 *
 */
public class EditorPanel extends JPanel{


	private static final long serialVersionUID = 1L;
	private EditorController editorController = new EditorController();
	private RayGrid editorGrid;
	
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
	 * @see panels.EditorPanel#resetGrid()
	 */
	public EditorPanel(MainFrame mainFrame){
		super(new BorderLayout());
		add(new CommonToolbar(mainFrame, this, "editor"), BorderLayout.PAGE_START);
		setSize(MainFrame.getDesktopSize());
		setLocation(0, 0);
	}
	
	/**
	 * generiert mit den erhaltenen Parametern height (H�he) und width (Breite) das zu bearbeitende Spielfeld
	 * @author Sebastian Kiepert
	 * @version 1.0
	 * @param height
	 * @param width
	 * @see panels.EditorPanel#EditorPanel(MainFrame)
	 */
	public void generateField(int height, int width){
		if (editorController.isSet()) remove(editorGrid);
		add(createEditorGrid(height, width));
		refresh();
//		System.out.println("ja hier:" + height + ", " + width);
	}
	
	/**
	 * generiert die Spielfeldgrundlage, das RayGrid
	 * @see components.RayGrid
	 * @param _numbers
	 * @return ray
	 * @see panels.EditorPanel#EditorPanel(MainFrame)
	 */
	private RayGrid createEditorGrid(int height, int width){
		editorController.setGrid(height, width);
		editorGrid = editorController.getActivGrid();
		editorGrid.addMouseListener(new EditorMouseListener());
		return editorGrid;
	}
	
	/**
	 * l�scht das erstellte Spielfeld und aktualisiert das Panel
	 * @see panels.EditorPanel#EditorPanel(MainFrame)
	 */
	public void resetGrid(){
		if (editorController.isSet()){
			remove(editorGrid);
			refresh();
		}
	}
	
	private void refresh(){
		setVisible(false);
		setVisible(true);
	}
	
	public EditorController getController(){
		return editorController;
	}

}
