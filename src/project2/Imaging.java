package project2;

public class Imaging extends Appointment {
    private Radiology room;

    public Imaging(Date date, Timeslot timeslot, Person patient, Technician technician, Radiology room) {
        super(date, timeslot, patient, technician);
        this.room = room;
    }


    public Radiology getRoom() {
        return room;
    }


    public void setRoom(Radiology room) {
        this.room = room;
    }
}
