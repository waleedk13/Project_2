package project2;

/**
 * The Person class represents a person with a profile. It provides methods to get the profile
 * and compare two people based on their profiles.
 *
 * @author Waleed Khalid, Rehan Baig
 *  * @implemented Waleed Khalid, Rehan Baig
 */
public class Person implements Comparable<Person> {
    protected Profile profile;  // The profile of the person

    /**
     * Constructs a Person with the specified profile.
     *
     * @param profile The profile of the person.
     */
    public Person(Profile profile) {
        this.profile = profile;
    }

    /**
     * Default constructor that creates a Person with no profile (null).
     */
    public Person() {
        this.profile = null;
    }

    /**
     * Gets the profile of this person.
     *
     * @return The profile of the person.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Compares this person with another person based on their profiles.
     *
     * @param otherPerson The person to compare with.
     * @return A negative integer, zero, or a positive integer as this person's profile is less than, equal to, or greater than the specified person's profile.
     */
    @Override
    public int compareTo(Person otherPerson) {
        return this.profile.compareTo(otherPerson.profile);
    }
}
