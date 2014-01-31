package history;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class TurnListRenderer extends JLabel implements ListCellRenderer<Command> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 172994472887264128L;

	public TurnListRenderer()
	{
		setOpaque(true);
	}
	
	@Override
	public Component getListCellRendererComponent(
			JList<? extends Command> list, Command value, int index,
			boolean isSelected, boolean cellHasFocus)
	{
		setText(value.toString());
		
		System.out.println("lastCommand : " + value.lastCommand());
		System.out.println("undone : " + value.undone());
		System.out.println("executed : " + value.executed());
		
		if(value.executed())
		{
			this.setForeground(Color.BLACK);
			this.setBackground(Color.WHITE);
		}
		if(value.undone())
		{
			this.setForeground(Color.LIGHT_GRAY);
			this.setBackground(Color.DARK_GRAY);
		}
		if(value.lastCommand())
		{
			this.setForeground(Color.WHITE);
			this.setBackground(Color.RED);
		}


		
		return this;
	}

}
