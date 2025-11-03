/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectdsa;


 class QueueM {
    LinkedListCinema queue = new LinkedListCinema();

    public void enqueue(CinemaNode data) {
        queue.insert(data);
    }

    public void dequeue() {
        queue.deleteAt(0);
    }

    public CinemaNode getAt(int index) {
        return queue.get(index);
    }

    public int count() {
        return queue.countNode();
    }

    public void deleteAt(int index) {
        queue.deleteAt(index);
    }
}
