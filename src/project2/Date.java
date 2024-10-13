/*@author Waleed Khalid
 * @author Rehan Baig
 *
 * !! Explain what this class does !!
 * */

package project2;
import java.util.Calendar;

public class Date implements Comparable<Date>{
    private final int month;
    private final int day;
    private final int year;



    public Date(int month, int day, int year){
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public Date(String date){
        String[] appointmentDateParts = date.split("/");
        this.month = Integer.parseInt(appointmentDateParts[0]);
        this.day = Integer.parseInt(appointmentDateParts[1]);
        this.year = Integer.parseInt(appointmentDateParts[2]);
    }

    public Date(){ //default is today's date
        Calendar today = Calendar.getInstance();
        this.year = today.get(Calendar.YEAR);
        this.month = today.get(Calendar.MONTH) + 1; // Calendar months are 0-based
        this.day = today.get(Calendar.DAY_OF_MONTH);
    }




    public String validateAppointmentDate(Date appointmentDate) {
        if (!appointmentDate.isValid()) {
              return "Appointment date: " + appointmentDate.toString() + " is not a valid calendar date.";
        }

        if (appointmentDate.isToday()|| appointmentDate.isBeforeToday()) {
            return "Appointment date: " + appointmentDate.toString() + " is today or a date before today.";
        }

        if (appointmentDate.isWeekend()) {
            return "Appointment date: " + appointmentDate.toString() + " is Saturday or Sunday.";
        }

        if (!appointmentDate.isWithinSixMonths()) {
            return "Appointment date: " + appointmentDate.toString() + " is not within six months.";
        }

        return null;
    }

    public String validateDobDate(Date dobDate){
        if (!dobDate.isValid()) {
            return "Patient dob: " + dobDate.toString() + " is not a valid calendar date.";
        }

        if (dobDate.isToday()|| dobDate.isAfterToday()) {
            return "Patient dob: " + dobDate.toString() + " is today or a date after today.";
        }

        return null;
    }


     public boolean isValid() {
         if (month < 1 || month > 12) {//conditional tp check if the month is valid
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

    public boolean isToday(){
        Calendar today = Calendar.getInstance();
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        int todayMonth = today.get(Calendar.MONTH) + 1; // Calendar months are 0-based
        int todayYear = today.get(Calendar.YEAR);
        return (this.day == todayDay && this.month == todayMonth && this.year == todayYear);
    }

    public boolean isBeforeToday(){
        Calendar today = Calendar.getInstance();
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.set(this.year, this.month - 1, this.day); // Calendar months are 0-based
        return appointmentDate.before(today);
    }

    public boolean isAfterToday(){
        Calendar today = Calendar.getInstance();
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.set(this.year, this.month - 1, this.day); // Calendar months are 0-based
        return appointmentDate.after(today);
    }

    public boolean isWeekend(){
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.set(this.year, this.month - 1, this.day); // Calendar months are 0-based
        int dayOfWeek = appointmentDate.get(Calendar.DAY_OF_WEEK);
        return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
    }

    public boolean isWithinSixMonths() {
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.set(this.year, this.month - 1, this.day);  // Calendar months are 0-based

        // Get today's date
        Calendar today = Calendar.getInstance();

        // Get the date six months from now
        Calendar sixMonthsFromNow = Calendar.getInstance();
        sixMonthsFromNow.add(Calendar.MONTH, 6);

        // The appointment date must be in the future and within the next 6 months
        return appointmentDate.after(today) && !appointmentDate.after(sixMonthsFromNow);
    }


    //implement a leap year method as well
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



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Date)) {
            return false;
        }
        Date other = (Date) obj;
        return this.year == other.year && this.month == other.month && this.day == other.day;
    }


