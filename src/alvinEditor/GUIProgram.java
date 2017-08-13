package alvinEditor;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUIProgram extends JPanel implements ActionListener {
	protected JTextField textField;
	protected JTextArea textArea;
	protected JLabel label;
	private final static String newline = "\n";

	public GUIProgram(JFrame owner) throws IOException, SQLException {
		// super(new BOXLayout());
		// techData t = new techData();
		

		// displayAllWords(trieList, "");
		//System.out.println(techData.search(trieList, "touchpad"));

		textField = new JTextField(20);
		textField.addActionListener(this);

		label = new JLabel("Search Here");
		textArea = new JTextArea(5, 20);
		textArea.setEditable(false);

		// JScrollPane scrollPane = new JScrollPane(textArea);

		// Add Components to this panel.
		// GridBagConstraints c = new GridBagConstraints();
		// c.gridwidth = GridBagConstraints.REMAINDER;

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(label);
		//textArea.setText(techData.search(trieList, textField.getText()));
		
		JButton b = new JButton("Search Online");
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		panel.add(textField, BorderLayout.NORTH);
		panel.add(textArea, BorderLayout.SOUTH);panel.add(b);
		add(panel);// , c);

		//b.addActionListener(this);
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				google(textField.getText().toLowerCase());
			}

		});
		/*
		 * c.fill = GridBagConstraints.BOTH; c.weightx = 1.0; c.weighty = 1.0;
		 * add(scrollPane, c);
		 */
	}
	public void actionPerformed(ActionEvent evt) {
		String text = textField.getText();
		
		//TrieContainer trieList = new TrieContainer();

		/*String file = "techData.txt";
		try {
			techData.buildDataTree(file, trieList);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textArea.append(techData.search(trieList, text) + newline);
		textField.selectAll();*/
		
		HashMap<String, String> hmap = TechTermsHash.buildHeap();
		//System.out.println(hmap.get("Touchpad".toLowerCase()));
		
		String str =hmap.get(text.toLowerCase()) ;
		
		if(str != null)
			textArea.setText("\n"+text+": "+hmap.get(text.toLowerCase()) + newline);
		else
			textArea.setText("\n"+text+": "+"Not Found in the DataBase" + newline);
			
		textField.selectAll();

		// Make sure the new text is visible, even if there
		// was a selection in the text area.
		
		textArea.setCaretPosition(textArea.getDocument().getLength());
		
		
	}
public void google(String str){
	try {
			Desktop.getDesktop().browse(new URI("https://www.google.co.in/search?q="+str));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	static void createAndShowGUI(JFrame owner) throws IOException, SQLException {
		// Create and set up the window.
		JFrame frame = new JFrame("TechPC");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add contents to the window.
		frame.add(new GUIProgram(owner));
		frame.setLocationRelativeTo(owner);
		// Display the window.
		frame.pack();
		frame.setVisible(true);
		frame.setSize(350,180);
	}
	

/*	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}*/
}
