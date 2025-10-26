package DataStructures;

import java.util.Scanner;

class Node{
    int data;
    Node next;
}

class LinkedList{
    Node head;
    
    void push(int data){
        Node node = new Node();
        node.data = data;
        if (head==null){
            head=node;
        }
        else{
          node.next = head;
          head = node;         
        }     
    }
    
    int pop(){
        int data = 0;
        if(head==null){
        System.out.println("Stack is empty!");
        }
        else{
          data = head.data;
          head = head.next;
        }
        return data;
     }
    
    void display(){
        Node node = head;
        while(node!=null){
          System.out.println(node.data);
          node = node.next;
        }
    }
    
    int peek(){
    return head.data;
    }
    
    boolean isEmpty(){
    return head == null;    
    }   
}

public class StackM {
  public static void main(String[] args) {
      LinkedList ll = new LinkedList();
      Scanner sc = new Scanner(System.in);
      int choice = 0;
      
      do{
        System.out.println("[1] Push");
        System.out.println("[2] Pop");
        System.out.println("[3] Peek");
        System.out.println("[4] Display");
        System.out.print("Choice: ");
        choice = sc.nextInt();
        
        if(choice==1){
            System.out.println("Enter data: ");
            int data = sc.nextInt();
            ll.push(data);
        }
        else if (choice==2){
            System.out.println("Removed value: " + ll.pop());
        }
        else if (choice==3){
            System.out.println("Recent data: " + ll.peek());
        }
        else if (choice==4){
            System.out.println("Entered data: ");
            ll.display();
        }
    }while(choice!=0);    
}
}
