package gamegrid;

import Controller.GridController;

/**
 * Klasse zum Entfernen von {@link Beam}<code>s</code> aus einem {@link GameGrid}.
 * 
 * @author Stephan
 *
 */
public class ContentConverter
{
	/**
	 * Löscht alle {@link Beam}<code>s</code> aus einem {@link GameGrid}
	 * 
	 * @param pSpielname das Spielfeld mit enthaltenen <code>Beams</code>
	 * @return das Spielfeld ohne <code>Beams</code>
	 */
	public static GameGrid clearGame()
	{
		Cell c = null;
		GameGrid grid = GridController.getGameGrid();
		
		for(int y = 0; y < grid.getHeight(); y++)
		{
			for(int x = 0; x < grid.getWidth(); x++)
			{
				c = grid.getCell(x, y);
				if(c.getContent() == null || c.isBeam())
				{
					c.setContent(new EmptyContent());
				}
				else if(c.isLightSource())
				{
					((LightSource)c.getContent()).clearBeamList();
				}
			}
		}
		return grid;
	}
}
