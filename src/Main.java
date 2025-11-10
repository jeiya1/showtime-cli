import Admin.AdminMain;
import Cashier.CashierMain;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice = -1;

        FrontEnd.Flow.cinemaName();

        do {
            FrontEnd.Flow.mainMenu();
            FrontEnd.Flow.userInput();

            try {
                choice = Integer.parseInt(input.nextLine().trim());

                switch (choice) {
                    case 1 -> AdminMain.run();
                    case 2 -> CashierMain.run();
                    case 0 -> {
                        FrontEnd.Flow.exitProgram();
                        input.close();
                    }
                    default -> System.out.println("ERROR: Invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid choice.");
            } catch (Exception e) {
                System.out.println("ERROR: Something unexpected occurred: " + e.getMessage());
            }

        } while (choice != 0);
    }
}