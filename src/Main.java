import User.*;
import Utils.Colors;

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

                if (choice == 0) {
                    FrontEnd.Flow.exitProgram();
                    input.close();
                    break;
                }

                UserActions user = UserFactory.createUser(choice);

                if (user != null) {
                    user.run();
                } else {
                    System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Invalid choice." + Colors.RESET);
                }

            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Invalid choice." + Colors.RESET);
            } catch (Exception e) {
                System.out.println(Colors.RED + Colors.ITALIC + "ERROR: Something unexpected occurred: " + e.getMessage() + Colors.RESET);
            }

        } while (choice != 0);
    }
}
