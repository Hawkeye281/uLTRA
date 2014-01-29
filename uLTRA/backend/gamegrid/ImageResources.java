package gamegrid;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import panels.GridPanel;

/**
 * Bildregister für alle Bilder die mit den {@link Beam}<code>s</code> oder {@link LightSource}<code>s</code> zu tun haben.
 * 
 * @author Stephan
 *
 */
public class ImageResources
{
	private static ImageResources _instance = new ImageResources();
	
	public static int _imageWidth;
	public static int _imageHeight;
	
	private static Map<ImageNames, ImageIcon> _images = new HashMap<ImageNames, ImageIcon>();
	
	public ImageResources()
	{
		try
		{
//			System.out.println(GridPanel.getGridSize().toString());
//			System.out.println(GridPanel.getGridSize().width + " / " + GameGrid.getInstance().getWidth());
			_imageWidth = (GridPanel.getGridSize().width / GameGrid.getInstance().getWidth());
//			System.out.println("_imageWidth: " + _imageWidth);
//			System.out.println(GridPanel.getGridSize().height + " / " + GameGrid.getInstance().getHeight());
			_imageHeight = (GridPanel.getGridSize().height / GameGrid.getInstance().getHeight());
//			System.out.println("_imageHeight: " + _imageHeight);
//			System.out.println("----");
		}
		catch (Exception e)
		{ 
			e.printStackTrace();
		}
	}
	
	/**
	 * Gibt das <code>ImageIcon</code> zurück, was dem {@link ImageNames} entspricht
	 * 
	 * @param pNames Name des Bildes
	 * 
	 * @return das jeweilige Bild als <code>ImageIcon</code>
	 */
	public static ImageIcon getImage(ImageNames pNames)
	{
		ImageIcon icon = null;
		
		if(pNames != null)
		{
			if(!_images.containsKey(pNames))
			{
				icon = new ImageIcon(pNames.getPath());
				icon = new ImageIcon(icon.getImage().getScaledInstance(_imageWidth, _imageHeight, 5));
				
				_images.put(pNames, icon);
			}
			
			return _images.get(pNames);
		}
		
		return null;
	}
	
	/**
	 * Gibt die Instanz der <code>ImageResources</code> zurück
	 * 
	 * @return <code>ImageResources</code>
	 */
	public static ImageResources getInstance()
	{
		return _instance;
	}
	
	/**
	 * Jedesmal wenn das GameGrid geändert wird, müssen die Bildgrößen angepasst werden.<br>
	 * Dies wird hier getriggert.
	 */
	public static void resetInstance()
	{
		_images.clear();
		_instance = new ImageResources();
	}

	/**
	 * Sammlung von Bildernamen
	 * 
	 * @author Stephan
	 *
	 */
	public enum ImageNames
	{
		ARROW_UP("../uLTRA/Documents/images/icons/arrow_up.png"),
		ARROW_UP_END("../uLTRA/Documents/images/icons/arrow_up_end.png"),
		
		ARROW_RIGHT("../uLTRA/Documents/images/icons/arrow_right.png"),
		ARROW_RIGHT_END("../uLTRA/Documents/images/icons/arrow_right_end.png"),
		
		ARROW_DOWN("../uLTRA/Documents/images/icons/arrow_down.png"),
		ARROW_DOWN_END("../uLTRA/Documents/images/icons/arrow_down_end.png"),
		
		ARROW_LEFT("../uLTRA/Documents/images/icons/arrow_left.png"),
		ARROW_LEFT_END("../uLTRA/Documents/images/icons/arrow_left_end.png"),
		
		SOURCE_SINGLE_UP("../uLTRA/Documents/images/icons/source_single_up.png"),
		SOURCE_SINGLE_RIGHT("../uLTRA/Documents/images/icons/source_single_right.png"),
		SOURCE_SINGLE_DOWN("../uLTRA/Documents/images/icons/source_single_down.png"),
		SOURCE_SINGLE_LEFT("../uLTRA/Documents/images/icons/source_single_left.png"),
		
