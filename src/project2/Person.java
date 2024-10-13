package project2;

public class Person implements Comparable<Person>{
    protected Profile profile;

    public Person(Profile profile) {
        this.profile = profile;
    }

    public Person() {
        this.profile = null;
    }

    public int compareTo(Person otherPerson) { //USES PROFILE COMPARE TO METHOD, might need to fix?
        return this.profile.compareTo(otherPerson.profile);
    }
}