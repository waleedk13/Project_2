package project2;

/**
 * The Visit class represents a node in a singly linked list that maintains a list of visits.
 * Each node contains an appointment and a reference to the next visit in the list.
 *
 * You can add constructors and methods but cannot change the instance variables.
 *
 * @author Waleed Khalid, Rehan Baig
 *  * @implemented Waleed Khalid, Rehan Baig
 */
public class Visit {
    private Appointment appointment;
    private Visit next;

    /**
     * Constructs a Visit node with the specified appointment and reference to the next visit.
     *
     * @param appointment The appointment for this visit.
     * @param next The next visit in the linked list.
     */
    public Visit(Appointment appointment, Visit next) {
        this.appointment = appointment;
        this.next = next;
    }

    /**
     * Returns the appointment associated with this visit.
     *
     * @return The appointment for this visit.
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Returns the next visit in the linked list.
     *
     * @return The next visit in the linked list, or null if this is the last visit.
     */
    public Visit getNext() {
        return next;
    }

    /**
     * Sets the reference to the next visit in the linked list.
     *
     * @param next The next visit to be linked.
     */
    public void setNext(Visit next) {
        this.next = next;
    }

    /**
     * Prints all visits starting from this visit, traversing through the linked list.
     */
    public void printVisits() {
        Visit current = this;
        while (current != null) {
            System.out.println(current.getAppointment().toString());
            System.out.flush(); // Print each appointment
            current = current.getNext(); // Move to the next node
        }
    }
}
