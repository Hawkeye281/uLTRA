package components;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Stephan
 *
 */
public class RayGrid extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int[][] _numbers;
	
	@SuppressWarnings("unused")
	private Object[][] _rays;
	
	private GridLayout _layout;

	public RayGrid(int[][] pNumbers, Object[][] pRays)
	{
		_numbers = pNumbers;
		_rays = pRays;

		_layout = new GridLayout(_numbers.length, _numbers[0].length);
		
		this.setLayout(_layout);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		resetLayout();
		this.setVisible(true);
	}
	
	private void resetLayout()
	{
		JLabel tempLabel;
		
		for(int y = 0; y < _numbers.length; y++)
		{
			for(int x = 0; x < _numbers[y].length; x++)
			{
				if(_numbers[y][x] != 0)
				{
					tempLabel = new JLabel(Integer.toString(_numbers[y][x]));
				}
				else
				{
					tempLabel = new JLabel("");
				}

				tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
				tempLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				this.add(tempLabel);
			}
		}
	}
}
