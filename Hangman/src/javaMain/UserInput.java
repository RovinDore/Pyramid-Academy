package javaMain;

import java.util.Scanner;

public class UserInput {

    public static String getString(){
        String name = "";
        while (name == ""){
            try{
                Scanner myObj = new Scanner(System.in);
                name = myObj.nextLine();
            } catch (Exception e){
                System.out.println("Wrong input, try again");
            }
        }
        return name;
    }

    public static Integer getChoice(){
        int choice = 0;
        boolean err = false;
        while (choice == 0){
            try{

                Scanner myObj = new Scanner(System.in);
                choice = myObj.nextInt();
            } catch (Exception e){
                System.out.println("Wrong input, try again");
            }
        }
        return choice;
    }
}
