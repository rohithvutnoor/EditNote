package alvinEditor;

import java.awt.BorderLayout;
import java.awt.Toolkit;
//import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Dictionary extends JDialog {

	/**
	 * Dictionary Optimized Code
	 */
	// Insert All Words to TrieList
	private static void insert(TrieContainer start, String word, String meaning) {
		word = word.trim(); //Trim for extra spaces
		for (int j = 0; j < word.length(); j++) {
			char character = word.charAt(j);
			// In series, check the position of character,
			// if it is already filled then check the series of filled Tries
			// object.
			// if it is not filled then create new TrieContainer object and
			// place it at correct position, and check
			// if it is end of the word, then mark isEnd = true or else false;
			if (start.childrens[character - 97] != null) {
				if (word.length() - 1 == j) {
					// if word is found till last character, then mark the end
					// as true.
					start.childrens[character - 97].isEnd = true;
				}
				start = start.childrens[character - 97];
			} else {
				TrieContainer trie = new TrieContainer();
				trie.isEnd = (word.length() - 1 == j ? true : false);
				// direct link to meaning
				trie.meaning = meaning; // if stored meanings search will be
										// O(word.length)
				trie.word = word; // if stored it's easy to print directly

				start.childrens[character - 97] = trie;
				start = start.childrens[character - 97];
			}
		}
	}

	// Search for a word in TrieList
	private static String search(TrieContainer start, String word) {
		// boolean isFound = true;
		String meaning = "";
		for (int i = 0; i < word.length(); i++) {
			char character = word.charAt(i);
			// if at character position TrieContainer object is present then
			// character is found and
			// start looking for next character is word.
			if (start.childrens[character - 97] != null) {
				if (word.length() - 1 != i) {
					start = start.childrens[character - 97];
				} else {
					if (start.childrens[character - 97].isEnd) { // !
						// isFound = true; //false
						meaning = start.meaning;
					}
				}
			} else {
				// isFound = false;
				break;
			}
		}
		return meaning;
	}

	// Build Dictionary into TrieList
	private static void buildDictionary(String file, TrieContainer trieList) throws IOException {
		String word = "";
		String meaning = "";
		String currentLine = "";
		BufferedReader buffer = null;
		buffer = new BufferedReader(new FileReader(file));
		int line = 0;
		while (((currentLine = buffer.readLine()) != null) && line <= 23202) {
			if (currentLine.indexOf("_") > 0) {
				String arr[] = currentLine.split("_");
				word = arr[0];
				meaning = arr[1];
				// if(word.equalsIgnoreCase("ses"))
				// System.out.println(meaning);
				insert(trieList, word.toLowerCase(), meaning);
				// System.out.println(currentLine.indexOf("_"));
			}
			++line;
		}
		buffer.close();
	}

	// Window Design
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JLabel label;
	JTextArea meaningArea;
	JPanel north, south, center;
	JTextField field;
	String strLine;
	String find;
	JButton button;

	Dictionary(JFrame owner) throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/IDictionary.png")));

		panel = new JPanel();
		label = new JLabel("Find Meaning : ");
		meaningArea = new JTextArea(5, 50);
		field = new JTextField(10);
		button = new JButton("Find");

		north = new JPanel();
		south = new JPanel();
		center = new JPanel();

		north.add(label);
		north.add(field);
		north.add(button);
		center.add(meaningArea);

		setVisible(true);
		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(center, BorderLayout.CENTER);
		setSize(owner.getX(), 200);
		// pack();
		setTitle("Dictionary");
		setLocationRelativeTo(owner);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		meaningArea.setText("");
		meaningArea.setEditable(false);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				String textFieldValue = field.getText();
				find = textFieldValue;
				
				TrieContainer trieList = new TrieContainer();
				String filePath = "dictionaryOptimized.txt";
				
				try {
					buildDictionary(filePath, trieList);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if (find.length() > 0) {
					String meaning = search(trieList, find.toLowerCase());
					String data = find + meaning;
					meaningArea.setText(data);
					meaningArea.setLineWrap(true);
					meaningArea.setWrapStyleWord(true);
				}
			}
		});

	}
}


/*
 * InputStream is = this.getClass().getResourceAsStream("/file/dictionary.txt");
					BufferedReader br = new BufferedReader(new InputStreamReader(is));

					strLine = null;
					find = find.toLowerCase();
					meaningArea.setText("");

					try {
						while (((strLine = br.readLine()) != null)) {
							if (strLine.contains(find)) {
								int l = find.length();

								String s1 = find.substring(0, l - 1);
								// String s2 = find.substring(l-1);

								char[] s3 = strLine.toCharArray();
								char s4 = s3[l + 1];

								if (strLine.startsWith(s1) && s4 == '(') {
									meaningArea.setText(strLine);
									meaningArea.setLineWrap(true);
									meaningArea.setWrapStyleWord(true);
								}

							}
						}
					} catch (IOException e) {
						System.out.println("Error");
					} finally {

						try {
							br.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
 */
