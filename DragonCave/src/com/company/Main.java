package com.company;
import java.util.Scanner;  // Import the Scanner class

public class Main {

//    Ingram ingram;

    public static void main(String[] args) {
	// write your code here
        UserInput getUserInput = new UserInput();
        System.out.println("You are in a land full of dragons. In front of you,");
        System.out.println("you see two caves. In one cave, the dragon is friendly");
        System.out.println("and will share his treasure with you. The other dragon");
        System.out.println("is greedy and hungry and will eat you on sight.\n");
        System.out.println("Which cave will you go into? (1 or 2)");

        int userInput = 0;
        try{
            userInput = getUserInput.getChoice();  // Read user input
        } catch (Exception e){
            System.out.println("Something went wrong");
        }

        if(userInput == 1){
            System.out.println("You approach the cave...");
            System.out.println("It is dark and spooky...");
            System.out.println("A large dragon jumps out in front of you! He opens his jaws and...");
            System.out.println("Gobbles you down in one bite!");
        } else if(userInput == 2){
            System.out.println("Congrats!!");
            System.out.println("The friendly dragon is sharing the treasure with you");
        } else System.out.println("Input must be 1 or 2");
    }
}
