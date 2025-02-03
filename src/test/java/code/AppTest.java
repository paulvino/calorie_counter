package code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AppTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testGetMenu() {
        Utils.getMenu();
        Assertions.assertEquals("Hello! This is a Calorie tracker\n"
                + "Select a number from the ones below and press Enter:\n"
                + "  1 - Enter the number of steps for the day\n"
                + "  2 - Print the last month stats\n"
                + "  3 - Change the goal for the steps per day\n"
                + "  0 - Exit\n"
                + "Your choice: ",
                output.toString());
    }

    @Test
    public void testRouteExit() {
        Utils.getRoute("0");
        Assertions.assertEquals("See you next time!\n", output.toString());
    }

    @Test
    public void testRouteDefaultWrongNumber() {
        Utils.getRoute("4");
        Assertions.assertEquals("You entered wrong number, try again\n", output.toString());
    }

    @Test
    public void testRouteDefaultText() {
        Utils.getRoute("one");
        Assertions.assertEquals("You entered wrong number, try again\n", output.toString());
    }
}
