package code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        Assertions.assertTrue(negativeReturn < 0);

        var nonNegativeReturnJanuary = StepTracker.checkInputMonth("january");
        var nonNegativeReturnDecember = StepTracker.checkInputMonth(" dEcEmBeR  ");
        Assertions.assertTrue(nonNegativeReturnJanuary >= 0);
        Assertions.assertTrue(nonNegativeReturnDecember >= 0);
    }
}
