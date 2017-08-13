package alvinEditor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class techData {

	private static void insert(TrieContainer start, String word, String meaning) {
		word = word.trim();
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

	static String search(TrieContainer start, String word) {
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

	@SuppressWarnings("unused")
	private static void displayAllWords(TrieContainer start, String toPrint) {
		if (start == null) {
			return;
		}
		if (start.isEnd) {
			System.out.println(toPrint);
			// System.out.println(start.word); if word is stored in the tries
		}
		for (int i = 0; i < start.childrens.length; i++) {
			TrieContainer t = start.childrens[i];
			if (t != null) {
				// System.out.print(toPrint + ", ");
				displayAllWords(t, toPrint + (char) (97 + i));
			}
		}
	}

	@SuppressWarnings("unused")
	static void buildDataTree(String file, TrieContainer trieList) throws IOException, SQLException {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "@");
				String key = st.nextToken();
				//System.out.print(key);
				// System.out.print("insert into mytechterms.techdatacollection
				// (Word, Meaning) values (\""+key+"\", \"");
				String value = st.nextToken().trim();// line.substring(key.length()+2);//substring(key.length()+2);
				//System.out.println(value);
				// System.out.println(value+"\");");
				// hmap.put(key, value);

				try {
					// Get Connection
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytechterms?useSSL=false", "madguy",
							"madguy");
					// Create Statement
					Statement stmt = con.createStatement();
					String queryFind = "select * from techdatacollection";
					ResultSet res = stmt.executeQuery(queryFind);
					while (res.next()) {
						//System.out.println(res.getString("Word") + " " + res.getString("Meaning"));
						insert(trieList, res.getString("Word").toLowerCase(), res.getString("Meaning"));
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				// insert into mytechterms.techdatacollection (Word, Meaning)
				// values
				// ("vpn", "virtual private network");
				//insert(trieList, key.toLowerCase(), value);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
