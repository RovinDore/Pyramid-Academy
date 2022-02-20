package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Random randomNum = new Random();
        int tryAmount = 0;
        int maxTries = 6;
        int sysGuess = 1 + randomNum.nextInt(20);
        int userGuess = 0;
        char playGame = 'y';
        UserInput getUserInput = new UserInput();

        System.out.println("Hello! What is your name?");
        String name = getUserInput.getString();

        System.out.println("\nWell, " + name + ", I am thinking of a number between 1 and 20. \n");

        while(userGuess != sysGuess && tryAmount < maxTries && playGame == 'y'){
            tryAmount++;
            if(tryAmount <= maxTries) System.out.println("Take a guess.\n");

            String guessState = tryAmount > 1 ? " guesses!" : " guess!";
            try{
                userGuess = getUserInput.getChoice();
            } catch (Exception e){
                System.out.println("Something went wrong");
                break;
            }

            if(userGuess != sysGuess){
                if(userGuess > sysGuess) System.out.println("Your guess is too high.");
                else System.out.println("Your guess is too low.");
            } else System.out.println("Good job, " + name + "! You guessed my number in " + tryAmount + guessState);

            if(userGuess == sysGuess || tryAmount >= maxTries){
                System.out.println("Would you like to play again? (y/n)");
                String again = getUserInput.getString();
                playGame = again.charAt(0);
                if(playGame == 'y' || playGame == 'y'){
                    tryAmount = 0;
                    sysGuess = 1 + randomNum.nextInt(20);
                } else playGame = 'n';

            }

        }
    }
}
