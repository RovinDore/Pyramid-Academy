package test;

import com.company.UserInput;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserInput {

    UserInput userInput;

    @Test
    void test_getString(){
        String data = "Username";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        UserInput getUserInput = new UserInput();
        assertEquals("Username", getUserInput.getString(), "Error");
    }

    @Test
    void test_getChoice(){
        int data = 1;
        System.setIn(new ByteArrayInputStream(String.valueOf(data).getBytes()));

        UserInput getUserInput = new UserInput();

        assertEquals(1, getUserInput.getChoice(), "Error");
    }
}

