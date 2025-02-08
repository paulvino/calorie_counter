package code;

//import java.util.ArrayList;
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

    // метод для вывода на экран вопросов пользователю
    public static int[] askUser(Scanner scanner) {

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

        System.out.print("\nYour data has been saved! What you wanna do next?");
        return answers;
    }
//
//    // метод для отображения статистики пользователю за конкретный выбранный им месяц
//    public static void printStats(Scanner scanner) {
//        System.out.println("\nSpecify the name of the month for which you want to output statistics and press Enter");
//
//        var stats = new ArrayList<>();
//
//        var answer = scanner.next();
//        answer += answer.replaceAll("\\s+", "");
//
//        boolean isIncorrect = true;
//
//        while (isIncorrect) {
//
//
//
//            if (!isIncorrect) {
//                break;
//            }
//
//            System.out.println("\nWARNING!!! You entered incorrect month. Lets try again.");
//        }
//    }

    class MonthData {
        String monthName;
        HashMap<Integer, Integer> stepsPerDay;

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
