package project2;

public class Timeslot implements Comparable<Timeslot> {
    private int hour;
    private int minute;


    public Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public int compareTo(Timeslot other) {
        if (this.hour != other.hour) {
            return Integer.compare(this.hour , other.hour);  // Return negative if this.hour is less, positive if greater
        }

        return Integer.compare(this.minute , other.minute);  // Return negative if this.minute is less, positive if greater
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }

        // Check if the object is an instance of Timeslot
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Timeslot other = (Timeslot) obj;
        return this.hour == other.hour && this.minute == other.minute;
    }

    @Override
    public String toString() {
        String period;
        if (hour >= 9 && hour <= 12) {
            period = "AM";
        } else {
            period = "PM";
        }
        return String.format("%d:%02d %s ", hour, minute, period);
    }

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

        // Afternoon slots: 2:00 PM to 5:00 PM (12-hour format)
        hour = 2;
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

    public static Timeslot getTimeslotFromNumber(int slotNumber, Timeslot[] timeslots) {
        if(slotNumber<1 || slotNumber>12){
            return null;
        }
        return timeslots[slotNumber - 1]; // Subtract 1 to match array index (0-based)
    }

    public static int getNumberFromTimeslot(Timeslot timeslot, Timeslot[] timeslots) {
        for (int i = 0; i < timeslots.length; i++) {
            if (timeslots[i].equals(timeslot)) {
                return i + 1; // Return the slot number (1-based index)
            }
        }
        return -1; // Return -1 if the timeslot is not found
    }


}




