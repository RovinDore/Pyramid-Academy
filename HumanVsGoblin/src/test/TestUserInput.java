import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserInput {
    @Test
    void test_getString(){
        String data = "Username";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        UserInput getUserInput = new UserInput();
        assertEquals("Username", getUserInput.getString(), "Error");
    }

}
