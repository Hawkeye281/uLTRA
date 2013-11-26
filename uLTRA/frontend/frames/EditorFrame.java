package frames;

import javax.swing.JInternalFrame;

import panels.EditorPanel;

public class EditorFrame extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Editorfenster - hier werden alle Editorfunktionalitäten über das EditorPanel bereitgestellt.
	 * Im Hauptfenster MainFrame geladen.
	 * @param mainFrame
	 * @author Sebastian Kiepert
	 * @version 1.0
	 * @see frames.MainFrame#MainFrame()
	 * @see panels.EditorPanel#EditorPanel(MainFrame)
	 */
	public EditorFrame(MainFrame mainFrame){
		closable = false;
		maximizable = false;
		resizable = false;
		isMaximum = true;
		setTitle("Editor");
		add(new EditorPanel(mainFrame));
		setVisible(false);
	}

	
}
