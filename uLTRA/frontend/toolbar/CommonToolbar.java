package toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.corba.se.spi.orbutil.fsm.FSM;
import com.sun.corba.se.spi.orbutil.fsm.Input;

import panels.EditorPanel;
import panels.GamePanel;
import toolbarActions.*;

import frames.MainFrame;

public class CommonToolbar extends JToolBar{

	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	
	/**
	 * Von Spielbrett und Editor gemeinsam verwendete Toolbar, die entsprechend ihres Erzeugers die entsprechenden
	 * Schaltflächen einbaut
	 * 
	 * @param final MainFrame mainFrame
	 * @param final EditorPanel editorPanel
	 * @param final String whoYouAre
	 * @author Sebatian Kiepert
	 * @version 1.2
	 * @see toolbarActions.SaveAction
	 * @see toolbarActions.LoadAction
	 * @see toolbarActions.GenerateAction
	 * @see toolbarActions.ResetAction
	 * @see toolbarActions.MainMenuAction
	 * @see toolbarActions.CloseAction
	 * 
	 */
	public CommonToolbar(final MainFrame mainFrame, final GamePanel gamePanel){
		this.mainFrame = mainFrame;
		JButton button = new JButton();
		this.add(button);
		button.addActionListener(new LoadAction(gamePanel));
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/load.png"));
		button.setText("Spiel laden");
		button.setToolTipText("Lädt ein gespeichertes Spiel");
		button.setBackground(Color.WHITE);
		button = new JButton();
		this.add(button);
		button.addActionListener(new SaveAction());
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/save.png"));
		button.setText("Spiel speichern");
		button.setToolTipText("Speichert das aktuelle Spiel");
		button.setBackground(Color.WHITE);
		add(seperator());
		add(seperator());
		commonTools();
		setFloatable(false);
	}
	
	public CommonToolbar(final MainFrame mainFrame, final EditorPanel editPanel){
		this.mainFrame = mainFrame;
		JButton button = new JButton();
		this.add(button);
		button.addActionListener(new GenerateAction((EditorPanel)editPanel));
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/new.png"));
		button.setText("Feld erstellen");
		button.setToolTipText("Erstellt ein leeres Spielfeld");
		button.setBackground(Color.WHITE);
		
		button = new JButton();
		this.add(button);
		button.addActionListener(new ResetAction((EditorPanel)editPanel));
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/reset.png"));
		button.setText("Zurücksetzen");
		button.setToolTipText("Setzt alle Änderungen zurück");
		button.setBackground(Color.WHITE);
		
		add(seperator());
		add(seperator());
		
		button = new JButton();
		this.add(button);
		button.addActionListener(new SaveAction());
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/save.png"));
		button.setText("Feld speichern");
		button.setToolTipText("Speichert das aktuelle Feld");
		button.setBackground(Color.WHITE);
		
		button = new JButton();
		this.add(button);
		button.addActionListener(new LoadAction(editPanel));
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/load.png"));
		button.setText("Feld laden");
		button.setToolTipText("Lädt ein gespeichertes Feld");
		button.setBackground(Color.WHITE);
		
		add(seperator());
		add(seperator());
		
		setFloatable(false);
		commonTools();
	}
	
	private void commonTools(){
		JButton button = new JButton();
		this.add(button);
		button.addActionListener(new MainMenuAction(mainFrame));
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/home.png"));
		button.setText("Zum Hauptmenü");
		button.setToolTipText("Bringt Sie zurück zum Hauptmenü");
		button.setBackground(Color.WHITE);
		add(seperator());
		add(seperator());
		button = new JButton();
		this.add(button);
		button.addActionListener(new CloseAction());
		button.setIcon(new ImageIcon("../uLTRA/Documents/images/icons/exit.png"));
		button.setText("Beenden");
		button.setToolTipText("Beendet das Programm");
		button.setBackground(Color.WHITE);
	}
	
	private JLabel seperator(){
		JLabel l = new JLabel();
		l.setText(" ");
		l.setFont(new Font(" ",Font.PLAIN, 14));
		return l;
	}
}
