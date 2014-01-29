/**
 * 
 */
package help;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Diese Klasse bildet das Hauptfenster der Hilfe ab. <br>
 * Das Fenster soll losgelöst vom Spiel sein, sodass es in einem eigenen Thread
 * neben dem eigentlichen Spielfenster bestehen kann.
 */
public class HelpWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	/** Singleton Instanz der Hilfe */
	private static HelpWindow _instance;
	/** Content der Hilfe */
	private final JEditorPane _content;
	/** Die geladene Hilfeseiten */
	private Hashtable<String, String> _helpContent;
	/** TreeValues */
	private Vector<String> _treeValues;

	public synchronized static HelpWindow getInstance() {
		if (_instance == null)
			_instance = new HelpWindow();
		if (!_instance.isVisible())
			_instance.setVisible(true);
		_instance.toFront();
		return _instance;
	}

	private HelpWindow() {
		super("uLTRA - Hilfe");
		ImageIcon icon = new ImageIcon("../uLTRA/Documents/images/icons/help.png");
		// CloseAction überschreiben
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(5, 0));
		_content = new JEditorPane();
		_content.setEditable(false);
		_content.setContentType("text/html");
		getContentPane().add(new JScrollPane(_content), BorderLayout.CENTER);

		initHelp();

		setMinimumSize(new Dimension(1100, 768));
		setResizable(false);
		setIconImage(icon.getImage());
		setLocationRelativeTo(null);
	}

	private void initHelp() {

		readHelpContentFromSystem();
		JTree tree = new JTree(_treeValues);
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent pE) {
				String selection = pE.getPath().getLastPathComponent()
						.toString();
				String pageName = _helpContent.get(selection);
				if (pageName != null) {
					String loadedPage = readHTMLPage(pageName);
					if (loadedPage != null)
						setContent(loadedPage);
				}

			}
		});
		tree.setSelectionRow(0);
		getContentPane().add(tree, BorderLayout.WEST);
	}

	private void setContent(String htmlPage) {

		_content.setText(htmlPage);
		_content.setCaretPosition(0);
	}

	private String readHTMLPage(String pageName) {
		InputStream is;
		StringBuilder sb = new StringBuilder();
		try {
			is = new FileInputStream(pageName);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);

			for (String s; (s = in.readLine()) != null;) {

				if (s.contains("<img")) {
					final String parsedString = parseIMGTag(s);
					sb.append(parsedString);
				} else
					sb.append(s);

			}
			in.close();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();

	}

	private String parseIMGTag(String s) {
		final int srcIndex = s.indexOf("src=") + 5;
		final int endIndex = s.indexOf("\"", srcIndex);
		final String imagePath = s.substring(srcIndex, endIndex);
		File test = new File(imagePath);
		URL url = null;
		try {
			url = test.toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return s.substring(0, srcIndex) + url
				+ s.substring(endIndex, s.length());
	}

	private void readHelpContentFromSystem() {
		InputStream is;
		try {
			is = new FileInputStream("../uLTRA/Documents/helpcontent/help.xml");
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document dom;
			dom = db.parse(is);
			Element docEle = dom.getDocumentElement();
			NodeList nl;
			nl = docEle.getElementsByTagName("kapitel"); // liefert den Inhalt
															// den der tag
															// umschließt

			_helpContent = new Hashtable<String, String>();
			_treeValues = new Vector<String>();
			for (int i = 0; i < nl.getLength(); i++) {
				_treeValues.add(nl.item(i).getFirstChild().getTextContent());
				_helpContent.put(nl.item(i).getFirstChild().getTextContent(),
						nl.item(i).getLastChild().getTextContent());
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
