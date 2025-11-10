package DataStructures;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class LinkedListM <T> implements Iterable<T> {
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public void insert(T data) {
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

    public void insertStart(T data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
    }

    public void insertAt(int index, T data) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot insert at Negative index!");
        }

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
    public void deleteValue(T value) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        if (head.data.equals(value)) {
            head = head.next;
            return;
        }

        Node current = head;
        Node prev = null;

        while (current != null) {
            if (current.data.equals(value)) {
                assert prev != null;
                prev.next = current.next;
                return;
            }
            prev = current;
            current = current.next;
        }

        System.out.println("Value not found in list!");
    }

    public void updateNode(int index, T data) {
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
            System.out.println("List is empty!");
            return;
        }
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public int count() {
        Node current = head;
        int count = 0;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return new Iterator<>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

}