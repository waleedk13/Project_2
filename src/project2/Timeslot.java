package project2;

/**
 * The Timeslot class represents a specific time of day, consisting of an hour and minute.
 * It provides methods to compare, format, and generate timeslots.
 * This class also supports generating and retrieving timeslots from a pre-defined array of time slots.
 *
 * @author Rehan Baig, Waleed Khalid
 *  * @implemented Waleed Khalid, Rehan Baig
 */
public class Timeslot implements Comparable<Timeslot> {
    private int hour;
    private int minute;

    /**
     * Constructs a Timeslot object with the specified hour and minute.
     *
     * @param hour The hour of the timeslot (in 24-hour format).
     * @param minute The minute of the timeslot.
     */
    public Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Gets the hour of this timeslot.
     *
     * @return The hour (in 24-hour format) of this timeslot.
     */
    public int getHour() {
        return hour;
    }

    /**
     * Gets the minute of this timeslot.
     *
     * @return The minute of this timeslot.
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Compares this timeslot to another timeslot based on the hour and minute.
     *
     * @param other The other timeslot to compare with.
     * @return A negative integer, zero, or a positive integer as this timeslot is less than,
     * equal to, or greater than the other timeslot.
     */
    @Override
    public int compareTo(Timeslot other) {
        if (this.hour != other.hour) {
            return Integer.compare(this.hour, other.hour);  // Compare hours
        }
        return Integer.compare(this.minute, other.minute);  // Compare minutes
    }

    /**
     * Checks whether this timeslot is equal to another object.
     *
     * @param obj The object to compare with.
     * @return true if the object is a Timeslot with the same hour and minute, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Timeslot other = (Timeslot) obj;
        return this.hour == other.hour && this.minute == other.minute;
    }

    /**
     * Returns a string representation of this timeslot in 12-hour format (AM/PM).
     *
     * @return A string representing the timeslot in the format "hour:minute AM/PM".
     */
    @Override
    public String toString() {
        String period;
        int displayHour = hour;
        if (hour < 12) {
            period = "AM";
        } else {
            period = "PM";
            if (hour > 12) {
                displayHour -= 12;  // Convert to 12-hour format for display
            }
        }
        return String.format("%d:%02d %s ", displayHour, minute, period);
    }

    /**
     * Populates an array of timeslots for morning and afternoon appointments.
     * Morning slots are from 9:00 AM to 12:00 PM, and afternoon slots are from 2:00 PM to 5:00 PM.
     *
     * @param timeslots The array of timeslots to populate.
     */
    public static void generateTimelots(Timeslot[] timeslots) {
        // Morning slots: 9:00 AM to 12:00 PM
        int hour = 9;
        int minute = 0;

        for (int i = 0; i < 6; i++) {
            timeslots[i] = new Timeslot(hour, minute);
            minute += 30;
            if (minute == 60) {
                hour++;
                minute = 0;
            }
        }

        // Afternoon slots: 2:00 PM to 5:00 PM (converted to 24-hour format)
        hour = 14;  // 2:00 PM in 24-hour format
        minute = 0;

        for (int i = 6; i < 12; i++) {
            timeslots[i] = new Timeslot(hour, minute);
            minute += 30;
            if (minute == 60) {
                hour++;
                minute = 0;
            }
        }
    }

    /**
     * Retrieves a timeslot from the given array based on the slot number.
     *
     * @param slotNumber The slot number (1-based index).
     * @param timeslots The array of timeslots.
     * @return The corresponding timeslot or null if the slot number is invalid.
     */
    public static Timeslot getTimeslotFromNumber(int slotNumber, Timeslot[] timeslots) {
        if (slotNumber < 1 || slotNumber > 12) {
            return null;
        }
        return timeslots[slotNumber - 1]; // Subtract 1 to match array index (0-based)
    }

    /**
     * Retrieves the slot number (1-based index) for the given timeslot from the array.
     *
     * @param timeslot The timeslot to search for.
     * @param timeslots The array of timeslots.
     * @return The slot number (1-based index), or -1 if the timeslot is not found.
     */
    public static int getNumberFromTimeslot(Timeslot timeslot, Timeslot[] timeslots) {
        for (int i = 0; i < timeslots.length; i++) {
            if (timeslots[i].equals(timeslot)) {
                return i + 1; // Return the slot number (1-based index)
            }
        }
        return -1; // Return -1 if the timeslot is not found
    }
}
