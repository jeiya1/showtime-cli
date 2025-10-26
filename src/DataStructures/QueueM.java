package DataStructures;

public class QueueM {
    LinkedListM queue= new LinkedListM();
    
    public void enqueue(int data) {
        queue.insert(data);
    }
    
    public void dequeue() {
        if(queue.head == null){
            System.out.println("Queue is empty");
        } else {
            queue.deleteAt(0);
        }
    }
    
    public int peek() {
        if(queue.head == null){
            System.out.println("Queue is empty");
            return -1;
        } else {
            return queue.head.data;
        }
    }
    
    public void display() {
        if (queue.head == null){
            System.out.println("Queue is empty");
        } else {
            queue.display();
        }
    }
}
