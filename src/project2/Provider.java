/*@author Waleed Khalid
 * @author Rehan Baig
 *
 * !! Explain what this class does !!
 * */


package project2;

public enum Provider {

    PATEL("Patel", Location.BRIDGEWATER, Specialty.FAMILY),
    LIM("Lim", Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
    ZIMNES("Zimnes", Location.CLARK, Specialty.FAMILY),
    HARPER("Harper", Location.CLARK, Specialty.FAMILY),
    KAUR("Kaur", Location.PRINCETON, Specialty.ALLERGIST),
    TAYLOR("Taylor", Location.PISCATAWAY, Specialty.PEDIATRICIAN),
    RAMESH("Ramesh", Location.MORRISTOWN, Specialty.ALLERGIST),
    CERAVOLO("Ceravolo", Location.EDISON, Specialty.PEDIATRICIAN);

    private final String lastName;
    private final Location location;
    private final Specialty specialty;


    Provider(String lastName, Location location, Specialty specialty) {
        this.lastName = lastName;
        this.location = location;
        this.specialty = specialty;
    }

    public static Provider getProviderByLastName(String lastName) {
        for (Provider provider : Provider.values()) {
            if (provider.getLastName().equalsIgnoreCase(lastName)) {
                return provider;
            }
        }
        System.out.println(lastName+ " - provider doesn't exist.");
        System.out.flush();
        
        return null;  // Return null if no provider matches
    }


    public String getLastName() {
        return lastName.toUpperCase();
    }

    public Location getLocation() {
        return location;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    @Override
    public String toString() {
        return "[" + lastName.toUpperCase() + ", " + location.getName() + ", " + location.getCounty() + " " + location.getZip() + ", " + specialty + "]";
    }

    public static void main(String[] args) {

        System.out.println("Testing Provider enum...");


        // Test Case 1: Retrieve provider by valid last name
        System.out.println("Test Case 1: Retrieving provider by valid last name...");
        Provider patel = Provider.getProviderByLastName("Patel");
        if (patel != null) {
            System.out.println("Provider: " + patel.getLastName() + ", Location: " + patel.getLocation() + ", Specialty: " + patel.getSpecialty());
        }

        // Test Case 2: Case-insensitive last name retrieval
        System.out.println("\nTest Case 2: Case-insensitive last name retrieval...");
        Provider kaur = Provider.getProviderByLastName("kaur");
        if (kaur != null) {
            System.out.println("Provider: " + kaur.getLastName() + ", Location: " + kaur.getLocation() + ", Specialty: " + kaur.getSpecialty());
        }

        // Test Case 3: Invalid provider last name
        System.out.println("\nTest Case 3: Retrieving provider by invalid last name...");
        Provider nonExistent = Provider.getProviderByLastName("Doe");
        System.out.println("Result for non-existent provider: " + nonExistent);

        // Test Case 4: Retrieving provider's location and specialty
        System.out.println("\nTest Case 4: Verifying provider's location and specialty...");
        Provider lim = Provider.getProviderByLastName("Lim");
        if (lim != null) {
            System.out.println("Provider: " + lim.getLastName() + ", Location: " + lim.getLocation() + ", Specialty: " + lim.getSpecialty());
        }

        // Test Case 5: Null input for last name
        System.out.println("\nTest Case 5: Handling null input for last name...");
        Provider nullProvider = Provider.getProviderByLastName(null);
        System.out.println("Result for null input: " + nullProvider);

        // Test Case 6: Blank input for last name
        System.out.println("\nTest Case 6: Handling blank input for last name...");
        Provider blankProvider = Provider.getProviderByLastName("");
        System.out.println("Result for blank input: " + blankProvider);
    }

}
