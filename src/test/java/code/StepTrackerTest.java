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
    public void testAskUserJanuary() {
        ByteArrayInputStream input = new ByteArrayInputStream("january\n1\n3_00-0\n".getBytes());
        System.setIn(input);

        Scanner scanner = new Scanner(System.in);

        StepTracker st = new StepTracker();
        st.askUser(scanner);

        Assertions.assertTrue(output.toString().contains(
                "Enter the name of the month for which you want to add data: "));
        Assertions.assertTrue(output.toString().contains(
                "Enter the number of the day for which you want to add data: "));
        Assertions.assertTrue(output.toString().contains("Enter the number of steps walked: "));
        Assertions.assertTrue(output.toString().contains("SUCCESS! Your data has been saved. What you wanna do next?"));

        Assertions.assertEquals("January", st.getMonthToData().get(0).monthName);
        Assertions.assertEquals(3000, st.getMonthToData().get(0).getStepsPerDay().get(1));
    }

    @Test
    public void testAskUserDecember() {
        ByteArrayInputStream input = new ByteArrayInputStream("december \n30\n50 000\n".getBytes());
        System.setIn(input);

        Scanner scanner = new Scanner(System.in);

        StepTracker st = new StepTracker();
        st.askUser(scanner);

        Assertions.assertTrue(output.toString().contains(
                "Enter the name of the month for which you want to add data: "));
        Assertions.assertTrue(output.toString().contains(
                "Enter the number of the day for which you want to add data: "));
        Assertions.assertTrue(output.toString().contains("Enter the number of steps walked: "));
        Assertions.assertTrue(output.toString().contains("SUCCESS! Your data has been saved. What you wanna do next?"));

        Assertions.assertEquals("December", st.getMonthToData().get(11).monthName);
        Assertions.assertEquals(50000, st.getMonthToData().get(11).getStepsPerDay().get(30));
    }

    @Test
    public void testAskUserIncorrectData() {
        ByteArrayInputStream input = new ByteArrayInputStream("RRRRR\nmay\n30\n50 000\n".getBytes());
        System.setIn(input);

        Scanner scanner = new Scanner(System.in);

        StepTracker st = new StepTracker();
        st.askUser(scanner);

        Assertions.assertTrue(output.toString().contains("WARNING!!! You entered incorrect data. Lets try again."));
    }

    @Test
    public void testPrintStatsEmpty() {
        ByteArrayInputStream input = new ByteArrayInputStream("may\n".getBytes());
        System.setIn(input);

        Scanner scanner = new Scanner(System.in);

        StepTracker st = new StepTracker();
        st.printStats(scanner);

        Assertions.assertTrue(output.toString().contains("1 день: 0, 2 день: 0, 3 день: 0, 4 день: 0, 5 день: 0"));
        Assertions.assertTrue(output.toString().contains("26 день: 0, 27 день: 0, 28 день: 0, 29 день: 0, 30 день: 0"));
        Assertions.assertTrue(output.toString().contains("Total number of steps walked in May: 0"));
        Assertions.assertTrue(output.toString().contains("Maximum number of steps walked in May: 0"));
        Assertions.assertTrue(output.toString().contains("Average number of steps walked in May: 0.0"));
        Assertions.assertTrue(output.toString().contains("Distance in kilometers walked in May: 0.0"));
        Assertions.assertTrue(output.toString().contains("Kilocalories burned in May: 0.0"));
        Assertions.assertFalse(output.toString().contains("31 день"));
        Assertions.assertFalse(output.toString().contains("-1 день"));
    }

    @Test
    public void testPrintStatsIncorrectMonth() {
        ByteArrayInputStream input = new ByteArrayInputStream("qwerty\nmay\n".getBytes());
        System.setIn(input);

        Scanner scanner = new Scanner(System.in);

        StepTracker st = new StepTracker();
        st.printStats(scanner);

        Assertions.assertTrue(output.toString().contains("WARNING!!! You entered incorrect month. Lets try again."));
        Assertions.assertTrue(output.toString().contains("Total number of steps walked in May: 0"));
        Assertions.assertTrue(output.toString().contains("Maximum number of steps walked in May: 0"));
        Assertions.assertTrue(output.toString().contains("Average number of steps walked in May: 0.0"));
        Assertions.assertTrue(output.toString().contains("Distance in kilometers walked in May: 0.0"));
        Assertions.assertTrue(output.toString().contains("Kilocalories burned in May: 0.0"));
    }

    @Test
    public void testPrintStatsNotEmptyFirstDay() {
        ByteArrayInputStream inputData = new ByteArrayInputStream("may\n1\n100\n".getBytes());
        ByteArrayInputStream inputStat = new ByteArrayInputStream("may\n".getBytes());
        System.setIn(inputData);

        Scanner scannerData = new Scanner(System.in);
        StepTracker st = new StepTracker();
        st.askUser(scannerData);

        System.setIn(inputStat);
        Scanner scannerStat = new Scanner(System.in);
        st.printStats(scannerStat);

        Assertions.assertTrue(output.toString().contains("1 день: 100, 2 день: 0, "));
        Assertions.assertTrue(output.toString().contains("Total number of steps walked in May: 100"));
        Assertions.assertTrue(output.toString().contains("Maximum number of steps walked in May: 100"));
        Assertions.assertTrue(output.toString().contains("Average number of steps walked in May: 3.3333333333333335"));
        Assertions.assertTrue(output.toString().contains("Distance in kilometers walked in May: 0.075"));
        Assertions.assertTrue(output.toString().contains("Kilocalories burned in May: 5000.0"));
    }

    @Test
    public void testPrintStatsNotEmptyThirtiethDay() {
        ByteArrayInputStream inputData = new ByteArrayInputStream("december\n30\n3000\n".getBytes());
        ByteArrayInputStream inputStat = new ByteArrayInputStream("december\n".getBytes());
        System.setIn(inputData);

        Scanner scannerData = new Scanner(System.in);
        StepTracker st = new StepTracker();
        st.askUser(scannerData);

        System.setIn(inputStat);
        Scanner scannerStat = new Scanner(System.in);
        st.printStats(scannerStat);

        Assertions.assertTrue(output.toString().contains("30 день: 3000"));
        Assertions.assertTrue(output.toString().contains("Total number of steps walked in December: 3000"));
        Assertions.assertTrue(output.toString().contains("Maximum number of steps walked in December: 3000"));
        Assertions.assertTrue(output.toString().contains("Average number of steps walked in December: 100"));
        Assertions.assertTrue(output.toString().contains("Distance in kilometers walked in December: 2.25"));
        Assertions.assertTrue(output.toString().contains("Kilocalories burned in December: 150000.0"));
    }
}
