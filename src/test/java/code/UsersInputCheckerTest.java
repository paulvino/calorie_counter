package code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsersInputCheckerTest {
    @Test
    public void testCheckInputMonth() {
        var negativeReturn = UsersInputChecker.checkInputMonth("test");
        var negativeReturnNumber = UsersInputChecker.checkInputMonth("202!:#?.,");
        Assertions.assertTrue(negativeReturn < 0);
        Assertions.assertTrue(negativeReturnNumber < 0);

        var nonNegativeReturnJanuary = UsersInputChecker.checkInputMonth("january");
        var nonNegativeReturnDecember = UsersInputChecker.checkInputMonth(" dEcEmBeR  ");
        Assertions.assertTrue(nonNegativeReturnJanuary >= 0);
        Assertions.assertTrue(nonNegativeReturnDecember >= 0);
    }

    @Test
    public void testCheckInputDay() {
        var negativeReturnZero = UsersInputChecker.checkInputDay("0");
        var negativeReturnWord = UsersInputChecker.checkInputDay("one");
        var negativeReturnThirtyOne = UsersInputChecker.checkInputDay("31");
        var negativeReturnMinusOne = UsersInputChecker.checkInputDay("-1");
        Assertions.assertTrue(negativeReturnZero < 0);
        Assertions.assertTrue(negativeReturnWord < 0);
        Assertions.assertTrue(negativeReturnThirtyOne < 0);
        Assertions.assertTrue(negativeReturnMinusOne < 0);

        var positiveReturnOne = UsersInputChecker.checkInputDay("1");
        var positiveReturnThirty = UsersInputChecker.checkInputDay("30");
        var positiveReturnFifteen = UsersInputChecker.checkInputDay("15");
        Assertions.assertTrue(positiveReturnOne > 0);
        Assertions.assertTrue(positiveReturnThirty > 0);
        Assertions.assertTrue(positiveReturnFifteen > 0);
    }

    @Test
    public void testCheckInputSteps() {
        var negativeReturnMinusOne = UsersInputChecker.checkInputSteps("-1");
        var negativeReturnWord = UsersInputChecker.checkInputSteps("o-n-e");
        Assertions.assertTrue(negativeReturnMinusOne < 0);
        Assertions.assertTrue(negativeReturnWord < 0);

        var nonNegativeReturnZero = UsersInputChecker.checkInputSteps("0");
        var nonNegativeReturnOne = UsersInputChecker.checkInputSteps("1");
        var nonNegativeReturnTenThousand = UsersInputChecker.checkInputSteps("1-00_0_0");
        Assertions.assertTrue(nonNegativeReturnZero > -1);
        Assertions.assertTrue(nonNegativeReturnOne > -1);
        Assertions.assertTrue(nonNegativeReturnTenThousand > -1);
    }
}
