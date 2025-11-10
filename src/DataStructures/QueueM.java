package DataStructures;

public class QueueM {
    LinkedListM<Integer> queue= new LinkedListM<>();
    
    public void enqueue(int data) {
        queue.insert(data);
    }

    public void dequeue() {
        if (queue.count() == 0) {
            System.out.println("Queue is empty");
        } else {
            queue.deleteAt(0);
        }
    }

    public int peek() {
        if (queue.count() == 0) {
            System.out.println("Queue is empty");
            return -1;
        } else {
            for (Integer data : queue) {
                return data; // first element
            }
        }
        return -1;
    }

    public void display() {
        queue.display();
    }

}
