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
        System.out.print("Enter your choice: ");
    }

    // HEADER CINEMA NAME
    public static void cinemaName() {
        System.out.println(
                """
                             _                   _   _               \s
                         ___| |__   _____      _| |_(_)_ __ ___   ___\s
                        / __| '_ \\ / _ \\ \\ /\\ / / __| | '_ ` _ \\ / _ \\
                        \\__ \\ | | | (_) \\ V  V /| |_| | | | | | |  __/
                        |___/_| |_|\\___/ \\_/\\_/  \\__|_|_| |_| |_|\\___|
                        
                        Welcome to ShowTime-CLI!
                        """
        );
    }

    // MAIN MENU
    public static void mainMenu() {
        System.out.println(
                """
                        ╔══════════════════════════════════╗
                        ║      CINEMA BOOKING SYSTEM       ║
                        ╠══════════════════════════════════╣
                        ║ [1] Admin                        ║
                        ║ [2] Cashier                      ║
                        ║ [0] Exit                         ║
                        ╚══════════════════════════════════╝"""
        );
    }

    // CASHIER DASHBOARD
    public static void cashierDashboard() {
        System.out.println(
                """
                        ╔═════════════════════════════╗
                        ║      CASHIER DASHBOARD      ║
                        ╠═════════════════════════════╣
                        ║ [1] Display All Seats       ║
                        ║ [2] Display Available Seats ║
                        ║ [3] Display All Receipts    ║
                        ║ [4] Display Receipt by ID   ║
                        ║ [5] View Sales Summary      ║
                        ║ [6] Reserve a Seat          ║
                        ║ [0] Back                    ║
                        ╚═════════════════════════════╝"""
        );
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
        System.out.println(
                """
                        ╔═══════════════════════════╗
                        ║       ADMIN DASHBOARD     ║
                        ╠═══════════════════════════╣
                        ║ [1] Add Cinema            ║
                        ║ [2] View Cinemas          ║
                        ║ [3] Update Cinema Info    ║
                        ║ [4] Delete Cinema         ║
                        ║ [0] Back                  ║
                        ╚═══════════════════════════╝"""
        );
    }

    // ADD CINEMA SAMPLE
    public static void addCinema() {
        System.out.println(
                """
                        ╔═══════════════════════════╗
                        ║       ADD CINEMA          ║
                        ╚═══════════════════════════╝"""
        );
    }

    // VIEW CINEMAS SAMPLE
    public static void viewCinemas() {
        System.out.println(
                """
                        ╔══════════════════════════════════╗
                        ║           VIEW CINEMAS           ║
                        ╚══════════════════════════════════╝
                        """
        );
    }

    public static void viewCinemasRecords(String records) {
        System.out.println(
                "╔══════════════════════════════════════════╗\n" +
                "║              LIST OF CINEMAS             ║\n" +
                "╠══════════════════════════════════════════╣\n" +
                "║ ID │ NAME       │ ROWS │  SEATS  | PRICE ║\n" +
                "╠════╪════════════╪══════╪═════════╪═══════╣\n" +
                        records +
                "╚══════════════════════════════════════════╝"
        );
        pressEnter();
    }

    public static void viewCinemasNone() {
        System.out.println(
                """
                        ╔══════════════════════════════════════════════════════╗
                        ║              NO CINEMA RECORDS AVAILABLE             ║
                        ╚══════════════════════════════════════════════════════╝"""
        );
        pressEnter();
    }

    public static void viewReceiptNone() {
        System.out.println(
                """
                        ╔══════════════════════════════════════════════════════╗
                        ║                  NO RECEIPTS AVAILABLE               ║
                        ╚══════════════════════════════════════════════════════╝"""
        );
        pressEnter();
    }

    public static void viewSalesNone() {
        System.out.println(
                """
                        ╔══════════════════════════════════════════════════════╗
                        ║                       NO SALES YET                   ║
                        ╚══════════════════════════════════════════════════════╝"""
        );
        pressEnter();
    }

    // UPDATE CINEMA SAMPLE
    public static void updateCinema() {
        System.out.println(
                """
                        ╔═══════════════════════════╗
                        ║     UPDATE CINEMA INFO    ║
                        ╚═══════════════════════════╝"""
        );
    }

    // DELETE CINEMA SAMPLE
    public static void deleteCinema() {
        System.out.println(
                """
                        ╔═══════════════════════════╗
                        ║       DELETE CINEMA       ║
                        ╚═══════════════════════════╝"""
        );
    }

    public static void pressEnter() {
        System.out.println("Press [Enter] to return to menu.");
    }

    // EXIT
    public static void exitProgram() {
        System.out.println("Exiting Program...");
    }
}
