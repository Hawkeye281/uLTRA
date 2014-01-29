package Controller;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.alee.laf.WebLookAndFeel;

public class LookAndFeelController {

	public static void initializeLookAndFeel(){
		try {
			WebLookAndFeel.setDecorateDialogs(true);
			WebLookAndFeel.setDecorateAllWindows(true);
			WebLookAndFeel.setDecorateFrames(true);
			UIManager.setLookAndFeel ( "com.alee.laf.WebLookAndFeel" );
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
