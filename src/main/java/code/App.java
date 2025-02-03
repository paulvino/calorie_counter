package code;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        run(scanner);
    }

    public static void run(Scanner scanner) {
        System.out.println("Hello! This is a Calorie tracker");

        var loopBreaker = true;

        while (loopBreaker) {
            printMenu();
            System.out.print("Your choice: ");

            if (!scanner.hasNext()) {
                break;
            }
            var userChoice = scanner.next();

            switch (userChoice) {
                case "1" -> System.out.println("Good choice");
                case "2" -> System.out.println("Excellent");
                case "3" -> System.out.println("Perfect");
                case "0" -> System.out.println("\nSee you soon!");
                default -> System.out.println("\nYou entered wrong number, try again");
            }

            loopBreaker = !userChoice.equals("0");
        }
    }

    public static void printMenu() {
        System.out.println("\nSelect a number from the ones below and press Enter:\n"
                + "  1 - Enter the number of steps for the day\n"
                + "  2 - Print the last month stats\n"
                + "  3 - Change the goal for the steps per day\n"
                + "  0 - Exit");
    }
}
