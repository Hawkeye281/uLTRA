package components;

import gamegrid.Turn;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

/**
 * 
 * @author Stephan
 * 
 *
 */
public class TurnList extends JList<Object>
{
	private static final long serialVersionUID = 1L;
	private List<Turn> _turns;
	
	private final Dimension _size = new Dimension(200, 100);
	
	public TurnList()
	{
		super();
		this.setAutoscrolls(true);
		this.setMinimumSize(_size);
		this.setSize(_size);
		this.setBackground(new Color(255,255,255,130));
		this.setPreferredSize(_size);
		this.setVisible(true);
		
		_turns = new ArrayList<Turn>();
		
		resetList(_turns);
		
	}
	
	private void resetList(List<Turn> pNewTurns)
	{
		_turns = pNewTurns;
		this.setListData(_turns.toArray());
	}
	
	public void addTurn(Turn pNewTurn)
	{
		_turns.add(pNewTurn);
		resetList(_turns);
	}
}
