package project2;

import java.util.Calendar;

public class Date implements Comparable<Date>{
    private final int day;
    private final int month;
    private final int year;


    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Constructor that parses a date string in the format into date format
     * It would look like M/D/Y
     * @param date The date in string representation
     */
    public Date(String date) {
        final int MONTH = 0;
        final int DAY = 1;
        final int YEAR = 2;
        String[] appointmentDateParts = date.split("/");
        this.month = Integer.parseInt(appointmentDateParts[MONTH]);
        this.day = Integer.parseInt(appointmentDateParts[DAY]);
        this.year = Integer.parseInt(appointmentDateParts[YEAR]);
    }

    /**
     * Default constructor setting the date to todays date
     */
    public Date(){ //default is today's date
        Calendar today = Calendar.getInstance();
        this.year = today.get(Calendar.YEAR);
        this.month = today.get(Calendar.MONTH) + 1;
        this.day = today.get(Calendar.DAY_OF_MONTH);
    }



    /**
     * Checks if the appointment given is valid and meets scheduling criteria
     *
     * Validates if appointment date is correct based on not a day, not a
     * weekend, or a later date and 6 months from today
     * @param appointmentDate The date of the appointment to check
     * @return True if the appointment date is valid, false if not.
     */
    public boolean validateAppointmentDate(Date appointmentDate) {
        boolean valid = true;
        if(!appointmentDate.isValid() || appointmentDate.isToday() ||
                appointmentDate.isBeforeToday() || appointmentDate.isWeekend()
                || !appointmentDate.isWithinSixMonths()){
            valid = false;
            return valid;
        }
        return true; // Date is valid
    }

    /**
     * Checks to see if the given date of birth is valid.
     * @param dobDate The date of birth to check
     * @return True if the DOB is valid, false if not.
     */
    public boolean validateDobDate(Date dobDate){
        if(!dobDate.isValid() || dobDate.isToday() || dobDate.isAfterToday()){
            return false;
        }
        return true;
    }


    /**
     * FIX THIS METHOD ---> MAKE IT SHORTER
     *
     * Checks to see if the current date is valid in the calendar
     * @return True if the date is works, false if it does not
     */
    public boolean isValid() {
        if (month < 1 || month > 12) {
            return false;
        }
        final int January = 1;
        final int February = 2;
        final int March = 3;
        final int April = 4;
        final int May = 5;
        final int June = 6;
        final int July = 7;
        final int August = 8;
        final int September = 9;
        final int October = 10;
        final int November = 11;
        final int December = 12;
        int maxDaysInMonth;
        switch (month) {
            case January:
            case March:
            case May:
            case July:
            case August:
            case October:
            case December:
                maxDaysInMonth = 31;
                break;
            case April:
            case June:
            case September:
            case November:
                maxDaysInMonth = 30;
                break;
            case February:
                if (leapYear(year)) {
                    maxDaysInMonth = 29;
                } else {
                    maxDaysInMonth = 28;
                }
                break;
            default:
                return false; // Invalid month
        }
        if (day < 1 || day > maxDaysInMonth) {
            return false;
        }
        return true;
    }


    /**
     * Checks to see if the date today is being date
     * @return True if the date is today and works, false if not.
     */
    public boolean isToday(){
        Calendar today = Calendar.getInstance();
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        int todayMonth = today.get(Calendar.MONTH) + 1;
        int todayYear = today.get(Calendar.YEAR);
        return (this.day == todayDay && this.month == todayMonth &&
                this.year == todayYear);
    }

    /**
     * Checks to see if the date is before today's date
     * @return True if the date is before today, false if not.
     */
    public boolean isBeforeToday(){
        Calendar today = Calendar.getInstance();
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.set(this.year, this.month - 1, this.day);
        return appointmentDate.before(today);
    }

    /**
     * Looks at if the date is after today's date
     * @return True if the date is after today, false if not
     */
    public boolean isAfterToday(){
        Calendar today = Calendar.getInstance();
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.set(this.year, this.month - 1, this.day);
        return appointmentDate.after(today);
    }

    /**
     * Checks if the date is by any chance on a weekend
     * @return True if the date is a weekend, false if not.
     */
    public boolean isWeekend(){
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.set(this.year, this.month - 1, this.day);
        int dayOfWeek = appointmentDate.get(Calendar.DAY_OF_WEEK);
        return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
    }

