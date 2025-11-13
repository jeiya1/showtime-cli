package Admin;

import java.util.Scanner;

import DataStructures.CinemaNode;
import DataStructures.LinkedListM;
import User.UserBase;
import Utils.Colors;

public class AdminMain extends UserBase {
    private static final LinkedListM<CinemaNode> cinemas = new LinkedListM<>();
    private static final Scanner input = new Scanner(System.in);

    public AdminMain() {
        setUserRole("Admin");
    }

    @Override
    public void displayMenu() {
        FrontEnd.Flow.adminDashboard();
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
                    case 1 -> addCinema();
                    case 2 -> viewCinemas();
                    case 3 -> updateCinema();
                    case 4 -> deleteCinema();
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
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Something unexpected occurred: \n" + e.getMessage() + Colors.RESET);
            }
        }
    }


    private void addCinema() {
        FrontEnd.Flow.addCinema();
        int id, rows, seatsPerRow;
        double price;
        String movie;


        while (true) {
            try {
                System.out.print(Colors.WHITE_BOLD + "\nEnter Cinema Number: " + Colors.RESET);
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
                    System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Cinema already exists.\n" + Colors.RESET);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number.\n" + Colors.RESET);
            }
        }

        System.out.print(Colors.WHITE_BOLD + "Enter Movie Title: " + Colors.RESET);
        movie = input.nextLine().trim();

        while (true) {
            try {
                System.out.print(Colors.WHITE_BOLD + "Enter Total Rows: " + Colors.RESET);
                rows = Integer.parseInt(input.nextLine().trim());
                if (rows <= 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Please enter a valid positive number.\n" + Colors.RESET);
            }
        }

        while (true) {
            try {
                System.out.print(Colors.WHITE_BOLD + "Enter Seats per Row: " + Colors.RESET);
                seatsPerRow = Integer.parseInt(input.nextLine().trim());
                if (seatsPerRow <= 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Please enter a valid positive number.\n" + Colors.RESET);
            }
        }

        while (true) {
            try {
                System.out.print(Colors.WHITE_BOLD + "Enter Pricing: " + Colors.RESET);
                price = Double.parseDouble(input.nextLine().trim());
                if (price <= 0.00) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number.\n" + Colors.RESET);
            }
        }

        CinemaNode cinema = new CinemaNode(id, "Cinema " + id, movie, rows, seatsPerRow, price);
        cinemas.insert(cinema);

        System.out.println(Colors.GREEN + Colors.BOLD + "\nMovie \"" + movie + "\" added to Cinema " + id + " successfully!" + Colors.RESET);
        FrontEnd.Flow.pressEnter();
        input.nextLine();
    }

    private void viewCinemas() {
        System.out.println();

        if (cinemas.count() == 0) {
            FrontEnd.Flow.viewCinemasNone();
            input.nextLine();
            return;
        }

        StringBuilder records = new StringBuilder();

        for (CinemaNode c : cinemas) {
            records.append(String.format(" %d  │ %-10s │ %4d │ %7d │ %10.2f %n",
                    c.getID(), c.getName(),
                    c.getRows(), c.getSeatsPerRow(), c.getPrice()));
        }

        FrontEnd.Flow.viewCinemasRecords(records.toString());
        input.nextLine();
    }

    private void updateCinema() {
        if (cinemas.count() == 0) {
            FrontEnd.Flow.viewCinemasNone();
            input.nextLine();
            return;
        }

        FrontEnd.Flow.updateCinema();

        System.out.print(Colors.WHITE_BOLD + "\nEnter Cinema ID to update: " + Colors.RESET);
        int id = Integer.parseInt(input.nextLine().trim());

        for (CinemaNode c : cinemas) {
            if (c.getID() == id) {
                System.out.println(Colors.WHITE_BOLD + "\nCurrent Cinema Info:" + Colors.RESET);
                System.out.printf(Colors.WHITE + "ID: %d%n" + Colors.RESET, c.getID());
                System.out.printf(Colors.WHITE + "Cinema Name: %s%n" + Colors.RESET, c.getName());
                System.out.printf(Colors.WHITE + "Movie Title: %s%n" + Colors.RESET, c.getMovie());
                System.out.printf(Colors.WHITE + "Rows: %d%n" + Colors.RESET, c.getRows());
                System.out.printf(Colors.WHITE + "Seats per Row: %d%n" + Colors.RESET, c.getSeatsPerRow());
                System.out.printf(Colors.WHITE + "Pricing: %.2f%n" + Colors.RESET, c.getPrice());


                System.out.print(Colors.WHITE_BOLD + "\nEnter new Cinema Name: " + Colors.RESET);
                String newName = input.nextLine();
                if (!newName.isEmpty()) c.setName(newName);

                System.out.print(Colors.WHITE_BOLD + "Enter new Movie Title: " + Colors.RESET);
                String newMovie = input.nextLine();
                if (!newMovie.isEmpty()) c.setMovie(newMovie);

                int newRows;
                while (true) {
                    try {
                        System.out.print(Colors.WHITE_BOLD + "Enter new Total Rows: " + Colors.RESET);
                        newRows = Integer.parseInt(input.nextLine().trim());
                        if (newRows <= 0) throw new NumberFormatException();
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number.\n" + Colors.RESET);
                    }
                }

                int newSeatsPerRow;
                while (true) {
                    try {
                        System.out.print(Colors.WHITE_BOLD + "Enter new Seats per Row: " + Colors.RESET);
                        newSeatsPerRow = Integer.parseInt(input.nextLine().trim());
                        if (newSeatsPerRow <= 0) throw new NumberFormatException();
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number.\n" + Colors.RESET);
                    }
                }

                c.setRows(newRows);
                c.setSeatsPerRow(newSeatsPerRow);

                double pricing;
                while (true) {
                    try {
                        System.out.print(Colors.WHITE_BOLD + "Enter new Pricing: " + Colors.RESET);
                        pricing = Double.parseDouble(input.nextLine().trim());
                        if (pricing <= 0) throw new NumberFormatException();
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number.\n" + Colors.RESET);
                    }
                }

                c.setPrice(pricing);

                System.out.printf(Colors.GREEN + "\nCinema \"%s - %s\" updated successfully!" + Colors.RESET, c.getName(), c.getMovie());
                FrontEnd.Flow.pressEnter();
                input.nextLine();
                return;
            }
        }

        System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Cinema not found.\n" + Colors.RESET);
        FrontEnd.Flow.pressEnter();
        input.nextLine();
    }

    private static void deleteCinema() {
        if  (cinemas.count() == 0) {
            FrontEnd.Flow.viewCinemasNone();
            input.nextLine();
            return;
        }

        FrontEnd.Flow.deleteCinema();

        System.out.print(Colors.WHITE_BOLD + "\nEnter Cinema ID to delete: " + Colors.RESET);
        int id = Integer.parseInt(input.nextLine().trim());

        CinemaNode deleteMe = null;

        for (CinemaNode c : cinemas) {
            if (c.getID() == id) {
                deleteMe = c;
            }
        }

        if (deleteMe != null) {
            cinemas.deleteValue(deleteMe);
            System.out.printf(Colors.GREEN + "\nCinema %d deleted successfully!%n\n" + Colors.RESET, id);
            System.out.println("Press " + Colors.GREEN + Colors.BOLD + "[Enter]" + Colors.RESET + "to return to menu.");
            input.nextLine();
        } else {
            System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Cinema not found.\n" + Colors.RESET);
        }

    }

    public static LinkedListM<CinemaNode> getCinemas() {
        return cinemas;
    }
}
