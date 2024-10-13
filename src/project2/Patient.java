/*@author Waleed Khalid
 * @author Rehan Baig
 *
 * !! Explain what this class does !!
 * */


package project2;

public class Patient implements Comparable<Patient> {
    private Profile profile;
    private Visit visits; // Linked list of visits (completed appointments)

    public Patient(Profile profile, Visit visits) {
        this.profile = profile;
        this.visits = visits;
    }

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
    public int compareTo(Patient other) {
        return this.profile.getLastName().compareTo(other.profile.getLastName()); // Compare patients by last name
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


    // Getter for Profile
    public Profile getProfile() {
        return profile;
    }

    // Getter for Visits
    public Visit getVisits() {
        return visits;
    }
}
