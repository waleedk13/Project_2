/**
 *
 *
 * @author Waleed Khalid, Rehan Baig
 */

package project2;

public class Doctor extends Provider{

    private Specialty specialty;
    private String npi;


    public Doctor(Profile profile, Specialty specialty, String npi) {
        super(profile);
        this.specialty = specialty;
        this.npi = npi;
    }

    @Override
    public int rate() {
        return specialty.getRatePerVisit();
    }


    @Override
    public String toString() {
        return "Doctor: " + profile.toString() + ", Specialty: " + specialty.toString() + ", NPI: " + npi;
    }

}
