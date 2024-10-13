/**
 *
 *
 * @author Waleed Khalid, Rehan Baig
 */

package project2;


public class Appointment implements Comparable <Appointment>{
    protected Date date;
    protected Timeslot timeslot;
    protected Person person;
    protected Person provider;


    /**
     * Creates an Appointment object with the following
     *
     * @param date The date of the appointment.
     * @param timeslot The timeslot of the appointment (enum class).
     * @param person **FILL THIS IN**
     * @param provider **FILL THIS IN**
     */



    /**
     * Creates an Appointment object with the following
     *
     * @param date The date of the appointment.
     * @param timeslot The timeslot of the appointment (enum class).
     * @param person The profile of the patient.
     * @param provider The provider responsible for the appointment (enum class)
     */
    public Appointment(Date date, Timeslot timeslot, Person person,
                       Person provider){
        this.date = date;
        this.timeslot = timeslot;
        this.person = person;
        this.provider = provider;
    }

    /**
     * Default constructor for an Appointment object.
     * Sets the appointment with null values for
     * all the parameters from the constructor class to ensure they do not
     * contain anything
     */
    public Appointment(){
        this.date = null;
        this.timeslot = null;
        this.person = null;
        this.provider = null;

    }


    /**
     *
     * FIX THIS METHOD --- MAKE SURE TIMESLOT CLASS IS ABLE TO IMPLEMENT
     *                      A compareTo METHOD
     *
     *
     * Compares two appointments based on the date, timeslot, and patient profile
     * @param otherAppointment The other appointment to compare with.
     * @return A negative integer, zero, or a positive integer
     * if this appointment is earlier than, equal to, or
     * later than the specified appointment.
     */
    @Override
    public int compareTo(Appointment otherAppointment) {
        // Method implementation here
        if (this.date == null || otherAppointment.date == null) {
            throw new NullPointerException("Date cannot be null");
        }
        int compareDates = this.date.compareTo(otherAppointment.date);
        if(compareDates != 0){
            return compareDates;
        }
        // Null check for timeslot
        if (this.timeslot == null || otherAppointment.timeslot == null) {
            throw new NullPointerException("Timeslot cannot be null");
        }

        int compareTimeslot = this.timeslot.compareTo(otherAppointment.timeslot);

        if(compareTimeslot != 0){
            return compareTimeslot;
        }
        // Null check for person
        if (this.person == null || otherAppointment.person == null) {
            throw new NullPointerException("Person cannot be null");
        }
        int comparePerson = this.person.compareTo(otherAppointment.person);
        return comparePerson;
    }
}