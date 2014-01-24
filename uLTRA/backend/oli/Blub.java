package oli;

import gamegrid.*;

public class Blub {

	public static void main(String[] args) {
		
		GameGrid grid = GameGrid.getInstance(5, 5);
		grid.setCell(new Cell(0, 0, new LightSource(5)), 0, 0);
		
		Validator val = Validator.getInstance(grid);
		grid.setCell(new Cell(0, 0, new Beam(BeamDirections.BEAM_DOWN)), 0, 0);
		
//		System.out.println("Done");
		
	}

}
