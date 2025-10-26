package DataStructures;

public class QueueM {
    LinkedListM queue= new LinkedListM();
    
    void enqueue(int data){
        queue.insert(data);
        System.out.println(data + " is enqueued ");
    }
    
    void dequeue(){
        if(queue.head == null){
            System.out.println("Queue is empty");
        } else {
            System.out.println(queue.head.data + " is dequeued");
            queue.deleteAt(0);
        }
    }
    
    void peek(){
        if(queue.head == null){
            System.out.println("Queue is empty");
        } else {
        System.out.println("Front element: " + queue.head.data);
        }
    }
    
    void display(){
        if (queue.head == null){
            System.out.println("Queue is empty");
        }else{
        System.out.println("Queue Contents: ");
        queue.display();
        }
    }
}
