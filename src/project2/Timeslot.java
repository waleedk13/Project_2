/*@author Waleed Khalid
 * @author Rehan Baig
 *
 * !! Explain what this class does !!
 * */


package project2;

//enum class for timeslot
public enum Timeslot{
    SLOT1(9,0),
    SLOT2(10, 45),
    SLOT3(11,15),
    SLOT4(13,30),
    SLOT5(15,0),
    SLOT6(16,15);

    final int hour;
    final int minute;

    Timeslot(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    //getter method to return hour
    public int getHour(){
        return hour;
    }
    //getter method to return minute
    public int getMinute() {
        return minute;
    }

    public static Timeslot timeslotFromNumber(int timeSlotNumber) {
        Timeslot[] timeslots = Timeslot.values();
        if (timeSlotNumber < 1 || timeSlotNumber > timeslots.length) {
            System.out.println(timeSlotNumber + " is not a valid time slot.");
            System.out.flush();

            return null;
        }
        return timeslots[timeSlotNumber - 1]; // 1-based index adjustment
    }


    public int getTimeslotNumber() {
        return this.ordinal() + 1;  // ordinal() gives the index, so add 1 to make it 1-based
    }

    @Override
    public String toString() {
        int displayHour = hour;
        String period = "AM";

        // Convert 24-hour format to 12-hour format
        if (hour >= 12) {
            period = "PM";
            if (hour > 12) {
                displayHour = hour - 12;
            }
        } else if (hour == 0) {
            displayHour = 12; // Midnight case
        }

        // Return formatted time with AM/PM
        return String.format("%d:%02d %s ", displayHour, minute, period);
    }

}