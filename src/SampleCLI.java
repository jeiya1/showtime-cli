public class SampleCLI {

    // ANSI colors for seats
    static final String GREEN = "\u001B[32m"; // free seat
    static final String RED = "\u001B[31m";   // taken seat
    static final String RESET = "\u001B[0m";  // reset color

    public static void main(String[] args) {

        displayCinemaName();
        displayMainMenu();
        displayCashierDashboard();
        displayAvailableSeats();
        reserveASeatSample();
        displaySalesSummarySample();
        displayAdminFlowSample();
        exitProgram();
    }

    // HEADER CINEMA NAME
    static void displayCinemaName() {
        System.out.println("     _                   _   _                ");
        System.out.println(" ___| |__   _____      _| |_(_)_ __ ___   ___ ");
        System.out.println("/ __| '_ \\ / _ \\ \\ /\\ / / __| | '_ ` _ \\ / _ \\");
        System.out.println("\\__ \\ | | | (_) \\ V  V /| |_| | | | | | |  __/");
        System.out.println("|___/_| |_|\\___/ \\_/\\_/  \\__|_|_| |_| |_|\\___|");
        System.out.println("\nWelcome to Sample CLI!\n");
    }

    // MAIN MENU
    static void displayMainMenu() {
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║          CINEMA BOOKING SYSTEM      ║");
        System.out.println("╠═════════════════════════════════════╣");
        System.out.println("║ [1] Admin                           ║");
        System.out.println("║ [2] Cashier                         ║");
        System.out.println("║ [0] Exit                            ║");
        System.out.println("╚═════════════════════════════════════╝");
        System.out.println("Select user type: ");
        System.out.println("You have selected: \n");
    }

    // CASHIER DASHBOARD
    static void displayCashierDashboard() {
        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║         CASHIER DASHBOARD       ║");
        System.out.println("╠═════════════════════════════════╣");
        System.out.println("║ [1] Display Available Seats     ║");
        System.out.println("║ [2] Reserve a Seat              ║");
        System.out.println("║ [3] View Sales Summary          ║");
        System.out.println("║ [0] Back                        ║");
        System.out.println("╚═════════════════════════════════╝");
        System.out.println("Select an option: \n");
    }

    // DISPLAY AVAILABLE SEATS SAMPLE
    static void displayAvailableSeats() {
        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║      DISPLAY AVAILABLE SEATS    ║");
        System.out.println("╚═════════════════════════════════╝");

        System.out.println("\n\t  AVAILABLE CINEMAS");
        System.out.println("======================================");
        System.out.println("Cinema 1 – VENOM: LET THERE BE CARNAGE");
        System.out.println("Cinema 2 – AVENGERS: ENDGAME");
        System.out.println("Cinema 3 – INSIDE OUT 2");
        System.out.println("Cinema 4 – A HAUNTING IN VENICE");
        System.out.println("======================================");

        System.out.println("\nSelect cinema (1-4): 2\n");
        System.out.println("Selected: Cinema 2 – AVENGERS: ENDGAME\n");

        System.out.println("Legend: " + GREEN + "[ ] Available  " + RED + "[X] Reserved" + RESET + "\n");

        System.out.println("            SCREEN THIS WAY");
        System.out.println("\n      1   2   3   4   5   6   7   8");

        System.out.println(" A | " + GREEN + "[ ] [ ] [ ] [ ]" + RED + " [X] [X]" + GREEN + " [ ] [ ]" + RESET);
        System.out.println(" B | " + GREEN + "[ ] [ ]" + RED + " [X] [X]" + GREEN + " [ ] [ ] [ ] [ ]" + RESET);
        System.out.println(" C | " + RED + "[X]" + GREEN + " [ ] [ ] [ ] [ ] [ ]" + RED + " [X]" + GREEN + " [ ]" + RESET);
        System.out.println(" D | " + GREEN + "[ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]" + RESET);
        System.out.println(" E | " + GREEN + "[ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]" + RESET);

        System.out.println("\nDisplayed successfully.\n");
    }

    // RESERVE SEAT SAMPLE
    static void reserveASeatSample() {
        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║          RESERVE A SEAT         ║");
        System.out.println("╚═════════════════════════════════╝");
        System.out.println("Enter Cinema Number: 2");
        System.out.println("Enter Seat (e.g. A1): B3\n");
        System.out.println("[Available] Seat B3 is available!");
        System.out.println("Confirm reservation (y/n)? y\n");
        System.out.println("Seat B3 reserved successfully!");
        System.out.println("Amount Paid: ₱300.00");
        System.out.println("Thank you for your purchase!\n");
    }

    // SALES SUMMARY SAMPLE
    static void displaySalesSummarySample() {
        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║        SALES SUMMARY MENU       ║");
        System.out.println("╚═════════════════════════════════╝");
        System.out.println("\n\t  AVAILABLE CINEMAS");
        System.out.println("======================================");
        System.out.println("Cinema 1 – VENOM: LET THERE BE CARNAGE");
        System.out.println("Cinema 2 – AVENGERS: ENDGAME");
        System.out.println("Cinema 3 – INSIDE OUT 2");
        System.out.println("Cinema 4 – A HAUNTING IN VENICE");
        System.out.println("======================================");
        System.out.println("Enter Cinema Number: 2\n");

        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║         CINEMA 2 SALES SUMMARY     ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║ Total Tickets Sold : 35            ║");
        System.out.println("║ Total Revenue      : ₱10,500.00    ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("Press [Enter] to return to menu.\n");
    }

    // ADMIN FLOW SAMPLE
    static void displayAdminFlowSample() {
        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║         ADMIN DASHBOARD         ║");
        System.out.println("╠═════════════════════════════════╣");
        System.out.println("║ [1] Add Cinema                  ║");
        System.out.println("║ [2] View Cinemas                ║");
        System.out.println("║ [3] Update Cinema Info          ║");
        System.out.println("║ [4] Delete Cinema               ║");
        System.out.println("║ [0] Back                        ║");
        System.out.println("╚═════════════════════════════════╝");
        System.out.println("Select an option: \n");

        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║           ADD CINEMA            ║");
        System.out.println("╚═════════════════════════════════╝");
        System.out.println("Enter Cinema Number: 3");
        System.out.println("Enter Movie Title: INSIDE OUT 2");
        System.out.println("Enter Total Rows: 5");
        System.out.println("Enter Seats per Row: 6\n");
        System.out.println("Movie \"INSIDE OUT 2\" added to Cinema 3 successfully!");
        System.out.println("Press [Enter] to return to menu.\n");

        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║        UPDATE CINEMA INFO       ║");
        System.out.println("╚═════════════════════════════════╝");
        System.out.println("Enter Cinema ID to update: 2");
        System.out.println("Enter New Cinema Name: Cinema 2 – Avengers");
        System.out.println("Enter New Total Rows: 5");
        System.out.println("Enter New Seats per Row: 25\n");
        System.out.println("Cinema \"Cinema 2 – Avengers\" updated successfully!");
        System.out.println("Press [Enter] to return to menu.\n");

        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║          DELETE CINEMA          ║");
        System.out.println("╚═════════════════════════════════╝");
        System.out.println("Enter Cinema ID to delete: 3");
        System.out.println("Are you sure you want to delete Cinema 3? (y/n): y\n");
        System.out.println("Cinema 3 deleted successfully!");
        System.out.println("Press [Enter] to return to menu.\n");
    }

    // EXIT
    static void exitProgram() {
        System.out.println("Exiting Program...");
    }
}
