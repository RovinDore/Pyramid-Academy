package com.company;
import java.util.Scanner;  // Import the Scanner class

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("You are in a land full of dragons. In front of you,");
        System.out.println("you see two caves. In one cave, the dragon is friendly");
        System.out.println("and will share his treasure with you. The other dragon");
        System.out.println("is greedy and hungry and will eat you on sight.");
        System.out.println("Which cave will you go into? (1 or 2)");
        int userInput = myObj.nextInt();  // Read user input

        if(userInput == 1){
            System.out.println("You approach the cave...");
            System.out.println("It is dark and spooky...");
            System.out.println("A large dargon jumps out in front of you! He opens his jaws and...");
            System.out.println("Gobbles you down in one bite!");
        } else if(userInput == 2){
            System.out.println("Congrats!!");
            System.out.println("The friendly dragon is sharing the treasure with you");
        }
    }
}
