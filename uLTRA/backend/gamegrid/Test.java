package gamegrid;

public class Test {

	public static void main(String[] args) {
		GameGrid pGrid = GameGrid.getInstance(5, 5);
		pGrid.getCell(0, 0).setContent(new LightSource(5));
		

	}

}
