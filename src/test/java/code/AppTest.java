package code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class AppTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void testPrintMenu() {
        App.printMainMenu();
        Assertions.assertEquals("\nSelect a number from the ones below and press Enter:\n"
                + "  1 - Enter the number of steps for the day\n"
                + "  2 - Print the last month stats\n"
                + "  3 - Change the goal for the steps per day\n"
                + "  0 - Exit\n", output.toString());
    }

    @Test
    public void testRun() {
        ByteArrayInputStream input = new ByteArrayInputStream("2\n3\n4\none\n0\n".getBytes());
        System.setIn(input);

        Scanner scanner = new Scanner(System.in);

        App.run(scanner);

        Assertions.assertTrue(output.toString().contains("Excellent"));
        Assertions.assertTrue(output.toString().contains("Perfect"));
        Assertions.assertTrue(output.toString().contains("You entered wrong number, try again"));
        Assertions.assertTrue(output.toString().contains("See you soon!"));
    }

    @Test
    public void testRunOne() {
        ByteArrayInputStream input = new ByteArrayInputStream("1\nJanuary\n15\n10000\n0\n".getBytes());
        System.setIn(input);

        Scanner scanner = new Scanner(input);

        App.run(scanner);

        Assertions.assertTrue(output.toString().contains(
                "Enter the name of the month for which you want to add data: "));
    }
}
