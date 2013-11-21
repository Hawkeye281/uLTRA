package toolbar;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JToolBar;

import frames.MainFrame;

public class CommonToolbar extends JToolBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CommonToolbar(final MainFrame mainFrame){
		setFloatable(false);
		add(new AbstractAction(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				putValue(Action.NAME, "Hauptmenü");
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.getGameFrame().setVisible(false);
				mainFrame.getMenuFrame().setVisible(true);
			}
		});
		add(new AbstractAction(){
			{
				putValue(Action.NAME, "Beenden");
			}
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		
	}
}
