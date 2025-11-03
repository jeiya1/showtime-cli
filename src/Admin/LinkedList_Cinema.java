
package projectdsa;

 class LinkedListCinema {
    CinemaNode head;

    public void insert(CinemaNode cinema) {
        if (head == null) head = cinema;
        else {
            CinemaNode current = head;
            while (current.next != null) current = current.next;
            current.next = cinema;
        }
    }

    public void deleteAt(int index) {
        if (head == null) return;
        if (index == 0) { head = head.next; return; }

        CinemaNode current = head;
        for (int i = 1; i < index && current.next != null; i++)
            current = current.next;

        if (current.next != null)
            current.next = current.next.next;
    }

    public CinemaNode get(int index) {
        CinemaNode current = head;
        for (int i = 0; i < index && current != null; i++)
            current = current.next;
        return current;
    }

    public int countNode() {
        int count = 0;
        CinemaNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
