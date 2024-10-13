package project2;

public class Doctor extends Provider{
    private Specialty specialty;//encapsulate the rate per visit based on specialty
    private String npi;

    public Doctor(Specialty specialty, String npi, Location location) {
        this.specialty = specialty;
        this.npi = npi;
        setLocation(location);  // You can set the location from Provider
    }


    @Override
    public int rate() {
        // Assuming Specialty has a method getCharge() that returns the rate for that specialty
        return specialty.getCharge();
    }

}
