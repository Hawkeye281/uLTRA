package components;

import gamegrid.Turn;

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
 *
 */
public class TurnList extends JList<Object> implements Observer
{
	//TODO Kann erst richtig implementiert werden, wenn die Spielzug Objekte implementiert wurden 
	
	private static final long serialVersionUID = 1L;
	private List<Turn> _turns;
	
	public TurnList()
	{
		super();
		this.setAutoscrolls(true);
		this.setMinimumSize(new Dimension(200, 100));
		this.setSize(new Dimension(200, 100));
		this.setPreferredSize(new Dimension(200, 100));
		this.setVisible(true);
		
		_turns = new ArrayList<Turn>();
		
		resetList(_turns);
		
	}

	@Override
	public void update(Observable pObservable, Object pNewTurn)
	{
		if(pNewTurn != null)
		{
			if(pNewTurn instanceof Turn)
			{
				_turns.add((Turn) pNewTurn);
				resetList(_turns);
			}
		}
	}
	
	private void resetList(List<Turn> pNewTurns)
	{
		_turns = pNewTurns;
		this.setListData(_turns.toArray());
	}
}
