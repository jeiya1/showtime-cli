package FrontEnd;

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

    public static void userInput() {
        System.out.print(Colors.WHITE_BOLD + "\nEnter your choice: " + Colors.RESET);
    }

    // HEADER CINEMA NAME
   public static void cinemaName() {
    System.out.println(
            Colors.YELLOW + """
                             _                   _   _               
                         ___| |__   _____      _| |_(_)_ __ ___   ___
                        / __| '_ \\ / _ \\ \\ /\\ / / __| | '_ ` _ \\ / _ \\
                        \\__ \\ | | | (_) \\ V  V /| |_| | | | | | |  __/
                        |___/_| |_|\\___/ \\_/\\_/  \\__|_|_| |_| |_|\\___|
                        """ + Colors.RESET
    );

    System.out.println(Colors.ITALIC + Colors.WHITE_BOLD + "Welcome to ShowTime-CLI!" + Colors.RESET);
}


    // MAIN MENU
    public static void mainMenu() {
        System.out.println(Colors.BG_RED + Colors.BLACK + "\no═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "║" + Colors.BOLD + Colors.YELLOW + "      CINEMA BOOKING SYSTEM       "
                        + Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);

        System.out.println(Colors.BG_BLACK + "                                    " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE_BOLD + "         Select User Type:          " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "           [1] Admin                " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "           [2] Cashier              " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "           [0] Exit                 " + Colors.RESET);
        
        System.out.println(Colors.BG_BLACK + Colors.BLACK + "-══════════════════════════════════-" + Colors.RESET);
    }

    // CASHIER DASHBOARD
    public static void cashierDashboard() {
        System.out.println(Colors.BG_RED + Colors.BLACK + "\no═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "║" + Colors.BOLD + Colors.YELLOW + "         CASHIER DASHBOARD        "
                        + Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);

        System.out.println(Colors.BG_BLACK + "                                    " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE_BOLD + "         Select an Option:          " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "     [1] Display All Seats          " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "     [2] Display Available Seats    " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "     [3] Display All Receipts       " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "     [4] Display Receipt by ID      " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "     [5] View Sales Summary         " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "     [6] Reserve a Seat             " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "     [0] Back                       " + Colors.RESET);

        System.out.println(Colors.BG_BLACK + Colors.BLACK + "-══════════════════════════════════-" + Colors.RESET);
    }

    // DISPLAY AVAILABLE SEATS SAMPLE
    public static void availableSeats() {
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
    public static void reserveSeat() {
        System.out.println(
                """
                        ╔═════════════════════════════╗
                        ║       RESERVE A SEAT        ║
                        ╚═════════════════════════════╝
                        Enter Cinema Number: 2
                        Enter Seat (e.g. A1): B3
                        [Available] Seat B3 is available!
                        Confirm reservation (y/n)? y
                        Seat B3 reserved successfully!
                        Amount Paid: ₱300.00
                        Thank you for your purchase!
                        """
        );
    }

    // SALES SUMMARY SAMPLE
    public static void salesSummary() {
        System.out.println(
                """
                        ╔═════════════════════════════╗
                        ║      SALES SUMMARY MENU     ║
                        ╚═════════════════════════════╝
                        
                        \t  AVAILABLE CINEMAS
                        ======================================
                        Cinema 1 – VENOM: LET THERE BE CARNAGE
                        Cinema 2 – AVENGERS: ENDGAME
                        Cinema 3 – INSIDE OUT 2
                        Cinema 4 – A HAUNTING IN VENICE
                        ======================================
                        Enter Cinema Number: 2
                        
                        ╔══════════════════════════════════╗
                        ║       CINEMA 2 SALES SUMMARY     ║
                        ╠══════════════════════════════════╣
                        ║ Total Tickets Sold : 35          ║
                        ║ Total Revenue      : ₱10,500.00  ║
                        ╚══════════════════════════════════╝"""
        );
    }

    // ADMIN DASHBOARD
    public static void adminDashboard() {
        System.out.println(Colors.BG_RED + Colors.BLACK + "\no═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "║" + Colors.BOLD + Colors.YELLOW + "          ADMIN DASHBOARD         "
                        + Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);

        System.out.println(Colors.BG_BLACK + "                                    " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE_BOLD + "         Select an Option:          " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "      [1] Add Cinema                " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "      [2] View Cinemas              " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "      [3] Update Cinema Info        " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "      [4] Delete Cinema             " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE + "      [0] Back                      " + Colors.RESET);

        System.out.println(Colors.BG_BLACK + Colors.BLACK + "-══════════════════════════════════-" + Colors.RESET);
    }


    // ADD CINEMA SAMPLE
    public static void addCinema() {
        System.out.println(Colors.BG_RED + Colors.BLACK + "\no═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "║" + Colors.BOLD + Colors.YELLOW + "            ADD CINEMA            "
                        + Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);
    }



    // VIEW CINEMAS SAMPLE
    public static void viewCinemas() {
        System.out.println(Colors.BG_RED + Colors.BLACK + "\no═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "║" + Colors.BOLD + Colors.YELLOW + "         VIEW CINEMAS           "
                        + Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);

        System.out.println(Colors.BG_BLACK + Colors.BLACK + "-══════════════════════════════════-" + Colors.RESET);
    }


    public static void viewCinemasRecords(String records) {
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════════o═══════════o═══════════o═════════o" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "║" + Colors.BOLD + Colors.YELLOW + "               LIST OF CINEMAS               "
                        + Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════════o═══════════o═══════════o═════════o" + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE_BOLD + " ID │ NAME       │ ROWS │  SEATS  │   PRICE    " + Colors.RESET);
        System.out.println(Colors.BG_BLACK + Colors.WHITE_BOLD + "════╪════════════╪══════╪═════════╪════════════" + Colors.RESET);

        String[] rows = records.split("\n");
        for (String row : rows) {
            System.out.println(Colors.BG_BLACK + Colors.WHITE + row + Colors.RESET);
        }

        System.out.println(Colors.BG_BLACK + Colors.BLACK + "-═════════════════════════════════════════════-" + Colors.RESET);
        pressEnter();
    }

    public static void viewCinemasNone() {
        System.out.println(Colors.BG_RED + Colors.BLACK + "\no═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "║" + Colors.BOLD + Colors.YELLOW + "    NO CINEMA RECORDS AVAILABLE   "
                + Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);
        pressEnter();
    }

    public static void viewReceiptNone() {
        System.out.println(Colors.BG_RED + Colors.BLACK + "\no═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "║" + Colors.BOLD + Colors.YELLOW + "       NO RECEIPTS AVAILABLE      "
                + Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);
        pressEnter();
    }

    public static void viewSalesNone() {
        System.out.println(Colors.BG_RED + Colors.BLACK + "\no═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "║" + Colors.BOLD + Colors.YELLOW + "            NO SALES YET          "
                + Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);
        pressEnter();
    }

    public static void updateCinema() {
        System.out.println(Colors.BG_RED + Colors.BLACK + "\no═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "║" + Colors.BOLD + Colors.YELLOW + "           UPDATE CINEMA INFO          "
                + Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);
    }

    public static void deleteCinema() {
        System.out.println(Colors.BG_RED + Colors.BLACK + "\no═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "║" + Colors.BOLD + Colors.YELLOW + "             DELETE CINEMA             "
                + Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);
    }


    public static void pressEnter() {
        System.out.println("\nPress " + Colors.GREEN + Colors.BOLD + "[Enter]" + Colors.RESET + " to return to menu.");
    }

    // EXIT
    public static void exitProgram() {
        System.out.println(Colors.ITALIC + "Exiting Program..." + Colors.RESET);
    }
}
