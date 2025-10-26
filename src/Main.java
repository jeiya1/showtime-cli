//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
import DataStructures.StackM;
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StackM s = new StackM();

        s.push(10);
        s.push(20);
        s.push(30);

        s.display();
        s.peek();

        s.pop();
        s.display();

        System.out.println("Is empty? " + s.isEmpty());
    }
}