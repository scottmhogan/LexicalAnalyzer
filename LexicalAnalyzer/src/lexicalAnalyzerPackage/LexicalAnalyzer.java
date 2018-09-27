//Team: Scott Hogan, Cameron Wright
//      Melanie Greyerbiehl, Cameron Foster
//
//CSCI 4200-DB, Fall 2018, Lexical Analyzer

package lexicalAnalyzerPackage;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class LexicalAnalyzer {

	private static final String INT_LIT = "INT_LIT";
	private static final String IDENT = "IDENT";
	private static final String ASSIGN_OP = "ASSIGN_OP";
	private static final String ADD_OP = "ADD_OP";
	private static final String SUB_OP = "SUB_OP";
	private static final String MULT_OP = "MULT_OP";
	private static final String DIV_OP = "DIV_OP";
	private static final String LEFT_PAREN = "LEFT_PAREN";
	private static final String RIGHT_PAREN = "RIGHT_PAREN";
	private static final String EQUAL_OP = "EQUAL_OP";
	private static final String END_OF_FILE = "END_OF_FILE";
	private static final String LETTER = "LETTER";
	private static final String DIGIT = "DIGIT";
	private static final String UNKNOWN = "UNKNOWN";
	
	final int MAX_LEXEME_LENGTH = 100;
	
	static String charClass;

	String token;

	static String nextToken;
	
	
	char[] lexeme = new char[MAX_LEXEME_LENGTH];
	static int nextChar;
	int lexLen;

	static int lineLength;
	
	//Initialize file variables
	static File file;
	static FileReader reader;
	static FileReader fileReader;
	static BufferedReader bufferedReader;
	static String fileName = "src/lexicalAnalyzerPackage/lexInput.txt";
	
	
	public static void main(String[] args){
		
		//Initialize file objects
		try {
			file = new File(fileName);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line;
			System.out.println("Scott Hogan, Melanie Greyerbiehl, Cameron Wright, Cameron Foster"
					+". \nCSCI 4200-DB, Fall 2018, Lexical Analyzer");
			while((line = bufferedReader.readLine()) != null){
				System.out.println("********************************************************************************");
				System.out.println("Input: "+line);
				//lineLength = line.length();
				getChar();
				//do {
					//lex();
				//} while (nextToken != END_OF_FILE);
				
				System.out.println("********************************************************************************");
			}
			
			
		} catch (Exception e) {
			System.out.println("ERROR - cannot open the lexical text file"
					+"\n Make sure the file is placed in the correct directory"
					+"\n and has the correct name, lexInput.txt");
			System.out.println();
			e.printStackTrace();
		}
	}
	
	public void addChar(char c){
		
	}
	
	public static void getChar() {
		try {
			if((nextChar = bufferedReader.read()) != -1){
				System.out.println((char)nextChar);
				if(Character.isLetter(nextChar)){
					charClass = LETTER;
				}
				else if(Character.isDigit(nextChar)){
					charClass = DIGIT;
				}
				else{
					charClass = UNKNOWN;
				}
			}
			else{
				charClass = END_OF_FILE;
			}
		} catch (IOException e) {
			System.out.println("IOException Occured");
			e.printStackTrace();
		}
	}
	
	public void getNonBlank() {
		while (Character.isWhitespace(nextChar)){
			getChar();
		}
	}
	
	public void lex(String line){
		
	}
}
