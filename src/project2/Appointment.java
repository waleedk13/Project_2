package project2;

public class Appointment implements Comparable<Appointment> {
    protected Date date;
    protected Timeslot timeslot;
    protected Person patientPerson;
    protected Person providerPerson;

    public Appointment(Date date, Timeslot timeslot, Person patientPerson, Person providerPerson) {
        this.date = date;
        this.timeslot = timeslot;
        this.patientPerson = patientPerson;
        this.providerPerson = providerPerson;
    }

    public Appointment() {
        this.date = null;
        this.timeslot = null;
        this.patientPerson = null;
        this.providerPerson = null;
    }

    @Override
    public int compareTo(Appointment o) {
        int compareDates = this.date.compareTo(o.date); // Compare dates first
        if (compareDates != 0) {
            return compareDates;
        }

        int compareTimeslot = this.timeslot.compareTo(o.timeslot); // Compare timeslot next
        if (compareTimeslot != 0) {
            return compareTimeslot;
        }

        return this.patientPerson.compareTo(o.patientPerson); // Finally, compare patients
    }

    @Override
    public String toString() {
        return date.toString() + " " + timeslot.toString() +
                patientPerson.getProfile().toString() + " " + providerPerson.toString();
    }

    public String toStringWithoutProvider() {
        return date.toString() + " " + timeslot.toString() +
                patientPerson.getProfile().toString();
    }

    // Purpose is to see if two appointment objects are equal
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { // Checks if both objects point to the same memory location
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) { // Checks if the object is null or not the same class
            return false;
        }
        Appointment other = (Appointment) obj;
        return date.equals(other.date) &&
                timeslot.equals(other.timeslot) &&
                patientPerson.getProfile().equals(other.patientPerson.getProfile()) &&
                providerPerson.getProfile().equals(other.providerPerson.getProfile()); // Compare providerPerson as well
    }

    public Person getPatientPerson() {
        return patientPerson;
    }

    public Date getDate() {
        return date;
    }

    public Timeslot getTimeslot() {
        return this.timeslot;
    }

    public Person getProviderPerson() {
        return providerPerson;
    }
}
