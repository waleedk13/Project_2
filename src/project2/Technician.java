package project2;

public class Technician extends Provider {
    private int ratePerVisit;  // The technician's rate per visit

    // Constructor for Technician
    public Technician(int ratePerVisit, Location location) {
        this.ratePerVisit = ratePerVisit;
        this.setLocation(location);  // Use the setter from Provider to set location
    }

    // Implement the abstract rate() method from Provider
    @Override
    public int rate() {
        return ratePerVisit;
    }
}
