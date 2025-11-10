package DataStructures;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {
    private final String receiptID;
    private final int cinemaID;
    private final String movieTitle;
    private final int row;
    private final int seatNumber;
    private final double price;
    private final String timestamp;

    public Receipt(String receiptID, int cinemaID, String movieTitle, int row, int seatNumber, double price) {
        this.receiptID = receiptID;
        this.cinemaID = cinemaID;
        this.movieTitle = movieTitle;
        this.row = row;
        this.seatNumber = seatNumber;
        this.price = price;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(dtf);
    }

    // Getters
    public String getReceiptID() {
        return receiptID;
    }
    public int getCinemaID() {
        return cinemaID;
    }
    public String getMovieTitle() {
        return movieTitle;
    }
    public int getRow() {
        return row;
    }
    public int getSeatNumber() {
        return seatNumber;
    }
    public double getPrice() {
        return price;
    }
    public  String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format(
                "Receipt ID: %s | Cinema: %d | Movie: %s | Seat: Row %d Seat %d | Price: %.2f | Time: %s",
                receiptID, cinemaID, movieTitle, row, seatNumber, price, timestamp);
    }
}
