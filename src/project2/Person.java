/**
 *
 *
 * @author Waleed Khalid, Rehan Baig
 */

package project2;
public class Person implements Comparable<Person> {
    protected Profile profile;

    // Constructor
    public Person(Profile profile) {
        this.profile = profile;
    }

    // Getter method for profile
    public Profile getProfile() {
        return profile;
    }

    // compareTo method for comparing Person objects
    @Override
    public int compareTo(Person otherPerson) {
        return this.profile.compareTo(otherPerson.profile);
        // Assuming Profile has compareTo() implemented
    }

    // equals method to check if two Person objects are equal
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return profile.equals(person.profile);
    }

    // toString method to represent the Person object as a string
    @Override
    public String toString() {
        return profile.toString();
        // Assuming Profile has a meaningful toString() method
    }
}
