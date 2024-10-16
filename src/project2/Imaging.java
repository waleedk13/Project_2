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

    public void setTechnician(Technician technician) {
        this.providerPerson = technician;  // Assuming providerPerson holds the Technician in the Appointment superclass
    }


    @Override
    public String toString() {
        return date.toString() + " " + timeslot.toString() +
                patientPerson.getProfile().toString() + " " + providerPerson.toString() + "[" + room.toString() + "]";
    }


}
