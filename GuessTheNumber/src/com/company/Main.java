package com.company;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner myObj = new Scanner(System.in);
        Random randomNum = new Random();
        int tryAmount = 0;
        int maxTries = 6;
        int sysGuess = 1 + randomNum.nextInt(20);
        int userGuess = 0;
        char playGame = 'y';

        System.out.println("Hello! What is your name?");
        String name = myObj.nextLine();

        System.out.println("Well, " + name + ", I am thinking of a number between 1 and 20.");

        while(userGuess != sysGuess && tryAmount < maxTries && playGame == 'y'){
            tryAmount++;
            if(tryAmount <= maxTries) System.out.println("Take a guess.");

            String guessState = tryAmount > 1 ? " guesses!" : " guess!";
            userGuess = myObj.nextInt();

            if(userGuess != sysGuess){
                if(userGuess > sysGuess){
                    System.out.println("Your guess is too high.");
                } else {
                    System.out.println("Your guess is too low.");
                }
//                if(tryAmount < maxTries) System.out.println("Take a guess.");
            } else{
                System.out.println("Good job, " + name + "! You guessed my number in " + tryAmount + guessState);
            }

            if(userGuess == sysGuess || tryAmount >= maxTries){
                System.out.println("Would you like to play again? (y/n)");
                playGame = myObj.next().charAt(0);
                if(playGame == 'Y' || playGame == 'y'){
                    tryAmount = 0;
                    sysGuess = 1 + randomNum.nextInt(20);
                } else playGame = 'n';

            }


        }

    }
}
