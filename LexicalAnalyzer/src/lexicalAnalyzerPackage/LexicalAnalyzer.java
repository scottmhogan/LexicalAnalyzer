//Team: Scott Hogan, Cameron Wright
//      Melanie Greyerbiehl, Cameron Foster
//
//CSCI 4200-DB, Fall 2018, Lexical Analyzer

package lexicalAnalyzerPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LexicalAnalyzer {

	// Character Classes
	static final String LETTER = "LETTER";
	static final String DIGIT = "DIGIT";
	static final String UNKNOWN = "UNKNOWN_UNKNOWN_UNKNOWN_DANGER_WILL_ROBINSON";

	// Token Codes
	static final String INT_LIT = "INT_LIT";
	static final String IDENT = "IDENT";
	static final String ASSIGN_OP = "ASSIGN_OP";
	static final String ADD_OP = "ADD_OP";
	static final String SUB_OP = "SUB_OP";
	static final String MULT_OP = "MULT_OP";
	static final String DIV_OP = "DIV_OP";
	static final String LEFT_PAREN = "LEFT_PAREN";
	static final String RIGHT_PAREN = "RIGHT_PAREN";
	static final String END_OF_FILE = "END_OF_FILE";

	// Extra Token Codes not related to assignment example
	static final String COMMA = "COMMA";
	static final String EXCLAMATION = "EXCLAMATION";
	static final String LESS_THAN = "LESS_THAN";
	static final String GREATER_THAN = "GREATER_THAN";
	static final String PERCENT = "PERCENT";
	static final String ERROR = "TOO_LONG";
	static final String LEFT_BRACKET = "LEFT_BRACKET";
	static final String RIGHT_BRAKCET = "RIGHT_BRACKET";
	static final String LEFT_CURLY_BRACKET = "LEFT_CURLY_BRACKET";
	static final String RIGHT_CURLY_BRACKET = "RIGHT_CURLY_BRACKET";
	static final String PERIOD = "PERIOD";
	static final String BACKSLASH = "BACKSLASH";
	static final String UNDERSCORE = "UNDERSCORE";
	static final String QUOTATIONS = "QUOTATIONS";

	// Logic variables
	static String charClass;
	static String nextToken;
	static int nextChar;
	static final int MAX_LEXEME_LENGTH = 100;
	static String line;
	static String lineCompare;
	static char[] lexeme = new char[MAX_LEXEME_LENGTH];
	static int lexLen;

	// File input variables
	static File file;
	static FileReader fileReader;
	static BufferedReader bufferedReader;
	static String fileName = "src/lexicalAnalyzerPackage/lexInput.txt";

	public static void main(String[] args) {
		try {
			file = new File(fileName);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);

			System.out.println("Scott Hogan, Melanie Greyerbiehl, Cameron Wright, Cameron Foster"
					+ ". \nCSCI 4200-DB, Fall 2018, Lexical Analyzer");
			System.out.println("********************************************************************************");

			while ((line = bufferedReader.readLine()) != null) {
				getChar();
				if (line.length() > 0) {
					System.out.println("Input: " + line);
					do {
						lex();
					} while (!line.equals(lineCompare));
					System.out.println(
							"********************************************************************************");
				}
				getChar();
				// lineCompare = null;
			}
			System.out.printf("%-30.50s  %-30.50s%n", "Next token is: " + END_OF_FILE, "Next lexeme is EOF");

		} catch (Exception e) {
			System.out.println("ERROR - cannot open the lexical text file"
                    + "\n Make sure the file is placed in the correct directory"
                    + "\n and has the correct name, lexInput.txt");
            System.out.println();
			e.printStackTrace();
		}
	}
	
	
	
}
