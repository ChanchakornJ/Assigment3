class Stack {
    private Node top;
    private int size;
    
    public Stack() {
        top = null;
        size = 0;
    }
    
    public void push(String data) {
        Node newNode = new Node(data);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        size++;
    }
    
    public Node pop() {
        if (top == null) {
            return null;
        } else {
            Node temp = top;
            top = top.next;
            size--;
            return temp;
        }
    }
    
    public Node peek() {
        return top;
    }
    
    public boolean isEmpty() {
        return top == null;
    }
    
    public int size() {
        return size;
    }
    
    public void display() {
        Node current = top;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }
}

class Node {
    String data;
    Node next;
    
    Node(String data) {
        this.data = data;
        this.next = null;
    }
    
    public void display() {
        System.out.println("Data: " + data);
    }
}
