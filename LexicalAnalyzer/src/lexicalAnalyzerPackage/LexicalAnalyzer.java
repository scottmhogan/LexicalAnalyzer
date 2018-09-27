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
import java.io.InputStreamReader;
import java.util.Scanner;


public class LexicalAnalyzer {

	final int MAX_LEXEME_LENGTH = 100;
	
	String charClass, token, nextToken;
	char[] lexeme = new char[MAX_LEXEME_LENGTH];
	char nextChar;
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
				
				
				System.out.println("********************************************************************************");
			}
			
			//getChar();
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
		
	}
	
	public void getNonBlank() {
		while (Character.isWhitespace(nextChar)){
			getChar();
		}
	}
}
