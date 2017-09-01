import java.util.Random;
import java.util.Scanner;
/*
 * Main program to run Hangman game.
 */
public class Hangman {
	private static HangmanLexicon lexicon;
	private static String secretWord;
	private static int guessCount = 8;
	private static Scanner scanner;
	private static String playersGuess;
	
	private static StringBuilder secretWordState = new StringBuilder();
	
	public static void main(String[] args) {
		lexicon = new HangmanLexicon();
		
		initialize();
		
		
		while (guessCount > 0) {
			getPlayersGuess();
			updateGameState();
		}
	}
	
	// Initial setup.
	private static void initialize() {
		selectSecretWord();
		displayInitialGameState();
	}
	private static void selectSecretWord() {
		Random random = new Random();
		int selectedNumber = random.nextInt(lexicon.getWordCount());
		secretWord = lexicon.getWord(selectedNumber);
	}
	private static void displayInitialGameState() {
		System.out.println("Welcome to Hangman!");
		for (int i = 0; i < secretWord.length(); i++) {
			secretWordState.append("-");
		}
		displaySecretWordState();
		displayCurrentGuessCounts();
	}
	
	// Running game.
	private static void getPlayersGuess() {
		scanner = new Scanner(System.in);
		System.out.print("Your guess: ");
		playersGuess = scanner.nextLine();
		
		if (playersGuess.length() > 1 || !Character.isLetter(playersGuess.charAt(0))) {
			System.out.println("Your guess must be an alphabetical character.");
			getPlayersGuess();
		}
	}
	
	// Evaluate guess.
	private static void updateGameState() {
		if (secretWord.contains(playersGuess.toUpperCase())) {
			displayCorrectGuess();
		} else {
			displayIncorrectGuess();
		}
	}
	private static void displayCorrectGuess() {
		String playersGuessUpper = playersGuess.toUpperCase();
		
		System.out.println("That guess is correct.");
		
		for (int i = 0; i < secretWord.length(); i++) {
			if (secretWord.charAt(i) == playersGuessUpper.charAt(0)) {
				secretWordState.replace(i, i+1, playersGuessUpper);
			}
		}
		
		displaySecretWordState();
		
		// Check if player guessed the word.
		if (secretWordState.toString().contains("-")) {
			displayCurrentGuessCounts();
		} else {
			displayWin();
		}
	}
	private static void displayIncorrectGuess() {
		System.out.println("There are no " + playersGuess + "'s in the word.");
		guessCount--;

		displaySecretWordState();
		displayCurrentGuessCounts();
	}
	
	// Update display for current game state.
	private static void displayCurrentGuessCounts() {
		if (guessCount == 1) {
			System.out.println("You have only one guess left.");
		} else if (guessCount > 1) {
			System.out.println("You have " + guessCount + " guesses left.");
		} else {
			displayLose();
		}
	}
	private static void displaySecretWordState() {
		System.out.print("The word now looks like this: ");
		System.out.println(secretWordState);
	}
	
	// End game.
	private static void displayLose() {
		System.out.println("You're completely hung.");
		System.out.println("The word was: " + secretWord);
		System.out.println("You lose.");
		endGame();
	}
	private static void displayWin() {
		System.out.println("You win.");
		endGame();
	}
	private static void endGame() {
		guessCount = -1;
	}
	
}
