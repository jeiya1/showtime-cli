import Utils.Colors;

public class Flow {

    public static void main(String[] args) {
        cinemaName();
        mainMenu();
        cashierDashboard();
        availableSeats();
        reserveSeat();
        salesSummary();
        adminDashboard();
        addCinema();
        viewCinemas();
        updateCinema();
        deleteCinema();
        exitProgram();
    }

    // HEADER CINEMA NAME
    static void cinemaName() {
        System.out.println(
            "     _                   _   _                \n" +
            " ___| |__   _____      _| |_(_)_ __ ___   ___ \n" +
            "/ __| '_ \\ / _ \\ \\ /\\ / / __| | '_ ` _ \\ / _ \\\n" +
            "\\__ \\ | | | (_) \\ V  V /| |_| | | | | | |  __/\n" +
            "|___/_| |_|\\___/ \\_/\\_/  \\__|_|_| |_| |_|\\___|\n" +
            "\nWelcome to Sample CLI!\n"
        );
    }

    // MAIN MENU
    static void mainMenu() {
        System.out.println(
            "╔══════════════════════════════════╗\n" +
            "║      CINEMA BOOKING SYSTEM       ║\n" +
            "╠══════════════════════════════════╣\n" +
            "║ [1] Admin                        ║\n" +
            "║ [2] Cashier                      ║\n" +
            "║ [0] Exit                         ║\n" +
            "╚══════════════════════════════════╝\n" +
            "Select user type: \n" +
            "You have selected: \n"
        );
    }

    // CASHIER DASHBOARD
    static void cashierDashboard() {
        System.out.println(
            "╔═════════════════════════════╗\n" +
            "║      CASHIER DASHBOARD      ║\n" +
            "╠═════════════════════════════╣\n" +
            "║ [1] Display Available Seats ║\n" +
            "║ [2] Reserve a Seat          ║\n" +
            "║ [3] View Sales Summary      ║\n" +
            "║ [0] Back                    ║\n" +
            "╚═════════════════════════════╝\n" +
            "Select an option: \n"
        );
    }

    // DISPLAY AVAILABLE SEATS SAMPLE
    static void availableSeats() {
        System.out.println(
            "╔═════════════════════════════╗\n" +
            "║   DISPLAY AVAILABLE SEATS   ║\n" +
            "╚═════════════════════════════╝\n" +
            "\n\t  AVAILABLE CINEMAS\n" +
            "======================================\n" +
            "Cinema 1 – VENOM: LET THERE BE CARNAGE\n" +
            "Cinema 2 – AVENGERS: ENDGAME\n" +
            "Cinema 3 – INSIDE OUT 2\n" +
            "Cinema 4 – A HAUNTING IN VENICE\n" +
            "======================================\n" +
            "\nSelect cinema (1-4): 2\n" +
            "Selected: Cinema 2 – AVENGERS: ENDGAME\n" +
            "\nLegend: " + Colors.GREEN + "[ ] Available  " + Colors.RED + "[X] Reserved" + Colors.RESET + "\n" +
            "\n            SCREEN THIS WAY\n" +
            "\n      1   2   3   4   5   6   7   8\n" +
            " A | " + Colors.GREEN + "[ ] [ ] [ ] [ ]" + Colors.RED + " [X] [X]" + Colors.GREEN + " [ ] [ ]" + Colors.RESET + "\n" +
            " B | " + Colors.GREEN + "[ ] [ ]" + Colors.RED + " [X] [X]" + Colors.GREEN + " [ ] [ ] [ ] [ ]" + Colors.RESET + "\n" +
            " C | " + Colors.RED + "[X]" + Colors.GREEN + " [ ] [ ] [ ] [ ] [ ]" + Colors.RED + " [X]" + Colors.GREEN + " [ ]" + Colors.RESET + "\n" +
            " D | " + Colors.GREEN + "[ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]" + Colors.RESET + "\n" +
            " E | " + Colors.GREEN + "[ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]" + Colors.RESET + "\n" +
            "\nDisplayed successfully.\n"
        );
    }

    // RESERVE SEAT SAMPLE
    static void reserveSeat() {
        System.out.println(
            "╔═════════════════════════════╗\n" +
            "║       RESERVE A SEAT        ║\n" +
            "╚═════════════════════════════╝\n" +
            "Enter Cinema Number: 2\n" +
            "Enter Seat (e.g. A1): B3\n" +
            "[Available] Seat B3 is available!\n" +
            "Confirm reservation (y/n)? y\n" +
            "Seat B3 reserved successfully!\n" +
            "Amount Paid: ₱300.00\n" +
            "Thank you for your purchase!\n"
        );
    }

