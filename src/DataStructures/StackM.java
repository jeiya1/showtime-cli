package DataStructures;

public class StackM{
    LinkedListM<Integer> stack = new LinkedListM<>();

    public void push(int data) {
        stack.insertStart(data);
    }
    
    public int pop() {
        if (stack.count() == 0){
            System.out.println("Stack is empty");
            return -1;
        } else {
            int data = -1;
            for (Integer value : stack) {
                data = value;
                break;
            }
            stack.deleteAt(0);
            return data;
        }
     }
    
    public void display() {
        if (stack.count() == 0) {
            System.out.println("Stack is empty!");
        } else {
            stack.display();
        }
    }
    
    public int peek() {
        if (stack.count() == 0) {
            System.out.println("Stack is empty!");
            return -1;
        } else {
            for (Integer value : stack) {
                return value;
            }
        }
        return -1;
    }
    
    public boolean isEmpty() {
        return stack.count() == 0;
    }
}