		SOURCE_DOUBLE_UP_RIGHT("../uLTRA/Documents/images/icons/source_double_up_right.png"),
		SOURCE_DOUBLE_UP_DOWN("../uLTRA/Documents/images/icons/source_double_up_down.png"),
		SOURCE_DOUBLE_UP_LEFT("../uLTRA/Documents/images/icons/source_double_up_left.png"),
		SOURCE_DOUBLE_RIGHT_DOWN("../uLTRA/Documents/images/icons/source_double_right_down.png"),
		SOURCE_DOUBLE_RIGHT_LEFT("../uLTRA/Documents/images/icons/source_double_right_left.png"),
		SOURCE_DOUBLE_DOWN_LEFT("../uLTRA/Documents/images/icons/source_double_down_left.png"),
		
		SOURCE_TRIPLE_UP_RIGHT_DOWN("../uLTRA/Documents/images/icons/source_triple_up_right_down.png"),
		SOURCE_TRIPLE_UP_RIGHT_LEFT("../uLTRA/Documents/images/icons/source_triple_up_right_left.png"),
		SOURCE_TRIPLE_UP_DOWN_LEFT("../uLTRA/Documents/images/icons/source_triple_up_down_left.png"),
		SOURCE_TRIPLE_RIGHT_DOWN_LEFT("../uLTRA/Documents/images/icons/source_triple_right_down_left.png"),
		
		SOURCE_QUAD("../uLTRA/Documents/images/icons/source_quad.png");
		
		private String _path;
		
		private ImageNames(String pPath)
		{
			_path = pPath;
		}
		
		/**
		 * Der Pfad zum Bild
		 * 
		 * @return den Pfad zum Bild
		 */
		public String getPath()
		{
			return _path;
		}
		
		/**
		 * Ermittelt den passenden Bildernamen zur übergebenen {@link LightSource} und gibt diesen zurück. 
		 * 
		 * @param pCell Die LightSource zu der das Bild ermittelt werden soll. 
		 * @return den passenden ImageName zur LightSource.
		 * @throws Exception
		 */
		public static ImageNames getSource(Cell pCell) throws Exception
		{
			Cell tempCell = null;
			String text = "SOURCE";
			String direction = "";
			int directions = 0;
			
			if(pCell.hasTopCell())
			{
				if((tempCell = pCell.getTopCell()) != null)
				{
					if(tempCell.isBeam())
					{
						if(((Beam) tempCell.getContent()).getDirection() == BeamDirections.BEAM_UP)
						{
							direction = direction + "_UP";
							directions++;
						}
					}
				}
			}
			
			if(pCell.hasRightCell())
			{
				if((tempCell = pCell.getRightCell()) != null)
				{
					if(tempCell.isBeam())
					{
						if(((Beam) tempCell.getContent()).getDirection() == BeamDirections.BEAM_RIGHT)
						{
							direction = direction + "_RIGHT";
							directions++;
						}
					}
				}
			}
			
			if(pCell.hasBottomCell())
			{
				if((tempCell = pCell.getBottomCell()) != null)
				{
					if(tempCell.isBeam())
					{
						if(((Beam) tempCell.getContent()).getDirection() == BeamDirections.BEAM_DOWN)
						{
							direction = direction + "_DOWN";
							directions++;
						}
					}
				}
			}
			
			if(pCell.hasLeftCell())
			{
				if((tempCell = pCell.getLeftCell()) != null)
				{
					if(tempCell.isBeam())
					{
						if(((Beam) tempCell.getContent()).getDirection() == BeamDirections.BEAM_LEFT)
						{
							direction = direction + "_LEFT";
							directions++;
						}
					}
				}
			}
			
			if(directions > 3)
			{
				text = text + "_QUAD";
			}
			else if(directions > 2)
			{
				text = text + "_TRIPLE" + direction;
			}
			else if(directions > 1)
			{
				text = text + "_DOUBLE" + direction;
			}
			else if(directions > 0)
			{
				text = text + "_SINGLE" + direction;
			}
			
			if(!text.equalsIgnoreCase("SOURCE"))
			{
				for(ImageNames i : ImageNames.values())
				{
					if(i.name().equals(text))
					{
						return i;
					}
				}
			}
			
			return null;
		}
	}
}
