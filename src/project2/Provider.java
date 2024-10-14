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


    public static void generateProviders(Provider[] providers) {
        String filePath = "src/project2/providers.txt"; // Path to your file
        try {
            Scanner scanner = new Scanner(new java.io.File(filePath));  // Open the file for reading
            int index = 0;

            // Read through the file line by line
            while (scanner.hasNextLine() && index < providers.length) {
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

                    providers[index++] = new Doctor(specialty, npi, profile, location);
                } else if (providerData[0].equals("T")) {
                    // Create a Technician
                    String firstName = providerData[1];
                    String lastName = providerData[2];
                    String dobString = providerData[3];
                    Location location = Location.valueOf(providerData[4].toUpperCase());
                    int ratePerVisit = Integer.parseInt(providerData[5]);

                    Date dob = new Date(dobString);
                    Profile profile = new Profile(firstName, lastName, dob);

                    providers[index++] = new Technician(ratePerVisit, profile, location);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + filePath);
        }
    }







}


//
//
//

////package project2;
////
////public enum Provider {
////
////    PATEL("Patel", Location.BRIDGEWATER, Specialty.FAMILY),
////    LIM("Lim", Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
////    ZIMNES("Zimnes", Location.CLARK, Specialty.FAMILY),
////    HARPER("Harper", Location.CLARK, Specialty.FAMILY),
////    KAUR("Kaur", Location.PRINCETON, Specialty.ALLERGIST),
////    TAYLOR("Taylor", Location.PISCATAWAY, Specialty.PEDIATRICIAN),
////    RAMESH("Ramesh", Location.MORRISTOWN, Specialty.ALLERGIST),
////    CERAVOLO("Ceravolo", Location.EDISON, Specialty.PEDIATRICIAN);
////
////    private final String lastName;
////    private final Location location;
////    private final Specialty specialty;
////
////
////    Provider(String lastName, Location location, Specialty specialty) {
////        this.lastName = lastName;
////        this.location = location;
////        this.specialty = specialty;
////    }
////
////    public static Provider getProviderByLastName(String lastName) {
////        for (Provider provider : Provider.values()) {
////            if (provider.getLastName().equalsIgnoreCase(lastName)) {
////                return provider;
////            }
////        }
////        System.out.println(lastName+ " - provider doesn't exist.");
////        System.out.flush();
////
////        return null;  // Return null if no provider matches
////    }
////
////
////    public String getLastName() {
////        return lastName.toUpperCase();
////    }
////
////    public Location getLocation() {
////        return location;
////    }
////
////    public Specialty getSpecialty() {
////        return specialty;
////    }
////
////    @Override
////    public String toString() {
////        return "[" + lastName.toUpperCase() + ", " + location.getName() + ", " + location.getCounty() + " " + location.getZip() + ", " + specialty + "]";
////    }
////
////    public static void main(String[] args) {
////
////    }
////}
