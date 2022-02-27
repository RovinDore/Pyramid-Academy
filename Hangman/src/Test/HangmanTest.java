package Test;

import org.junit.jupiter.api.*;
import static org.junit.Assert.*;

import javaMain.Hangman;

public class HangmanTest {
    Hangman m = new Hangman();

    @Test
    private void getRandomWord(){
        assertNotEquals("", m.choseWord());
    }

    @Test
    private void checkStatus(){
        assertEquals(false, m.checkStatus());
    }

    @Test
    private void wordContains(){
        assertEquals(false, m.wordContains('c'));
    }
}
