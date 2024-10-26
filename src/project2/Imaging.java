package project2;

import util.Date;

/**
 * The Imaging class represents an imaging appointment, which is a type of appointment
 * that involves a radiology room and a technician as the provider.
 * It extends the Appointment class and includes additional fields and methods specific to imaging.
 *
 * @author Waleed Khalid, Rehan Baig
 *  * @implemented Waleed Khalid, Rehan Baig
 */
public class Imaging extends Appointment {
    private Radiology room;  // The radiology room for the imaging appointment

    /**
     * Constructs an Imaging appointment with the specified date, timeslot, patient, technician, and radiology room.
     *
     * @param date The date of the imaging appointment.
     * @param timeslot The timeslot of the imaging appointment.
     * @param patient The patient involved in the imaging appointment.
     * @param technician The technician performing the imaging.
     * @param room The radiology room for the imaging appointment (e.g., XRAY, CATSCAN, etc.).
     */
    public Imaging(Date date, Timeslot timeslot, Person patient, Technician technician, Radiology room) {
        super(date, timeslot, patient, technician);  // Call the constructor of the Appointment class
        this.room = room;
    }

    /**
     * Gets the radiology room for this imaging appointment.
     *
     * @return The radiology room for the appointment.
     */
    public Radiology getRoom() {
        return room;
    }

    /**
     * Sets the radiology room for this imaging appointment.
     *
     * @param room The radiology room to be set.
     */
    public void setRoom(Radiology room) {
        this.room = room;
    }

    /**
     * Sets the technician for this imaging appointment.
     * This updates the providerPerson field in the Appointment superclass.
     *
     * @param technician The technician to be set as the provider.
     */
    public void setTechnician(Technician technician) {
        this.providerPerson = technician;  // Assuming providerPerson holds the Technician in the Appointment superclass
    }

    /**
     * Returns a string representation of the imaging appointment, including the date, timeslot, patient, technician, and room.
     *
     * @return A string representing the imaging appointment.
     */
    @Override
    public String toString() {
        return date.toString() + " " + timeslot.toString() +
                patientPerson.getProfile().toString() + " " + providerPerson.toString() + "[" + room.toString() + "]";
    }
}
