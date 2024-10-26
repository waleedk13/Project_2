package project2;

/**
 * The Node class represents a node in a linked list structure, containing a Technician and a reference to the next node.
 * It provides methods to access and modify the technician and the next node.
 *
 * @author Waleed Khalid, Rehan Baig
 *  * @implemented Waleed Khalid, Rehan Baig
 */
public class Node {
    private Technician technician;  // The technician associated with this node
    Node next;  // Reference to the next node in the linked list

    /**
     * Constructs a Node with the specified technician and reference to the next node.
     *
     * @param technician The technician associated with this node.
     * @param next The next node in the linked list.
     */
    public Node(Technician technician, Node next) {
        this.technician = technician;
        this.next = next;
    }

    /**
     * Gets the technician associated with this node.
     *
     * @return The technician in this node.
     */
    public Technician getTechnician() {
        return technician;
    }

    /**
     * Sets the technician for this node.
     *
     * @param technician The technician to be associated with this node.
     */
    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    /**
     * Gets the next node in the linked list.
     *
     * @return The next node.
     */
    public Node getNext() {
        return next;
    }

    /**
     * Sets the next node in the linked list.
     *
     * @param next The node to set as the next node.
     */
    public void setNext(Node next) {
        this.next = next;
    }
}
