package panels;

import gamegrid.Beam;
import gamegrid.Cell;
import gamegrid.CellContent;
import gamegrid.GameGrid;
import gamegrid.ImageResources;
import gamegrid.LightSource;
import gamegrid.ImageResources.ImageNames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.GridController;

/**
 * 
 * @author Stephan
 *
 */
public class GridPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private int _startFieldX = 0;
	private int _startFieldY = 0;
	
	private int _endFieldX = 0;
	private int _endFieldY = 0;
	
	private GridLayout _layout;
	private GameGrid _gridCont;
	
	private static Dimension _size = new Dimension(500,500);

	public GridPanel()
	{
		_gridCont = GridController.initGameGrid();
		_layout = new GridLayout(_gridCont.getHeight(), _gridCont.getWidth());
		_size = (_gridCont.getHeight()>_gridCont.getWidth())?
				new Dimension(500/_gridCont.getHeight()*_gridCont.getWidth(),500) :
					new Dimension(500,500/_gridCont.getWidth()*_gridCont.getHeight());
		
		this.setLayout(_layout);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.setPreferredSize(_size);
		resetLayout();
		this.setVisible(true);
	}
	
	public void resetLayout()
	{
		this.removeAll();
		JLabel tempLabel;
		Cell c = null;
		CellContent cc = null;
		ImageIcon direction = null;
		
		for(int y = 0; y < _gridCont.getHeight(); y++)
		{
			for(int x = 0; x < _gridCont.getWidth(); x++)
			{
				c = _gridCont.getCell(x, y);
				cc = c.getContent();
				direction = null;
				
				if(cc instanceof LightSource)
				{
					try
					{
						direction = ImageResources.getImage(ImageNames.getSource(c));
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
					if(direction != null)
					{
						tempLabel = new JLabel(direction);
						tempLabel.setIconTextGap(-(direction.getIconWidth()/2)-4);
					}
					else
					{
						tempLabel = new JLabel();
					}

					tempLabel.setForeground(Color.WHITE);	
					tempLabel.setText(Integer.toString(((LightSource)cc).getCapacity()));
				}
				else if(cc instanceof Beam)
				{
					switch(((Beam)cc).getDirection())
					{
						case BEAM_UP:
							if(!((Beam)cc).isBeamEnd())
							{
								direction = ImageResources.getImage(ImageNames.ARROW_UP);
							}
							else
							{
								direction = ImageResources.getImage(ImageNames.ARROW_UP_END);
							}
							break;
						case BEAM_RIGHT:
							if(!((Beam)cc).isBeamEnd())
							{
								direction = ImageResources.getImage(ImageNames.ARROW_RIGHT);
							}
							else
							{
								direction = ImageResources.getImage(ImageNames.ARROW_RIGHT_END);
							}
							break;
						case BEAM_DOWN:
							if(!((Beam)cc).isBeamEnd())
							{
								direction = ImageResources.getImage(ImageNames.ARROW_DOWN);
							}
							else
							{
								direction = ImageResources.getImage(ImageNames.ARROW_DOWN_END);
							}
							break;
						case BEAM_LEFT:
							if(!((Beam)cc).isBeamEnd())
							{
								direction = ImageResources.getImage(ImageNames.ARROW_LEFT);
							}
							else
							{
								direction = ImageResources.getImage(ImageNames.ARROW_LEFT_END);
							}
							break;
					}

					tempLabel = new JLabel(direction);
				}
				else
				{
					tempLabel = new JLabel("");
				}
				
				tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
				tempLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				
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
