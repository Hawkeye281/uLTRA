/**
 * 
 */
package panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import listener.EditorMouseListener;

import Controller.EditorController;


import toolbar.CommonToolbar;

import frames.MainFrame;

/**
 * @author Sebastian Kiepert
 *
 */
public class EditorPanel extends JPanel{


	private static final long serialVersionUID = 1L;
	private static EditorController editorController = new EditorController();
	private GridPanel editorGrid;
	
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
		add(new CommonToolbar(mainFrame, this), BorderLayout.PAGE_START);
		setSize(MainFrame.getDesktopSize());
		setLocation(0, 0);
		if (editorController.isSet()) editorController.removeGrid();
	}
	
	/**
	 * generiert mit den erhaltenen Parametern height (Höhe) und width (Breite) das zu bearbeitende Spielfeld
	 * @author Sebastian Kiepert
	 * @version 1.0
	 * @param height
	 * @param width
	 * @throws Exception 
	 * @see panels.EditorPanel#EditorPanel(MainFrame)
	 */
	public void generateField(int height, int width) throws Exception{
		if (editorController.isSet()) editorController.removeGrid();
		if (getComponentCount()>1) remove(getComponentCount()-1);
		JPanel helpPanel = new JPanel();
		helpPanel.add(createEditorGrid(height, width));
		add(helpPanel, BorderLayout.CENTER);
		refresh();
	}
	
	/**
	 * generiert die Spielfeldgrundlage
	 * @see panels.GridPanel
	 * @param _numbers
	 * @return ray
	 * @throws Exception 
	 * @see panels.EditorPanel#EditorPanel(MainFrame)
	 */
	private GridPanel createEditorGrid(int height, int width) throws Exception{
		editorController.setGameGrid(height, width);
		editorGrid = editorController.getGridPanel();
		editorGrid.addMouseListener(new EditorMouseListener(this));
		return editorGrid;
	}
	
	/**
	 * löscht das erstellte Spielfeld und aktualisiert das Panel
	 * @see panels.EditorPanel#EditorPanel(MainFrame)
	 */
	public void resetGrid(){
		if (editorController.isSet()){
			editorController.removeGrid();
			remove(getComponentCount()-1);
			refresh();
		}
	}
	
	public void reloadEditor(){
		remove(getComponentCount()-1);
		JPanel helpPanel = new JPanel();
		editorGrid = editorController.getGridPanel();
		editorGrid.addMouseListener(new EditorMouseListener(this));
		helpPanel.add(editorGrid);
		add(helpPanel, BorderLayout.CENTER);
		refresh();
	}
	
	private void refresh(){
		setVisible(false);
		setVisible(true);
	}
	
	public static EditorController getController(){
		return editorController;
	}

}
