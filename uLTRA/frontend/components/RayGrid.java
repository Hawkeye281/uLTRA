package components;

import gamegrid.GameGrid;
import gamegrid.LightSource;
import gamegrid.Turn;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.GameController;

import panels.GamePanel;

/**
 * 
 * @author Stephan
 *
 */
public class RayGrid extends JPanel
{
	private static final long serialVersionUID = 1L;
	private GameGrid _grid;
	
	private int _windowHeight = 0;
	private int _windowWidth = 0;
	
	private int _fieldHeight = 0;
	private int _fieldWidth = 0;
	
	private int _startFieldX = 0;
	private int _startFieldY = 0;
	
	private int _endFieldX = 0;
	private int _endFieldY = 0;
	
	private GridLayout _layout;

	public RayGrid(GameGrid pGrid)
	{
		_grid = pGrid;
		_layout = new GridLayout(_grid.getWidth(), _grid.getHeight());
		
		this.setLayout(_layout);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		resetLayout();
		this.setVisible(true);

		this.addMouseListener(new MouseListener()
		{	
			@Override
			public void mouseReleased(MouseEvent e)
			{
				int endX = e.getX();
				int endY = e.getY();
				boolean inField = true;
				
				if(endX > 0 && endX < _windowWidth)
				{
					endX /= _fieldWidth;
				}
				else
				{
					inField = false;
				}
				
				if(endX > 0 && endY < _windowHeight)
				{
					endY /= _fieldHeight;
				}
				else
				{
					inField = false;
				}
				
				if(inField)
				{
					_endFieldX = endX;
					_endFieldY = endY;

					GameController gC = new GameController();
					gC.addTurn(getStartingPosition(), getEndingPosition());
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(_windowWidth == 0 || _windowHeight == 0)
				{
					_windowWidth = e.getComponent().getSize().width;
					_windowHeight = e.getComponent().getSize().height;
					
					_fieldWidth = (_windowWidth /_grid.getWidth());
					_fieldHeight = (_windowHeight / _grid.getHeight());
				}
				
				int startX = e.getX();
				int startY = e.getY();
				boolean inField = true;
				
				if(startX > 0 && startX < _windowWidth)
				{
					startX /= _fieldWidth;
				}
				else
				{
					inField = false;
				}
				
				if(startY > 0 && startY < _windowHeight)
				{
					startY /= _fieldHeight;
				}
				else
				{
					inField = false;
				}
				
				if(inField)
				{
					_startFieldX = startX;
					_startFieldY = startY;
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				
			}
		});
	}
	
	private void resetLayout()
	{
		JLabel tempLabel;
		for(int y = 0; y < _grid.getHeight(); y++)
		{
			for(int x = 0; x < _grid.getWidth(); x++)
			{
				if(_grid.getCell(x, y).getContent() instanceof LightSource)
				{
					tempLabel = new JLabel(Integer.toString(((LightSource)_grid.getCell(x, y).getContent()).getCapacity()));
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
}
