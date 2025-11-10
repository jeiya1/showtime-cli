import Admin.AdminMain;
import Cashier.CashierMain;
import java.util.Scanner;
import User.UserBase;

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

                UserBase user = null;

                switch (choice) {
                    case 1 -> user = new AdminMain();
                    case 2 -> user = new CashierMain();
                    case 0 -> {
                        FrontEnd.Flow.exitProgram();
                        input.close();
                    }
                    default -> System.out.println("ERROR: Invalid choice");
                }

                if (user != null) {
                    user.run();
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid choice.");
            } catch (Exception e) {
                System.out.println("ERROR: Something unexpected occurred: " + e.getMessage());
            }

        } while (choice != 0);
    }
}