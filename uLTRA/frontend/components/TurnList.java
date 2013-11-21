package components;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JList;

/**
 * 
 * @author Stephan
 *
 */
public class TurnList extends JList<Object> implements Observer
{
	//TODO Kann erst richtig implementiert werden, wenn die Spielzug Objekte implementiert wurden 
	
	private static final long serialVersionUID = 1L;
	private List<Object> _turns;
	
	public TurnList()
	{
		super();
		this.setAutoscrolls(true);
		this.setMinimumSize(new Dimension(200, 100));
		this.setSize(new Dimension(200, 100));
		this.setPreferredSize(new Dimension(200, 100));
		this.setVisible(true);
		
		_turns = new ArrayList<Object>();
		_turns.add("Foo");
		_turns.add("Bar");
		
		resetList(_turns);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable pObservable, Object pNewTurns)
	{
		if(pObservable.hasChanged())
		{
			if(pNewTurns != null)
			{
				if(pNewTurns instanceof List)
				{
					resetList((List<Object>) pNewTurns);
				}
			}
		}
	}
	
	private void resetList(List<Object> pNewTurns)
	{
		_turns = pNewTurns;
		this.setListData(_turns.toArray());
	}
}
