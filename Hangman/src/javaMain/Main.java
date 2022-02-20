package javaMain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Main {

    public static final String[] WORDS = {
            "ABSTRACT", "ASSERT", "BOOLEAN", "BREAK", "BYTE",
            "CASE", "CATCH", "CHAR", "CLASS", "CONST",
            "CONTINUE", "DEFAULT", "DOUBLE", "DO", "ELSE",
            "ENUM", "EXTENDS", "FALSE", "FINAL", "FINALLY",
            "FLOAT", "FOR", "GOTO", "IF", "IMPLEMENTS",
            "IMPORT", "INSTANCEOF", "INT", "INTERFACE",
            "LONG", "NATIVE", "NEW", "NULL", "PACKAGE",
            "PRIVATE", "PROTECTED", "PUBLIC", "RETURN",
            "SHORT", "STATIC", "STRICTFP", "SUPER", "SWITCH",
            "SYNCHRONIZED", "THIS", "THROW", "THROWS",
            "TRANSIENT", "TRUE", "TRY", "VOID", "VOLATILE", "WHILE"
    };

    public static String randomWord;
    public static int numTries = 0;
    public static final int maxTries = 6;
    public static char[] hangWord, randomWordCharSet;

    public static String choseWord(){
        Random wordIndex = new Random();
        String finalWord = WORDS[wordIndex.nextInt(WORDS.length)];
        hangWord = new char[finalWord.length()];
        randomWordCharSet = new char[finalWord.length()];
        for (int i = 0; i < finalWord.length(); i++) {
            hangWord[i] = '_';
            randomWordCharSet[i] = finalWord.charAt(i);
        }
        return finalWord;
    }
    private static void drawStickMan(){
        System.out.println("--------------------------");
        if(numTries > 0) System.out.println("|                       ()");
        else System.out.println("|");
        if(numTries == 2) System.out.println("|                       /");
        else if(numTries < 2) System.out.println("|");
        if(numTries == 3 ) System.out.println("|                       /\\");
        else if(numTries < 3) System.out.println("|");
        if(numTries >= 4) System.out.println("|                       /|\\");
        else System.out.println("|");
        if(numTries == 5) System.out.println("|                       /");
        else if(numTries < 5) System.out.println("|");
        if(numTries >= 6) System.out.println("|                       / \\");
        else System.out.println("|");
        System.out.println("---------------------------");

    }

    private static boolean checkStatus(){
        int correct = 0;
        for (int i = 0; i < hangWord.length; i++) {
            if(hangWord[i] == randomWordCharSet[i]) correct++;
        }
        if(correct == hangWord.length) return true;
        return false;
    }

    private static void playGame(){
        boolean gotWord = false;
        String randomWord = choseWord();
        HashMap<Character, Integer> alreadyTried = new HashMap<>();

        while (!gotWord && numTries <= maxTries){
            gotWord = checkStatus();
            drawStickMan();

            if(gotWord){
                System.out.println("Congrats you won!");
                break;
            }
            else if(!gotWord && numTries >= maxTries){
                System.out.println("You lost. You didn't get the word correct. which was " + randomWord);
                break;
            }

            hangWordBuilder();
            if(alreadyTried.size() > 0) missedWordBuilder(alreadyTried);

            System.out.println("Take a guess. ");

            String guessCharacter = UserInput.getString();
            char userGuess = guessCharacter.charAt(0);
            if(alreadyTried.get(userGuess) == null){
                if(!wordContains(userGuess)){
                    System.out.println("Wrong, there are no " + userGuess);
                    ++numTries;
                }
                alreadyTried.put(guessCharacter.charAt(0), 1);
            } else System.out.println("You already guessed that.");


        }
    }

    private static boolean wordContains(char character){
        boolean found = false;
        for (int i = 0; i < randomWordCharSet.length; i++) {
            if(Character.toUpperCase(character) == randomWordCharSet[i] && hangWord[i] == '_') {
                hangWord[i] = Character.toUpperCase(character);
                found = true;
            }
        }
        return found;

    }

    private static void hangWordBuilder(){
        for (int i = 0; i < hangWord.length; i++) {
            System.out.print(hangWord[i]);
        }
        System.out.println("");
    }

    public static void missedWordBuilder(HashMap<Character, Integer> alreadyTried){
        System.out.print("Already tried: ");
        for(Character i: alreadyTried.keySet()){
            System.out.print(i.toString().toUpperCase());
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        // write your code here
        System.out.println("Welcome to Hangman game. ");
        System.out.println(("Would you like to play? (y/n)"));
        String playing = "";

        try {
            playing = UserInput.getString();
        } catch(Exception e){
            System.out.println("Something went wrong;");
        }

        if(playing.charAt(0) == 'y') playGame();
        else System.out.println("Goodbye!");

    }
}
