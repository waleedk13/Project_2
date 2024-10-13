/*@author Waleed Khalid
 * @author Rehan Baig
 *
 * !! Explain what this class does !!
 * */


package project2;

public class Profile implements Comparable<Profile>{

    private final String firstName;
    private final String lastName;
    private final Date dob;


    public Profile(){
        this.firstName = null;
        this.lastName = null;
        this.dob = null;
    }

    public Profile(String firstName, String lastName, Date dob){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    @Override
    public int compareTo(Profile o) {
        //Compare last names
        int lastNameComparison = this.lastName.compareToIgnoreCase(o.lastName);
        if (lastNameComparison != 0) {
            return  Integer.signum(lastNameComparison);
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
        return firstName.equalsIgnoreCase(other.firstName) && lastName.equalsIgnoreCase(other.lastName) &&  dob.equals(other.dob);
    }

    @Override
    public String toString(){
        String dobString = dob.getMonth() + "/" + dob.getDay() + "/" + dob.getYear();
        return (firstName + " " + lastName + " " + dobString);
    }


    //getter methods to reutn all the information from the instance variables
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public Date getDob(){
        return dob;
    }

    public static void main(String[] args) {
        // Create Date objects
        Date dob1 = new Date(3, 15, 1995);  // March 15, 1995
        Date dob2 = new Date(5, 10, 1992);  // May 10, 1992
        Date dob3 = new Date(3, 15, 1995);  // March 15, 1995 (same as dob1)
        Date dob4 = new Date(6, 20, 2000);  // June 20, 2000

        // Create Profile objects
        Profile profile1 = new Profile("John", "Doe", dob1);
        Profile profile2 = new Profile("Jane", "Doe", dob2);
        Profile profile3 = new Profile("John", "Smith", dob3);
        Profile profile4 = new Profile("John", "Doe", dob3);  // Same as profile1, different object

        // Test toString() method
        System.out.println("Profile 1: " + profile1.toString());
         // Expected: "Profile: John Doe, DOB: ..."
        System.out.println("Profile 2: " + profile2.toString());
        // Expected: "Profile: Jane Doe, DOB: ..."
        System.out.println("Profile 3: " + profile3.toString());
        // Expected: "Profile: John Smith, DOB: ..."
        System.out.println("Profile 4: " + profile4.toString());
         // Expected: "Profile: John Doe, DOB: ..."

        // Test equals() method
        System.out.println("profile1.equals(profile4): " + profile1.equals(profile4));
        // Expected: true
        System.out.println("profile1.equals(profile2): " + profile1.equals(profile2));
        // Expected: false
        System.out.println("profile1.equals(profile3): " + profile1.equals(profile3));
        // Expected: false

        // Test compareTo() method
        System.out.println("profile1.compareTo(profile4): " + profile1.compareTo(profile4));
        // Expected: 0 (they are the same)
        System.out.println("profile1.compareTo(profile2): " + profile1.compareTo(profile2));
        // Expected: positive (John > Jane)
        System.out.println("profile2.compareTo(profile3): " + profile2.compareTo(profile3));
        // Expected: negative (Doe < Smith)
        System.out.println("profile1.compareTo(profile3): " + profile1.compareTo(profile3));
        // Expected: negative (Doe < Smith)
        System.out.println("profile3.compareTo(profile4): " + profile3.compareTo(profile4));
        // Expected: positive (Smith > Doe)
    }



}

