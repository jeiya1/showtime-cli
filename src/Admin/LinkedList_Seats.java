
package projectdsa;

 class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

 class LinkedListM {
    Node head;

    public void insert(int data) {
        Node node = new Node(data);
        if (head == null) head = node;
        else {
            Node current = head;
            while (current.next != null) current = current.next;
            current.next = node;
        }
    }

    public void deleteAt(int index) {
        if (head == null) return;
        if (index == 0) { head = head.next; return; }

        Node current = head;
        for (int i = 1; i < index && current.next != null; i++)
            current = current.next;

        if (current.next != null)
            current.next = current.next.next;
    }

    public void updateAt(int index, int data) {
        Node current = head;
        for (int i = 0; i < index && current != null; i++)
            current = current.next;
        if (current != null)
            current.data = data;
    }

    public int get(int index) {
        Node current = head;
        for (int i = 0; i < index && current != null; i++)
            current = current.next;
        return (current == null) ? -1 : current.data;
    }

    public int countNode() {
        Node current = head;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
