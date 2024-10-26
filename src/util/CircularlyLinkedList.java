package util;

import project2.Node;
import project2.Technician;

/**
 * The CircularlyLinkedList class is a utility class that implements a circular linked list of technicians.
 * It provides methods for adding technicians, getting the current technician, and advancing through the list in a circular manner.
 *
 * @author Waleed Khalid, Rehan Baig
 */
public class CircularlyLinkedList {
    private Node head; // Points to the first node
    private Node current; // Points to the current node (used for rotation)

    /**
     * Constructor initializes an empty circularly linked list.
     */
    public CircularlyLinkedList() {
        head = null;
        current = null;
    }

    /**
     * Gets the current technician without advancing to the next one.
     * If the current node is null, it is set to the head of the list.
     *
     * @return The current Technician object.
     */
    public Technician getCurrentTechnician() {
        if (current == null) {
            current = head; // Initialize to head if current is not set
        }
        return current.getTechnician();
    }

    /**
     * Advances to the next technician in the circular list.
     * The list is circular, so it wraps around back to the head when the end is reached.
     */
    public void advanceToNextTechnician() {
        if (current != null) {
            current = current.getNext();
        }
    }

    /**
     * Adds a technician to the circular linked list.
     * The new technician is added to the end of the list, and the list remains circular.
     *
     * @param technician The Technician object to be added to the list.
     */
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
            newNode.setNext(head);  // New node points to the head

            // Reset current to head to ensure it starts from the beginning of the list
            current = head;
        }
    }

    /**
     * Checks if the circular linked list is empty.
     *
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return head == null;
    }
}
