/**
 *
 *
 * @author Waleed Khalid, Rehan Baig
 */

package project2;
public class Patient implements Comparable<Patient> {
    private Profile profile;
    private Visit visits; //a linked list of visits (completed appt.)


    public Patient(Profile profile, Visit visits){
        this.visits = visits;
        this.profile = profile;
    }



    public int charge() {

        return 0;

    } //traverse the linked list to compute the charge

    @Override
    public int compareTo(Patient o) {
        return 0;
    }
}

