package project2;

import util.Date;
import util.List;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Provider class represents a healthcare provider, which can be either a doctor or a technician.
 * It extends the Person class and adds the location of the provider. This is an abstract class
 * with an abstract method for determining the rate per visit.
 *
 * @author Rehan Baig, Waleed Khalid
 *  * @implemented Waleed Khalid, Rehan Baig
 */
public abstract class Provider extends Person {
    private Location location;  // The location of the provider (city, state, etc.)

    /**
     * Constructs a Provider with the specified profile and location.
     *
     * @param profile The profile of the provider.
     * @param location The location of the provider.
     */
    public Provider(Profile profile, Location location) {
        super(profile);
        this.location = location;
    }

    /**
     * Gets the location of the provider.
     *
     * @return The provider's location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the provider.
     *
     * @param location The new location of the provider.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Abstract method for getting the rate per visit of the provider.
     *
     * @return The rate per visit for the provider.
     */
    public abstract int rate();  // Abstract method for rate

    /**
     * Generates a list of providers (doctors and technicians) by reading from a file.
     * The file should contain information about the providers in the specified format.
     *
     * @param providers The list to which the generated providers will be added.
     * @throws RuntimeException if the file is not found.
     */
    public static void generateProviders(List<Provider> providers) {
        String filePath = "src/project2/providers.txt"; // Path to the file containing provider information
        try {
            Scanner scanner = new Scanner(new java.io.File(filePath));  // Open the file for reading

            // Read through the file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] providerData = line.split("\\s+");

                // Check if the provider is a doctor or technician
                if (providerData[0].equals("D")) {
                    // Create a Doctor
                    String firstName = providerData[1];
                    String lastName = providerData[2];
                    String dobString = providerData[3];
                    Location location = Location.valueOf(providerData[4].toUpperCase());
                    Specialty specialty = Specialty.valueOf(providerData[5].toUpperCase());
                    String npi = providerData[6];

                    Date dob = new Date(dobString);
                    Profile profile = new Profile(firstName, lastName, dob);

                    providers.add(new Doctor(specialty, npi, profile, location));
                } else if (providerData[0].equals("T")) {
                    // Create a Technician
                    String firstName = providerData[1];
                    String lastName = providerData[2];
                    String dobString = providerData[3];
                    Location location = Location.valueOf(providerData[4].toUpperCase());
                    int ratePerVisit = Integer.parseInt(providerData[5]);

                    Date dob = new Date(dobString);
                    Profile profile = new Profile(firstName, lastName, dob);

                    providers.add(new Technician(ratePerVisit, profile, location));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + filePath);
        }
    }
}
