package code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConverterTest {
    @Test
    public void testConvertStepsToKilometers() {
        var zeroSteps = Converter.convertStepsToKilometers(0);
        Assertions.assertEquals(0.0, zeroSteps);

        var tenSteps = Converter.convertStepsToKilometers(10);
        Assertions.assertEquals(0.0075, tenSteps);

        var oneMillionSteps = Converter.convertStepsToKilometers(1000000);
        Assertions.assertEquals(750.0, oneMillionSteps);

        var twoHundredFiftyFourThousandFiveHundredTwentyThree = Converter.convertStepsToKilometers(254523);
        Assertions.assertEquals(190.89225, twoHundredFiftyFourThousandFiveHundredTwentyThree);
    }
}
