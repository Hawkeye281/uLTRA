package listener;

import gamegrid.Beam;
import gamegrid.BeamDirections;
import gamegrid.Cell;
import gamegrid.GameGrid;
import gamegrid.LightSource;

import java.awt.event.MouseEvent;

import panels.GamePanel;

import Controller.GameController;
import Controller.GridController;

public class MouseTurnListener extends AbstractMousePositionListener
{
	private GridController gridCont = new GridController();
	private GameGrid gg = gridCont.getGameGrid();
	private GameController gC = new GameController();
	
	@Override
	public void mouseReleased(MouseEvent pEvent)
	{	
		super.mouseReleased(pEvent);
		
		int x_start = 0, x_end = 0;
		int y_start = 0, y_end = 0;
		BeamDirections _direction = null;

//		System.out.println(super._startPoint.toString());
//		System.out.println(super._endPoint.toString());
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
				y_end = super._startPoint.y;
				_direction = BeamDirections.BEAM_UP;
			}
			
			for(; y_start <= y_end; y_start++)
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
					x_end = super._startPoint.x;
					_direction = BeamDirections.BEAM_LEFT;
				}
				
				for(; x_start <= x_end; x_start++)
				{
					Cell c = gg.getCell(x_start, y_start);
					
					if(c.getContent() != null)
					{
						if(!(c.getContent() instanceof LightSource) && c.getContent() instanceof Beam)
						{
							//TODO Command-Pattern undo einfügen
						}
					}
					else
					{
						c.setContent(new Beam(_direction));
					}
				}
			}

			if(!super._startPoint.equals(super._endPoint))
			{
				gC.addTurn(super._startPoint, super._endPoint);
				GamePanel.getRayGrid().resetLayout();
			}
		}
	}
}
