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
                    default -> System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Invalid option!" + Colors.RESET);
                }

            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Invalid input. Please enter a number." + Colors.RESET);
            } catch (Exception e) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Something unexpected occurred: " + e.getMessage() + Colors.RESET);
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
                System.out.print(Colors.WHITE_BOLD + "\nEnter Cinema Number (0 to cancel): " + Colors.RESET);
                id = Integer.parseInt(input.nextLine().trim());
                if (id < 0) {
                    throw new NumberFormatException();
                } else if (id == 0) {
                    return;
                }
                boolean exists = false;

                for (CinemaNode c : cinemas) {
                    if (c.getID() == id) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Cinema already exists." + Colors.RESET);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number." + Colors.RESET);
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
                System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Please enter a valid positive number." + Colors.RESET);
            }
        }

        while (true) {
            try {
                System.out.print(Colors.WHITE_BOLD + "Enter Seats per Row: " + Colors.RESET);
                seatsPerRow = Integer.parseInt(input.nextLine().trim());
                if (seatsPerRow <= 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC + "\nERROR: Please enter a valid positive number." + Colors.RESET);
            }
        }

        while (true) {
            try {
                System.out.print(Colors.WHITE_BOLD + "Enter Pricing: " + Colors.RESET);
                price = Double.parseDouble(input.nextLine().trim());
                if (price <= 0.00) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number." + Colors.RESET);
            }
        }

        CinemaNode cinema = new CinemaNode(id, "Cinema " + id, movie, rows, seatsPerRow, price);
        cinemas.insert(cinema);

        System.out.println(Colors.GREEN + Colors.BOLD + "\nMovie \"" + movie + "\" added to Cinema " + id + " successfully!" + Colors.RESET);
        FrontEnd.Flow.pressEnter();
        input.nextLine();
    }

    private void viewCinemas() {
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

        while (true) {
            int id = 0;
            try {
                System.out.print(Colors.WHITE_BOLD + "\nEnter Cinema ID to update (0 to cancel): " + Colors.RESET);
                id = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number." + Colors.RESET);
                continue;
            }

            if (id == 0) {
                return;
            } else if (id < 0) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number." + Colors.RESET);
                continue;
            }

            boolean found = false;

            for (CinemaNode c : cinemas) {
                if (c.getID() == id) {
                    found = true;
                    System.out.println();
                        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);
                        System.out.println(
                                Colors.BG_RED + Colors.BLACK + "║" +
                                Colors.BOLD + Colors.YELLOW + "        CURRENT CINEMA INFO       " +
                                Colors.RESET + Colors.BG_RED + Colors.BLACK + "║" +
                                Colors.RESET
                        );
                        System.out.println(Colors.BG_RED + Colors.BLACK + "o═══════o════════o════════o════════o" + Colors.RESET);
                    
                    System.out.printf(Colors.WHITE_BOLD + "\nID: " + Colors.RESET + c.getID() + "\n");
                    System.out.printf(Colors.WHITE_BOLD + "Cinema Name: " + Colors.RESET + c.getName() + "\n");
                    System.out.printf(Colors.WHITE_BOLD + "Movie Title: " + Colors.RESET + c.getMovie() + "\n");
                    System.out.printf(Colors.WHITE_BOLD + "Rows: " + Colors.RESET + c.getRows() + "\n");
                    System.out.printf(Colors.WHITE_BOLD + "Seats per Row: " + Colors.RESET + c.getSeatsPerRow() + "\n");
                    System.out.printf(Colors.WHITE_BOLD + "Pricing: " + Colors.RESET + c.getPrice() + "\n");


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
                            String in = input.nextLine().trim();
                            if (in.isEmpty()) break;
                            newRows = Integer.parseInt(in);
                            if (newRows <= 0) throw new NumberFormatException();
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number." + Colors.RESET);
                        }
                    }
                    newRows = c.getRows();

                    int newSeatsPerRow;
                    while (true) {
                        try {
                            System.out.print(Colors.WHITE_BOLD + "Enter new Seats per Row: " + Colors.RESET);
                            String in = input.nextLine().trim();
                            if (in.isEmpty()) break;
                            newSeatsPerRow = Integer.parseInt(in);
                            if (newSeatsPerRow <= 0) throw new NumberFormatException();
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number." + Colors.RESET);
                        }
                    }

                    newSeatsPerRow = c.getSeatsPerRow();

                    c.setRows(newRows);
                    c.setSeatsPerRow(newSeatsPerRow);

                    double pricing;
                    while (true) {
                        try {
                            System.out.print(Colors.WHITE_BOLD + "Enter new Pricing: " + Colors.RESET);
                            String in = input.nextLine().trim();
                            if (in.isEmpty()) break;
                            pricing = Double.parseDouble(in);
                            if (pricing <= 0) throw new NumberFormatException();
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number." + Colors.RESET);
                        }
                    }

                    pricing = c.getPrice();

                    c.setPrice(pricing);

                    System.out.printf(Colors.GREEN + Colors.BOLD + "\nCinema \"%s - %s\" updated successfully!\n" + Colors.RESET, c.getName(), c.getMovie());
                    FrontEnd.Flow.pressEnter();
                    input.nextLine();
                    return;
                }
            }
            if (!found) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Cinema not found." + Colors.RESET);
            }
        }
    }

    private static void deleteCinema() {
        if  (cinemas.count() == 0) {
            FrontEnd.Flow.viewCinemasNone();
            input.nextLine();
            return;
        }

        FrontEnd.Flow.deleteCinema();

        while (true) {
            int id = 0;
            try {
                System.out.print(Colors.WHITE_BOLD + "\nEnter Cinema ID to delete (0 to cancel): " + Colors.RESET);
                id = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number." + Colors.RESET);
                continue;
            }
            if (id == 0) {
                return;
            } else if (id < 0) {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Please enter a valid positive number." + Colors.RESET);
                continue;
            }

            CinemaNode deleteMe = null;

            for (CinemaNode c : cinemas) {
                if (c.getID() == id) {
                    deleteMe = c;
                }
            }

            if (deleteMe != null) {
                cinemas.deleteValue(deleteMe);
                System.out.printf(Colors.GREEN + Colors.BOLD + "\nCinema %d deleted successfully!%n" + Colors.RESET, id);
                System.out.println("Press " + Colors.GREEN + Colors.BOLD + "[Enter]" + Colors.RESET + "to return to menu.");
                input.nextLine();
                return;
            } else {
                System.out.println(Colors.RED + Colors.ITALIC +"\nERROR: Cinema not found." + Colors.RESET);
            }
        }
    }

    public static LinkedListM<CinemaNode> getCinemas() {
        return cinemas;
    }
}
