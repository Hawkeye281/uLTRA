package stephan;

import Controller.GridController;
import gamegrid.Cell;
import gamegrid.EmptyContent;
import gamegrid.GameGrid;

public class ContentConverter
{
	public static void main(String[] args)
	{
		String spielname = "111";
		
		System.out.println("Dump beams from savegame \"" + spielname + "\".puzzle");
		
		Cell c = null;
		GameGrid grid = GridController.loadGame(spielname + ".puzzle");
		
		for(int y = 0; y < grid.getHeight(); y++)
		{
			for(int x = 0; x < grid.getWidth(); x++)
			{
				c = grid.getCell(x, y);
				if(c.getContent() == null || c.isBeam())
				{
					c.setContent(new EmptyContent());
					
					System.out.println("[" + x + ":" + y + "] : " + c.isEmpty());
				}
			}
		}
		
		GridController.saveGame(grid, "empty_" + spielname);
		System.out.println("Clean savegame saved as \"empty_" + spielname + ".puzzle\"");
	}
}
