/**
 * 
 */
package panels;

import history.Command;
import history.TurnListModel;
import history.TurnListRenderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import listener.EditorMouseListener;
import listener.MouseTurnListener;
import sebastian.CheckEditRules;
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
	private JList turnList;
	private boolean checked = false;
	
	public GamePanel(PanelMode mode){
		super(new BorderLayout());
		_gamePanelNew = this;
		if (this.getComponentCount()>0) this.removeAll();
		this.mode = mode.getPanelMode();
		this.setSize(MainFrame.getDesktopSize());
		this.setLocation(0,0);
//		this.setBackground(new Color(68, 68, 68, 150));
		this.setOpaque(false);
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
		editorController.resetGrid();
	}
	
	public EditorController getEditorController(){
		return this.editorController;
	}
	
	private void setGridController(){
		this.gridController = new GridController();
		gridController.resetGrid();
	}
	
	public GridController getGridController(){
		return this.gridController;
	}
	
	public void setTurnList(){
		switch(mode)
		{
		case GAME:
			this.turnList = new JList<Command>(new TurnListModel());
			this.turnList.setCellRenderer(new TurnListRenderer());
			break;
		case EDIT:
			this.turnList = new JList<String>(new DefaultListModel());
			break;
		}

		this.turnList.setPreferredSize(new Dimension(200, (int)this.getSize().getHeight()));
	}
	
	public JList<Command> getTurnList(){
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
		if ((mode == Mode.GAME && gridController.getPlayable()) || mode == Mode.EDIT){
			if (componentsExist()) {
				this.remove(this.groundPanel);
				this.remove(this.turnList);
			}
			this.groundPanel = new JPanel();
			if(mode == Mode.EDIT)
			{
				this.groundPanel.setBorder(BorderFactory.createTitledBorder("Feld enthält: " 
											+ CheckEditRules.lightSourceCount() + " Lichtquelle(n)"
											+ " mit einer Gesamtstärke von " + CheckEditRules.lightCapacityCount() + ", "
											+ CheckEditRules.beamCount() + " Lichtstrahl(en), "
											+ CheckEditRules.emptyCellCount() + " leere Felder"));
			}
			
			this.setTurnList();
			this.groundPanel.setBackground(new Color(255,255,255,130));
			this.groundPanel.add(setGridPanel());
			this.add(this.groundPanel, BorderLayout.CENTER);
			this.add(this.getTurnListPanel(), BorderLayout.EAST);
			if (this.checked)
				CheckEditRules.check(this);
		}
	}
	
	public void checkRules(){
		if (componentsExist()){
			this.remove(this.turnList);
		}
		this.setTurnList();
		this.add(this.turnList, BorderLayout.EAST);
		CheckEditRules.check(this);
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
		this.gridPanel.setBackground(new Color(68,68,68));
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
		if (_gamePanelNew.getEditorController() != null && this.gridController == null){
			if (this.editorController.gridIsSet())
				this.editorController.removeGrid();	
		}
		else if (_gamePanelNew.getGridController() != null && this.editorController == null)
			if (this.gridController.gridIsSet())
				this.gridController.removeGrid();
		resetPanel();
	}
	
	private JPanel getTurnListPanel()
	{
		JPanel listPanel = new JPanel();
		switch(mode)
		{
		case EDIT:
			listPanel.setLayout(new BorderLayout());
			listPanel.add(new JScrollPane(this.turnList), BorderLayout.CENTER);
			listPanel.setBorder(BorderFactory.createTitledBorder("Fehlerliste"));
			break;
		case GAME:
			listPanel.setLayout(new BorderLayout());
			listPanel.add(new JScrollPane(this.turnList), BorderLayout.CENTER);
			listPanel.setBorder(BorderFactory.createTitledBorder("Zugliste"));
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(1,2));
			
			JButton undoButton = new JButton("<- undo");
			undoButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					((TurnListModel)turnList.getModel()).undo();
				}});
			
			buttonPanel.add(undoButton);
			
			JButton redoButton = new JButton("redo ->");
			redoButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					((TurnListModel)turnList.getModel()).redo();
				}});
			buttonPanel.add(redoButton);
			
			listPanel.add(buttonPanel, BorderLayout.SOUTH);
			break;
		}
		return listPanel;		
	}
	

}
