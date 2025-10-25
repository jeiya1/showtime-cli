package DataStructures;

public class LinkedListM {
    Node head;

    public void insert(int data){
        Node node =new Node(data);
        if(head==null) head=node;
        else{
            Node curr=head;
            while (curr.next!=null){
                curr=curr.next;
            }
            curr.next=node;
        }
    }
    public void insertStart(int data){
        Node node=new Node(data);
        node.next=head;
        head=node;
    }
    public void insertAt(int index,int data){
        Node node =new Node(data);
        if(index==0) insertStart(data);
        else{
            Node curr=head;
            for(int i=1;i<index;i++){
                curr=curr.next;
            } 
            node.next=curr.next;
            curr.next=node;
        }
    }
    public void delete(int index){
        Node curr=head;
        if(index==0) head=head.next;
        else {
            for(int i=1;i<index;i++){
                curr=curr.next;
            }
            curr.next=curr.next.next;
        }
    }
    public void updateNode(int index, int data){
        Node curr=head;
        if(index ==0 ) head.data=data;
        else{
            for(int i=0;i<index;i++){
                curr=curr.next;
            } curr.data=data;
        }
    }
    public void display(){
        Node curr=head;
        if (head == null) {
        System.out.println("List is empty.");
        return;
    }
        while(curr!=null){
            System.out.print(curr.data+" ");
            curr=curr.next;
        }
    }
    public int countNode(){
        Node curr=head;
        int count=0;
        while(curr!=null){
            curr =curr.next;
            count++;
        }
        return count;
    }
}
class Node {
    int data;
    Node next;
    Node (int data){
        this.data=data;
        this.next=null;
    }
}
