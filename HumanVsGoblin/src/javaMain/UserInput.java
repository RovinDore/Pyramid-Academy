import java.util.Scanner;

public class UserInput {
    public String getString(){
        String name = "";
        while (name == ""){
            try{
                Scanner myObj = new Scanner(System.in);
                name = myObj.nextLine();
            } catch (Exception e){
                System.out.println("Wrong input, try again " + e.getMessage());
            }
        }
        return name;
    }

    public static Integer getChoice(){
        int choice = 0;
        while (choice == 0){
            try{
                Scanner myObj = new Scanner(System.in);
                choice = myObj.nextInt();
            } catch (Exception e){
                System.out.println("Wrong input, try again " + e.getMessage());
            }
        }
        return choice;
    }
}
