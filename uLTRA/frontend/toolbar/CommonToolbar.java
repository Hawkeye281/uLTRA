package toolbar;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JToolBar;

import panels.EditorPanel;

import dialogs.FieldSizeDialog;

import frames.MainFrame;

public class CommonToolbar extends JToolBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CommonToolbar(final MainFrame mainFrame, final EditorPanel editPanel, final String whoYouAre){
		setFloatable(false);
		add(new AbstractAction(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			{
				putValue(Action.NAME, "laden");
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		add(new AbstractAction(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			{
				putValue(Action.NAME, "speichern");
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		if (whoYouAre.equals("editor")){
			add(new AbstractAction(){

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				{
					putValue(Action.NAME, "Feld generieren");
				}

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					@SuppressWarnings("unused")
					FieldSizeDialog fsd = new FieldSizeDialog(editPanel);
				}
				
			});
		}
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
				if(whoYouAre.equals("editor")){
					mainFrame.setEditorVisibility(false);
				}
				else{
					mainFrame.setGameVisibility(false);
				}
				mainFrame.setMenuVisibility(true);
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
