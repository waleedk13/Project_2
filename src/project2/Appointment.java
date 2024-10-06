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

    public Appointment(Date date, Timeslot timeslot, Person person, Person provider){
        this.date = date;
        this.timeslot = timeslot;
        this.person = person;
        this.provider = provider;
    }

    public Appointment(){
        this.date = null;
        this.timeslot = null;
        this.person = null;
        this.provider = null;

    }

    @Override
    public int compareTo(Appointment o) {
        return 0;
    }
}