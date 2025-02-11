package code;

public class Converter {
    public static double convertStepsToKilometers(int steps) {
        double oneStepInKilometers = 0.00075;
        return (double) steps * oneStepInKilometers;
    }
}