    @Override
    public String toString(){
        return month + "/" + day + "/" + year;
    }

    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return this.year - other.year; // returns positive if this.year > other.year, negative if this.year < other.year
        }

        else if (this.month != other.month) {
            return this.month - other.month; // returns positive if this.month > other.month, negative if this.month < other.month
        }

        else if (this.day != other.day) {
            return this.day - other.day; // returns positive if this.day > other.day, negative if this.day < other.day
        }
        else {
            return 0;
        }
    }

    //getter methods for Date
    public int getYear(){
        return year;
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }


    public static void main(String[] args) {
        // Test Cases for validateAppointmentDate
        System.out.println("Testing validateAppointmentDate...");

        // Test Case 1: Valid Appointment Date
        Date appointmentDate1 = new Date(3, 15, 2024);
        System.out.println("Test Case 1 (Valid Appointment date - 03/15/2024): " + appointmentDate1.validateAppointmentDate(appointmentDate1));

        // Test Case 2: Invalid Date (April 31, 2024)
        Date appointmentDate2 = new Date(4, 31, 2024);
        System.out.println("Test Case 2 (Invalid Appointment date - 04/31/2024): " + appointmentDate2.validateAppointmentDate(appointmentDate2));

        // Test Case 3: Today’s Date
        Date appointmentDate3 = new Date();
        System.out.println("Test Case 3 (Today's Date): " + appointmentDate3.validateAppointmentDate(appointmentDate3));

        // Test Case 4: Weekend Date
        Date appointmentDate4 = new Date(11, 17, 2024); // Assume this is a Sunday
        System.out.println("Test Case 4 (Weekend Appointment date - 11/17/2024): " + appointmentDate4.validateAppointmentDate(appointmentDate4));

        // Test Case 5: Appointment Beyond Six Months
        Date appointmentDate5 = new Date(11, 15, 2024); // Adjust depending on today
        System.out.println("Test Case 5 (Appointment Beyond Six Months - 11/15/2024): " + appointmentDate5.validateAppointmentDate(appointmentDate5));

        // Test Case 6: Appointment on a Leap Year (February 29, 2024)
        Date appointmentDate6 = new Date(2, 29, 2024);
        System.out.println("Test Case 6 (Leap Year Date - 02/29/2024): " + appointmentDate6.validateAppointmentDate(appointmentDate6));

        // Test Case 7: Invalid Appointment Date (February 30)
        Date appointmentDate7 = new Date(2, 30, 2024);
        System.out.println("Test Case 7 (Invalid Appointment date - 02/30/2024): " + appointmentDate7.validateAppointmentDate(appointmentDate7));

        // Test Case 8: Appointment Before Today
        Date appointmentDate8 = new Date(3, 10, 2022);
        System.out.println("Test Case 8 (Appointment Before Today - 03/10/2022): " + appointmentDate8.validateAppointmentDate(appointmentDate8));

        // Test Cases for validateDobDate
        System.out.println("\nTesting validateDobDate...");

        // Test Case 1: Valid Date of Birth
        Date dobDate1 = new Date(12, 13, 1989);
        System.out.println("Test Case 1 (Valid DOB - 12/13/1989): " + dobDate1.validateDobDate(dobDate1));

        // Test Case 2: Today’s Date as Date of Birth
        Date dobDate2 = new Date();
        System.out.println("Test Case 2 (Today's Date as DOB): " + dobDate2.validateDobDate(dobDate2));

        // Test Case 3: Future Date of Birth
        Date dobDate3 = new Date(12, 13, 2025);
        System.out.println("Test Case 3 (Future DOB - 12/13/2025): " + dobDate3.validateDobDate(dobDate3));

        // Test Case 4: Invalid Date of Birth (February 30, 1990)
        Date dobDate4 = new Date(2, 30, 1990);
        System.out.println("Test Case 4 (Invalid DOB - 02/30/1990): " + dobDate4.validateDobDate(dobDate4));

        // Test Case 5: Leap Year Date of Birth
        Date dobDate5 = new Date(2, 29, 2000);
        System.out.println("Test Case 5 (Leap Year DOB - 02/29/2000): " + dobDate5.validateDobDate(dobDate5));

        // Test Case 6: Date of Birth Before Year 1900
        Date dobDate6 = new Date(7, 15, 1899);
        System.out.println("Test Case 6 (DOB Before Year 1900 - 07/15/1899): " + dobDate6.validateDobDate(dobDate6));
    }


}
