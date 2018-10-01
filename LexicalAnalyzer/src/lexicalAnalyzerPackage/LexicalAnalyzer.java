


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

    //GLOBAL DECLARATIONS

    //CHARACTER CLASSES
    private static final String LETTER = "LETTER";
    private static final String DIGIT = "DIGIT";
    private static final String UNKNOWN = "UNKNOWN_UNKNOWN_UNKNOWN_DANGER_WILL_ROBINSON";

    //TOKEN CODES
    private static final String INT_LIT = "INT_LIT";
    private static final String IDENT = "IDENT";
    private static final String ASSIGN_OP = "ASSIGN_OP";
    private static final String ADD_OP = "ADD_OP";
    private static final String SUB_OP = "SUB_OP";
    private static final String MULT_OP = "MULT_OP";
    private static final String DIV_OP = "DIV_OP";
    private static final String LEFT_PAREN = "LEFT_PAREN";
    private static final String RIGHT_PAREN = "RIGHT_PAREN";
    private static final String END_OF_FILE = "END_OF_FILE";
    private static final String APOSTROPHE = "APOSTROPHE";
    private static final String COLON = "COLON";
    private static final String SEMI_COLON = "SEMI_COLON";
    private static final String LEFT_BRACKET = "LEFT_BRACKET";
    private static final String RIGHT_BRAKCET = "RIGHT_BRAKCET";
    private static final String LEFT_CURLY_BRACKET = "LEFT_CURLY_BRACKET";
    private static final String RIGHT_CURLY_BRACKET = "RIGHT_CURLY_BRACKET";
    private static final String PERIOD = "PERIOD";
    private static final String BACKSLASH = "BACKSLASH";
    private static final String UNDERSCORE = "UNDERSCORE";
    private static final String QUOTATIONS = "QUOTATIONS";
    private static final String VERTICAL_BAR = "VERTICAL_BAR";
    private static final String COMMA = "COMMA";
    private static final String EXCLAMATION = "EXCLAMATION";
    private static final String LESS_THAN = "LESS_THAN";
    private static final String GREATER_THAN = "GREATER_THAN";
    private static final String PERCENT = "PERCENT";


    //VARIABLES
    private static String charClass;
    private static String nextToken;
    private static int nextChar;
    //FILE VARIABLES
    private static File file;
    private static FileReader fileReader1;
    private static FileReader fileReader2;
    private static BufferedReader bufferedReader1;
    private static BufferedReader bufferedReader2;
    private static String fileName = "src/lexicalAnalyzerPackage/lexInput.txt";
    private static final int MAX_LEXEME_LENGTH = 100;
    private static String line;
    private static String lineCompare;
    private static char[] lexeme = new char[MAX_LEXEME_LENGTH];
    private static int lexLen;

    //MAIN METHOD
    public static void main(String[] args) {

        try {
            //READ FILE BY LINE
            file = new File(fileName);
            fileReader1 = new FileReader(file);
            bufferedReader1 = new BufferedReader(fileReader1);
            //READ FILE BY CHARACTER
            fileReader2 = new FileReader(file);
            bufferedReader2 = new BufferedReader(fileReader2);

            //OUTPUT TOKENS AND LEXEMES FOR EACH LINE
            //IF READER2 HAS REACHED THE END OF THE LINE IT
            //READS THE NEXT LINE FROM READER1 AND THEN CONTINUES
            //WITH READER2
            System.out.println("Scott Hogan, Melanie Greyerbiehl, Cameron Wright, Cameron Foster"
                    + ". \nCSCI 4200-DB, Fall 2018, Lexical Analyzer");
            System.out.println("********************************************************************************");
            while((line = bufferedReader1.readLine()) != null) {

                getChar();
                if(line.trim().length() > 0) {
                    System.out.println("Input: " + line);
                    do {
                        lex();
                    } while (!line.trim().equals(lineCompare.trim()));
                    System.out.println("********************************************************************************");
                }
                getChar();
                lineCompare = null;
            }
            System.out.printf("%-30.50s  %-30.50s%n","Next token is: " + END_OF_FILE, "Next lexeme is EOF");
            System.out.println("Lexical analysis of the program is complete!");

        } catch (Exception e) {
            System.out.println("ERROR - cannot open the lexical text file"
                    + "\n Make sure the file is placed in the correct directory"
                    + "\n and has the correct name, lexInput.txt");
            System.out.println();
            e.printStackTrace();
        }
    }

    //GET NEXT CHARACTER IN FILE AND DETERMINE CLASS
    public static void getChar() {
        try {
            if ((nextChar = bufferedReader2.read()) != -1) {
                if (Character.isLetter(nextChar)) {
                    charClass = LETTER;
                } else if (Character.isDigit(nextChar)) {
                    charClass = DIGIT;
                } else {
                    charClass = UNKNOWN;
                }
            } else {
                charClass = END_OF_FILE;
            }
        } catch (IOException e) {
            System.out.println("IOException Occured");
            e.printStackTrace();
        }
    }

    //ADD CHARACTER TO LEXEME AND LINECOMPARE
    public static void addChar() {
        if (lexLen < (MAX_LEXEME_LENGTH - 2)) {
            lexeme[lexLen++] = (char) nextChar;
            if(lineCompare == null)
                lineCompare = String.valueOf((char)nextChar);
            else
                lineCompare += (char)nextChar;
            lexeme[lexLen] = 0;
        }
    }

    //FIND NEXT CHARACTER THAT ISN'T WHITESPACE
    public static void getNonBlank() {
        while (Character.isWhitespace(nextChar)) {
            if(lineCompare == null)
                lineCompare = String.valueOf((char)nextChar);
            else
                lineCompare += (char)nextChar;
            getChar();
        }
    }

    // CONTAINS TOKENS FOR NONLETTERS AND NONDIGITS
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

            case '+':
                addChar();
                nextToken = ADD_OP;
                break;

            case '-':
                addChar();
                nextToken = SUB_OP;
                break;

            case '*':
                addChar();
                nextToken = MULT_OP;
                break;

            case '/':
                addChar();
                nextToken = DIV_OP;
                break;

            case '\'':
                addChar();
                nextToken = APOSTROPHE;
                break;

            case ':':
                addChar();
                nextToken = COLON;
                break;

            case ';':
                addChar();
                nextToken = SEMI_COLON;
                break;

            case '[':
                addChar();
                nextToken = LEFT_BRACKET;
                break;

            case ']':
                addChar();
                nextToken = RIGHT_BRAKCET;
                break;

            case '{':
                addChar();
                nextToken = LEFT_CURLY_BRACKET;
                break;

            case '}':
                addChar();
                nextToken = RIGHT_CURLY_BRACKET;
                break;

            case '.':
                addChar();
                nextToken = PERIOD;
                break;

            case '\\':
                addChar();
                nextToken = BACKSLASH;
                break;

            case '_':
                addChar();
                nextToken = UNDERSCORE;
                break;

            case '"':
                addChar();
                nextToken = QUOTATIONS;
                break;

            case '|':
                addChar();
                nextToken = VERTICAL_BAR;
                break;

            case ',':
                addChar();
                nextToken = COMMA;
                break;

            case '!':
                addChar();
                nextToken = EXCLAMATION;
                break;

            case '<':
                addChar();
                nextToken = LESS_THAN;
                break;

            case '>':
                addChar();
                nextToken = GREATER_THAN;
                break;

            case '%':
                addChar();
                nextToken = PERCENT;
                break;

            default:
                addChar();
                nextToken = UNKNOWN;
                break;
        }
        return nextToken;
    }

    //CREATES LEXEME AND ITS MATCHING TOKEN
    public static void lex() {
        lexLen = 0;
        lexeme = new char[MAX_LEXEME_LENGTH];
        getNonBlank();
        switch (charClass) {
            case LETTER:
                addChar();
                getChar();
                while (charClass == LETTER || charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                nextToken = IDENT;
                break;

            case DIGIT:
                addChar();
                getChar();
                while (charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                nextToken = INT_LIT;
                break;

            case UNKNOWN:
                lookup((char) nextChar);
                getChar();
                break;
        }
        System.out.printf("%-30.50s  %-30.50s%n","Next token is: " + nextToken, "Next lexeme is " + new String(lexeme) +"");


    }
}

