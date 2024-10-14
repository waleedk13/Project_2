package project2;

public class Profile implements Comparable<Profile> {

    private final String firstName;
    private final String lastName;
    private final Date dob;


    public Profile() {
        this.firstName = null;
        this.lastName = null;
        this.dob = null;
    }

    public Profile(String firstName, String lastName, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    @Override
    public int compareTo(Profile o) {
        //Compare last names
        int lastNameComparison = this.lastName.compareToIgnoreCase(o.lastName);
        if (lastNameComparison != 0) {
            return Integer.signum(lastNameComparison);
        }

        // Compare first names if last names are the same
        int firstNameComparison = this.firstName.compareToIgnoreCase(o.firstName);
        if (firstNameComparison != 0) {
            return Integer.signum(firstNameComparison);
        }

        //Compare dob if both names are the same
        int dobComparison = this.dob.compareTo(o.dob);
        return Integer.signum(dobComparison);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Profile other = (Profile) obj; // Cast obj to Profile
        return firstName.equalsIgnoreCase(other.firstName) && lastName.equalsIgnoreCase(other.lastName) && dob.equals(other.dob);
    }

    @Override
    public String toString() {
        String dobString = dob.getMonth() + "/" + dob.getDay() + "/" + dob.getYear();
        return (firstName + " " + lastName + " " + dobString);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }
}

