package DataStructures;

public class StackM<T> {
    LinkedListM<T> stack = new LinkedListM<>();

    public void push(T data) {
        stack.insertStart(data);
    }
    
    public T pop() {
        if (stack.count() == 0){
            System.out.println("Stack is empty");
            return null;
        } else {
            T data = null;
            for (T value : stack) {
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
    
    public T peek() {
        if (stack.count() == 0) {
            System.out.println("Stack is empty!");
            return null;
        } else {
            for (T value : stack) {
                return value;
            }
        }
        return null;
    }
    
    public boolean isEmpty() {
        return stack.count() == 0;
    }
}