package project2;

/**
 * The Technician class represents a medical technician who provides imaging services.
 * This class extends the Provider class and includes a rate per visit.
 * It implements the abstract method to return the technician's rate and provides
 * methods to generate string representations for various outputs.
 *
 * @author Rehan Baig, Waleed Khalid
 *  * @implemented Waleed Khalid, Rehan Baig
 */
public class Technician extends Provider {
    private int ratePerVisit;  // The technician's rate per visit

    /**
     * Constructs a Technician object with the specified rate, profile, and location.
     *
     * @param ratePerVisit The rate the technician charges per visit.
     * @param profile The technician's profile (name, contact info, etc.).
     * @param location The technician's location (city, state, etc.).
     */
    public Technician(int ratePerVisit, Profile profile, Location location) {
        super(profile, location);  // Call the Provider constructor
        this.ratePerVisit = ratePerVisit;
    }

    /**
     * Returns the technician's rate per visit.
     *
     * @return The rate the technician charges per visit.
     */
    public int rate() {
        return ratePerVisit;
    }

    /**
     * Returns a string representation of the technician including their profile, location, and rate.
     *
     * @return A formatted string containing the technician's profile, location, and rate per visit.
     */
    @Override
    public String toString() {
        return String.format("[%s, %s][rate: $%s.00]", this.getProfile().toString(), this.getLocation(), this.rate() );
    }

    /**
     * Returns a string representation of the technician for rotation list display.
     *
     * @return A formatted string containing the technician's first and last name along with their location.
     */
    public String toStringForRotationList(){
        return String.format("%s %s (%s)", this.getProfile().getFirstName(), this.getProfile().getLastName(), this.getLocation().getName());
    }

    /**
     * Returns a string representation of the technician without the rate information.
     *
     * @return A formatted string containing the technician's profile and location without rate.
     */
    public String toStringWithoutRate(){
        return String.format("%s, (%s)", this.getProfile().toString(), this.getLocation());
    }

    /**
     * Gets the technician's profile.
     *
     * @return The profile of the technician.
     */
    @Override
    public Profile getProfile(){
        return profile;
    }
}
