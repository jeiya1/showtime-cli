package Cashier;

import java.util.Scanner;

import Data.Database;
import DataStructures.*;
import User.UserBase;
import Utils.Colors;

import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CashierMain extends UserBase {
    private static final Scanner input = new Scanner(System.in);
    private static final LinkedListM<CinemaNode> cinemas = Database.cinemas;
    private static final LinkedListM<Receipt> receipts = Database.receipts;
    private static final CinemaNode CANCELLED = new CinemaNode(-1, null, null, 0, 0, 0);
//    private static final StackM<Receipt> undoStack = new StackM<>();
//    private static final StackM<Receipt> redoStack = new StackM<>();

    public CashierMain() {
        setUserRole("Cashier");
    }

    @Override
    public void displayMenu() {
        FrontEnd.Flow.cashierDashboard();
        FrontEnd.Flow.userInput();
    }

    @Override
    public void run() {
        displayWelcome();
        while (true) {
            try {
                displayMenu();
                int choice = Integer.parseInt(input.nextLine().trim());

                switch (choice) {
                    case 1 -> displayAllSeats();
                    case 2 -> displayAvailableSeats();
                    case 3 -> showAllReceipts();
                    case 4 -> searchReceipt();
                    case 5 -> viewSalesSummary();
                    case 6 -> takeReservation();
                    case 0 -> {
                        displayGoodbye();
                        System.out.println(Colors.ITALIC + "\nReturning to main menu..." + Colors.RESET);
                        return;
                    }
                    default -> System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Invalid option!" + Colors.RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Invalid input. Please enter a number." + Colors.RESET);
            } catch (Exception e) {
                System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Something unexpected occurred: " + e.getMessage() + Colors.RESET);
            }
        }
    }

    // ---------------------- DISPLAY AVAILABLE SEATS (Improved UX) 
    private void displayAvailableSeats() {
        if (cinemas.count() == 0) {
            FrontEnd.Flow.viewCinemasNone();
            input.nextLine();
            return;
        }

        // Show Available Cinemas First
        System.out.println();
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println(
                Colors.BG_RED + Colors.BLACK + "║" +
                Colors.BOLD + Colors.YELLOW + "         AVAILABLE CINEMAS        " +
                Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" +
                Colors.RESET
        );
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);

        for (CinemaNode c : cinemas) {
            System.out.println(
                    Colors.WHITE_BOLD + "\nCinema " + c.getID() + ": " +
                    Colors.YELLOW + c.getMovie() + Colors.RESET
            );
        }
        System.out.println();

        CinemaNode cinema = getCinemaByID();
        if (cinema == CANCELLED) return;

        if (cinema != null) {
            printSeatGrid(cinema);
            return;
        } else {
            System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Cinema not found." + Colors.RESET);
            return;
        }
    }

    private void takeReservation() {
        while (true) {
            CinemaNode cinema = getCinemaByID();
            if (cinema == CANCELLED) return;
            if (cinema == null) {
                System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Cinema not found." + Colors.RESET);
                continue;
            }

            try {
                printSeatGrid(cinema);
                int rows = cinema.getRows();
                int seatsPerRow = cinema.getSeatsPerRow();

                while (true) {
                    int row = promptRow(rows);
                    if (row == 0) return;

                    int seatNum = promptSeat(seatsPerRow);
                    if (seatNum == 0) return;

                    if (isSeatBooked(cinema.getID(), row, seatNum)) {
                        System.out.println(Colors.RED + Colors.ITALIC + "ERROR: Seat already booked. Please choose another seat." + Colors.RESET);
                        continue;
                    }

                    SeatNode reservedSeat = new SeatNode(row, seatNum, true);
                    reservedSeat.book();

                    String receiptId = UUID.randomUUID().toString().substring(0, 8);
                    double price = cinema.getPrice();

                    Receipt r = new Receipt(receiptId, cinema.getID(), cinema.getMovie(), row, seatNum, price);
                    receipts.insert(r);

                    System.out.printf(Colors.GREEN + Colors.BOLD + "\nReservation successful!\n" + Colors.RESET);

                    printReceipt(r, cinema);
                    return;
                }

            } catch (Exception e) {
                System.out.println(Colors.RED + Colors.ITALIC + "ERROR: Something unexpected occurred: " + e.getMessage() + Colors.RESET);
            }
        }
    }

    private int promptRow(int rows) {
        while (true) {
            try {
                System.out.print(Colors.WHITE_BOLD + "\nEnter Row Number (0 to cancel): " + Colors.RESET);
                int row = Integer.parseInt(input.nextLine().trim());
                if (row == 0) {
                    System.out.println(Colors.GREEN + Colors.BOLD + "\nBooking cancelled." + Colors.RESET);
                    return 0;
                }
                if (row < 1 || row > rows) {
                    System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Please enter a valid row number (1–" + rows + ")." + Colors.RESET);
                    continue;
                }
                return row;
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Invalid input. Please enter a number." + Colors.RESET);
            }
        }
    }

    private int promptSeat(int seatsPerRow) {
        while (true) {
            try {
                System.out.print(Colors.WHITE_BOLD + "Enter Seat Number (0 to cancel): " + Colors.RESET);
                int seatNum = Integer.parseInt(input.nextLine().trim());
                if (seatNum == 0) {
                    System.out.println(Colors.GREEN + Colors.BOLD + "\nBooking cancelled." + Colors.RESET);
                    return 0;
                }
                if (seatNum < 1 || seatNum > seatsPerRow) {
                    System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Please enter a valid seat number (1–" + seatsPerRow + ")." + Colors.RESET);
                    continue;
                }
                return seatNum;
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Invalid input. Please enter a number." + Colors.RESET);
            }
        }
    }

    private void printSeatGrid(CinemaNode cinema) {
        System.out.println();
        System.out.println(Colors.YELLOW + Colors.BOLD + "Cinema " + cinema.getID() + " : " + cinema.getMovie() + Colors.RESET);
        System.out.println();

        int rows = cinema.getRows();
        int seatsPerRow = cinema.getSeatsPerRow();

        System.out.print(Colors.WHITE_BOLD + "         "); 
        for (int s = 1; s <= seatsPerRow; s++) {
            System.out.printf("%2d  ", s);
        }
        System.out.println(Colors.RESET);

        for (int r = 1; r <= rows; r++) {
            System.out.print(Colors.WHITE_BOLD + String.format("Row %2d | ", r) + Colors.RESET);
            for (int s = 1; s <= seatsPerRow; s++) {
                boolean booked = isSeatBooked(cinema.getID(), r, s);
                System.out.print(booked ? Colors.RED + "[X] " + Colors.RESET : Colors.GREEN + "[ ] " + Colors.RESET);
            }
            System.out.println();
        }

        System.out.println("\n" + Colors.WHITE_BOLD + "Legend: " + Colors.RESET
                + Colors.GREEN + "[ ] Available " + Colors.RESET
                + Colors.RED + "[X] Booked" + Colors.RESET);

        System.out.println("\n--------------------------------------");
    }

    private String formatCurrency(double amount) {
        return "₱" + String.format("%.2f", amount);
    }

    private void printReceipt(Receipt r, CinemaNode cinema, String movie) {
        final int WIDTH = 40;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String now = LocalDateTime.now().format(dtf);
        String EQ = "========================================";
        String DASH = "----------------------------------------";
        System.out.println();
        System.out.println(EQ);
        System.out.println("                 SHOWTIME               ");
        System.out.println("              OFFICIAL RECEIPT          ");
        System.out.println(EQ);

        System.out.printf("%-15s : %s%n", "Receipt ID", r.getReceiptID());
        System.out.printf("%-15s : %s%n", "Date/Time", now);

        System.out.println(DASH);
        int cinemaID = r.getCinemaID();
        System.out.printf("%-15s : %d%n", "Cinema",cinemaID);
        System.out.printf("%-15s : %s%n", "Movie", trimTo(movie, 30));
        System.out.printf("%-16s: Row %d, Seat %d%n", "Seat", r.getRow(), r.getSeatNumber());

        System.out.println(DASH);

        String price = formatCurrency(r.getPrice());
        System.out.printf("%-25s %13s%n", "Ticket x1", price);

        System.out.println(DASH);

        System.out.printf("%-25s %13s%n", "TOTAL", price);

        System.out.println(EQ);

        System.out.println("        |======================|       ");
        System.out.println("                " + r.getReceiptID());
        System.out.println();
        System.out.println("            Enjoy the movie!          ");
        System.out.println();
        System.out.println(EQ);
        System.out.println();
    }

    private void printReceipt(Receipt r, CinemaNode cinema) {
        final int WIDTH = 40;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String now = LocalDateTime.now().format(dtf);

        String EQ = "========================================";
        String DASH = "----------------------------------------";
        System.out.println();
        System.out.println(EQ);
        System.out.println("                 SHOWTIME               ");
        System.out.println("              OFFICIAL RECEIPT          ");
        System.out.println(EQ);

        System.out.printf("%-15s : %s%n", "Receipt ID", r.getReceiptID());
        System.out.printf("%-15s : %s%n", "Date/Time", now);

        System.out.println(DASH);


        String movieShown = (cinema != null && cinema.getMovie() != null) ? cinema.getMovie() : r.getMovieTitle();
        int cinemaIdShown = r.getCinemaID();

        System.out.printf("%-15s : %d%n", "Cinema", cinemaIdShown);
        System.out.printf("%-15s : %s%n", "Movie", trimTo(movieShown, 30));
        System.out.printf("%-16s: Row %d, Seat %d%n", "Seat", r.getRow(), r.getSeatNumber());

        System.out.println(DASH);

        String price = formatCurrency(r.getPrice());
        System.out.printf("%-25s %13s%n", "Ticket x1", price);

        System.out.println(DASH);

        System.out.printf("%-25s %13s%n", "TOTAL", price);

        System.out.println(EQ);

        System.out.println("        |======================|       ");
        System.out.println("                " + r.getReceiptID());
        System.out.println();
        System.out.println("            Enjoy the movie!          ");
        System.out.println();
        System.out.println(EQ);
        System.out.println();
    }

    private void viewSalesSummary() {
        if (receipts.count() == 0) {
            FrontEnd.Flow.viewSalesNone();
            input.nextLine();
            return;
        }

        int totalTickets = 0;
        double totalRevenue = 0.0;

        System.out.println();
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "║" +
                Colors.BOLD + Colors.YELLOW + "           SALES SUMMARY          " +
                Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println();

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println(Colors.WHITE + "           " + now + Colors.RESET);
    
        System.out.println(Colors.WHITE + "-------------------------------------" + Colors.RESET);
        System.out.printf(Colors.WHITE + "%-25s %10s%n" + Colors.RESET, "Cinema (Movie)", "Revenue");

        for (Receipt r : receipts) {
            CinemaNode cinema = getCinemaByID(r.getCinemaID());
            String movie = (cinema != null) ? cinema.getMovie() : "(Deleted Cinema)";
            double rev = r.getPrice();
            String left = String.format("%d (%s)", r.getCinemaID(), trimTo(movie, 18));
            System.out.printf(Colors.WHITE + "%-25s %10s%n" + Colors.RESET, left, formatCurrency(rev));
            totalTickets += 1;
            totalRevenue += rev;
        }

        System.out.println(Colors.WHITE + "-------------------------------------" + Colors.RESET);
        System.out.printf(Colors.WHITE + "%-25s %10d%n" + Colors.RESET, "Total Tickets:", totalTickets);
        System.out.printf(Colors.WHITE + "%-25s %10s%n" + Colors.RESET, "Total Revenue:", formatCurrency(totalRevenue));

        System.out.println(Colors.WHITE + "=====================================" + Colors.RESET);
        System.out.println(Colors.WHITE + "             End of Report           " + Colors.RESET);
        System.out.println(Colors.WHITE + "=====================================" + Colors.RESET);
        System.out.println();
    }


    private String trimTo(String s, int max) {
        if (s == null) return "";
        return s.length() <= max ? s : s.substring(0, max - 3) + "...";
    }

    private void displayAllSeats() {
    if (cinemas.count() == 0) {
        FrontEnd.Flow.viewCinemasNone();
        input.nextLine();
        return;
    }

    System.out.println();
    System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);
    System.out.println(Colors.BG_RED + Colors.BLACK + "║" + Colors.BOLD + Colors.YELLOW + "      ALL CINEMA SEAT LAYOUTS     " + Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" + Colors.RESET);
    System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);

    for (CinemaNode cinema : cinemas) {
        printSeatGrid(cinema);
        System.out.println("--------------------------------------");
    }
}


    private void showAllReceipts() {
        if (receipts.count() == 0) {
            FrontEnd.Flow.viewReceiptNone();
            input.nextLine();
            return;
        }
        System.out.println();
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println(
                Colors.BG_RED + Colors.BLACK + "║" 
                + Colors.BOLD + Colors.YELLOW + "           ALL RECEIPTS           "
                + Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" 
                + Colors.RESET
        );
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);

        for (Receipt r : receipts) {
            CinemaNode cinema = getCinemaByID(r.getCinemaID());
            String movie = (cinema != null) ? cinema.getMovie() : "(Deleted Cinema)";
            printReceipt(r, cinema, movie);
        }
    }

    private void searchReceipt() {
        if (receipts.count() == 0) {
            FrontEnd.Flow.viewReceiptNone();
            input.nextLine();
            return;
        }
        String searchId;
        while (true) {
            System.out.print(Colors.WHITE_BOLD + "\nEnter Receipt ID to search (0 to cancel): " + Colors.RESET);
            searchId = input.nextLine().trim();
            if (searchId.equals("0")) return;
            if (searchId.length() < 8) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid ID." + Colors.RESET);
                continue;
            }
            for (Receipt r : receipts) {
                if (r.getReceiptID().equalsIgnoreCase(searchId)) {
                    CinemaNode cinema = getCinemaByID(r.getCinemaID());
                    printReceipt(r, cinema);
                    return;
                }
            }
            System.out.println(Colors.RED + Colors.ITALIC + "ERROR: Receipt not found." + Colors.RESET);
        }
    }

    private CinemaNode getCinemaByID() {
        if (cinemas.count() == 0) {
            FrontEnd.Flow.viewCinemasNone();
            input.nextLine();
            return CANCELLED;
        }
        int id = 0;
        while (true) {
            try {
                System.out.print(Colors.WHITE_BOLD + "\nEnter Cinema ID (0 to cancel): " + Colors.RESET);
                id = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number." + Colors.RESET);
                continue;
            }

            if (id == 0) {
                return CANCELLED;
            } else if (id < 1) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number." + Colors.RESET);
                continue;
            }
            return getCinemaByID(id);
        }
    }

    private CinemaNode getCinemaByID(int id) {
        for (CinemaNode c : cinemas) {
            if (c.getID() == id) return c;
        }
        return null;
    }

    private boolean isSeatBooked(int cinemaId, int row, int seatNum) {
        for (Receipt r : receipts) {
            if (r.getCinemaID() == cinemaId && r.getRow() == row && r.getSeatNumber() == seatNum) {
                return true;
            }
        }
        return false;
    }
    public static LinkedListM<Receipt> getReceipts() {
        return receipts;
    }
}
