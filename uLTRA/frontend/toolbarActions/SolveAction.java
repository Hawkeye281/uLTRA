package toolbarActions;

import gamegrid.GameGrid;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import solver.SmartSolver;

public class SolveAction extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			SmartSolver.solve(GameGrid.getInstance());
		} catch (Exception e) {
			// No game has been loaded yet
			//TODO replace by appropriate behavior
			System.out.println(e +" : " + e.getMessage());
			System.out.println("No game loaded yet. Load game and repeat.");
		}

	}

}
