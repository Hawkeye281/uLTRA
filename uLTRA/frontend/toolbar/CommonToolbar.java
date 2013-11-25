package toolbar;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JToolBar;

import panels.EditorPanel;
import toolbarActions.*;

import frames.MainFrame;

public class CommonToolbar extends JToolBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CommonToolbar(final MainFrame mainFrame, final EditorPanel editPanel, final String whoYouAre){
		setFloatable(false);
		add(new LoadAction());
		add(new SaveAction());
		if (whoYouAre.equals("editor")){
			add(seperator());
			add(new GenerateAction(editPanel));
			add(new ResetAction(editPanel));
		}
		add(seperator());
		add(new MainMenuAction(mainFrame, whoYouAre));
		add(new CloseAction());
		
	}
	
	private JLabel seperator(){
		JLabel l = new JLabel();
		l.setText("|");
		l.setFont(new Font("|",Font.PLAIN, 20));
		return l;
	}
}
