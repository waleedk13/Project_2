package project2;

import util.Date;

/**
 * The Profile class represents a profile with a first name, last name, and date of birth (dob).
 * It provides methods for comparing profiles, checking equality, and returning profile details.
 *
 * @author Waleed Khalid, Rehan Baig
 *  * @implemented Waleed Khalid, Rehan Baig
 */
public class Profile implements Comparable<Profile> {

    private final String firstName;  // The first name of the profile
    private final String lastName;   // The last name of the profile
    private final Date dob;          // The date of birth of the profile

    /**
     * Default constructor that creates a Profile with null values for first name, last name, and date of birth.
     */
    public Profile() {
        this.firstName = null;
        this.lastName = null;
        this.dob = null;
    }

    /**
     * Constructs a Profile with the specified first name, last name, and date of birth.
     *
     * @param firstName The first name of the profile.
     * @param lastName The last name of the profile.
     * @param dob The date of birth of the profile.
     */
    public Profile(String firstName, String lastName, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    /**
     * Compares this profile with another profile based on last name, first name, and date of birth.
     *
     * @param o The profile to be compared with.
     * @return A negative integer, zero, or a positive integer as this profile is less than, equal to, or greater than the specified profile.
     */
    @Override
    public int compareTo(Profile o) {
        // Compare last names
        assert this.lastName != null;
        int lastNameComparison = this.lastName.compareToIgnoreCase(o.lastName);
        if (lastNameComparison != 0) {
            return Integer.signum(lastNameComparison);
        }

        // Compare first names if last names are the same
        assert this.firstName != null;
        int firstNameComparison = this.firstName.compareToIgnoreCase(o.firstName);
        if (firstNameComparison != 0) {
            return Integer.signum(firstNameComparison);
        }

        // Compare dob if both names are the same
        assert this.dob != null;
        int dobComparison = this.dob.compareTo(o.dob);
        return Integer.signum(dobComparison);
    }

    /**
     * Checks if this profile is equal to another object.
     *
     * @param obj The object to compare with.
     * @return true if the profiles are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Profile otherProfile = (Profile) obj;

        // Safeguard against null values in firstName and lastName
        boolean firstNameMatch = (this.firstName != null && otherProfile.firstName != null)
                ? this.firstName.trim().equalsIgnoreCase(otherProfile.firstName.trim())
                : this.firstName == otherProfile.firstName;

        boolean lastNameMatch = (this.lastName != null && otherProfile.lastName != null)
                ? this.lastName.trim().equalsIgnoreCase(otherProfile.lastName.trim())
                : this.lastName == otherProfile.lastName;

        boolean dobMatch = this.dob.equals(otherProfile.dob);

        return firstNameMatch && lastNameMatch && dobMatch;
    }

    /**
     * Returns a string representation of the profile, including first name, last name, and date of birth.
     *
     * @return A string containing the first name, last name, and date of birth in the format "MM/DD/YYYY".
     */
    @Override
    public String toString() {
        String dobString = dob.getMonth() + "/" + dob.getDay() + "/" + dob.getYear();
        return (firstName + " " + lastName + " " + dobString);
    }

    /**
     * Gets the first name of the profile.
     *
     * @return The first name of the profile.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the profile.
     *
     * @return The last name of the profile.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the date of birth of the profile.
     *
     * @return The date of birth (dob) of the profile.
     */
    public Date getDob() {
        return dob;
    }
}
