package project2;


public class Doctor extends Provider {
    private Specialty specialty;  // Encapsulate the rate per visit based on specialty
    private String npi;

    public Doctor(Specialty specialty, String npi, Profile profile, Location location) {
        super(profile, location);  // Call the Provider constructor
        this.specialty = specialty;
        this.npi = npi;
    }

    @Override
    public int rate() {
        return specialty.getCharge();  // Assuming Specialty has a getCharge() method
    }

    public String getNpi() {
        return npi;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public static Doctor getDoctorByNpi(String npi, List<Provider> providerList) {
        for (int i = 0; i < providerList.size(); i++) {
            Provider provider = providerList.get(i);
            if (provider instanceof Doctor doctor) {
                if (doctor.getNpi().equals(npi)) {
                    return doctor;  // Return the matching doctor
                }
            }
        }
        return null;
    }


    public String toString() {
        return String.format("[%s, %s][%s, #%s]", this.getProfile().toString(), this.getLocation(), this.specialty, this.npi);
    }

}
