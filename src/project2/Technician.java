package project2;


public class Technician extends Provider {
    private int ratePerVisit;  // The technician's rate per visit

    // Constructor for Technician
    public Technician(int ratePerVisit, Profile profile, Location location) {
        super(profile, location);  // Call the Provider constructor
        this.ratePerVisit = ratePerVisit;
    }

    // Implement the abstract rate() method from Provider
    public int rate() {
        return ratePerVisit;
    }

    public String toString() {
        return String.format("[%s, %s][rate: $%s.00]", this.getProfile().toString(), this.getLocation(), this.rate() );
    }

    public String toStringForRotationList(){
        return String.format("%s %s (%s)", this.getProfile().getFirstName(), this.getProfile().getLastName(), this.getLocation().getName());

    }

    public String toStringWithoutRate(){
        return String.format("%s, (%s)", this.getProfile().toString(), this.getLocation());
    }

    public Profile getProfile(){
        return profile;
    }






}
