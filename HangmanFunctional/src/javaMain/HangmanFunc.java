//package javaMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HangmanFunc {
    private final String[] WORDS = {
            "ABSTRACT", "ASSERT", "BOOLEAN", "BREAK", "BYTE",
            "CASE", "CATCH", "CHAR", "CLASS", "CONST",
            "CONTINUE", "DEFAULT", "DOUBLE", "DO", "ELSE",
            "ENUM", "EXTENDS", "FALSE", "FINAL", "FINALLY",
            "FLOAT", "FOR", "GOTO", "IF", "IMPLEMENTS",
            "IMPORT", "INSTANCE", "INT", "INTERFACE",
            "LONG", "NATIVE", "NEW", "NULL", "PACKAGE",
            "PRIVATE", "PROTECTED", "PUBLIC", "RETURN",
            "SHORT", "STATIC", "STRICTFP", "SUPER", "SWITCH",
            "SYNCHRONIZED", "THIS", "THROW", "THROWS",
            "TRANSIENT", "TRUE", "TRY", "VOID", "VOLATILE", "WHILE"
    };

    private String randomWord;
    private int numTries;
    private final int maxTries = 6;
    private List<String> randomWordCharSet, hangWord;
    private int iterate = 0, playerScore = 100, highestScore = 0;
    private String playerName = "";

    public HangmanFunc(){
        this.randomWord = choseWord();
        this.numTries = 0;
    }

    //Getters & Setters
    public void addTry(){ ++this.numTries; }
    public int getNumTries(){ return this.numTries; }
    public String getRandomWord(){ return this.randomWord; }
    public int getMaxTries(){ return this.maxTries; }
    public HangmanFunc setPlayerName(String name){ this.playerName = name; return this;}
    public HangmanFunc addToPlayerScore(int score){ this.playerScore += score; return this;}

    //Methods
    public HangmanFunc addToHistory(){
        String fileName = "playerStats.txt";
        String fileData = "";
        try {
            File myObj = getFileFromResource(fileName);
            Scanner myReader = new Scanner(myObj);
            String[] temp = new String[2];
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                temp = data.split("-");
                fileData = fileData + data + "\n";
                if(Integer.parseInt(temp[1]) > highestScore) highestScore = Integer.parseInt(temp[1]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("src\\resources\\"+fileName);
            myWriter.write(fileData + playerName + "-" + playerScore);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(playerScore > highestScore) System.out.println("Congratulations you scored the highest! " + playerScore);

        return this;
    }

    public HangmanFunc showHangman(){
        String fileName = "Strike";
        try {
            File myObj = getFileFromResource(fileName + numTries + ".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return this;
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }

    }

    public String choseWord(){
        Random wordIndex = new Random();
        String finalWord = WORDS[wordIndex.nextInt(WORDS.length)];
        this.hangWord = Arrays.stream(new String[finalWord.length()]).map(i -> "_").collect(Collectors.toList());
        this.randomWordCharSet = Arrays.asList(finalWord.split(""));

        return finalWord;
    }

    public boolean checkStatus(){
        List<String> check = hangWord.stream().filter(letter -> letter == "_").collect(Collectors.toList());

        return check.size() == 0;
    }

    public boolean compareStatus(String letter){
        return letter != randomWordCharSet.get(iterate + 1);
    }

    public boolean wordContains(char character){
        this.iterate = 0;
        String userGuess = String.valueOf(Character.toUpperCase(character));
        List<String> contained = this.randomWordCharSet.stream().filter(letter -> compareLetter(letter, userGuess)).collect(Collectors.toList());

        return contained.size() >= 1;
    }

    public boolean compareLetter(String letter, String userGuess){
        boolean found = false;
        String toCheck = hangWord.get(iterate);
        if(toCheck.equals("_") && letter.equals(userGuess)) {
            hangWord.set(iterate, userGuess);
            found = true;
        }
        ++this.iterate;
        return found;
    }

    public void hangWordBuilder(){
        hangWord.stream().forEach(letter -> {
            letter = letter == null ? "_" : letter;
            System.out.print(letter);
        });
        System.out.println();
    }

    public void missedWordBuilder(HashMap<Character, Integer> alreadyTried){
        System.out.print("Already tried: ");
        Arrays.stream(alreadyTried.keySet().toArray()).forEach(userTry ->  System.out.print(userTry.toString().toUpperCase()));
        System.out.println();
    }
}