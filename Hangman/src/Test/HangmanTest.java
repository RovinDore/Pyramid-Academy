package Test;

import org.junit.jupiter.api.*;
import static org.junit.Assert.*;

import javaMain.Main;

public class HangmanTest {
    Main m = new Main();

    @Test
    private void testz(){
        assertNotEquals("", Main.choseWord());
    }
}
