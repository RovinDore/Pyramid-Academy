//package javaMain;

import java.util.HashMap;
import java.util.Random;

public class Hangman {
    private final String[] WORDS = {
            "ABSTRACT", "ASSERT", "BOOLEAN", "BREAK", "BYTE",
            "CASE", "CATCH", "CHAR", "CLASS", "CONST",
            "CONTINUE", "DEFAULT", "DOUBLE", "DO", "ELSE",
            "ENUM", "EXTENDS", "FALSE", "FINAL", "FINALLY",
            "FLOAT", "FOR", "GOTO", "IF", "IMPLEMENTS",
            "IMPORT", "INSTANCE", "INT", "INTERFACE",
            "LONG", "NATIVE", "NEW", "NULL", "PACKAGE",
            "PRIVATE", "PROTECTED", "PUBLIC", "RETURN",
            "SHORT", "STATIC", "STRICTFP", "SUPER", "SWITCH",
            "SYNCHRONIZED", "THIS", "THROW", "THROWS",
            "TRANSIENT", "TRUE", "TRY", "VOID", "VOLATILE", "WHILE"
    };

    public String randomWord;
    public int numTries;
    public final int maxTries = 6;
    private char[] hangWord, randomWordCharSet;

    public Hangman(){
        this.randomWord = choseWord();
        this.numTries = 0;
    }

    //Getters & Setters
//    public int addTry(){ return ++this.numTries; }
    public void addTry(){ ++this.numTries; }
    public int getNumTries(){ return this.numTries; }
    public String getRandomWord(){ return this.randomWord; }
    public int getMaxTries(){ return this.maxTries; }

    //Methods
    public String choseWord(){
        Random wordIndex = new Random();
        String finalWord = WORDS[wordIndex.nextInt(WORDS.length)];
        this.hangWord = new char[finalWord.length()];
        this.randomWordCharSet = new char[finalWord.length()];
        for (int i = 0; i < finalWord.length(); i++) {
            this.hangWord[i] = '_';
            this.randomWordCharSet[i] = finalWord.charAt(i);
        }
        return finalWord;
    }

    public boolean checkStatus(){
        int correct = 0;
        for (int i = 0; i < hangWord.length; i++) {
            if(hangWord[i] == randomWordCharSet[i]) correct++;
        }
        return correct == hangWord.length;
    }

    public boolean wordContains(char character){
        boolean found = false;
        for (int i = 0; i < randomWordCharSet.length; i++) {
            if(Character.toUpperCase(character) == randomWordCharSet[i] && hangWord[i] == '_') {
                hangWord[i] = Character.toUpperCase(character);
                found = true;
            }
        }
        return found;
    }

    public void hangWordBuilder(){
        for (Character i: hangWord) System.out.print(i);
        System.out.println();
    }

    public void missedWordBuilder(HashMap<Character, Integer> alreadyTried){
        System.out.print("Already tried: ");
        for(Character i: alreadyTried.keySet()) System.out.print(i.toString().toUpperCase());
        System.out.println();
    }
}