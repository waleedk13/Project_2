package project2;

/**
 * The Specialty enum represents the different medical specialties that a doctor can have,
 * each associated with a specific charge per visit.
 *
 * It includes three specialties: FAMILY, PEDIATRICIAN, and ALLERGIST, each with their own rate.
 *
 * @author Rehan Baig, Waleed Khalid
 *  * @implemented Waleed Khalid, Rehan Baig
 */
public enum Specialty {
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST(350);

    private final int charge;

    /**
     * Constructs a Specialty with the specified charge per visit.
     *
     * @param charge The rate for this specialty.
     */
    Specialty(int charge) {
        this.charge = charge;
    }

    /**
     * Returns the charge associated with this specialty.
     *
     * @return The charge for the specialty.
     */
    public int getCharge() {
        return charge;
    }
}
