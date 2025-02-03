package code;

public class Utils {
    public static void getMenu() {
        System.out.println("Hello! This is a Calorie tracker\n"
                + "Select a number from the ones below and press Enter:\n"
                + "  1 - Enter the number of steps for the day\n"
                + "  2 - Print the last month stats\n"
                + "  3 - Change the goal for the steps per day\n"
                + "  0 - Exit");
        System.out.print("Your choice: ");
    }

    public static void getRoute(String userChoice) {
        switch (userChoice) {
            case "0" -> System.out.println("See you next time!");
            default -> System.out.println("You entered wrong number, try again");
        }
    }
}
