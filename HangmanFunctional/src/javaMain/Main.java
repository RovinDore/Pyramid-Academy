import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) {

        // write your code here
        System.out.println("***************************");
        System.out.println("Welcome to the Hangman game");
        System.out.println("***** Coding Edition ******");
        System.out.println(("\nWould you like to play? (y/n)"));
        String playing = "";
        UserInput getInput = new UserInput();

        try {
            playing = getInput.getString();
        } catch(Exception e){
            System.out.println("Something went wrong getting input " + e.getMessage());
        }

        if(playing.length() > 0 && playing.charAt(0) == 'y'){
            try{ playGame(); }
            catch (Exception e){
                System.out.println("Something went wrong playing the game " + e.getMessage());
            }
        }
        else System.out.println("Goodbye!");

    }

    public static void playGame(){
        //Create new hangman game obj and set up game
        HangmanFunc hangmanGame = new HangmanFunc();
        String randomWord = hangmanGame.getRandomWord();
        HashMap<Character, Integer> alreadyTried = new HashMap<>();
        boolean gotWord = false;
        final int maxTries = hangmanGame.getMaxTries();
        UserInput getInput = new UserInput();

        //Set player name
        System.out.println("Please enter your username.");
        String playerName = getInput.getString();
        hangmanGame.setPlayerName(playerName);

        while (hangmanGame.getNumTries() <= maxTries){
            gotWord = hangmanGame.checkStatus();
            if(gotWord){
                System.out.println("Congrats you won! The word was " + randomWord);
                break;
            } else if(!gotWord && hangmanGame.getNumTries() >= maxTries){
                System.out.println("You lost. You didn't get the word correct. which was " + randomWord);
                break;
            }

            hangmanGame.showHangman();

            System.out.print("Word: "); hangmanGame.hangWordBuilder();
            if(alreadyTried.size() > 0) hangmanGame.missedWordBuilder(alreadyTried);

            // Get user input
            System.out.println("Take a guess.");
            String guessCharacter;
            try{
                guessCharacter = getInput.getString();
            } catch(Exception e){
                System.out.println("Something went wrong");
                break;
            }

            // Determine if the character user guessed was right
            char userGuess = guessCharacter.charAt(0);
            if(alreadyTried.get(userGuess) == null){
                if(!hangmanGame.wordContains(userGuess)){
                    System.out.println("Wrong, there aren't any '" + userGuess + "'\n");
                    hangmanGame.addTry();
                    hangmanGame.addToPlayerScore(-10);
                } else hangmanGame.addToPlayerScore(10);
                alreadyTried.put(guessCharacter.charAt(0), 1);
            } else System.out.println("You already guessed that.");

        }

        //Add score to history
        hangmanGame.addToHistory();
    }

}
