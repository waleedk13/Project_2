package project2;

public class Appointment implements Comparable <Appointment> {
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

        int compareDates = this.date.compareTo(o.date); //compares date first
        if(compareDates != 0){
            return compareDates;
        }

        int compareTimeslot = this.timeslot.compareTo(o.timeslot); //compares timeslot next
        if(compareTimeslot != 0){
            return compareTimeslot;
        }

        return this.patientPerson.compareTo(o.patientPerson); //then compares patients
    }


    @Override
    public String toString(){
        return date.toString() + " " + timeslot.toString() +
                patientPerson.getProfile().toString() + " " + providerPerson.toString() + " booked";
    }


    //purpose is to see if two appointment objects are equal
    @Override
    public boolean equals(Object obj){
        if(this == obj){ //checks to see if both objects point to the same memory location
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) { //if the object is null or if
            return false;
        }
        Appointment other = (Appointment) obj;
        return date.equals(other.date) && timeslot.equals(other.timeslot) &&
                patientPerson.getProfile().equals(other.patientPerson.getProfile());
    }


    public Person getPatientPerson(){
        return patientPerson;

    }

    public Date getDate(){
        return date;
    }


    public Timeslot getTimeslot() {
        return this.timeslot;
    }


    public Person getProviderPerson() {
        return providerPerson;
    }

}
