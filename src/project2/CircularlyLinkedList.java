package project2;

public class CircularlyLinkedList {
    private Node head; // Points to the first node
    private Node current; // Points to the current node (used for rotation)

    // Constructor initializes an empty list
    public CircularlyLinkedList() {
        head = null;
        current = null;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Technician getNextTechnician() {
        if (current == null) {
            return null; // No technicians in the list
        }

        Technician technician = current.getTechnician(); // Get the current technician
        current = current.getNext(); // Move to the next technician in the list
        return technician;
    }


    public Node getCurrent() {
        return current;
    }

    public void setCurrent(Node current) {
        this.current = current;
    }

    public void advanceCurrent() {
        if (current != null) {
            current = current.getNext();  // Move the pointer to the next node
        }
    }

    // Method to add a technician to the front of the circular list
    public void addTechnician(Technician technician) {
        Node newNode = new Node(technician, null);

        if (head == null) {
            // If the list is empty, initialize the first node
            head = newNode;
            newNode.setNext(head);  // Circular link to itself
            current = head;         // Initialize the current node
        } else {
            // Find the last node (the node that points back to the head)
            Node temp = head;
            while (temp.getNext() != head) {
                temp = temp.getNext();
            }

            // Insert newNode at the front
            temp.setNext(newNode);  // Last node now points to the new node
            newNode.setNext(head);   // New node points to the current head
            head = newNode;          // Update head to the new node
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}

