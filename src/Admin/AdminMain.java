package Admin;

import java.util.Scanner;

import DataStructures.CinemaNode;
import DataStructures.LinkedListM;
import User.UserBase;

public class AdminMain extends UserBase {
    private static final LinkedListM<CinemaNode> cinemas = new LinkedListM<>();
    private static final Scanner input = new Scanner(System.in);

    @Override
    public void displayMenu() {
        FrontEnd.Flow.adminDashboard();
        FrontEnd.Flow.userInput();
    }

    @Override
    public void run() {
        while (true) {
            try {
                displayMenu();

                int choice = Integer.parseInt(input.nextLine().trim());

                switch (choice) {
                    case 1 -> addCinema();
                    case 2 -> viewCinemas();
                    case 3 -> updateCinema();
                    case 4 -> deleteCinema();
                    case 0 -> {
                        System.out.println("Exiting...");
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


    private static void addCinema() {
        FrontEnd.Flow.addCinema();
        int id, rows, seatsPerRow;
        double price;
        String movie;


        while (true) {
            try {
                System.out.print("Enter Cinema Number: ");
                id = Integer.parseInt(input.nextLine().trim());
                if (id <= 0) throw new NumberFormatException();

                boolean exists = false;

                for (CinemaNode c : cinemas) {
                    if (c.getID() == id) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("ERROR: Cinema already exists.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Please enter a valid positive number.");
            }
        }

        System.out.print("Enter Movie Title: ");
        movie = input.nextLine().trim();

        while (true) {
            try {
                System.out.print("Enter Total Rows: ");
                rows = Integer.parseInt(input.nextLine().trim());
                if (rows <= 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Please enter a valid positive number.");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Seats per Row: ");
                seatsPerRow = Integer.parseInt(input.nextLine().trim());
                if (seatsPerRow <= 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Please enter a valid positive number.");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Pricing: ");
                price = Double.parseDouble(input.nextLine().trim());
                if (price <= 0.00) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Please enter a valid positive number.");
            }
        }

        CinemaNode cinema = new CinemaNode(id, "Cinema " + id, movie, rows, seatsPerRow, price);
        cinemas.insert(cinema);

        System.out.println("Movie \"" + movie + "\" added to Cinema " + id + " successfully!");
        FrontEnd.Flow.pressEnter();
        input.nextLine();
    }

    private static void viewCinemas() {
        System.out.println();

        if (cinemas.count() == 0) {
            FrontEnd.Flow.viewCinemasNone();
            input.nextLine();
            return;
        }

        StringBuilder records = new StringBuilder();

        for (CinemaNode c : cinemas) {
            records.append(String.format("║ %d  │ %-10s │  %d  │  %d     | %.2f ║  %n",
                    c.getID(), c.getName(),
                    c.getRows(), c.getSeatsPerRow(), c.getPrice()));
        }

        FrontEnd.Flow.viewCinemasRecords(records.toString());
        input.nextLine();
    }

    private static void updateCinema() {
        if (cinemas.count() == 0) {
            FrontEnd.Flow.viewCinemasNone();
            input.nextLine();
            return;
        }

        FrontEnd.Flow.updateCinema();

        System.out.print("Enter Cinema ID to update: ");
        int id = Integer.parseInt(input.nextLine().trim());

        for (CinemaNode c : cinemas) {
            if (c.getID() == id) {
                System.out.println("\nCurrent Cinema Info:");
                System.out.printf("ID: %d%n", c.getID());
                System.out.printf("Cinema Name: %s%n", c.getName());
                System.out.printf("Movie Title: %s%n", c.getMovie());
                System.out.printf("Rows: %d%n", c.getRows());
                System.out.printf("Seats per Row: %d%n", c.getSeatsPerRow());
                System.out.printf("Pricing: %.2f %n", c.getPrice());

                System.out.print("\nEnter new Cinema Name: ");
                String newName = input.nextLine();
                if (!newName.isEmpty()) c.setName(newName);

                System.out.print("Enter new Movie Title: ");
                String newMovie = input.nextLine();
                if (!newMovie.isEmpty()) c.setMovie(newMovie);

                int newRows;
                while (true) {
                    try {
                        System.out.print("Enter new Total Rows: ");
                        newRows = Integer.parseInt(input.nextLine().trim());
                        if (newRows <= 0) throw new NumberFormatException();
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: Please enter a valid positive number.");
                    }
                }

                int newSeatsPerRow;
                while (true) {
                    try {
                        System.out.print("Enter new Seats per Row: ");
                        newSeatsPerRow = Integer.parseInt(input.nextLine().trim());
                        if (newSeatsPerRow <= 0) throw new NumberFormatException();
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: Please enter a valid positive number.");
                    }
                }

                c.setRows(newRows);
                c.setSeatsPerRow(newSeatsPerRow);

                double pricing;
                while (true) {
                    try {
                        System.out.print("Enter new Pricing: ");
                        pricing = Double.parseDouble(input.nextLine().trim());
                        if (pricing <= 0) throw new NumberFormatException();
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: Please enter a valid positive number.");
                    }
                }

                c.setPrice(pricing);

                System.out.printf("Cinema \"%s - %s\" updated successfully!", c.getName(), c.getMovie());
                FrontEnd.Flow.pressEnter();
                input.nextLine();
                return;
            }
        }

        System.out.println("ERROR: Cinema not found.");
        FrontEnd.Flow.pressEnter();
        input.nextLine();
    }

    static void deleteCinema() {
        if  (cinemas.count() == 0) {
            FrontEnd.Flow.viewCinemasNone();
            input.nextLine();
            return;
        }

        FrontEnd.Flow.deleteCinema();

        System.out.print("Enter Cinema ID to delete: ");
        int id = Integer.parseInt(input.nextLine().trim());

        CinemaNode deleteMe = null;

        for (CinemaNode c : cinemas) {
            if (c.getID() == id) {
                deleteMe = c;
            }
        }

        if (deleteMe != null) {
            cinemas.deleteValue(deleteMe);
            System.out.printf("Cinema %d deleted successfully!%n", id);
            System.out.println("Press [Enter] to return to menu.");
            input.nextLine();
        } else {
            System.out.println("ERROR: Cinema not found.");
        }

    }

    public static LinkedListM<CinemaNode> getCinemas() {
        return cinemas;
    }
}