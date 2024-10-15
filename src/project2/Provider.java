package project2;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Provider extends Person {
    private Location location;

    public Provider(Profile profile, Location location) {
        super(profile);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public abstract int rate();  // Abstract method for rate


    public static void generateProviders(List<Provider> providers) {
        String filePath = "src/project2/providers.txt"; // Path to your file
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
