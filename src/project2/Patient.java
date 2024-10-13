/*@author Waleed Khalid
 * @author Rehan Baig
 *
 * !! Explain what this class does !!
 * */

package project2;

public class Patient extends Person {
    private Visit visits; // Linked list of visits (completed appointments)

    // Constructor for Patient that takes a Profile and a Visit
    public Patient(Profile profile, Visit visits) {
        super(profile); // Call the constructor of the Person class
        this.visits = visits;
    }

    // Method to calculate total charge based on visits
    public int charge() {
        int totalCharge = 0;
        Visit currentVisit = this.visits; // Start from the first visit in the list

        while (currentVisit != null) {
            totalCharge += currentVisit.getAppointment().getProvider().getSpecialty().getCharge();
            currentVisit = currentVisit.getNext();
        }

        return totalCharge;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Same object
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Null or different class
        }
        Patient other = (Patient) obj;
        return profile.equals(other.profile); // Compare profiles for equality
    }

    @Override
    public String toString() {
        return "Patient: " + profile.getFirstName() + " " + profile.getLastName() + ", DOB: " + profile.getDob();
    }

    // Getter for Visits
    public Visit getVisits() {
        return visits;
    }
}
