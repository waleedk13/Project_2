/*@author Waleed Khalid
 * @author Rehan Baig
 *
 * !! Explain what this class does !!
 * */



package project2;


/*The purpose of this class is to define a node in a singly linked list which
* maintains the list of vists
* you can add constructors and method but can not change the instance variables
* */
public class Visit {
    private Appointment appointment;
    private Visit next;


    public Visit(Appointment appointment, Visit next){
        this.appointment = appointment;
        this.next = next;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    // Getter for the next Visit object (next node in the linked list)
    public Visit getNext() {

        return next;
    }

    public void setNext(Visit next) {
        this.next = next;
    }

    public void printVisits() {
        Visit current = this;
        while (current != null) {
            System.out.println(current.getAppointment().toString());
            System.out.flush(); // Print each appointment
            current = current.getNext(); // Move to the next node
        }
    }
}
