package solver;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import gamegrid.GameGrid;

public class GameFinished {

	public static boolean isGameFinished(){
		try {
			GameGrid grid = GameGrid.getInstance();
			
			for(int i = 0; i < grid.getHeight(); i++){
				for(int j = 0; j < grid.getWidth(); j++){
					if(grid.getCell(j, i).isEmpty())
						return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

	public static void createCongratulationsDialog() {
		ImageIcon icon = new ImageIcon("../uLTRA/Documents/images/icons/congratulations.png");
		String message = "Herzlichen Gl�ckwunsch! \n" +
				"Sie haben das Puzzle vollst�ndig und korrekt gel�st!";
		JOptionPane.showMessageDialog(null, message, "Gl�ckwunsch!", JOptionPane.INFORMATION_MESSAGE, icon);
	}
}
