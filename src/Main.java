import User.UserBase;
import User.UserFactory;
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

                UserBase user = UserFactory.createUser(choice);

                if (user != null) {
                    user.run();
                } else {
                    System.out.println("ERROR: Invalid choice");
                }

            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid choice.");
            } catch (Exception e) {
                System.out.println("ERROR: Something unexpected occurred: " + e.getMessage());
            }

        } while (choice != 0);
    }
}