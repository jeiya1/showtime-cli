package DataStructures;

public class CinemaNode {
    private final int id;
    private String name;
    private String movie;
    private int rows;
    private int seatsPerRow;
    private double price;
    private CinemaNode next;

    public CinemaNode(int id, String name, String movie, int rows, int seatsPerRow,  double price) {
        this.id = id;
        this.name = name;
        this.movie = movie;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.price = price;
        LinkedListM<SeatNode> seats = new LinkedListM<>();

        for  (int r = 0; r < rows; r++) {
            for (int s = 0; s < seatsPerRow; s++) {
                seats.insert(new SeatNode(r + 1, s + 1, false));
            }

        }
    }

    // Getters
    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getMovie() {
        return movie;
    }
    public int getRows() {
        return rows;
    }
    public int getSeatsPerRow() {
        return seatsPerRow;
    }
    public double getPrice() {
        return price;
    }

    // Setters
    public void setMovie(String movie) {
        this.movie = movie;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalSeats() {
        return rows * seatsPerRow;
    }

    @Override
    public String toString() {
        return String.format("%d │ %-10s │ %-15s │ Rows: %d │ Total Seats: %d",
                id, name, movie, rows, getTotalSeats());
    }
}
