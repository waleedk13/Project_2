/**
 * The Profile class represents a patient's profile
 * It consists of first name, last name, and date of birth (dob).
 *
 * This class allows comparison between profiles, comparing their first name,
 * last name, and date of birth. It includes methods to compare, check if they
 * are equal and get a string representation of profile
 * @author Waleed Khalid, Rehan Baig
 */

package project2;

public class Profile implements Comparable<Profile>{

    //Instance variables
    private final String firstName;
    private final String lastName;
    private final Date dob;

    /**
     * Default constructor for creating  empty profile object
     * They are all assigned null values to begin with
     */
    public Profile(){
        this.firstName = null;
        this.lastName = null;
        this.dob = null;
    }

    /**
     * Constructor to create a Profile with the given details
     * such as first name, last name, and date of birth
     *
     * @param firstName The first name of the profile.
     * @param lastName The last name of the profile.
     * @param dob The date of birth of the profile.
     */
    public Profile(String firstName, String lastName, Date dob){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    /**
     * Compares this profile with another profile based on profile details.
     * It compares based on last name, first name, and date of birth and then
     * checks to see if they are equal.
     *
     *
     * @param otherProfile The other profile to compare to.
     * @return A negative integer, zero, or a positive integer if this profile
     *         is less than, equal to, or greater than the specified profile.
     */
    @Override
    public int compareTo(Profile otherProfile) {
        //Compare last names
        int lastNameComparison = this.lastName.compareTo(otherProfile.lastName);
        if (lastNameComparison != 0) {
            return Integer.compare(lastNameComparison, 0);
        }

        // Compare first names if last names are the same
        int firstNameComparison = this.firstName.compareTo
                (otherProfile.firstName);
        if (firstNameComparison != 0) {
            return Integer.compare(firstNameComparison, 0); // Ensures result is -1, 0, or 1
        }

        //Compare dob if both names are the same
        int dobComparision = this.dob.compareTo(otherProfile.dob);
        return Integer.compare(dobComparision, 0); // Ensures result is -1, 0, or 1

    }

    /**
     * Checks whether two profile objects are equal
     * This is checked based on their first name, last name, and date of birth.
     *
     * @param obj The object to compare to.
     * @return true if the profiles are equal, false if they are not.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Profile other = (Profile) obj; // Cast obj to Profile
        return firstName.equals(other.firstName) && lastName.
                equals(other.lastName) &&  dob.equals(other.dob);
    }


    /**
     * Provides a string representation of the profile
     * Would provide it in the format of M/D/Y

     * @return The string representation of the profile.
     */
    @Override
    public String toString(){
        String dobString = dob.getMonth() + "/" + dob.getDay() + "/"
                + dob.getYear();
        return (firstName + " " + lastName + " " + dobString);
    }

    /**
     * Gets the first name of the profile.
     *
     * @return The first name of the profile.
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Gets the last name of the profile.
     *
     * @return The last name of the profile.
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Gets the DOB of the profile.
     *
     * @return The DOB of the profile.
     */
    public Date getDob(){
        return dob;
    }

    public static void main(String[] args) {
        // Create Date objects for different profiles
        Date dob1 = new Date(3, 15, 1995);  // March 15, 1995
        Date dob2 = new Date(5, 10, 1992);  // May 10, 1992
        Date dob3 = new Date(7, 25, 1995);  // July 25, 1995
        Date dob4 = new Date(3, 15, 1995);  // March 15, 1995 (same as dob1)
        Date dob5 = new Date(6, 12, 2000);  // June 12, 2000
        Date dob6 = new Date(11, 11, 1998); // November 11, 1998
        Date dob7 = new Date(12, 5, 1988);  // December 5, 1988

        // Create Profile objects
        Profile profile1 = new Profile("John", "Doe", dob1);  // John Doe, March 15, 1995
        Profile profile2 = new Profile("Jane", "Doe", dob2);  // Jane Doe, May 10, 1992
        Profile profile3 = new Profile("John", "Smith", dob3); // John Smith, July 25, 1995
        Profile profile4 = new Profile("John", "Doe", dob4);  // John Doe, March 15, 1995 (same as profile1)
        Profile profile5 = new Profile("Alice", "Brown", dob5); // Alice Brown, June 12, 2000
        Profile profile6 = new Profile("Bob", "Brown", dob6);   // Bob Brown, November 11, 1998
        Profile profile7 = new Profile("John", "Smith", dob7);  // John Smith, December 5, 1988

        // Test cases returning -1
        // Case 1: Jane Doe vs John Doe (first name comparison)
        System.out.println("Test Case 1: Jane Doe vs John Doe");
        System.out.println("Expected result: -1");
        System.out.println("Actual result: " + profile2.compareTo(profile1));
        System.out.println();

        // Case 2: Alice Brown vs Bob Brown (first name comparison)
        System.out.println("Test Case 2: Alice Brown vs Bob Brown");
        System.out.println("Expected result: -1");
        System.out.println("Actual result: " + profile5.compareTo(profile6));
        System.out.println();

        /*  Case 3: John Doe (1995) vs John Doe (1992) (DOB comparison)
        * FIX THIS ONE, THE ACTUAL RESULT IS 0 WHEN IT SHOULD BE ONE*/
        System.out.println("Test Case 3: John Doe (1995) vs John Doe (1992)");
        System.out.println("Expected result: -1");
        System.out.println("Actual result: " + profile1.compareTo(profile4));
        System.out.println();

        // Test cases returning 1
        // Case 4: John Doe vs Jane Doe (first name comparison)
        System.out.println("Test Case 4: John Doe vs Jane Doe");
        System.out.println("Expected result: 1");
        System.out.println("Actual result: " + profile1.compareTo(profile2));
        System.out.println();

        // Case 5: Bob Brown vs Alice Brown (first name comparison)
        System.out.println("Test Case 5: Bob Brown vs Alice Brown");
        System.out.println("Expected result: 1");
        System.out.println("Actual result: " + profile6.compareTo(profile5));
        System.out.println();

        // Case 6: John Smith (1995) vs John Smith (1988) (DOB comparison)
        System.out.println("Test Case 6: John Smith (1995) vs John Smith (1988)");
        System.out.println("Expected result: 1");
        System.out.println("Actual result: " + profile3.compareTo(profile7));
        System.out.println();

        // Test case returning 0
        // Case 7: John Doe (1995) vs John Doe (1995) (all attributes are identical)
        System.out.println("Test Case 7: John Doe (1995) vs John Doe (1995)");
        System.out.println("Expected result: 0");
        System.out.println("Actual result: " + profile1.compareTo(profile4));
        System.out.println();
    }

}

