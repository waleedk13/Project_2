/*@author Waleed Khalid
* @author Rehan Baig
*
* !! Explain what this class does !!
* */


package project2;

public class Appointment implements Comparable <Appointment> {
    //below are the instance variables
    private Date date;
    private Timeslot timeslot; //enum class
    private Profile patientProfile;
    private Provider provider; //enum class

    //constructor class
    public Appointment( Date date, Timeslot timeslot, Profile patientProfile, Provider provider){
        this.date = date;
        this.timeslot = timeslot;
        this.patientProfile = patientProfile;
        this.provider = provider;
    }
    //default constructor class
    public Appointment(){
        this.date = null;
        this.timeslot = null;
        this.patientProfile = null;
        this.provider = null;
    }

    //purpose is to see if two appointment objects are equal
    @Override
    public boolean equals(Object obj){
        if(this == obj){//checks to see if both objects point to the same memory location
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) { //if the object is null or if
            return false;
        }
        Appointment other = (Appointment) obj;/*isn't this pointless? because both objects are being compared already
                                                meaning they should both be within the appointment class
                                                   so why would you need to cast it to an appoinment object*/

        return date.equals(other.date) && timeslot.equals(other.timeslot) && patientProfile.equals(other.patientProfile);
    }

    @Override
    public int compareTo(Appointment o) {

        int compareDates = this.date.compareTo(o.date);
        if(compareDates != 0){
            return compareDates;
        }


        int compareTimeslot = this.timeslot.compareTo(o.timeslot);
        if(compareTimeslot != 0){
            return compareTimeslot;
        }
        int comparePatient = this.patientProfile.compareTo(o.patientProfile);
        return comparePatient;


        /*implement the logic in here
        *
        * Purpose of this method is to compare two appointments by their
        * date
        * time
        *
        *
        * write code as to how you code compare the two using (if) conditionals2*/
    }


    @Override
    public String toString(){
        return date + " " + timeslot +
                patientProfile.getFirstName() + " " + patientProfile.getLastName() + " " + patientProfile.getDob()
                + " [" + provider.getLastName() + ", " + provider.getLocation().name()
                + ", " + provider.getLocation().getCounty() + " " + provider.getLocation().getZip()
                + ", " + provider.getSpecialty().name() + "]";

    }

    public String toStringWithoutProvider(){
        return date + " " + timeslot +
                patientProfile.getFirstName() + " " + patientProfile.getLastName() + " " + patientProfile.getDob();
    }

    public static void main(String[] args) {
        Profile patientProfile = new Profile("John", "Doe", new Date(1989, 12, 13));
        Timeslot timeslot = Timeslot.SLOT2;
        Date appointmentDate = new Date(2024, 9, 30);
        Provider provider = Provider.PATEL;

        Appointment appointment = new Appointment(appointmentDate, timeslot, patientProfile, provider);

        System.out.println(appointment.toString());
        System.out.flush();
    }

    public Profile getPatientProfile(){
        return patientProfile;

    }

    public Date getDate(){
        return date;
    }


    public Timeslot getTimeslot() {
        return this.timeslot;
    }


    public Provider getProvider() {
        return provider;
    }
}