    /**
     * Looks to see if the date is within six months from today's date
     * @return True if the date is within six months, false if not.
     */
    public boolean isWithinSixMonths() {
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.set(this.year, this.month - 1, this.day);
        Calendar today = Calendar.getInstance();
        Calendar sixMonthsFromNow = Calendar.getInstance();
        sixMonthsFromNow.add(Calendar.MONTH, 6);
        return appointmentDate.after(today) && !appointmentDate.
                after(sixMonthsFromNow);
    }

    /**
     * Helper method to check to see if a year is a leap year
     *
     * @param year The year to check.
     * @return True if it's a leap year, false if not.
     */
    private static boolean leapYear(int year){
        final int QUADRENNIAL = 4;
        final int CENTENNIAL = 100;
        final int QUARTERCENTENNIAL = 400;

        // Step 1: If the year is evenly divisible by 4, go to step 2.
        if (year % QUADRENNIAL == 0) {
            // Step 2: If the year is evenly divisible by 100, go to step 3.
            if (year % CENTENNIAL == 0) {
                // Step 3: If the year is evenly divisible by 400, go to step 4.
                if (year % QUARTERCENTENNIAL == 0) {
                    return true; // Step 4: The year is a leap year.
                } else {
                    return false; // Step 5: The year is not a leap year.
                }
            } else {
                return true; // If not divisible by 100, it's a leap year.
            }
        } else {
            return false; // If not divisible by 4, it's not a leap year.
        }
    }


    /**
     * Checks if two Date objects are equal based on their date
     *
     * @param obj The other object to compare.
     * @return True if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj){

        if (obj == null){
            return false;
        }
        if(this == obj){
            return true;
        }
        if (!(obj instanceof Date)) {
            return false;
        }
        Date other = (Date) obj;
        return this.year == other.year && this.month == other.month
                && this.day == other.day;

    }

    /**
     * Returns the string representation of the date
     * Usually would return in M/D/Y format
     *
     * @return A date in string representation
     */
    @Override
    public String toString(){
        return month + "/" + day + "/" + year;
    }

    /**
     * Compares two Date objects by first year, then month, then day
     *
     * @param other The other date to compare with.
     * @return A negative number if earlier date, a positive number if
     *         later, or 0 if they are the same.
     */

    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return this.year - other.year;
        }
        else if (this.month != other.month) {
            return this.month - other.month;
        }
        else if (this.day != other.day) {
            return this.day - other.day;
        }
        else {
            return 0;
        }
    }

    /**
     * Getter method for the day.
     * Uses to get the year
     * @return The year of the day.
     */
    public int getDay(){
        return day;
    }

    /**
     * Getter method for the month.
     * Uses to get the year
     * @return The year of the month.
     */
    public int getMonth(){
        return month;
    }

    /**
     * Getter method for the year.
     * Uses to get the year
     * @return The year of the date.
     */
    public int getYear(){
        return year;
    }

    //testbed main
    public static void main(String [] args){
        Date date1 = new Date(1, 1, 2022);
        Date date2 = new Date(2, 2, 2022);
        Date date3 = new Date(1, 1, 2022);  // Same as date1
        Date date4 = new Date("3/15/2023"); // From string
        Date today = new Date(); // Should be today's date

        // Test toString() method
        System.out.println("Date 1: " + date1.toString());
        System.out.println("Date 2: " + date2.toString());
        System.out.println("Date 3: " + date3.toString());
        System.out.println("Date 4: " + date4.toString());
        System.out.println("Today's date: " + today.toString());

        // Test equals() method
        System.out.println("date1 equals date2? " + date1.equals(date2)); // Expected: false
        System.out.println("date1 equals date3? " + date1.equals(date3)); // Expected: true

        // Test compareTo() method
        System.out.println("date1 compareTo date2: " + date1.compareTo(date2)); // Expected: Negative
        System.out.println("date2 compareTo date1: " + date2.compareTo(date1)); // Expected: Positive
        System.out.println("date1 compareTo date3: " + date1.compareTo(date3)); // Expected: 0 (same dates)

        // Test validation
        System.out.println("Is date1 valid? " + date1.isValid()); // Expected: true
        System.out.println("Is today valid? " + today.isValid()); // Expected: true
        System.out.println("Is date4 valid? " + date4.isValid()); // Depends on input date

        // Test other methods
        System.out.println("Is date1 before today? " + date1.isBeforeToday());
        System.out.println("Is date4 after today? " + date4.isAfterToday());
        System.out.println("Is date1 a weekend? " + date1.isWeekend());
        System.out.println("Is date4 within six months from today? " + date4.isWithinSixMonths());


    }

}
