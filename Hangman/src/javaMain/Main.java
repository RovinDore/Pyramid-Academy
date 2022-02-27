import java.util.HashMap;

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
        Hangman hangmanGame = new Hangman();
        String randomWord = hangmanGame.getRandomWord();
        HashMap<Character, Integer> alreadyTried = new HashMap<>();
        boolean gotWord = false;
        final int maxTries = hangmanGame.getMaxTries();
        UserInput getInput = new UserInput();

        while (hangmanGame.getNumTries() <= maxTries){
            gotWord = hangmanGame.checkStatus();
            if(gotWord){
                System.out.println("Congrats you won! The word was " + randomWord);
                break;
            } else if(!gotWord && hangmanGame.getNumTries() >= maxTries){
                System.out.println("You lost. You didn't get the word correct. which was " + randomWord);
                break;
            }

            drawStickMan(hangmanGame.getNumTries());

            System.out.print("Word: ");
            hangmanGame.hangWordBuilder();
            if(alreadyTried.size() > 0) hangmanGame.missedWordBuilder(alreadyTried);

            System.out.println("Take a guess.");

            String guessCharacter;
            try{
                guessCharacter = getInput.getString();
            } catch(Exception e){
                System.out.println("Something went wrong");
                break;
            }

            char userGuess = guessCharacter.charAt(0);
            if(alreadyTried.get(userGuess) == null){
                if(!hangmanGame.wordContains(userGuess)){
                    System.out.println("Wrong, there aren't any '" + userGuess + "'\n");
                    hangmanGame.addTry();
                }
                alreadyTried.put(guessCharacter.charAt(0), 1);
            } else System.out.println("You already guessed that.");

        }
    }

    public static void drawStickMan(int numTries){
        System.out.println("--------------------------");
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
