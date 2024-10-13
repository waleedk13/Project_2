/**
 *
 *
 * @author Waleed Khalid, Rehan Baig
 */

package project2;
public enum Radiology {

    CATSCAN,
    ULTRASOUND,
    XRAY;

    @Override
    public String toString() {
        if (this == CATSCAN) {
            return "CAT Scan";
        } else if (this == ULTRASOUND) {
            return "Ultrasound";
        } else if (this == XRAY) {
            return "X-ray";
        } else {
            return super.toString();  // Default to the name of the
            // enum constant
        }
    }

}