    // SALES SUMMARY SAMPLE
    static void salesSummary() {
        System.out.println(
            "╔═════════════════════════════╗\n" +
            "║      SALES SUMMARY MENU     ║\n" +
            "╚═════════════════════════════╝\n" +
            "\n\t  AVAILABLE CINEMAS\n" +
            "======================================\n" +
            "Cinema 1 – VENOM: LET THERE BE CARNAGE\n" +
            "Cinema 2 – AVENGERS: ENDGAME\n" +
            "Cinema 3 – INSIDE OUT 2\n" +
            "Cinema 4 – A HAUNTING IN VENICE\n" +
            "======================================\n" +
            "Enter Cinema Number: 2\n" +
            "\n╔══════════════════════════════════╗\n" +
            "║       CINEMA 2 SALES SUMMARY     ║\n" +
            "╠══════════════════════════════════╣\n" +
            "║ Total Tickets Sold : 35          ║\n" +
            "║ Total Revenue      : ₱10,500.00  ║\n" +
            "╚══════════════════════════════════╝\n" +
            "Press [Enter] to return to menu.\n"
        );
    }

    // ADMIN DASHBOARD
    static void adminDashboard() {
        System.out.println(
            "╔═══════════════════════════╗\n" +
            "║       ADMIN DASHBOARD     ║\n" +
            "╠═══════════════════════════╣\n" +
            "║ [1] Add Cinema            ║\n" +
            "║ [2] View Cinemas          ║\n" +
            "║ [3] Update Cinema Info    ║\n" +
            "║ [4] Delete Cinema         ║\n" +
            "║ [0] Back                  ║\n" +
            "╚═══════════════════════════╝\n" +
            "Select an option: \n"
        );
    }

    // ADD CINEMA SAMPLE
    static void addCinema() {
        System.out.println(
            "╔═══════════════════════════╗\n" +
            "║       ADD CINEMA          ║\n" +
            "╚═══════════════════════════╝\n" +
            "Enter Cinema Number: 3\n" +
            "Enter Movie Title: INSIDE OUT 2\n" +
            "Enter Total Rows: 5\n" +
            "Enter Seats per Row: 6\n" +
            "Movie \"INSIDE OUT 2\" added to Cinema 3 successfully!\n" +
            "Press [Enter] to return to menu.\n"
        );
    }

    // VIEW CINEMAS SAMPLE
    static void viewCinemas() {
        System.out.println(
            "╔══════════════════════════════════╗\n" +
            "║           VIEW CINEMAS           ║\n" +
            "╚══════════════════════════════════╝\n" +
            "\n" +
            "╔══════════════════════════════════╗\n" +
            "║          LIST OF CINEMAS         ║\n" +
            "╠══════════════════════════════════╣\n" +
            "║ ID │ NAME       │ ROWS │  SEATS  ║\n" +
            "╠════╪════════════╪══════╪═════════╣\n" +
            "║ 1  │ Cinema 1   │  5   │  25     ║\n" +
            "║ 2  │ Cinema 2   │  4   │  20     ║\n" +
            "║ 3  │ Cinema 3   │  6   │  36     ║\n" +
            "╚══════════════════════════════════╝\n" +
            "Press [Enter] to return to menu.\n"
        );
    }

    // UPDATE CINEMA SAMPLE
    static void updateCinema() {
        System.out.println(
            "╔═══════════════════════════╗\n" +
            "║     UPDATE CINEMA INFO    ║\n" +
            "╚═══════════════════════════╝\n" +
            "Enter Cinema ID to update: 2\n" +
            "Enter New Cinema Name: Cinema 2 – Avengers\n" +
            "Enter New Total Rows: 5\n" +
            "Enter New Seats per Row: 25\n" +
            "Cinema \"Cinema 2 – Avengers\" updated successfully!\n" +
            "Press [Enter] to return to menu.\n"
        );
    }

    // DELETE CINEMA SAMPLE
    static void deleteCinema() {
        System.out.println(
            "╔═══════════════════════════╗\n" +
            "║       DELETE CINEMA       ║\n" +
            "╚═══════════════════════════╝\n" +
            "Enter Cinema ID to delete: 3\n" +
            "Are you sure you want to delete Cinema 3? (y/n): y\n" +
            "Cinema 3 deleted successfully!\n" +
            "Press [Enter] to return to menu.\n"
        );
    }

    // EXIT
    static void exitProgram() {
        System.out.println("Exiting Program...");
    }
}
