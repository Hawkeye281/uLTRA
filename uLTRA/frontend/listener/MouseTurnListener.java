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

		if(_startPoint != null && _endPoint != null)
		{
			if(gg.getCell(super._startPoint).getContent() instanceof LightSource)
			{
				if(super._startPoint.y < super._endPoint.y)
				{
					y_start = super._startPoint.y + 1;
					y_end = super._endPoint.y;
					_direction = BeamDirections.BEAM_DOWN;
				}
				else if(super._startPoint.y == super._endPoint.y)
				{
					y_start = super._startPoint.y;
					y_end = super._endPoint.y;
				}
				else
				{
					y_start = super._endPoint.y;
					y_end = super._startPoint.y - 1;
					_direction = BeamDirections.BEAM_UP;
				}
				
				for(int temp_y = y_start; temp_y <= y_end; temp_y++)
				{
					if(super._startPoint.x < super._endPoint.x)
					{
						x_start = super._startPoint.x + 1;
						x_end = super._endPoint.x;
						_direction = BeamDirections.BEAM_RIGHT;
					}
					else if(super._startPoint.x == super._endPoint.x)
					{
						x_start = super._startPoint.x;
						x_end = super._endPoint.x;
					}
					else
					{
						x_start = super._endPoint.x;
						x_end = super._startPoint.x - 1;
						_direction = BeamDirections.BEAM_LEFT;
					}
					
					for(int temp_x = x_start; temp_x <= x_end; temp_x++)
					{
						Cell c = gg.getCell(temp_x, temp_y);
						
						System.out.println("empty: " + (!c.isEmpty() ? "Nicht leer" : "Ist leer"));
						
						if(!c.isEmpty())
						{
							valid = false;
							break;
						}
					}
				}
	
				if(!super._startPoint.equals(super._endPoint) && valid)
				{
					for(int temp_y = y_start; temp_y <= y_end; temp_y++)
					{
						for(int temp_x = x_start; temp_x <= x_end; temp_x++)
						{
							Cell c = gg.getCell(temp_x, temp_y);
							
							c.setContent(new Beam(_direction));
						}
					}
					
					GamePanel.getGridPanel().resetLayout();
					GamePanel.getTurnList().addTurn(new Turn(super._startPoint, super._endPoint));
				}
			}
		}
	}
}
