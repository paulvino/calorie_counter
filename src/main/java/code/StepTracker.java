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

    // метод для вопросов пользователю
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
                        checkResult = checkInputMonth(answer);
                    }
                    case 1 -> {
                        checkResult = checkInputDay(answer);
                    }
                    case 2 -> {
                        checkResult = checkInputSteps(answer);
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

    // метод проверяет, является ли введенное значение месяцем, и возвращает индекс (порядковый номер) месяца,
    // если введенное значение является месяцем. Если значение не является месяцем, метод возвращает -1
    public static int checkInputMonth(String input) {
        input = input.trim();
        int monthIndex = -1;
        // сначала проверим, является ли введенное значение строкой
        if (input.trim().matches("[a-zA-z]+")) {
            // проверим, является ли введенное значение месяцем
            // для этого проходимся по массиву месяцев и сравниваем ввод с имеющимися в массиве значениями
            for (int i = 0; i < MONTHS.length; i++) {
                // если ввод совпал с одним из значений в массиве, то получаем "индекс" - порядковый номер месяца
                // если не совпал, то "индекс" имеет отрицательное значение
                monthIndex = MONTHS[i].equalsIgnoreCase(input) ? i : -1;

                // в случае, если у нас есть положительный "индекс" (порядковый номер месяца), получаем объект
                // этого месяца
                if (monthIndex >= 0) {
                    return monthIndex;
                }
            }
        }

        return monthIndex;
    }

    public static int checkInputDay(String input) {
        int dayNumber = -1;
        input = input.replaceAll("(?<=.)[^0-9]", "");
        if (input.matches("[0-9-]+")) {
            var intInputDay = Integer.parseInt(input);
            dayNumber = (0 < intInputDay) && (intInputDay < 31) ? intInputDay : -1;
        }

        return dayNumber;
    }

    public static int checkInputSteps(String input) {
        int stepsNumber = -1;
        input = input.replaceAll("(?<=.)[^0-9]", "");
        if (input.matches("[0-9-]+")) {
            var intInputSteps = Integer.parseInt(input);
            stepsNumber = intInputSteps >= 0 ? intInputSteps : -1;
        }

        return stepsNumber;
    }

    // пользователь должен указать название месяца, номер дня и количество шагов, пройденных в этот день
    // количество шагов должно быть не отрицательным
    // в месяце ровно 30 дней
    // если статистики за день нет, количество шагов равно нулю

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
