package history;

import javax.swing.DefaultListModel;

public class TurnListModel extends DefaultListModel<Command> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3965445604971611488L;

	private int active;
	
	public TurnListModel()
	{
		active = 0;
	}
	
	public void addElement(Command c)
	{
		// not inserting at the end, delete all elements behind the active one,
		// then insert new element at end of list
		// this is necessary because inserting a turn in the middle of the list might make future turns impossible
		if(size() > 0 && active != size()-1)
			removeRange(active+1, size()-1);
		
		super.addElement(c);
		get(active).setLastCommand(false);
		c.setLastCommand(true);
		active = size()-1;
	}
	
	public void undo()
	{
		if(size()==0)
			return;
		
		get(active).undo();
		get(active).setLastCommand(false);
		if(active > 0)
		{
			active--;
			get(active).setLastCommand(true);
		}
		fireContentsChanged(this, active, active+1);
	}
	
	public void redo()
	{
		if(size()==0)
			return;
		
		if(get(active).lastCommand())
		{
			get(active).setLastCommand(false);
			if(active < size()-1)
			{
				active++;
				get(active).execute();
			}
			get(active).setLastCommand(true);
		}
		else
		{
			get(active).setLastCommand(true);
			get(active).execute();
		}
			
	}
}
