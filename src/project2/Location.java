package project2;

/**
 * The Location enum represents various locations, each associated with a county and ZIP code.
 * It provides methods to retrieve the name of the location, its county, and its ZIP code, as well as a string representation.
 *
 * @author Waleed Khalid, Rehan Baig
 *  * @implemented Waleed Khalid, Rehan Baig
 */
public enum Location {

    BRIDGEWATER("Somerset", "08807"),
    EDISON("Middlesex", "08817"),
    PISCATAWAY("Middlesex", "08854"),
    PRINCETON("Mercer", "08542"),
    MORRISTOWN("Morris", "07960"),
    CLARK("Union", "07066");

    private final String county;  // The county of the location
    private final String zip;     // The ZIP code of the location

    /**
     * Constructs a Location enum value with the specified county and ZIP code.
     *
     * @param county The county associated with the location.
     * @param zip The ZIP code associated with the location.
     */
    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    /**
     * Gets the name of the location.
     *
     * @return The name of the location (the enum constant name).
     */
    public String getName() {
        return name();  // Returns the enum constant name
    }

    /**
     * Gets the county of the location.
     *
     * @return The county associated with the location.
     */
    public String getCounty() {
        return county;
    }

    /**
     * Gets the ZIP code of the location.
     *
     * @return The ZIP code associated with the location.
     */
    public String getZip() {
        return zip;
    }

    /**
     * Returns a string representation of the location, including its name, county, and ZIP code.
     *
     * @return A string representing the location.
     */
    @Override
    public String toString() {
        return String.format("%s, %s %s", getName(), getCounty(), getZip());
    }
}
