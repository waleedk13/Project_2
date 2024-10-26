package project2;

import util.Date;

/**
 * The Appointment class represents an appointment in the clinic.
 * It contains the date, timeslot, patient, and provider involved in the appointment.
 * The class also provides methods to compare, check equality, and represent the appointment as a string.
 *
 * @author Waleed Khalid, Rehan Baig
 * @implemented Waleed Khalid, Rehan Baig
 */
public class Appointment implements Comparable<Appointment> {
    protected Date date;  // The date of the appointment
    protected Timeslot timeslot;  // The timeslot of the appointment
    protected Person patientPerson;  // The patient involved in the appointment
    protected Person providerPerson;  // The provider (doctor/technician) involved in the appointment

    /**
     * Constructs an Appointment with the specified date, timeslot, patient, and provider.
     *
     * @param date The date of the appointment.
     * @param timeslot The timeslot of the appointment.
     * @param patientPerson The patient involved in the appointment.
     * @param providerPerson The provider involved in the appointment.
     */
    public Appointment(Date date, Timeslot timeslot, Person patientPerson, Person providerPerson) {
        this.date = date;
        this.timeslot = timeslot;
        this.patientPerson = patientPerson;
        this.providerPerson = providerPerson;
    }

    /**
     * Default constructor that creates an Appointment with null values.
     */
    public Appointment() {
        this.date = null;
        this.timeslot = null;
        this.patientPerson = null;
        this.providerPerson = null;
    }

    /**
     * Compares this appointment to another appointment based on date, timeslot, and provider's last name.
     *
     * @param o The appointment to compare to.
     * @return A negative number, zero, or a positive number depending on the comparison.
     */
    @Override
    public int compareTo(Appointment o) {
        // Compare dates first
        int compareDates = this.date.compareTo(o.date);
        if (compareDates != 0) {
            return compareDates;
        }

        // Compare timeslots next
        int compareTimeslot = this.timeslot.compareTo(o.timeslot);
        if (compareTimeslot != 0) {
            return compareTimeslot;
        }

        // Compare provider's last names if both provider persons are of type 'Provider'
        if (this.providerPerson instanceof Provider && o.providerPerson instanceof Provider) {
            Provider thisProvider = (Provider) this.providerPerson;
            Provider otherProvider = (Provider) o.providerPerson;

            return thisProvider.getProfile().getLastName().compareTo(otherProvider.getProfile().getLastName());
        }

        return 0; // If all comparisons return equal
    }

    /**
     * Returns a string representation of the appointment including date, timeslot, patient, and provider.
     *
     * @return A string representing the appointment.
     */
    @Override
    public String toString() {
        return date.toString() + " " + timeslot.toString() +
                patientPerson.getProfile().toString() + " " + providerPerson.toString();
    }

    /**
     * Returns a string representation of the appointment excluding the provider.
     *
     * @return A string representing the appointment without the provider.
     */
    public String toStringWithoutProvider() {
        return date.toString() + " " + timeslot.toString() +
                patientPerson.getProfile().toString() + " ";
    }

    /**
     * Checks if two Appointment objects are equal based on date, timeslot, patient, and provider (if not null).
     *
     * @param obj The object to compare to.
     * @return true if the appointments are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Appointment other = (Appointment) obj;

        boolean dateMatch = this.date.equals(other.date);
        boolean timeSlotMatch = this.timeslot.equals(other.timeslot);
        boolean profileMatch = this.patientPerson.getProfile().equals(other.patientPerson.getProfile());

        // Ensure that null providerPerson does not throw exceptions
        boolean providerMatch = (this.providerPerson == null && other.providerPerson == null) ||
                (this.providerPerson != null && other.providerPerson != null &&
                        this.providerPerson.getProfile().equals(other.providerPerson.getProfile()));

        return dateMatch && timeSlotMatch && profileMatch && providerMatch;
    }

    /**
     * Gets the patient involved in this appointment.
     *
     * @return The patient person object.
     */
    public Person getPatientPerson() {
        return patientPerson;
    }

    /**
     * Gets the date of this appointment.
     *
     * @return The date object.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the timeslot of this appointment.
     *
     * @return The timeslot object.
     */
    public Timeslot getTimeslot() {
        return this.timeslot;
    }

    /**
     * Gets the provider involved in this appointment.
     *
     * @return The provider person object.
     */
    public Person getProviderPerson() {
        return providerPerson;
    }
}
