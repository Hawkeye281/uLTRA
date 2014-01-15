package listener;

import gamegrid.Beam;
import gamegrid.BeamDirections;
import gamegrid.Cell;
import gamegrid.GameGrid;
import gamegrid.LightSource;
import gamegrid.Turn;

import java.awt.event.MouseEvent;

import panels.GamePanel;

import Controller.GridController;

/**
 * 
 * @author Stephan
 *
 */
public class MouseTurnListener extends AbstractMousePositionListener
{
	private GameGrid gg = GridController.getGameGrid();
	
	@Override
	public void mouseReleased(MouseEvent pEvent)
	{	
		super.mouseReleased(pEvent);
		
		boolean valid = true; 
		
		int x_start = 0, x_end = 0;
		int y_start = 0, y_end = 0;
		BeamDirections _direction = null;

		if(getStartPoint() != null && getEndPoint() != null)
		{
			if(gg.getCell(getStartPoint()).getContent() instanceof LightSource)
			{
				if(getStartPoint().y < getEndPoint().y)
				{
					y_start = getStartPoint().y + 1;
					y_end = getEndPoint().y;
					_direction = BeamDirections.BEAM_DOWN;
				}
				else if(getStartPoint().y == getEndPoint().y)
				{
					y_start = getStartPoint().y;
					y_end = getEndPoint().y;
				}
				else
				{
					y_start = getEndPoint().y;
					y_end = getStartPoint().y - 1;
					_direction = BeamDirections.BEAM_UP;
				}
				
				for(int temp_y = y_start; temp_y <= y_end; temp_y++)
				{
					if(getStartPoint().x < getEndPoint().x)
					{
						x_start = getStartPoint().x + 1;
						x_end = getEndPoint().x;
						_direction = BeamDirections.BEAM_RIGHT;
					}
					else if(getStartPoint().x == getEndPoint().x)
					{
						x_start = getStartPoint().x;
						x_end = getEndPoint().x;
					}
					else
					{
						x_start = getEndPoint().x;
						x_end = getStartPoint().x - 1;
						_direction = BeamDirections.BEAM_LEFT;
					}
					
					for(int temp_x = x_start; temp_x <= x_end; temp_x++)
					{
						Cell c = gg.getCell(temp_x, temp_y);
						
						if(!c.isEmpty())
						{
							valid = false;
							break;
						}
					}
				}
	
				if(!getStartPoint().equals(getEndPoint()) && valid)
				{
					LightSource quelle = ((LightSource) gg.getCell(getStartPoint()).getContent());
					
					for(int temp_y = y_start; temp_y <= y_end; temp_y++)
					{
						for(int temp_x = x_start; temp_x <= x_end; temp_x++)
						{
							if(quelle.getCapacity() > 0)
							{
								Cell c = gg.getCell(temp_x, temp_y);							
								c.setContent(new Beam(_direction));
								quelle.setCapacity(quelle.getCapacity() - 1);
							}
						}
					}
					
					GamePanel.getGridPanel().resetLayout();
					GamePanel.getTurnList().addTurn(new Turn(getStartPoint(), getEndPoint()));
				}
			}
		}
	}
}
