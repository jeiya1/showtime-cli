package DataStructures;

public class StackM{
    LinkedListM stack = new LinkedListM();

    public void push(int data) {
        stack.insertStart(data);
    }
    
    public int pop() {
        if (stack.head == null){
            System.out.println("Stack is empty");
            return -1;
        } else {
            int data = stack.head.data;
            stack.deleteAt(0);
            return data;
        }
     }
    
    public void display() {
        if (stack.head == null) {
            System.out.println("Stack is empty!");
        } else {
            stack.display();
        }
    }
    
    public int peek() {
        if (stack.head == null) {
            System.out.println("Stack is empty!");
            return -1;
        } else {
            return stack.head.data;
        }
    }
    
    public boolean isEmpty() {
        return stack.head == null;
    }
}