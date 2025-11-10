package Cashier;

import java.util.Scanner;
import DataStructures.*;
import Admin.AdminMain;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CashierMain {
    private static final Scanner input = new Scanner(System.in);
    private static final LinkedListM<Receipt> receipts = new LinkedListM<>();

    private static final LinkedListM<CinemaNode> cinemas = AdminMain.getCinemas();

    public static void run() {
        while (true) {
            try {
                FrontEnd.Flow.cashierDashboard();
                FrontEnd.Flow.userInput();

                int choice = Integer.parseInt(input.nextLine().trim());

                switch (choice) {
                    case 1 -> displayAllSeats();
                    case 2 -> displayAvailableSeats();
                    case 3 -> showAllReceipts();
                    case 4 -> searchReceipt();
                    case 5 -> viewSalesSummary();
                    case 6 -> takeReservation();
                    case 0 -> {
                        System.out.println("Returning to main menu...");
                        return;
                    }
                    default -> System.out.println("ERROR: Invalid option!");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("ERROR: Something unexpected occurred: " + e.getMessage());
            }
        }
    }

    private static void takeReservation() {
        CinemaNode cinema = getCinemaByID();
        if (cinema == null) {
            return;
        }

        printSeatGrid(cinema);
        int rows = cinema.getRows();
        int seatsPerRow = cinema.getSeatsPerRow();

        LinkedListM<SeatNode> seats = new LinkedListM<>();
        for (int r = 1; r <= rows; r++) {
            for (int s = 1; s <= seatsPerRow; s++) {
                seats.insert(new SeatNode(r, s, false));
            }
        }

        System.out.print("\nEnter Row Number: ");
        int row = Integer.parseInt(input.nextLine().trim());
        System.out.print("Enter Seat Number: ");
        int seatNum = Integer.parseInt(input.nextLine().trim());

        if (row <= 0 || row > rows || seatNum <= 0 || seatNum > seatsPerRow) {
            System.out.println("ERROR: Invalid seat selection.");
            return;
        }

        SeatNode reservedSeat = new SeatNode(row, seatNum, true);
        reservedSeat.book();


        String receiptId = UUID.randomUUID().toString().substring(0, 8);
        double price = cinema.getPrice();
        Receipt r = new Receipt(receiptId, cinema.getID(), cinema.getMovie(), row, seatNum, price);
        receipts.insert(r);

        System.out.printf("Reservation successful!%nReceipt ID: %s%n", receiptId);
    }

    private static void displayAllSeats() {
        if (cinemas.count() == 0) {
            FrontEnd.Flow.viewCinemasNone();
            input.nextLine();
            return;
        }

        System.out.println("\n=== ALL CINEMA SEAT LAYOUTS ===");

        for (CinemaNode cinema : cinemas) {
            printSeatGrid(cinema);
            System.out.println("--------------------------------------");
        }
    }

    private static void displayAvailableSeats() {
        CinemaNode cinema = getCinemaByID();
        if (cinema != null) {
            printSeatGrid(cinema);
        }
    }

    private static void showAllReceipts() {
        if (receipts.count() == 0) {
            FrontEnd.Flow.viewReceiptNone();
            input.nextLine();
            return;
        }

        System.out.println("\n=== ALL RECEIPTS ===");
        for (Receipt r : receipts) {
            System.out.println(r);
        }
    }

    private static void searchReceipt() {
        if (receipts.count() == 0) {
            FrontEnd.Flow.viewReceiptNone();
            input.nextLine();
            return;
        }

        System.out.print("Enter Receipt ID to search: ");
        String searchId = input.nextLine().trim();

        for (Receipt r : receipts) {
            if (r.getReceiptID().equalsIgnoreCase(searchId)) {
                System.out.println("\nReceipt Found:\n" + r);
                return;
            }
        }

        System.out.println("ERROR: Receipt not found.");
    }

    private static boolean isSeatBooked(int cinemaId, int row, int seatNum) {
        for (Receipt r : receipts) {
            if (r.getCinemaID() == cinemaId && r.getRow() == row && r.getSeatNumber() == seatNum) {
                return true;
            }
        }
        return false;
    }

    private static void printSeatGrid(CinemaNode cinema) {
        int id = cinema.getID();
        int rows = cinema.getRows();
        int seatsPerRow = cinema.getSeatsPerRow();

        System.out.printf("%n=== SEAT LAYOUT FOR CINEMA %d (%s) ===%n", id, cinema.getMovie());
        System.out.println("(X = Booked, O = Available)\n");

        System.out.print("       ");
        for (int s = 1; s <= seatsPerRow; s++) {
            System.out.printf("%2d ", s);
        }
        System.out.println();

        for (int r = 1; r <= rows; r++) {
            System.out.printf("Row %2d ", r);
            for (int s = 1; s <= seatsPerRow; s++) {
                boolean booked = isSeatBooked(id, r, s);
                System.out.print(booked ? " X " : " O ");
            }
            System.out.println();
        }
    }


    private static void viewSalesSummary() {
        if (receipts.count() == 0) {
            FrontEnd.Flow.viewSalesNone();
            input.nextLine();
            return;
        }

        double totalRevenue = 0;
        int totalTickets = 0;

        System.out.println("\n=== SALES SUMMARY ===");
        for (CinemaNode cinema : cinemas) {
            int cinemaTickets = 0;
            double cinemaRevenue = 0;

            for (Receipt r : receipts) {
                if (r.getCinemaID() == cinema.getID()) {
                    cinemaTickets++;
                    cinemaRevenue += r.getPrice();
                }
            }

            if (cinemaTickets > 0) {
                System.out.printf("Cinema %d (%s): %d tickets sold | Revenue: %.2f%n",
                        cinema.getID(), cinema.getMovie(), cinemaTickets, cinemaRevenue);
            }

            totalTickets += cinemaTickets;
            totalRevenue += cinemaRevenue;
        }

        System.out.println("------------------------------");
        System.out.printf("Total Tickets Sold: %d%n", totalTickets);
        System.out.printf("Total Revenue: %.2f%n", totalRevenue);
    }

    @Nullable
    private static CinemaNode getCinemaByID() {
        if (cinemas.count() == 0) {
            FrontEnd.Flow.viewCinemasNone();
            input.nextLine();
            return null;
        }

        System.out.print("Enter Cinema ID: ");
        int id = Integer.parseInt(input.nextLine().trim());

        for (CinemaNode c : cinemas) {
            if (c.getID() == id) {
                return c;
            }
        }

        System.out.println("ERROR: Cinema not found.");
        return null;
    }
}