package gamegrid;

import Controller.GridController;

public class ContentConverter
{
	public static GameGrid clearGame(String pSpielname)
	{
//		System.out.println("Dump beams from savegame: \"" + pSpielname + "\"");
		
		Cell c = null;
		GameGrid grid = GridController.loadGame(pSpielname);
		
		for(int y = 0; y < grid.getHeight(); y++)
		{
			for(int x = 0; x < grid.getWidth(); x++)
			{
				c = grid.getCell(x, y);
				if(c.getContent() == null || c.isBeam())
				{
					c.setContent(new EmptyContent());
				}
			}
		}

//		System.out.println("Removed all beams from savegame: \"" + pSpielname + "\"");
		return grid;
	}
}
