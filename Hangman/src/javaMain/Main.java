package javaMain;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("***************************");
        System.out.println("Welcome to the Hangman game");
        System.out.println("***************************");
        System.out.println(("\nWould you like to play? (y/n) \n"));
        String playing = "";

        try {
            playing = UserInput.getString();
        } catch(Exception e){
            System.out.println("Something went wrong getting input " + e.getMessage());
        }

        if(playing.charAt(0) == 'y'){
            try{ playGame(); }
            catch (Exception e){
                System.out.println("Something went wrong; " + e.getMessage());
            }
        }
        else System.out.println("Goodbye!");

    }

    public static void playGame(){
        Hangman hangmanGame = new Hangman();
        String randomWord = hangmanGame.getRandomWord();
        HashMap<Character, Integer> alreadyTried = new HashMap<>();
        boolean gotWord = false;
        final int maxTries = hangmanGame.getMaxTries();

        while (hangmanGame.getNumTries() <= maxTries){
            drawStickMan(hangmanGame.getNumTries());
            gotWord = hangmanGame.checkStatus();
            if(gotWord){
                System.out.println("Congrats you won!");
                break;
            } else if(!gotWord && hangmanGame.getNumTries() >= maxTries){
                System.out.println("You lost. You didn't get the word correct. which was " + randomWord);
                break;
            }

            hangmanGame.hangWordBuilder();
            if(alreadyTried.size() > 0) hangmanGame.missedWordBuilder(alreadyTried);

            System.out.println("Take a guess. \n");

            String guessCharacter = UserInput.getString();
            char userGuess = guessCharacter.charAt(0);
            if(alreadyTried.get(userGuess) == null){
                if(!hangmanGame.wordContains(userGuess)){
                    System.out.println("Wrong, there are no '" + userGuess + "'\n");
                    hangmanGame.addTry();
                }
                alreadyTried.put(guessCharacter.charAt(0), 1);
            } else System.out.println("You already guessed that. \n");

        }
    }

    public static void drawStickMan(int numTries){
        System.out.println("\n--------------------------");
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
}
