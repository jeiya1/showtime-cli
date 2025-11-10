package DataStructures;

public class SeatNode {
    private final int row;
    private final int number;
    private boolean booked;

    public SeatNode(int row, int number, boolean b) {
        this.row = row;
        this.number = number;
        this.booked = false;
    }

    public int getRow() {
        return row;
    }
    public int getNumber() {
        return number;
    }
    public boolean isBooked() {
        return booked;
    }

    public void book() {
        if (!booked) {
            booked = true;
        } else {
            System.out.println("Seat already booked!");
        }
    }

    public void cancelBooking() {
        if (booked) {
            booked = false;
        } else {
            System.out.println("Seat is not booked!");
        }
    }

    @Override
    public String toString() {
        return String.format("Row %d Seat %d [%s]", row, number, booked ? "Booked" : "Available");
    }
}
