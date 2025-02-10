package code;

import java.util.HashMap;
import java.util.Scanner;

public class StepTracker {

    public static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August",
        "September", "October", "November", "December"};
    public static final String[] QUESTIONS = {
        "\nEnter the name of the month for which you want to add data: ",
        "\nEnter the number of the day for which you want to add data: ",
        "\nEnter the number of steps walked: "
    };

    HashMap<Integer, MonthData> monthToData = new HashMap<>();

    public StepTracker() {
        for (int i = 0; i < 12; i++) {
            monthToData.put(i, new MonthData());

            var currentMonth = monthToData.get(i);
            currentMonth.setMonthName(MONTHS[i]);
        }
    }

    public HashMap<Integer, MonthData> getMonthToData() {
        return this.monthToData;
    }

    // метод для вывода на экран вопросов пользователю
    public void askUser(Scanner scanner) {

        int[] answers = new int[3];

        for (int i = 0; i < QUESTIONS.length; i++) {
            var isIncorrect = true;

            while (isIncorrect) {
                var checkResult = -1;
                System.out.print(QUESTIONS[i]);
                var answer = scanner.next();
                answer += scanner.nextLine();
                answer = answer.replaceAll("\\s+", "");

                switch (i) {
                    case 0 -> {
                        checkResult = UsersInputChecker.checkInputMonth(answer);
                    }
                    case 1 -> {
                        checkResult = UsersInputChecker.checkInputDay(answer);
                    }
                    case 2 -> {
                        checkResult = UsersInputChecker.checkInputSteps(answer);
                    }
                    default -> System.out.println("\ndefault");
                }

                if (checkResult >= 0) {
                    answers[i] = checkResult;
                    isIncorrect = false;
                    break;
                }

                System.out.println("\nWARNING!!! You entered incorrect data. Lets try again.");
            }
        }

        // сохраняем полученные от пользователя ответы в MonthData()
        var currentMonth = getMonthToData().get(answers[0]);
        currentMonth.setStepsPerDay(answers[1], answers[2]);

        System.out.print("\nSUCCESS! Your data has been saved. What you wanna do next?");
    }

    // метод для отображения статистики пользователю за конкретный выбранный им месяц
    public void printStats(Scanner scanner) {
        System.out.println("\nSpecify the name of the month for which you want to output statistics and press Enter");

        var monthNumber = -1;
        boolean isIncorrect = true;

        while (isIncorrect) {
            var answer = scanner.next();
            monthNumber = UsersInputChecker.checkInputMonth(answer);

            isIncorrect = monthNumber < 0;

            if (!isIncorrect) {
                break;
            }
            System.out.println("\nWARNING!!! You entered incorrect month. Lets try again.");
        }

        var currentMonth = getMonthToData().get(monthNumber);

        System.out.println("name: " + currentMonth.monthName + " steps: " + currentMonth.stepsPerDay);
    }

    class MonthData {
        String monthName;
        HashMap<Integer, Integer> stepsPerDay;

        MonthData() {
            this.stepsPerDay = new HashMap<>();
        }

        public void setMonthName(String monthName) {
            MonthData.this.monthName = monthName;
        }

        public String getMonthName() {
            return this.monthName;
        }

        public void setStepsPerDay(int dayNumber, int numberOfSteps) {
            MonthData.this.stepsPerDay.put(dayNumber, numberOfSteps);
        }

        public HashMap<Integer, Integer> getStepsPerDay() {
            return this.stepsPerDay;
        }
    }
}
