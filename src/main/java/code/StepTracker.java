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
                System.out.print(QUESTIONS[i]);
                String answer = scanner.next();

                int checkResult = -1;
                switch (i) {
                    case 0 -> {
                        checkResult = checkInputMonth(answer);
                    }
                    case 1 -> System.out.println("not ready for case 2");
                    case 2 -> System.out.println("not ready for case 3");
                    default -> System.out.println("default");
                }

                if (checkResult >= 0) {
                    answers[i] = checkResult;
                    isIncorrect = false;
                    break;
                }

                System.out.println("You entered incorrect data. Lets try again.");
            }
        }

        return answers;
    }

    // метод проверяет, является ли введенное значение месяцем, и возвращает индекс (порядковый номер) месяца,
    // если введенное значение является месяцем. Если значение не является месяцем, метод возвращает -1
    public static int checkInputMonth(String input) {
        var validInput = input.trim().toLowerCase().toLowerCase();
        // сначала проверим, является ли введенное значение строкой
        if (validInput.matches("[a-zA-z]+")) {
            // проверим, является ли введенное значение месяцем
            // для этого проходимся по массиву месяцев и сравниваем ввод с имеющимися в массиве значениями
            for (int i = 0; i < MONTHS.length; i++) {
                // если ввод совпал с одним из значений в массиве, то получаем "индекс" - порядковый номер месяца
                // если не совпал, то "индекс" имеет отрицательное значение
                int monthIndex = MONTHS[i].equalsIgnoreCase(validInput) ? i : -1;

                // в случае, если у нас есть положительный "индекс" (порядковый номер месяца), получаем объект
                // этого месяца
                if (monthIndex >= 0) {
                    return monthIndex;
                }
            }
        }

        return -1;
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
