package alvinEditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class TechTermsHash {
	//public static void main(String[] args) throws FileNotFoundException {
		/*HashMap<String, String> hmap = buildHeap();
		System.out.println(hmap.get("Touchpad".toLowerCase()));*/
	//}

	/**
	 * @return
	 */
	public static HashMap<String, String> buildHeap() {
		File file = new File("techData.txt");
		HashMap<String, String> hmap = new HashMap<String, String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "@");
				String key = st.nextToken();
				// System.out.println(key);
				String value = st.nextToken().trim();// line.substring(key.length()+2);//substring(key.length()+2);
				//System.out.println(value);
				hmap.put(key, value);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return hmap;
	}
}