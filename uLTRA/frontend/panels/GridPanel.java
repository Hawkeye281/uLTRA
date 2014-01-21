package panels;

import gamegrid.Beam;
import gamegrid.CellContent;
import gamegrid.GameGrid;
import gamegrid.LightSource;

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
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setPreferredSize(_size);
		resetLayout();
		this.setVisible(true);
	}
	
	public void resetLayout()
	{
		this.removeAll();
		JLabel tempLabel;
		CellContent cc = null;
		ImageIcon direction = null;
		String img_end = "";
		
		for(int y = 0; y < _gridCont.getHeight(); y++)
		{
			for(int x = 0; x < _gridCont.getWidth(); x++)
			{
				cc = _gridCont.getCell(x, y).getContent();
				img_end = "";
				
				if(cc instanceof LightSource)
				{
					tempLabel = new JLabel(Integer.toString(((LightSource)_gridCont.getCell(x, y).getContent()).getCapacity()));
				}
				else if(cc instanceof Beam)
				{
					if(((Beam) cc).isBeamEnd())
					{
						img_end = "_end";
					}
					
					switch(((Beam)cc).getDirection())
					{
						case BEAM_UP:
							direction = new ImageIcon("../uLTRA/Documents/images/icons/arrow_up" + img_end + ".png");
							break;
						case BEAM_RIGHT:
							direction = new ImageIcon("../uLTRA/Documents/images/icons/arrow_right" + img_end + ".png");
							break;
						case BEAM_DOWN:
							direction = new ImageIcon("../uLTRA/Documents/images/icons/arrow_down" + img_end + ".png");
							break;
						case BEAM_LEFT:
							direction = new ImageIcon("../uLTRA/Documents/images/icons/arrow_left" + img_end + ".png");
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
