package Cashier;

import java.util.Scanner;
import DataStructures.*;
import Admin.AdminMain;
import User.UserBase;
import Utils.Colors;

import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CashierMain extends UserBase {
    private static final Scanner input = new Scanner(System.in);
    private static final LinkedListM<Receipt> receipts = new LinkedListM<>();
    private static final LinkedListM<CinemaNode> cinemas = AdminMain.getCinemas();
    private static final CinemaNode CANCELLED = new CinemaNode(-1, null, null, 0, 0, 0);

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
                    default -> System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Invalid option!\n" + Colors.RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Invalid input. Please enter a number.\n" + Colors.RESET);
            } catch (Exception e) {
                System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Something unexpected occurred:\n" + e.getMessage() + Colors.RESET);
            }
        }
    }

    private void takeReservation() {
        CinemaNode cinema = getCinemaByID();
        if (cinema == CANCELLED) return;
        if (cinema == null) {
            System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Cinema not found." + Colors.RESET);
            return;
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

                System.out.printf(Colors.GREEN + Colors.BOLD + "\nReservation successful!\nReceipt ID: %s%n" + Colors.RESET, receiptId);

                printReceipt(r, cinema);
                break;
            }

        } catch (Exception e) {
            System.out.println(Colors.RED + Colors.ITALIC + "ERROR: Something unexpected occurred: " + e.getMessage() + Colors.RESET);
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
        int rows = cinema.getRows();
        int seatsPerRow = cinema.getSeatsPerRow();

        System.out.println();
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "║" + Colors.BOLD + Colors.YELLOW + "         CINEMA SEAT LAYOUT       "
                + Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" + Colors.RESET);
        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);

        System.out.print(Colors.WHITE_BOLD + "         ");
        for (int s = 1; s <= seatsPerRow; s++) {
            System.out.printf("%2d  ", s);
        }
        System.out.println(Colors.RESET);

        for (int r = 1; r <= rows; r++) {
            System.out.print(Colors.WHITE_BOLD + String.format("Row %2d | ", r) + Colors.RESET);
            for (int s = 1; s <= seatsPerRow; s++) {
                boolean booked = isSeatBooked(cinema.getID(), r, s);
                if (booked) {
                    System.out.print(Colors.RED + "[X] " + Colors.RESET);
                } else {
                    System.out.print(Colors.GREEN + "[ ] " + Colors.RESET);
                }
            }
            System.out.println();
        }

        System.out.println("\n" + Colors.WHITE_BOLD + "Legend: " + Colors.RESET
                + Colors.GREEN + "[ ] Available " + Colors.RESET
                + Colors.RED + "[X] Booked" + Colors.RESET);
    }

    private String repeat(char c, int n) {
        return new String(new char[n]).replace('\0', c);
    }

    private String center(String s, int width) {
        if (s == null) s = "";
        if (s.length() >= width) return s.substring(0, width);
        int left = (width - s.length()) / 2;
        return repeat(' ', left) + s + repeat(' ', width - left - s.length());
    }

    private String formatCurrency(double amount) {
        return String.format("%.2f", amount);
    }

    private void printReceipt(Receipt r, CinemaNode cinema) {
        final int WIDTH = 40;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String now = LocalDateTime.now().format(dtf);

        System.out.println(Colors.WHITE + "=".repeat(WIDTH) + Colors.RESET);

        System.out.println(Colors.WHITE + center("SHOWTIME", WIDTH) + Colors.RESET);
        System.out.println(Colors.WHITE + center("OFFICIAL RECEIPT", WIDTH) + Colors.RESET);

        System.out.println(Colors.WHITE + "=".repeat(WIDTH) + Colors.RESET);

        System.out.printf(Colors.WHITE + "%-20s %s%n" + Colors.RESET, "Receipt ID:", r.getReceiptID());
        System.out.printf(Colors.WHITE + "%-20s %s%n" + Colors.RESET, "Date/Time:", now);

        System.out.println(Colors.WHITE + "-".repeat(WIDTH) + Colors.RESET);

        System.out.printf(Colors.WHITE + "%-10s: %s%n" + Colors.RESET, "Cinema", cinema.getID() + " (" + trimTo(cinema.getMovie(), 18) + ")");
        System.out.printf(Colors.WHITE + "%-10s: Row %d, Seat %d%n" + Colors.RESET, "Seat", r.getRow(), r.getSeatNumber());

        System.out.println(Colors.WHITE + "-".repeat(WIDTH) + Colors.RESET);

        String priceLabel = "Ticket x1";
        String priceStr = formatCurrency(r.getPrice());
        System.out.printf(Colors.WHITE + "%-30s %9s%n" + Colors.RESET, priceLabel, priceStr);

        System.out.println(Colors.WHITE + "-".repeat(WIDTH) + Colors.RESET);
        System.out.printf(Colors.WHITE + "%-30s %9s%n" + Colors.RESET, "TOTAL:", formatCurrency(r.getPrice()));

        System.out.println(Colors.WHITE + "=".repeat(WIDTH) + Colors.RESET);

        String bars = "|" + "=".repeat(Math.max(0, Math.min(30, r.getReceiptID().length()))) + "|";
        System.out.println(Colors.WHITE + center(bars, WIDTH) + Colors.RESET);
        System.out.println(Colors.WHITE + center(r.getReceiptID(), WIDTH) + Colors.RESET);

        System.out.println();
        System.out.println(Colors.WHITE + center("Enjoy the movie!", WIDTH) + Colors.RESET);
        System.out.println();

        System.out.println(Colors.WHITE + "=".repeat(WIDTH) + Colors.RESET);
        System.out.println();
    }


    private void viewSalesSummary() {
        if (receipts.count() == 0) {
            FrontEnd.Flow.viewSalesNone();
            input.nextLine();
            return;
        }

        final int WIDTH = 40;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String now = LocalDateTime.now().format(dtf);

        int totalTickets = 0;
        double totalRevenue = 0.0;

        System.out.println(Colors.WHITE + repeat('=', WIDTH) + Colors.RESET);
        System.out.println(Colors.WHITE + center("SALES SUMMARY", WIDTH) + Colors.RESET);
        System.out.println(Colors.WHITE + center(now, WIDTH) + Colors.RESET);
        System.out.println(Colors.WHITE + repeat('-', WIDTH) + Colors.RESET);

        System.out.printf(Colors.WHITE + "%-25s %10s%n" + Colors.RESET, "Cinema (Movie)", "Revenue");

        for (CinemaNode cinema : cinemas) {
            int sold = 0;
            double rev = 0.0;
            for (Receipt r : receipts) {
                if (r.getCinemaID() == cinema.getID()) {
                    sold++;
                    rev += r.getPrice();
                }
            }
            if (sold > 0) {
                String left = String.format("%d (%s)", cinema.getID(), trimTo(cinema.getMovie(), 18));
                System.out.printf(Colors.WHITE + "%-25s %10s%n" + Colors.RESET, left, formatCurrency(rev));
            }
            totalTickets += sold;
            totalRevenue += rev;
        }

        System.out.println(Colors.WHITE + repeat('-', WIDTH) + Colors.RESET);
        System.out.printf(Colors.WHITE + "%-25s %10d%n" + Colors.RESET, "Total Tickets:", totalTickets);
        System.out.printf(Colors.WHITE + "%-25s %10s%n" + Colors.RESET, "Total Revenue:", formatCurrency(totalRevenue));
        System.out.println(Colors.WHITE + repeat('=', WIDTH) + Colors.RESET);
        System.out.println(Colors.WHITE + center("End of Report", WIDTH) + Colors.RESET);
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
        System.out.println(Colors.WHITE_BOLD + "\n=== ALL CINEMA SEAT LAYOUTS ===" + Colors.RESET);
        for (CinemaNode cinema : cinemas) {
            printSeatGrid(cinema);
            System.out.println("--------------------------------------");
        }
    }

    private void displayAvailableSeats() {
        CinemaNode cinema = getCinemaByID();
        if (cinema == CANCELLED) return;
        if (cinema != null) {
            printSeatGrid(cinema);
        } else {
            System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Cinema not found." + Colors.RESET);
        }
    }

    private void showAllReceipts() {
        if (receipts.count() == 0) {
            FrontEnd.Flow.viewReceiptNone();
            input.nextLine();
            return;
        }
        System.out.println(Colors.WHITE_BOLD + "\n=== ALL RECEIPTS ===" + Colors.RESET);
        for (Receipt r : receipts) {
            CinemaNode cinema = getCinemaByID(r.getCinemaID());
            if (cinema != null) printReceipt(r, cinema);
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
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid ID.\n" + Colors.RESET);
                continue;
            }
            for (Receipt r : receipts) {
                if (r.getReceiptID().equalsIgnoreCase(searchId)) {
                    CinemaNode cinema = getCinemaByID(r.getCinemaID());
                    if (cinema != null) printReceipt(r, cinema);
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
            return null;
        }
        int id = 0;
        while (true) {
            try {
                System.out.print(Colors.WHITE_BOLD + "Enter Cinema ID (0 to cancel): " + Colors.RESET);
                id = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number.\n" + Colors.RESET);
                continue;
            }

            if (id == 0) {
                return CANCELLED;
            } else if (id < 1) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number.\n" + Colors.RESET);
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
}
