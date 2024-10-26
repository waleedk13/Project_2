package util;

import java.util.Calendar;

/**
 * The Date class represents a calendar date and provides methods for date validation,
 * comparison, and various checks related to calendar operations.
 * It supports operations like checking if a date is before or after today, if it's valid,
 * and if it's within a specific time range.
 *
 * @author Waleed Khalid, Rehan Baig
 */
public class Date implements Comparable<Date> {
    private final int month;
    private final int day;
    private final int year;

    /**
     * Constructs a Date object using the specified month, day, and year.
     *
     * @param month The month (1-12).
     * @param day The day of the month.
     * @param year The year.
     */
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Constructs a Date object using a date string in the format "MM/DD/YYYY".
     *
     * @param date The date string in the format "MM/DD/YYYY".
     */
    public Date(String date) {
        String[] appointmentDateParts = date.split("/");
        this.month = Integer.parseInt(appointmentDateParts[0]);
        this.day = Integer.parseInt(appointmentDateParts[1]);
        this.year = Integer.parseInt(appointmentDateParts[2]);
    }

    /**
     * Default constructor that initializes the Date object to today's date.
     */
    public Date() {
        Calendar today = Calendar.getInstance();
        this.year = today.get(Calendar.YEAR);
        this.month = today.get(Calendar.MONTH) + 1; // Calendar months are 0-based
        this.day = today.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Gets the year of this Date.
     *
     * @return The year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the month of this Date.
     *
     * @return The month (1-12).
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets the day of the month of this Date.
     *
     * @return The day of the month.
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns a string representation of the Date in the format "MM/DD/YYYY".
     *
     * @return A string representing the Date.
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     * Compares this Date object with another Date object for order.
     *
     * @param other The other Date to be compared.
     * @return A negative integer, zero, or a positive integer as this Date is before, equal to, or after the other Date.
     */
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        } else if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        } else {
            return Integer.compare(this.day, other.day);
        }
    }

    /**
     * Compares this Date with another object for equality.
     *
     * @param obj The object to compare with.
     * @return True if the objects represent the same date, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Date other = (Date) obj;
        return this.year == other.year && this.month == other.month && this.day == other.day;
    }

    /**
     * Checks if the Date is today.
     *
     * @return True if the Date is today's date, false otherwise.
     */
    public boolean isToday() {
        Calendar today = Calendar.getInstance();
        Date todayDate = new Date(today.get(Calendar.MONTH) + 1, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.YEAR));
        return this.equals(todayDate);
    }

    /**
     * Checks if the Date is before today's date.
     *
     * @return True if the Date is before today, false otherwise.
     */
    public boolean isBeforeToday() {
        Calendar today = Calendar.getInstance();
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.set(this.year, this.month - 1, this.day);
        return appointmentDate.before(today);
    }

    /**
     * Checks if the Date is after today's date.
     *
     * @return True if the Date is after today, false otherwise.
     */
    public boolean isAfterToday() {
        Calendar today = Calendar.getInstance();
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.set(this.year, this.month - 1, this.day);
        return appointmentDate.after(today);
    }

    /**
     * Checks if the Date falls on a weekend.
     *
     * @return True if the Date is on a Saturday or Sunday, false otherwise.
     */
    public boolean isWeekend() {
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.set(this.year, this.month - 1, this.day);
        int dayOfWeek = appointmentDate.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }

    /**
     * Checks if the Date is within the next six months.
     *
     * @return True if the Date is within the next six months, false otherwise.
     */
    public boolean isWithinSixMonths() {
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.set(this.year, this.month - 1, this.day);

        Calendar today = Calendar.getInstance();
        Calendar sixMonthsFromNow = Calendar.getInstance();
        sixMonthsFromNow.add(Calendar.MONTH, 6);

        return appointmentDate.after(today) && !appointmentDate.after(sixMonthsFromNow);
    }

    /**
     * Validates a date of birth (DOB).
     *
     * @param dobDate The DOB to validate.
     * @return An error message if the DOB is invalid, or null if it is valid.
     */
    public String validateDobDate(Date dobDate) {
        if (!dobDate.isValid()) {
            return "Patient dob: " + dobDate.toString() + " is not a valid calendar date.";
        }

        if (dobDate.isToday() || dobDate.isAfterToday()) {
            return "Patient dob: " + dobDate.toString() + " is today or a date after today.";
        }

        return null;
    }

    /**
     * Validates an appointment date.
     *
     * @param appointmentDate The appointment date to validate.
     * @return An error message if the appointment date is invalid, or null if it is valid.
     */
    public String validateAppointmentDate(Date appointmentDate) {
        if (!appointmentDate.isValid()) {
            return "Appointment date: " + appointmentDate.toString() + " is not a valid calendar date.";
        }

        if (appointmentDate.isToday() || appointmentDate.isBeforeToday()) {
            return "Appointment date: " + appointmentDate.toString() + " is today or a date before today.";
        }

        if (appointmentDate.isWeekend()) {
            return "Appointment date: " + appointmentDate.toString() + " is on a Saturday or Sunday.";
        }

        if (!appointmentDate.isWithinSixMonths()) {
            return "Appointment date: " + appointmentDate.toString() + " is not within six months.";
        }

        return null;
    }

    /**
     * Checks if the date is valid (e.g., correct days for the given month and year).
     *
     * @return True if the date is valid, false otherwise.
     */
    public boolean isValid() {
        if (month < 1 || month > 12) {
            return false;
        }

        int maxDaysInMonth;
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                maxDaysInMonth = 31;
                break;
            case 4: case 6: case 9: case 11:
                maxDaysInMonth = 30;
                break;
            case 2:
                maxDaysInMonth = leapYear(year) ? 29 : 28;
                break;
            default:
                return false;
        }

        return day >= 1 && day <= maxDaysInMonth;
    }

    /**
     * Determines if the given year is a leap year.
     *
     * @param year The year to check.
     * @return True if the year is a leap year, false otherwise.
     */
    private static boolean leapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        }
        return false;
    }
}
