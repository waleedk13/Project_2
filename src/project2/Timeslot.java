/**
 *
 *
 * @author Waleed Khalid, Rehan Baig
 */

package project2;
public class Timeslot implements Comparable<Timeslot> {
    private int hour;
    private int minute;

    // Constructor
    public Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    // Getter methods
    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    // equals() method to compare if two Timeslot objects are the same
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Timeslot timeslot = (Timeslot) obj;
        return hour == timeslot.hour && minute == timeslot.minute;
    }

    // toString() method to represent Timeslot as a string in "HH:MM" format
    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute); // Ensures time is displayed in two digits
    }

    // compareTo() method to compare two Timeslot objects
    @Override
    public int compareTo(Timeslot otherTimeslot) {
        if (this.hour != otherTimeslot.hour) {
            return Integer.compare(this.hour, otherTimeslot.hour);
        }
        return Integer.compare(this.minute, otherTimeslot.minute);
    }
}
