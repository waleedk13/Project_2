package project2;

public class Person implements Comparable<Person>{
    protected Profile profile;

    public Person(Profile profile) {
        this.profile = profile;
    }

    public Person() {
        this.profile = null;
    }

    public Profile getProfile() {
        return profile;
    }

    public int compareTo(Person otherPerson) {
        return this.profile.compareTo(otherPerson.profile);
    }



}