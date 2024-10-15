package project2;
import java.util.Calendar;

public class Date implements Comparable<Date>{
    private final int month;
    private final int day;
    private final int year;

    //constructor from integers
    public Date(int month, int day, int year){
        this.month = month;
        this.day = day;
        this.year = year;
    }

    //constructor from String
    public Date(String date){
        String[] appointmentDateParts = date.split("/");
        this.month = Integer.parseInt(appointmentDateParts[0]);
        this.day = Integer.parseInt(appointmentDateParts[1]);
        this.year = Integer.parseInt(appointmentDateParts[2]);
    }

    //default constructor
    public Date(){
        Calendar today = Calendar.getInstance();
        this.year = today.get(Calendar.YEAR);
        this.month = today.get(Calendar.MONTH) + 1; // Calendar months are 0-based
        this.day = today.get(Calendar.DAY_OF_MONTH);
    }


    public int getYear(){
        return year;
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    @Override
    public String toString(){
        return month + "/" + day + "/" + year;
    }

    @Override
    public int compareTo(Date other) {

        if (this.year != other.year) {
            return Integer.compare(this.year , other.year); // returns +1 if this.year > other.year, -1 if this.year < other.year
        }

        else if (this.month != other.month) {
            return Integer.compare(this.month , other.month); // returns +1 if this.month > other.month, -1 if this.month < other.month
        }

        else if (this.day != other.day) {
            return Integer.compare(this.day , other.day); // returns +1 if this.day > other.day, -1 if this.day < other.day
        }

        return 0;
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


    public boolean isToday(){
        Calendar today = Calendar.getInstance();
        Date todayDate = new Date(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DAY_OF_MONTH));
        return (this.equals(todayDate));
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


    public String validateDobDate(Date dobDate){
        if (!dobDate.isValid()) {
            return "Patient dob: " + dobDate.toString() + " is not a valid calendar date.";
        }

        if (dobDate.isToday()|| dobDate.isAfterToday()) {
            return "Patient dob: " + dobDate.toString() + " is today or a date after today.";
        }

        return null;
    }

    public String validateAppointmentDate(Date appointmentDate){
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




}

