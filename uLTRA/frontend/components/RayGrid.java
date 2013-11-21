package components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	private GridBagLayout _layout = new GridBagLayout();
	private GridBagConstraints _bagConsts = new GridBagConstraints();

	public RayGrid(int[][] pNumbers, Object[][] pRays)
	{
		_numbers = pNumbers;
		_rays = pRays;

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
				_bagConsts.gridx = x;
				_bagConsts.gridy = y;
				_bagConsts.insets = new Insets(10, 10, 10, 10);
								
				if(_numbers[y][x] != 0)
				{
					tempLabel = new JLabel(Integer.toString(_numbers[y][x]));
				}
				else
				{
					tempLabel = new JLabel("");
					tempLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
				
				_layout.setConstraints(tempLabel, _bagConsts);
				this.add(tempLabel);
			}
		}
	}
}
