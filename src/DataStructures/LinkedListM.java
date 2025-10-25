package DataStructures;

public class LinkedListM {
    Node head;

    public void insert(int data) {
        Node node = new Node(data);
        if (head == null) {
          head = node;
        } else {
            Node current = head;
            while (current.next != null){
                current = current.next;
            }
            current.next = node;
        }
    }
    public void insertStart(int data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
    }
    public void insertAt(int index, int data) {
        Node node = new Node(data);
        if (index == 0) {
            insertStart(data);
        } else {
            Node current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            } 
            node.next = current.next;
            current.next = node;
        }
    }
    public void deleteAt(int index) {
        Node current = head;
        if (index == 0) {
            head = head.next;
        } else {
            for (int i=1; i < index; i++){
                current = current.next;
            }
            current.next = current.next.next;
        }
    }
    public void updateNode(int index, int data) {
        Node current = head;
        if (index == 0) {
            head.data = data;
        } else {
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            current.data = data;
        }
    }
    public void display() {
        Node current = head;
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
    public int countNode() {
        Node current = head;
        int count = 0;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }
}
class Node {
    int data;
    Node next;

    Node (int data) {
        this.data = data;
        this.next = null;
    }
}