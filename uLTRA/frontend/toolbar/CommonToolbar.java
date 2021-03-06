package toolbar;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import com.alee.laf.button.WebButtonUI;


import panels.GamePanel;
import toolbarActions.*;

import frames.MainFrame;

public class CommonToolbar extends JToolBar{

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private MainFrame mainFrame;
	private GamePanel gamePanel = GamePanel.getGamePanel();
	
	/**
	 * Von Spielbrett und Editor gemeinsam verwendete Toolbar, die entsprechend ihres Erzeugers die entsprechenden
	 * Schaltfl�chen einbaut
	 * 
	 * @param final MainFrame mainFrame
	 * @param final EditorPanel editorPanel
	 * @param final String whoYouAre
	 * @author Sebastian Kiepert
	 * @version 1.2
	 * @see toolbarActions.SaveAction
	 * @see toolbarActions.LoadAction
	 * @see toolbarActions.GenerateAction
	 * @see toolbarActions.ResetAction
	 * @see toolbarActions.MainMenuAction
	 * @see toolbarActions.CloseAction
	 * 
	 */
	public CommonToolbar(){
		this.mainFrame = gamePanel.getMainFrame();
		switch (this.gamePanel.getPanelMode()){
		case GAME:{
			this.setGameTools();
			break;
		}
		case EDIT:{
			this.setEditorTools();
			break;
		}
		}
		commonTools();
		this.setFloatable(false);
	}
	
	private void setGameTools(){
		JButton button = new JButton();
		((WebButtonUI)button.getUI()).setBottomBgColor(Color.WHITE);
		((WebButtonUI)button.getUI()).setBottomSelectedBgColor(new Color(230,230,230));
		((WebButtonUI)button.getUI()).setRolloverShine(true);
		((WebButtonUI)button.getUI()).setShineColor(new Color(180, 180, 180));
		this.add(button);
		button.addActionListener(new LoadAction());
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/load.png"));
		button.setText("laden");
		button.setToolTipText("L�dt ein gespeichertes Spiel");
		
		button = new JButton();
		((WebButtonUI)button.getUI()).setBottomBgColor(Color.WHITE);
		((WebButtonUI)button.getUI()).setBottomSelectedBgColor(new Color(230,230,230));
		((WebButtonUI)button.getUI()).setRolloverShine(true);
		((WebButtonUI)button.getUI()).setShineColor(new Color(180, 180, 180));
		this.add(button);
		button.addActionListener(new SaveAction());
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/save.png"));
		button.setText("speichern");
		button.setToolTipText("Speichert das aktuelle Spiel");
		add(seperator());
		add(seperator());
		
		button = new JButton();
		((WebButtonUI)button.getUI()).setBottomBgColor(Color.WHITE);
		((WebButtonUI)button.getUI()).setBottomSelectedBgColor(new Color(230,230,230));
		((WebButtonUI)button.getUI()).setRolloverShine(true);
		((WebButtonUI)button.getUI()).setShineColor(new Color(180, 180, 180));
		this.add(button);
		button.addActionListener(new SolveAction());
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/solve.png"));
		button.setText("Spiel l�sen");
		button.setToolTipText("L�st das aktuelle Spiel");
		add(seperator());
		add(seperator());
	}
	
	private void setEditorTools(){
		JButton button = new JButton();
		((WebButtonUI)button.getUI()).setBottomBgColor(Color.WHITE);
		((WebButtonUI)button.getUI()).setBottomSelectedBgColor(new Color(230,230,230));
		((WebButtonUI)button.getUI()).setRolloverShine(true);
		((WebButtonUI)button.getUI()).setShineColor(new Color(180, 180, 180));
		this.add(button);
		button.addActionListener(new GenerateAction());
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/new.png"));
		button.setText("Feld erstellen");
		button.setToolTipText("Erstellt ein leeres Spielfeld");
		
		button = new JButton();
		((WebButtonUI)button.getUI()).setBottomBgColor(Color.WHITE);
		((WebButtonUI)button.getUI()).setBottomSelectedBgColor(new Color(230,230,230));
		((WebButtonUI)button.getUI()).setRolloverShine(true);
		((WebButtonUI)button.getUI()).setShineColor(new Color(180, 180, 180));
		this.add(button);
		button.addActionListener(new ResetAction());
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/reset.png"));
		button.setText("Zur�cksetzen");
		button.setToolTipText("Setzt alle �nderungen zur�ck");
		
		button = new JButton();
		((WebButtonUI)button.getUI()).setBottomBgColor(Color.WHITE);
		((WebButtonUI)button.getUI()).setBottomSelectedBgColor(new Color(230,230,230));
		((WebButtonUI)button.getUI()).setRolloverShine(true);
		((WebButtonUI)button.getUI()).setShineColor(new Color(180, 180, 180));
		this.add(button);
		button.addActionListener(new CheckRulesAction());
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/check.png"));
		button.setText("Regeln pr�fen");
		
		add(seperator());
		add(seperator());
		
		button = new JButton();
		((WebButtonUI)button.getUI()).setBottomBgColor(Color.WHITE);
		((WebButtonUI)button.getUI()).setBottomSelectedBgColor(new Color(230,230,230));
		((WebButtonUI)button.getUI()).setRolloverShine(true);
		((WebButtonUI)button.getUI()).setShineColor(new Color(180, 180, 180));
		this.add(button);
		button.addActionListener(new SaveAction());
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/save.png"));
		button.setText("speichern");
		button.setToolTipText("Speichert das aktuelle Feld");
		
		button = new JButton();
		((WebButtonUI)button.getUI()).setBottomBgColor(Color.WHITE);
		((WebButtonUI)button.getUI()).setBottomSelectedBgColor(new Color(230,230,230));
		((WebButtonUI)button.getUI()).setRolloverShine(true);
		((WebButtonUI)button.getUI()).setShineColor(new Color(180, 180, 180));
		this.add(button);
		button.addActionListener(new LoadAction());
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/load.png"));
		button.setText("laden");
		button.setToolTipText("L�dt ein gespeichertes Feld");
		
		add(seperator());
		add(seperator());
	}
	
	private void commonTools(){
		JButton button = new JButton();
		((WebButtonUI)button.getUI()).setBottomBgColor(Color.WHITE);
		((WebButtonUI)button.getUI()).setBottomSelectedBgColor(new Color(230,230,230));
		((WebButtonUI)button.getUI()).setRolloverShine(true);
		((WebButtonUI)button.getUI()).setShineColor(new Color(180, 180, 180));
		this.add(button);
		button.addActionListener(new MainMenuAction());
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/home.png"));
		button.setToolTipText("Bringt Sie zur�ck zum Hauptmen�");
		add(seperator());
		add(seperator());
		button = new JButton();
		((WebButtonUI)button.getUI()).setBottomBgColor(Color.WHITE);
		((WebButtonUI)button.getUI()).setBottomSelectedBgColor(new Color(230,230,230));
		((WebButtonUI)button.getUI()).setRolloverShine(true);
		((WebButtonUI)button.getUI()).setShineColor(new Color(180, 180, 180));
		this.add(button);
		button.addActionListener(new CloseAction());
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/exit.png"));
		button.setToolTipText("Beendet das Programm");
	}
	
	private JLabel seperator(){
		JLabel l = new JLabel();
		l.setText(" ");
		l.setFont(new Font(" ",Font.PLAIN, 14));
		return l;
	}
}
