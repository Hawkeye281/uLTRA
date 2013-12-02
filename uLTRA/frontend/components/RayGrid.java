package components;

import gamegrid.Beam;
import gamegrid.CellContent;
import gamegrid.GameGrid;
import gamegrid.LightSource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import listener.MouseTurnListener;

/**
 * 
 * @author Stephan
 *
 */
public class RayGrid extends JPanel
{
	private static final long serialVersionUID = 1L;
	private GameGrid _grid;
	
	private int _startFieldX = 0;
	private int _startFieldY = 0;
	
	private int _endFieldX = 0;
	private int _endFieldY = 0;
	
	private GridLayout _layout;
	
	private static Dimension _size = new Dimension(592,539);

	public RayGrid(GameGrid pGrid)
	{
		_grid = pGrid;
		_layout = new GridLayout(_grid.getHeight(), _grid.getWidth());
		
		this.setLayout(_layout);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setPreferredSize(_size);
		resetLayout();
		this.setVisible(true);

		this.addMouseListener(new MouseTurnListener());
	}
	
	public void resetLayout()
	{
		this.removeAll();
		JLabel tempLabel;
		CellContent cc = null;
		String direction = "";
		
		for(int y = 0; y < _grid.getHeight(); y++)
		{
			for(int x = 0; x < _grid.getWidth(); x++)
			{
				cc = _grid.getCell(x, y).getContent();
				
				if(cc instanceof LightSource)
				{
					tempLabel = new JLabel(Integer.toString(((LightSource)_grid.getCell(x, y).getContent()).getCapacity()));
				}
				else if(cc instanceof Beam)
				{
					switch(((Beam)cc).getDirection())
					{
						case BEAM_UP:
							direction = "^";
							break;
						case BEAM_RIGHT:
							direction = ">";
							break;
						case BEAM_DOWN:
							direction = "v";
							break;
						case BEAM_LEFT:
							direction = "<";
							break;
					}

					tempLabel = new JLabel(direction);
				}
				else
				{
					tempLabel = new JLabel("");
				}
				
				tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
				tempLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				this.add(tempLabel);
			}
		}
	}
	
	public Point getStartingPosition()
	{
		return new Point(_startFieldX, _startFieldY);
	}
	
	public Point getEndingPosition()
	{
		return new Point(_endFieldX, _endFieldY);
	}
	
	public static Dimension getGridSize()
	{
		return _size;
	}
}
