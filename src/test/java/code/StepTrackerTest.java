package code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class StepTrackerTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void cleanUpStream() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void testCheckInputMonth() {
        var negativeReturn = StepTracker.checkInputMonth("test");
        var negativeReturnNumber = StepTracker.checkInputMonth("202!:#?.,");
        Assertions.assertTrue(negativeReturn < 0);
        Assertions.assertTrue(negativeReturnNumber < 0);

        var nonNegativeReturnJanuary = StepTracker.checkInputMonth("january");
        var nonNegativeReturnDecember = StepTracker.checkInputMonth(" dEcEmBeR  ");
        Assertions.assertTrue(nonNegativeReturnJanuary >= 0);
        Assertions.assertTrue(nonNegativeReturnDecember >= 0);
    }

    @Test
    public void testCheckInputDay() {
        var negativeReturnZero = StepTracker.checkInputDay("0");
        var negativeReturnWord = StepTracker.checkInputDay("one");
        var negativeReturnThirtyOne = StepTracker.checkInputDay("31");
        var negativeReturnMinusOne = StepTracker.checkInputDay("-1");
        Assertions.assertTrue(negativeReturnZero < 0);
        Assertions.assertTrue(negativeReturnWord < 0);
        Assertions.assertTrue(negativeReturnThirtyOne < 0);
        Assertions.assertTrue(negativeReturnMinusOne < 0);

        var positiveReturnOne = StepTracker.checkInputDay("1");
        var positiveReturnThirty = StepTracker.checkInputDay("30");
        var positiveReturnFifteen = StepTracker.checkInputDay("15");
        Assertions.assertTrue(positiveReturnOne > 0);
        Assertions.assertTrue(positiveReturnThirty > 0);
        Assertions.assertTrue(positiveReturnFifteen > 0);
    }

    @Test
    public void testCheckInputSteps() {
        var negativeReturnMinusOne = StepTracker.checkInputSteps("-1");
        var negativeReturnWord = StepTracker.checkInputSteps("o-n-e");
        Assertions.assertTrue(negativeReturnMinusOne < 0);
        Assertions.assertTrue(negativeReturnWord < 0);

        var nonNegativeReturnZero = StepTracker.checkInputSteps("0");
        var nonNegativeReturnOne = StepTracker.checkInputSteps("1");
        var nonNegativeReturnTenThousand = StepTracker.checkInputSteps("1-00_0_0");
        Assertions.assertTrue(nonNegativeReturnZero > -1);
        Assertions.assertTrue(nonNegativeReturnOne > -1);
        Assertions.assertTrue(nonNegativeReturnTenThousand > -1);
    }

    @Test
    public void testAskUserJanuary() {
        ByteArrayInputStream input = new ByteArrayInputStream("january\n1\n3_00-0\n".getBytes());
        System.setIn(input);

        Scanner scanner = new Scanner(System.in);

        int[] result = StepTracker.askUser(scanner);

        Assertions.assertTrue(output.toString().contains(
                "Enter the name of the month for which you want to add data: "));
        Assertions.assertTrue(output.toString().contains(
                "Enter the number of the day for which you want to add data: "));
        Assertions.assertTrue(output.toString().contains("Enter the number of steps walked: "));
        Assertions.assertTrue(output.toString().contains("Your data has been saved! What you wanna do next?"));

        Assertions.assertEquals(0, result[0]);
        Assertions.assertEquals(1, result[1]);
        Assertions.assertEquals(3000, result[2]);
    }

    @Test
    public void testAskUserDecember() {
        ByteArrayInputStream input = new ByteArrayInputStream("december \n30\n50 000\n".getBytes());
        System.setIn(input);

        Scanner scanner = new Scanner(System.in);

        int[] result = StepTracker.askUser(scanner);

        Assertions.assertTrue(output.toString().contains(
                "Enter the name of the month for which you want to add data: "));
        Assertions.assertTrue(output.toString().contains(
                "Enter the number of the day for which you want to add data: "));
        Assertions.assertTrue(output.toString().contains("Enter the number of steps walked: "));
        Assertions.assertTrue(output.toString().contains("Your data has been saved! What you wanna do next?"));

        Assertions.assertEquals(11, result[0]);
        Assertions.assertEquals(30, result[1]);
        Assertions.assertEquals(50000, result[2]);
    }

    @Test
    public void testAskUserIncorrectData() {
        ByteArrayInputStream input = new ByteArrayInputStream("RRRRR\nmay\n30\n50 000\n".getBytes());
        System.setIn(input);

        Scanner scanner = new Scanner(System.in);

        StepTracker.askUser(scanner);

        Assertions.assertTrue(output.toString().contains("WARNING!!! You entered incorrect data. Lets try again."));
    }
}
