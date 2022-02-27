import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class HangmanTest {
    Hangman game = new Hangman();

    @Test
    void getRandomWord(){
        assertNotEquals("", game.randomWord);
    }

    @Test
    void choseWord(){
        assertNotEquals("", game.choseWord());
    }

    @Test
    void checkStatus(){
        assertEquals(false, game.checkStatus());
    }

    @Test
    void wordContains(){
        assertEquals(false, game.wordContains('c'));
    }

    @Test
    void getTries(){
        assertEquals(0, game.getNumTries());
    }

    @Test
    void addTry(){
        game.addTry();
        game.addTry();
        assertEquals(2, game.numTries);
    }

    @Test
    void getMaxTry(){
        assertEquals(6, game.getMaxTries());
    }
}
