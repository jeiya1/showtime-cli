/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectdsa;


public class CinemaNode {
    int id;
    String name;
    String movie;
    int rows;
    int seatsPerRow;
    LinkedListM seats;
    CinemaNode next;

    public CinemaNode(int id, String name, String movie, int rows, int seatsPerRow) {
        this.id = id;
        this.name = name;
        this.movie = movie;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        seats = new LinkedListM();
        for (int i = 0; i < rows; i++)
            seats.insert(seatsPerRow);
    }

    public int totalSeats() {
        return rows * seatsPerRow;
    }

    public String toString() {
        return id + " │ " + name + " │ " + rows + " │ " + totalSeats();
    }
}
