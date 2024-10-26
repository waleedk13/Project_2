package project2;

import util.List;

/**
 * The Doctor class represents a medical doctor as a type of provider.
 * It contains the doctor's specialty and National Provider Identifier (NPI),
 * and overrides methods to get the rate per visit based on the doctor's specialty.
 * The class also provides a method to search for a doctor by their NPI.
 *
 * @author Waleed Khalid, Rehan Baig
 *  * @implemented Waleed Khalid, Rehan Baig
 */
public class Doctor extends Provider {
    private Specialty specialty;  // Encapsulate the rate per visit based on specialty
    private String npi;

    /**
     * Constructs a Doctor object with the specified specialty, NPI, profile, and location.
     *
     * @param specialty The doctor's specialty, which determines the rate per visit.
     * @param npi The National Provider Identifier for the doctor.
     * @param profile The doctor's profile (name, contact info, etc.).
     * @param location The doctor's location (city, state, etc.).
     */
    public Doctor(Specialty specialty, String npi, Profile profile, Location location) {
        super(profile, location);  // Call the Provider constructor
        this.specialty = specialty;
        this.npi = npi;
    }

    /**
     * Returns the doctor's rate per visit based on their specialty.
     *
     * @return The rate the doctor charges per visit, as determined by their specialty.
     */
    @Override
    public int rate() {
        return specialty.getCharge();  // Assuming Specialty has a getCharge() method
    }

    /**
     * Gets the National Provider Identifier (NPI) of the doctor.
     *
     * @return The doctor's NPI as a string.
     */
    public String getNpi() {
        return npi;
    }

    /**
     * Gets the doctor's specialty, which determines their rate per visit.
     *
     * @return The doctor's specialty.
     */
    public Specialty getSpecialty() {
        return specialty;
    }

    /**
     * Searches for and returns a doctor from a list of providers using their NPI.
     *
     * @param npi The NPI of the doctor to search for.
     * @param providerList The list of providers to search in.
     * @return The doctor with the matching NPI, or null if no match is found.
     */
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

    /**
     * Returns a string representation of the doctor, including their profile,
     * location, specialty, and NPI.
     *
     * @return A string containing the doctor's profile, location, specialty,
     * and NPI.
     */
    @Override
    public String toString() {
        return String.format("[%s, %s][%s, #%s]", this.getProfile().toString(),
                this.getLocation(), this.specialty, this.npi);
    }
}
