package code;

public class UsersInputChecker {

    // метод проверяет, является ли введенное значение месяцем, и возвращает индекс (порядковый номер) месяца,
    // если введенное значение является месяцем. Если значение не является месяцем, метод возвращает -1
    public static int checkInputMonth(String input) {
        input = input.trim();
        int monthIndex = -1;
        // сначала проверим, является ли введенное значение строкой
        if (input.trim().matches("[a-zA-z]+")) {
            // проверим, является ли введенное значение месяцем
            // для этого проходимся по массиву месяцев и сравниваем ввод с имеющимися в массиве значениями
            for (int i = 0; i < StepTracker.MONTHS.length; i++) {
                // если ввод совпал с одним из значений в массиве, то получаем "индекс" - порядковый номер месяца
                // если не совпал, то "индекс" имеет отрицательное значение
                monthIndex = StepTracker.MONTHS[i].equalsIgnoreCase(input) ? i : -1;

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
}
