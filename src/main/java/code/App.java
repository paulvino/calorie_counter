package code;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Сканнер для считывания ввода от пользователя
        // Ввод передается в метод run в качестве аргумента
        Scanner scanner = new Scanner(System.in);
        run(scanner);
    }

    // основной первичный метод приложения с логикой "меню"
    public static void run(Scanner scanner) {
        StepTracker st = new StepTracker();
        System.out.println("Hello! This is a Calorie tracker");

        // переменная-флаг для выхода из цикла
        var loopBreaker = true;

        // цикл с основной логикой меню: пока пользователь не передаст "0",
        // приложение будет работать (цикл не остановится)
        while (loopBreaker) {
            // метод для печати пользовательского меню
            printMainMenu();
            // предлагаем пользователю выбрать пункт
            System.out.print("Your choice: ");

            // присваиваем переменной userChoice значение, которое ввел пользователь
            var userChoice = scanner.next();

            // оператор для маршрутизации по приложению в зависимости от ввода пользователя
            switch (userChoice) {
                case "1" -> st.askUser(scanner);
                case "2" -> st.printStats(scanner);
                case "3" -> System.out.println("Perfect");
                case "0" -> System.out.println("\nSee you soon!");
                // стандартное значение на случай, если ввод некорректен
                default -> System.out.println("\nYou entered wrong number, try again");
            }

            // проверка ввода, если это "0", то выходим из цикла и приложение завершает работу
            loopBreaker = !userChoice.equals("0");
        }
    }

    // простой метод для вывода пользователю меню
    public static void printMainMenu() {
        System.out.println("\nSelect a number from the ones below and press Enter:\n"
                + "  1 - Enter the number of steps for the day\n"
                + "  2 - Print the month stats\n"
                + "  3 - Change the goal for the steps per day\n"
                + "  0 - Exit");
    }
}
