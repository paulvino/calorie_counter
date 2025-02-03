package code;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Utils.getMenu();

        var scanner = new Scanner(System.in);
        var userChoice = scanner.next();
        System.out.println();

        Utils.getRoute(userChoice);
    }
}
