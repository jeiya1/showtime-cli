package DataStructures;

public class QueueM {
    LinkedListM queue= new LinkedListM();
    
    public void enqueue(int data){
        queue.insert(data);
        System.out.println(data + " is enqueued ");
    }
    
    public void dequeue(){
        if(queue.head == null){
            System.out.println("Queue is empty");
        } else {
            System.out.println(queue.head.data + " is dequeued");
            queue.deleteAt(0);
        }
    }
    
    public void peek(){
        if(queue.head == null){
            System.out.println("Queue is empty");
        } else {
        System.out.println("Front element: " + queue.head.data);
        }
    }
    
    public void display(){
        if (queue.head == null){
            System.out.println("Queue is empty");
        }else{
        System.out.println("Queue Contents: ");
        queue.display();
        }
    }
}
