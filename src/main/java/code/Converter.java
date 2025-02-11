package code;

public class Converter {
    public static double convertStepsToKilometers(int steps) {
        double oneStepInKilometers = 0.00075;
        return (double) steps * oneStepInKilometers;
    }

    public static double kcalBurned(int steps) {
        double oneStepCal = 50;
        return (double) (steps * oneStepCal) / 1000;
    }
}
