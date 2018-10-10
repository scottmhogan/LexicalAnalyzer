//Team: Scott Hogan, Cameron Wright
//      Melanie Greyerbiehl, Cameron Foster
//      *Super Awesome Programming Pals*
//
//CSCI 4200-DB, Fall 2018, Lexical Analyzer

package lexicalAnalyzerPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LexicalAnalyzer {

	/* Declaration of character classes */
	static final String LETTER = "LETTER";
	static final String DIGIT = "DIGIT";
	static final String UNKNOWN = "UNKNOWN";

	/* Declaration of token codes */
	static final String INT_LIT = "INT_LIT";
	static final String IDENT = "IDENT";
	static final String ASSIGN_OP = "ASSIGN_OP";
	static final String LEFT_PAREN = "LEFT_PAREN";
	static final String RIGHT_PAREN = "RIGHT_PAREN";
	static final String ADD_OP = "ADD_OP";
	static final String SUB_OP = "SUB_OP";
	static final String MULT_OP = "MULT_OP";
	static final String DIV_OP = "DIV_OP";
	static final String END_OF_FILE = "END_OF_FILE";
	static final String ERROR = "TOO_LONG";

	/* Declaration of logic variables */
	static String charClass;
	static String nextToken;
	static int nextChar;
	static final int MAX_LEXEME_LENGTH = 100;
	static String line;
	static String lineCompare;
	static char[] lexeme = new char[MAX_LEXEME_LENGTH];
	static int lexLen;

	/* Declaration of variables used to read the file */
	static File file;
	static FileReader fileLineReader;
	static FileReader fileCharacterReader;
	static BufferedReader bufferedLineReader;
	static BufferedReader bufferedCharacterReader;
	static String fileName = "src/lexicalAnalyzerPackage/lexInput.txt";

	public static void main(String[] args) {

		try {
			/*
			 * This section reads the file line by line. Supports multi-line .txt files for
			 * lexical analyzing multiple equations on different lines
			 */
			file = new File(fileName);
			fileLineReader = new FileReader(file);
			bufferedLineReader = new BufferedReader(fileLineReader);

			/* This block reads the file character by character */
			fileCharacterReader = new FileReader(file);
			bufferedCharacterReader = new BufferedReader(fileCharacterReader);

			System.out.println("Scott Hogan, Melanie Greyerbiehl, Cameron Wright, Cameron Foster"
					+ ". \nCSCI 4200-DB, Fall 2018, Lexical Analyzer");
			System.out.println("********************************************************************************");

			// Runs until the buffered reader reaches the end of the line.
			while ((line = bufferedLineReader.readLine()) != null) {
				getChar();

				/*
				 * We .trim() the lines to prevent a bug that loops infinitely if a line has
				 * only spaces
				 */
				if (line.trim().length() > 0) {
					System.out.println("Input: " + line);
					do {
						lex();
					} while (!line.trim().equals(lineCompare.trim()));
					System.out.println(
							"********************************************************************************");
				}
				getChar();
				
				// Set lineCompare to null to prevent infinite looping
				lineCompare = null;
			}

			// Print results in formatted format. The first argument of printf is for
			// formatting.
			System.out.printf("%-30.50s  %-30.50s%n", "Next token is: " + END_OF_FILE, "Next lexeme is EOF");
			System.out.println("Lexical analysis of the program is complete!");

			/*
			 * Exception handling. This only handles a generic exception, but from what i've
			 * seen the exception only occurs when the lexInput.txt file is in the wrong
			 * directory. I print the stack trace for debugging.
			 */
		} catch (Exception e) {
			System.out.println("ERROR - cannot open the lexical text file"
					+ "\n Make sure the file is placed in the correct directory"
					+ "\n and has the correct name, lexInput.txt");
			System.out.println();
			e.printStackTrace();
		}
	}

	// Get the next character and define its class.
	public static void getChar() {
		try {
			/*
			 * The buffered reader's .read() function will return a -1 if there is no more
			 * information for the reader to read.
			 */
			if ((nextChar = bufferedCharacterReader.read()) != -1) {
				if (Character.isLetter(nextChar))
					charClass = LETTER;
				else if (Character.isDigit(nextChar))
					charClass = DIGIT;
				else
					charClass = UNKNOWN;
			} else
				charClass = END_OF_FILE;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Add the character to the lexeme.
	public static void addChar() {
		if (lineCompare == null)
			lineCompare = String.valueOf((char) nextChar);
		else
			/*
			 * Theoretically, StringBuilder would be faster since it is O(N) time instead of
			 * O(N^2) time. However, we had to stick with concatenating to lineCompare like
			 * this since we call .trim() onto lineCompare. StringBuilder doesn't support .trim().
			 */
			lineCompare += (char) nextChar;

		if (lexLen < (MAX_LEXEME_LENGTH - 2)) {
			lexeme[lexLen++] = (char) nextChar;
			lexeme[lexLen] = 0;
		}
	}

	//Gets the next non whitespace character
	public static void getNonBlank() {
		while (Character.isWhitespace(nextChar)) {
			if (lineCompare == null)
				lineCompare = String.valueOf((char) nextChar);
			else
				lineCompare += (char) nextChar;
			getChar();
		}
	}

	// Cases for non-digits and non-letters.
	public static String lookup(char ch) {
		switch (ch) {
		case '(':
			addChar();
			nextToken = LEFT_PAREN;
			break;

		case ')':
			addChar();
			nextToken = RIGHT_PAREN;
			break;

		case '=':
			addChar();
			nextToken = ASSIGN_OP;
			break;
			
		case '-':
			addChar();
			nextToken = SUB_OP;
			break;
			
		case '+':
			addChar();
			nextToken = ADD_OP;
			break;

		case '/':
			addChar();
			nextToken = DIV_OP;
			break;
			
		case '*':
			addChar();
			nextToken = MULT_OP;
			break;

		

		default:
			addChar();
			nextToken = UNKNOWN;
			break;
		}
		return nextToken;
	}

	//Create the lexeme and matching token.
	public static void lex() {
		lexLen = 0;
		lexeme = new char[MAX_LEXEME_LENGTH];
		getNonBlank();
		switch (charClass) {
		case DIGIT:
			addChar();
			getChar();
			while (charClass == DIGIT) {
				addChar();
				getChar();
			}
			if (!(lexLen < (MAX_LEXEME_LENGTH - 2)))
				nextToken = ERROR;
			else
				nextToken = INT_LIT;
			break;

		case LETTER:
			addChar();
			getChar();
			while (charClass == LETTER || charClass == DIGIT) {
				addChar();
				getChar();
			}
			
			if (!(lexLen < (MAX_LEXEME_LENGTH - 2)))
				nextToken = ERROR;
			else
				nextToken = IDENT;
			break;

		case UNKNOWN:
			lookup((char) nextChar);
			getChar();
			break;
		}
		System.out.printf("%-30.113s  %-30.113s%n", "Next token is: " + nextToken,
				"Next lexeme is " + new String(lexeme) + "");

	}

}
