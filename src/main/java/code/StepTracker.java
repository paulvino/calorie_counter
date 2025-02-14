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

    // конструктор класса
    public StepTracker() {
        // при инициализации класса StepTracker автоматически создаются 12 экземпляров класса MonthData
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

        // массив, в котором агрегируем валидную информацию от пользователя
        int[] answers = new int[3];

        // проходимся по массиву вопросов
        for (int i = 0; i < QUESTIONS.length; i++) {
            var isIncorrect = true;

            // по умолчанию считаем, что пользователь ввел некорректные данные, если это не так – ниже флаг меняется
            // на false
            while (isIncorrect) {
                // переменная для хранения результата определения корректности пользовательского ввода
                var checkResult = -1;
                // вывод на экран вопроса пользователю
                System.out.print(QUESTIONS[i]);

                // получение ответа пользователя и его обработка
                var answer = scanner.next();
                answer += scanner.nextLine();
                answer = answer.replaceAll("\\s+", "");

                // switch для вызова проверочных методов (проверяем пользовательский ввод на корректность)
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
        System.out.print("\nSpecify the name of the month for which you want to see statistics and press Enter: ");

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
        var stepsPerDay = currentMonth.getStepsPerDay();

        for (int i = 1; i < 31; i++) {
            System.out.print(i + " день: " + stepsPerDay.getOrDefault(i, 0));
            if (i < 30) {
                System.out.print(", ");
            }
        }

        var currentMonthName = currentMonth.getMonthName();
        var currentMonthTotalSteps = currentMonth.getTotalSteps();
        var currentMonthMaxSteps = currentMonth.getMaxSteps();
        var currentMonthAverageSteps = currentMonth.getAverageSteps();
        var currentMonthInKilometers = Converter.convertStepsToKilometers(currentMonthTotalSteps);
        var currentMonthKcalBurned = Converter.kcalBurned(currentMonthTotalSteps);
        System.out.println("\nTotal number of steps walked in " + currentMonthName + ": " + currentMonthTotalSteps);
        System.out.println("\nMaximum number of steps walked in " + currentMonthName + ": " + currentMonthMaxSteps);
        System.out.println("\nAverage number of steps walked in " + currentMonthName + ": " + currentMonthAverageSteps);
        System.out.println("\nDistance in kilometers walked in " + currentMonthName + ": " + currentMonthInKilometers);
        System.out.println("\nKilocalories burned in " + currentMonthName + ": " + currentMonthKcalBurned);
    }

    class MonthData {
        String monthName;
        HashMap<Integer, Integer> stepsPerDay = new HashMap<>();

        MonthData() {
            for (int i = 1; i < 31; i++) {
                stepsPerDay.put(i, 0);
            }
        }

        private void setMonthName(String monthName) {
            MonthData.this.monthName = monthName;
        }

        public String getMonthName() {
            return MonthData.this.monthName;
        }

        private void setStepsPerDay(int dayNumber, int numberOfSteps) {
            MonthData.this.stepsPerDay.put(dayNumber, numberOfSteps);
        }

        public HashMap<Integer, Integer> getStepsPerDay() {
            return this.stepsPerDay;
        }

        public int getTotalSteps() {
            int total = 0;
            for (int i : MonthData.this.stepsPerDay.keySet()) {
                total += stepsPerDay.get(i);
            }
            return total;
        }

        public int getMaxSteps() {
            int max = 0;
            for (int i : MonthData.this.stepsPerDay.keySet()) {
                max = stepsPerDay.get(i) > max ? stepsPerDay.get(i) : max;
            }
            return max;
        }

        public double getAverageSteps() {
            return ((double) getTotalSteps() / stepsPerDay.size());
        }
    }
}
