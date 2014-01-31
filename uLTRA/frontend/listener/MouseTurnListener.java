package listener;

import gamegrid.Beam;
import gamegrid.BeamDirections;
import gamegrid.GameGrid;
import gamegrid.LightSource;
import history.AddBeamCommand;
import history.Command;
import history.RemoveBeamCommand;
import history.TurnListModel;

import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;

import panels.GamePanel;
import solver.GameFinished;
import Controller.GridController;

/**
 * 
 * @author Stephan
 *
 */
public class MouseTurnListener extends AbstractMousePositionListener
{
	private GameGrid gg = GridController.getGameGrid();
	
	private TurnListModel model = (TurnListModel) GamePanel.getGamePanel().getTurnList().getModel();

	private boolean log = false;
	/*
	 * (non-Javadoc)
	 * @see listener.AbstractMousePositionListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent pEvent)
	{
		if(pEvent.getButton() == MouseEvent.BUTTON1){
			addBeam(pEvent);
			if(GameFinished.isGameFinished())
				GameFinished.createCongratulationsDialog();
		}
		else if(pEvent.getButton() == MouseEvent.BUTTON3)
			removeBeam(pEvent);
			
		
	}
	
	private void addBeam(MouseEvent pEvent)
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
		if(!(gg.getCell(xStart, yStart).isLightSource()) || !((yStart==yEnd)&&(xStart!=xEnd)) && !((yStart!=yEnd)&&(xStart==xEnd)))
			return;

		if(yStart == yEnd)
			// beam is horizontal
			direction = xStart < xEnd ? BeamDirections.BEAM_RIGHT : BeamDirections.BEAM_LEFT;
		else // beam is vertical
			direction = yStart < yEnd ? BeamDirections.BEAM_DOWN : BeamDirections.BEAM_UP;
		
		// check whether the beam can be drawn as far as indicated by the mouse movement
		LightSource ls = (LightSource)gg.getCell(xStart, yStart).getContent();
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
		if(log)System.out.println("distance: " + distance);
		if(distance > ls.getRemainingCapacity())
		{
			int adjustment = distance - ls.getRemainingCapacity();
			// adjust beam length to possible maximum
			switch(direction)
			{
			case BEAM_UP:
				yEnd += adjustment;
				break;
			case BEAM_DOWN:
				yEnd -= adjustment;
				break;
			case BEAM_LEFT:
				xEnd += adjustment;
				break;
			case BEAM_RIGHT:
				xEnd -= adjustment;
				break;
			}
		}
		
		// step along beam path to find any obstructions (end of gamegrid, lightsource or other beam)
		int freeFields = 0;
		int activeCoord = 0;
		boolean noBeam = false;
		switch(direction)
		{
		case BEAM_UP:
			// walk up, decreasing y coordinate
			activeCoord = yStart-1;
			if(log)System.out.println("xStart/yStart: " + xStart + "/" + yStart);
			if(log)System.out.println("start at: " + xStart + "/" + activeCoord);
			while(activeCoord >= 0 && gg.getCell(xStart, activeCoord).isEmpty() && activeCoord >= yEnd)
			{
				if(log)System.out.println("check: " + xStart + "/" + activeCoord);
				freeFields++; // count number of free fields
				activeCoord--; // move up
			}
			activeCoord--; // adjust to last free field
			noBeam = freeFields == 0;
			if(yStart-freeFields > yEnd) // possible beam shorter than clicked beam?
				yEnd = yStart-freeFields;
			break;
		case BEAM_DOWN:
			// walk down, increasing y coordinate
			activeCoord = yStart+1;
			if(log)System.out.println("xStart/yStart: " + xStart + "/" + yStart);
			if(log)System.out.println("start at: " + xStart + "/" + activeCoord);
			while(activeCoord < gg.getHeight() && gg.getCell(xStart, activeCoord).isEmpty() && activeCoord <= yEnd)
			{
				if(log)System.out.println("check: " + xStart + "/" + activeCoord);
				freeFields++;
				activeCoord++; // move down
			}
			activeCoord--; // adjust to last free field
			noBeam = freeFields == 0;
			if(yStart+freeFields < yEnd) // possible beam shorter than clicked beam?
				yEnd = yStart+freeFields;
			break;
		case BEAM_LEFT:
			// walk left, decreasing x coordinate
			activeCoord = xStart-1;
			if(log)System.out.println("xStart/yStart: " + xStart + "/" + yStart);
			if(log)System.out.println("start at: " + activeCoord + "/" + yStart);
			while(activeCoord >= 0 && gg.getCell(activeCoord, yStart).isEmpty() && activeCoord >= xEnd)
			{
				if(log)System.out.println("check: " + activeCoord + "/" + yStart);
				freeFields++;
				activeCoord--;
			}
			activeCoord--; // adjust to last free field
			noBeam = freeFields == 0;
			if(xStart-freeFields > xEnd) // possible beam shorter than clicked beam?
				xEnd = xStart-freeFields;
			break;
		case BEAM_RIGHT:
			// walk right, increasing x coordinate
			activeCoord = xStart+1;
			if(log)System.out.println("xStart/yStart: " + xStart + "/" + yStart);
			if(log)System.out.println("start at: " + activeCoord + "/" + yStart);
			while(activeCoord < gg.getWidth() && gg.getCell(activeCoord, yStart).isEmpty() && activeCoord <= xEnd)
			{
				if(log)System.out.println("check: " + activeCoord + "/" + yStart);
				freeFields++;
				activeCoord++;
			}
			activeCoord--; // adjust to last free field
			noBeam = freeFields == 0;
			if(xStart+freeFields < xEnd)
				xEnd = xStart+freeFields;
			break;
		}
		
		if(log)System.out.println("noBeam: " + noBeam);
		if(log)System.out.println("freeFields: " + freeFields);
		if(log)System.out.println("activeCoord: " + activeCoord);
		if(log)System.out.println("xEnd: " + xEnd);
		if(log)System.out.println("yEnd: " + yEnd);
		if(log)System.out.println("");
		
		if(noBeam) // abort if no beam can be drawn
			return;
		
		// xStart,yStart contain coordinates of lightsource
		// xEnd,yEnd contain coordinates of last beam element
		try {
			AddBeamCommand abc = new AddBeamCommand(xStart, yStart, xEnd, yEnd, direction);
			abc.execute();
			model.addElement(abc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void removeBeam(MouseEvent pEvent)
	{
		super.mouseReleased(pEvent);
		
		int xClick = (int)getEndPoint().getX();
		int yClick = (int)getEndPoint().getY();
		
		// remove nothing if not clicked on beam
		if(!gg.getCell(xClick, yClick).isBeam())
			return;
		
		BeamDirections direction = ((Beam)gg.getCell(xClick, yClick).getContent()).getDirection();
		LightSource lightSource = ((Beam)gg.getCell(xClick, yClick).getContent()).getRemLightSource();
		
		// walk in beam direction to find beam end
		// then get first beam element from lightsource and direction
		int xEnd, yEnd;
		int xStart, yStart;
		int activeCoordinate;
		switch(direction)
		{
		case BEAM_UP:
			// walk up until end of beam found
			activeCoordinate = yClick;
			while( !((Beam)gg.getCell(xClick, activeCoordinate).getContent()).isBeamEnd() )
				activeCoordinate--;
			xEnd = xClick;
			yEnd = activeCoordinate;
			// cell above lightsource is first beam element
			xStart = xClick;
			activeCoordinate = yClick;
			while( !gg.getCell(xClick, activeCoordinate).isLightSource() )
				activeCoordinate++;
			yStart = activeCoordinate-1;
			break;
			
		case BEAM_DOWN:
			// walk down until end of beam found
			activeCoordinate = yClick;
			while( !((Beam)gg.getCell(xClick, activeCoordinate).getContent()).isBeamEnd() )
				activeCoordinate++;
			xEnd = xClick;
			yEnd = activeCoordinate;
			// cell below lightsource is first beam element
			xStart = xClick;
			activeCoordinate = yClick;
			while( !gg.getCell(xClick, activeCoordinate).isLightSource() )
				activeCoordinate--;
			yStart = activeCoordinate+1;
			break;
			
		case BEAM_LEFT:
			// walk left until end of beam found
			activeCoordinate = xClick;
			while( !((Beam)gg.getCell(activeCoordinate, yClick).getContent()).isBeamEnd() )
				activeCoordinate--;
			xEnd = activeCoordinate;
			yEnd = yClick;
			// cell left of lightsource is first beam element
			activeCoordinate = xClick;
			while( !gg.getCell(activeCoordinate, yClick).isLightSource() )
				activeCoordinate++;
			xStart = activeCoordinate-1;
			yStart = yClick;
			break;
			
		case BEAM_RIGHT:
			// walk right until end of beam found
			activeCoordinate = xClick;
			while( !((Beam)gg.getCell(activeCoordinate, yClick).getContent()).isBeamEnd() )
				activeCoordinate++;
			xEnd = activeCoordinate;
			yEnd = yClick;
			// cell right of lightsource is first beam element
			activeCoordinate = xClick;
			while( !gg.getCell(activeCoordinate, yClick).isLightSource() )
				activeCoordinate--;
			xStart = activeCoordinate+1;
			yStart = yClick;
			break;
		default: //avoid compiler errors
			xEnd = -1;
			yEnd = -1;
			xStart = -1;
			yStart = -1;
			break;
		}
		
		// determine first beam element from lightsource and direction
		try {
			RemoveBeamCommand rbc = new RemoveBeamCommand(xStart, yStart, xEnd, yEnd, lightSource, direction);
			rbc.execute();
			model.addElement(rbc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
