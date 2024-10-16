package project2;

public class CircularlyLinkedList {
    private Node head; // Points to the first node
    private Node current; // Points to the current node (used for rotation)

    // Constructor initializes an empty list
    public CircularlyLinkedList() {
        head = null;
        current = null;
    }

    // Get the current technician without moving to the next one
    public Technician getCurrentTechnician() {
        if (current == null) {
            current = head; // Initialize to head if current is not set
        }
        return current.getTechnician();
    }

    // Move to the next technician in the list
    public void advanceToNextTechnician() {
        if (current != null) {
            current = current.getNext();
        }
    }

    // Method to add a technician to the circular list
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

            // Insert newNode at the end
            temp.setNext(newNode);  // Last node now points to the new node
            newNode.setNext(head);   // New node points to the head

            // Reset current to head to ensure it starts from the beginning of the list
            current = head;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}
