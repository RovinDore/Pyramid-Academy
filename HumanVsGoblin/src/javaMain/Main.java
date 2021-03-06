public class Main {

    public static void main(String[] args) {
	    // write your code here
        System.out.println("***************************");
        System.out.println("**** Human Vs Goblins *****");
        System.out.println("***************************");

        try{
            UserInput getInput = new UserInput();

            System.out.println("\nWhat is your name?");
            String playerName = getInput.getString();

            System.out.println("\nWelcome " + playerName + "!!!.");
            System.out.println("\nTo move around the game you should use n/s/w/e.");
            System.out.println("n - North \ns - South \nw - Left \ne - Right");

            HVGGame game = new HVGGame(playerName);
            game.play();
        } catch (Exception e){
            System.out.println("Something went wrong! " + e.getMessage());
        }

    }
}
