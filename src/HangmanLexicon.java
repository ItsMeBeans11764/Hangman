import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/*
 * Hangman Lexicon using words from text file.
 */
public class HangmanLexicon {
	
	private static String filename = "res\\HangmanLexicon.txt";
	private List<String> wordList;
	
	/** HangmanLexicon constructor. */
	public HangmanLexicon() {
		wordList = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String word;
			while ((word = reader.readLine()) != null) {
				wordList.add(word);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		System.out.println(System.getProperty("user.dir"));
		return wordList.size();
	}
	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordList.get(index);
	}
}
