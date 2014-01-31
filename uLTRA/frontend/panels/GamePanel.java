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
	private JList<Command> turnList;
	private JList<String> errorList;
	private boolean checked = false;
	private JPanel listPanel;
	
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
			this.turnList.setPreferredSize(new Dimension(200, (int)this.getSize().getHeight()));
			break;
		case EDIT:
			this.errorList = new JList<String>();
			this.errorList.setPreferredSize(new Dimension(200, (int)this.getSize().getHeight()));
			break;
		}

		
	}
	
	public JList<Command> getTurnList(){
		return this.turnList;
	}	
	
	public JList<String> getErrorList()
	{
		return errorList;
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
				switch(mode)
				{
				case EDIT:
					this.remove(listPanel);
					break;
				case GAME:
					this.remove(listPanel);
					break;
				}
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
//			this.setTurnList();
			listPanel = getTurnListPanel();
			this.groundPanel.setBackground(new Color(255,255,255,130));
			this.groundPanel.add(setGridPanel());
			this.add(this.groundPanel, BorderLayout.CENTER);
			this.add(listPanel, BorderLayout.EAST);
			if (checked)
				checkRules();
		}
	}
	
	public void checkRules(){
		if (componentsExist()){
			this.listPanel.removeAll();
		}
		switch(mode)
		{
		case EDIT:			
			this.listPanel.add(initializeErrorPanel(), BorderLayout.EAST);
			break;
		case GAME:
			this.listPanel.add(initializeTurnPanel(), BorderLayout.EAST);
			break;
		}
		
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
			this.remove(listPanel);
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
			listPanel.add(initializeErrorPanel(), BorderLayout.CENTER);
			listPanel.setBorder(BorderFactory.createTitledBorder("Fehlerliste"));
			listPanel.setBackground(new Color(255,255,255,130));
			break;
		case GAME:
			listPanel.setLayout(new BorderLayout());
			listPanel.add(initializeTurnPanel(), BorderLayout.CENTER);
			listPanel.setBorder(BorderFactory.createTitledBorder("Zugliste"));
			listPanel.setBackground(new Color(255,255,255,130));
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
			buttonPanel.setBackground(new Color(255,255,255,0));
			listPanel.add(buttonPanel, BorderLayout.SOUTH);
			break;
		}
		return listPanel;		
	}
	
	/**
	 * Initialisiert und gibt eine fertige JScrollPane zurück
	 * @return turnList -> JScrollPane
	 */
	private JScrollPane initializeTurnPanel(){
		this.turnList = new JList<Command>(new TurnListModel());
		JScrollPane turnList = new JScrollPane(this.turnList);
		this.turnList.setCellRenderer(new TurnListRenderer());
		this.turnList.setPreferredSize(new Dimension(200, (int)this.getSize().getHeight()));
		this.turnList.setBackground(new Color(180,180,180,130));
		
		return turnList;
	}
	
	private JScrollPane initializeErrorPanel()
	{
		this.errorList = new JList<String>();
		JScrollPane errorPane = new JScrollPane(this.errorList);
		this.turnList.setPreferredSize(new Dimension(200, (int)this.getSize().getHeight()));
		this.errorList.setBackground(new Color(180,180,180,130));
		return errorPane;
	}

}
