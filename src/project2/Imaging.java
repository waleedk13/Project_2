/**
 *
 *
 * @author Waleed Khalid, Rehan Baig
 */

package project2;
public class Imaging extends Appointment {
    private Radiology room;


    //constructor class
    public Imaging(Date date, Timeslot timeslot, Person patient,
                   Person provider, Radiology room){
        super(date, timeslot, patient, provider);
        this.room = room;
    }

    public Radiology getRoom(){
        return room;
    }

    // Override the toString method for better output formatting
    @Override
    public String toString() {
        return super.toString() + ", Room: " + room.toString();
    }
}


