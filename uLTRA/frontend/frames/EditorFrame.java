package frames;

import javax.swing.JInternalFrame;

import panels.EditorPanel;

public class EditorFrame extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
