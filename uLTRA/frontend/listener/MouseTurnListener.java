package listener;

import gamegrid.Beam;
import gamegrid.BeamDirections;
import gamegrid.Cell;
import gamegrid.EmptyContent;
import gamegrid.GameGrid;
import gamegrid.LightSource;
import gamegrid.Turn;

import java.awt.Point;
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

	/*
	 * (non-Javadoc)
	 * @see listener.AbstractMousePositionListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent pEvent)
	{
		super.mouseReleased(pEvent);
		
		int xStart, yStart; // coordinates where mouse was pressed
		int xEnd, yEnd; // coordinates where mouse was released
		
		BeamDirections direction;
		
		xStart = (int)getStartPoint().getX();
		yStart = (int)getStartPoint().getY();
		
		xEnd = (int)getEndPoint().getX();
		yEnd = (int)getEndPoint().getY();
		
		// check if move starts at a lightsource, is either vertical or horizontal and spans at least two fields
		// abort if invalid move
		if(!(gg.getCell(xStart, yStart).getContent() instanceof LightSource) || !((yStart==yEnd)&&(xStart!=xEnd)) && !((yStart!=yEnd)&&(xStart==xEnd)))
			return;

		if(yStart == yEnd)
			// beam is horizontal
			direction = xStart < xEnd ? BeamDirections.BEAM_LEFT : BeamDirections.BEAM_RIGHT;
		else // beam is vertical
			direction = yStart < yEnd ? BeamDirections.BEAM_DOWN : BeamDirections.BEAM_UP;
		
		// check whether the beam can be drawn as far as indicated by the mouse movement
		LightSource ls = (LightSource)gg.getCell(xStart, xEnd).getContent();
		int distance;
		switch(direction)
		{
		case BEAM_LEFT:
		case BEAM_RIGHT:
			distance = Math.abs(xStart-xEnd);
			break;
		default:
			distance = Math.abs(yStart-yEnd);
		}
		if(distance > ls.getRemainingCapacity())
			// adjust beam length to possible maximum
		
		
		
		
		
		
		/*
		 * alles ab hier ist mist und sollte verbrannt werden
		 */
		
		
		boolean valid = true;	// Ist der Zug okay?
		boolean validTurn = true; // Soll in die TurnList geschrieben werden?
		boolean keepLooking = false; // Ist das eine Rückgängigaktion (Dient zum "überbrücken" der Schleife, dass die Beams der kompletten Richtung gelöscht werden, selbst wenn man die Reihe per Rechtsklick nicht komplett gezogen hat) 
		
		int x_start = 0, x_end = 0;
		int y_start = 0, y_end = 0;
		BeamDirections _direction = null;

		if(getStartPoint() != null && getEndPoint() != null)
		{
			if(gg.getCell(getStartPoint()).getContent() instanceof LightSource)
			{
				// Ermittlung der Strahlausrichtung (Y-Achse)
				
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
					// Ermittlung der Strahlenausrichtung (X-Achse)
					
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
										
					//Ist ein Feld schon belegt? Wenn ja, brich ab.
					for(int temp_x = x_start; temp_x <= x_end; temp_x++)
					{
						Cell c = gg.getCell(temp_x, temp_y);
						
						if(!c.isEmpty())
						{
							if(pEvent.getButton() != MouseEvent.BUTTON3)
							{
								valid = false;
								break;
							}
						}
					}
				}
	
				if(!getStartPoint().equals(getEndPoint()) && valid)
				{
					LightSource quelle = ((LightSource) gg.getCell(getStartPoint()).getContent());
					
					// Die Lichtquelle hat keinen Saft mehr
					if(quelle.getCapacity() == 0)
					{
						validTurn = false;
					}

					Cell c = null;
					boolean beamEnd = false;
					Point endPoint = getEndPoint();

					int temp_x = getStartPoint().x;
					int temp_y = getStartPoint().y;

					// Solange nicht der Endpunkt des Zuges oder im Löschfall nicht das Ende des Strahls erreicht worden ist 
					while((temp_x != getEndPoint().x) != (temp_y != getEndPoint().y) != keepLooking)
					{
						c = null;
						endPoint = new Point(temp_x, temp_y);
						
						switch(_direction)
						{							
							case BEAM_UP:
								if(temp_y > 0)
									c = gg.getCell(temp_x, --temp_y);
								break;
							case BEAM_RIGHT:
								if((temp_x + 1) < gg.getWidth())
									c = gg.getCell(++temp_x, temp_y);
								break;
							case BEAM_DOWN:
								if((temp_y + 1) < gg.getHeight())
									c = gg.getCell(temp_x, ++temp_y);
								break;
							case BEAM_LEFT:
								if(temp_x > 0)
									c = gg.getCell(--temp_x, temp_y);
								break;
						}

						if(c != null)
						{
							// Strahl zeichnen
							if(pEvent.getButton() == MouseEvent.BUTTON1)
							{
								if(quelle.getCapacity() > 0)
								{
									if(c.isEmpty())
									{
										if((temp_x == getEndPoint().x) && (temp_y == getEndPoint().y) || quelle.getCapacity() == 1)
										{
											beamEnd = true;
										}
										else
										{
											beamEnd = false;
										}
										
										c.setContent(new Beam(_direction, beamEnd));
										quelle.setCapacity(quelle.getCapacity() - 1);
									}
									else
									{
										keepLooking = false;
										break;
									}
								}
								else
								{
									keepLooking = false;
									break;
								}
							}
							//Strahl löschen
							else if(pEvent.getButton() == MouseEvent.BUTTON3)
							{
								if(c.isBeam())
								{
									if(((Beam)c.getContent()).getDirection() == _direction)
									{
										c.setContent(new EmptyContent());
										quelle.setCapacity(quelle.getCapacity() + 1);
										
										if((temp_x == getEndPoint().x) && (temp_y == getEndPoint().y))
										{
											keepLooking = true;
										}
										else
										{
											keepLooking = false;
										}
									}
									else
									{
										keepLooking = true;
									}
								}
								else
								{
									keepLooking = true;
								}
							}
						}
						else
						{
							break;
						}
					}
					
					if(validTurn)
					{
						GamePanel.getGamePanel().getTurnList().addTurn(new Turn(getStartPoint(), endPoint));
					}
				}
			}
			GamePanel.getGamePanel().getGridPanel().resetLayout();
			GamePanel.getGamePanel().refresh();
		}
	}
}
