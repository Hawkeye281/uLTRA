package toolbar;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import panels.EditorPanel;
import panels.GamePanel;
import toolbarActions.*;

import frames.MainFrame;

public class CommonToolbar extends JToolBar{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Von Spielbrett und Editor gemeinsam verwendete Toolbar, die entsprechend ihres Erzeugers die entsprechenden
	 * Schaltflächen einbaut
	 * 
	 * @param final MainFrame mainFrame
	 * @param final EditorPanel editorPanel
	 * @param final String whoYouAre
	 * @author Sebatian Kiepert
	 * @version 1.2
	 * @see toolbarActions.SaveAction
	 * @see toolbarActions.LoadAction
	 * @see toolbarActions.GenerateAction
	 * @see toolbarActions.ResetAction
	 * @see toolbarActions.MainMenuAction
	 * @see toolbarActions.CloseAction
	 * 
	 */
	public CommonToolbar(final MainFrame mainFrame, final JPanel editPanel, final String whoYouAre){
		setFloatable(false);
		add(new LoadAction((GamePanel)editPanel));
		add(new SaveAction());
		if (whoYouAre.equals("editor")){
			add(seperator());
			add(new GenerateAction((EditorPanel)editPanel));
			add(new ResetAction((EditorPanel)editPanel));
		}
		add(seperator());
		add(new MainMenuAction(mainFrame));
		add(new CloseAction());
		
	}
	
	private JLabel seperator(){
		JLabel l = new JLabel();
		l.setText(" ");
		l.setFont(new Font(" ",Font.PLAIN, 14));
		return l;
	}
}
