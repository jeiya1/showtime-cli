
package projectdsa;

import java.util.Scanner;

 class AdminMain {
     
    static Scanner sc = new Scanner(System.in);
    static QueueM cinemaQueue = new QueueM();
    static int cinemaCount = 0;

    public static void main(String[] args) {
        while (true) {
            adminDashboard();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addCinema();
                case 2 -> viewCinemas();
                case 3 -> updateCinema();
                case 4 -> deleteCinema();
                case 0 -> { System.out.println("Exiting..."); return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }

   static void adminDashboard() {
        System.out.print(
            "\n=============================\n" +
            "=       ADMIN DASHBOARD     =\n" +
            "=============================\n" +
            "= [1] Add Cinema            =\n" +
            "= [2] View Cinemas          =\n" +
            "= [3] Update Cinema Info    =\n" +
            "= [4] Delete Cinema         =\n" +
            "= [0] Back                  =\n" +
            "=============================\n" +
            "Select an option: "
        );
    }

    static void addCinema() {
        System.out.print("\nEnter Cinema Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Movie Title: ");
        String movie = sc.nextLine();
        System.out.print("Enter Total Rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter Seats per Row: ");
        int seatsPerRow = sc.nextInt();
        sc.nextLine();

        cinemaCount++;
        cinemaQueue.enqueue(new CinemaNode(cinemaCount, name, movie, rows, seatsPerRow));
        System.out.println("\nCinema added successfully!");
        System.out.println("Press [Enter] to return to main menu.");
        sc.nextLine();

    }

static void viewCinemas() {
    System.out.println();

    if (cinemaQueue.count() == 0) {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║              NO CINEMA RECORDS AVAILABLE              ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println("Press [Enter] to return to main menu.");
        sc.nextLine();
        return; 
    }

    System.out.println("╔════════════════════════════════════════════════════════════╗");
    System.out.println("║                       LIST OF CINEMAS                      ║");
    System.out.println("╠════╪════════════╪════════════════════╪══════╪══════════════╣");
    System.out.printf("║ %-3s│ %-10s│ %-20s│ %-5s│ %-12s║%n",
            "ID", "CINEMA NAME", "MOVIE TITLE", "ROWS", "SEATS");
    System.out.println("╠════╪════════════╪════════════════════╪══════╪══════════════╣");

    for (int i = 0; i < cinemaQueue.count(); i++) {
        CinemaNode c = cinemaQueue.getAt(i);
        System.out.printf("║ %-3d│ %-10s│ %-20s│ %-5d│ %-12d║%n",
                c.id, c.name, c.movie, c.rows, c.totalSeats());
    }

    System.out.println("╚════════════════════════════════════════════════════════════╝");
    System.out.println("Press [Enter] to return to main menu.");
    sc.nextLine();
}



static void updateCinema() {
    System.out.print("Enter Cinema ID to update: ");
    int id = sc.nextInt();
    sc.nextLine();

    for (int i = 0; i < cinemaQueue.count(); i++) {
        CinemaNode c = cinemaQueue.getAt(i);
        if (c.id == id) {

            System.out.println();
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║               CURRENT CINEMA INFO                ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.printf("║ ID: %-44d║%n", c.id);
            System.out.printf("║ Cinema Name: %-42s║%n", c.name);
            System.out.printf("║ Movie Title: %-41s║%n", c.movie);
            System.out.printf("║ Rows: %-43d║%n", c.rows);
            System.out.printf("║ Seats per Row: %-33d║%n", c.seatsPerRow);
            System.out.println("╚══════════════════════════════════════════════════╝");

            System.out.print("\nEnter new Cinema Name: ");
            c.name = sc.nextLine();
            System.out.print("Enter new Movie Title: ");
            c.movie = sc.nextLine();
            System.out.print("Enter new Total Rows: ");
            c.rows = sc.nextInt();
            System.out.print("Enter new Seats per Row: ");
            c.seatsPerRow = sc.nextInt();
            sc.nextLine();

            c.seats = new LinkedListM();
            for (int j = 0; j < c.rows; j++) {
                c.seats.insert(c.seatsPerRow);
            }

            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════╗");
            System.out.println("║                CINEMA UPDATED SUCCESSFULLY                  ║");
            System.out.println("╠════╪════════════╪════════════════════╪══════╪══════════════╣");
            System.out.printf("║ %-3s│ %-10s│ %-20s│ %-5s│ %-12s║%n",
                    "ID", "CINEMA NAME", "MOVIE ", "ROWS", "SEATS");
            System.out.println("╠════╪════════════╪════════════════════╪══════╪══════════════╣");
            System.out.printf("║ %-3d│ %-10s│ %-20s│ %-5d│ %-12d║%n",
                    c.id, c.name, c.movie, c.rows, c.totalSeats());
            System.out.println("╚════════════════════════════════════════════════════════════╝");
            System.out.println("Press [Enter] to return to menu.");
            sc.nextLine();
            return;
        }
    }
    System.out.println("Cinema not found!");
}

    static void deleteCinema() {
        System.out.print("Enter Cinema ID to delete: ");
        int id = sc.nextInt();
        for (int i = 0; i < cinemaQueue.count(); i++) {
            CinemaNode c = cinemaQueue.getAt(i);
            if (c.id == id) {
                cinemaQueue.deleteAt(i);
                System.out.println("Cinema deleted successfully!");
                return;
            }
            
        }
        System.out.println("Cinema not found!");
    }
}
