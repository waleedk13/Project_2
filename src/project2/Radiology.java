package project2;

/**
 * The Radiology enum represents the different types of radiology services available in the clinic.
 * It includes three types: XRAY, ULTRASOUND, and CATSCAN. The toString method is overridden
 * to return the corresponding string representation of the radiology type.
 *
 * @author Rehan Baig, Waleed Khalid
 *  * @implemented Waleed Khalid, Rehan Baig
 */
public enum Radiology {
    XRAY,
    ULTRASOUND,
    CATSCAN;

    /**
     * Overrides the default toString method to return a more readable string representation
     * of the radiology type.
     *
     * @return A string representation of the radiology type (e.g., "XRAY", "ULTRASOUND", "CATSCAN").
     * @throws IllegalArgumentException if the enum value is unexpected.
     */
    @Override
    public String toString() {
        if (this == XRAY) {
            return "XRAY";
        } else if (this == ULTRASOUND) {
            return "ULTRASOUND";
        } else if (this == CATSCAN) {
            return "CATSCAN";
        } else {
            throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }
}
