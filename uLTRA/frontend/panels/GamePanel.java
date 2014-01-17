/**
 * 
 */
package panels;

import java.awt.BorderLayout;
import java.awt.Color;

import components.TurnList;

import javax.swing.JPanel;

import listener.EditorMouseListener;
import listener.MouseTurnListener;

import sebastian.Mode;
import sebastian.PanelMode;
import toolbar.CommonToolbar;

import Controller.EditorController;
import Controller.GridController;

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
	
	private static GamePanel _gamePanelNew;
	private MainFrame mainFrame = MainFrame.getMainFrame();
	private Mode mode;
	private JPanel groundPanel;
	private GridPanel gridPanel;
	private GridController gridController;
	private EditorController editorController;
	private TurnList turnList;
	private boolean checked = false;
	
	public GamePanel(PanelMode mode){
		super(new BorderLayout());
		_gamePanelNew = this;
		if (this.getComponentCount()>0) this.removeAll();
		this.mode = mode.getPanelMode();
		this.setSize(MainFrame.getDesktopSize());
		this.setLocation(0,0);
		switch(mode.getPanelMode()){
			case GAME:
			{
				this.setGridController();
				this.add(new CommonToolbar(), BorderLayout.PAGE_START);
				break;
			}
			case EDIT:
			{
				this.setEditorController();
				this.add(new CommonToolbar(), BorderLayout.PAGE_START);
				break;
			}
		}
		this.setVisible(true);
	}
	
	public static GamePanel getGamePanel(){
		return _gamePanelNew;
	}
	
	private void setEditorController(){
		this.editorController = new EditorController();
	}
	
	public EditorController getEditorController(){
		return this.editorController;
	}
	
	private void setGridController(){
		this.gridController = new GridController();
	}
	
	public GridController getGridController(){
		return this.gridController;
	}
	
	public void setTurnList(){
		this.turnList = new TurnList();
	}
	
	public TurnList getTurnList(){
		return this.turnList;
	}	
	
	public MainFrame getMainFrame(){
		return this.mainFrame;
	}
	
	public Mode getPanelMode(){
		return this.mode;
	}
	
	public void setChecked(){
		this.checked = true;
	}
	
	public boolean isChecked(){
		return this.checked;
	}
	
	public void setGroundPanel(){
		if (this.getComponentCount()>1) this.remove(this.groundPanel);
		this.groundPanel = new JPanel();
		this.setTurnList();
		this.groundPanel.setBackground(Color.WHITE);
		this.groundPanel.add(setGridPanel());
		this.add(this.groundPanel, BorderLayout.CENTER);
		this.add(this.turnList, BorderLayout.EAST);
	}
	
	public JPanel getGroundPanel(){
		return this.groundPanel;
	}
	
	private GridPanel setGridPanel(){
		switch (this.mode){
		case EDIT:	
		{
			this.gridPanel = this.editorController.getGridPanel();
			this.gridPanel.addMouseListener(new EditorMouseListener());
			break;
		}
		case GAME:	
		{
			this.gridPanel = new GridPanel();
			this.gridPanel.addMouseListener(new MouseTurnListener());
			break;
		}
		}
		this.gridPanel.setBackground(Color.WHITE);
		return this.gridPanel;
	}
	
	public GridPanel getGridPanel(){
		return this.gridPanel;
	}
	
	public boolean componentsExist(){
		return (this.getComponentCount()>1)? true : false;
	}
	
	public void refresh(){
		this.setVisible(false);
		this.setVisible(true);
	}
	
	public void resetPanel(){
		if (componentsExist()){
			this.remove(this.groundPanel);
			this.remove(this.turnList);
		}
		this.checked = false;
		this.refresh();
	}
	
	public void resetGrid(){
		if (this.editorController != null)
			if (this.editorController.gridIsSet())
				this.editorController.removeGrid();
		resetPanel();
	}
	

}
