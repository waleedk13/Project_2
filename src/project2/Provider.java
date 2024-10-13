package project2;

public abstract class Provider extends Person{
    private Location location;
    public abstract int rate();

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}




///*@author Waleed Khalid
// * @author Rehan Baig
// *
// * !! Explain what this class does !!
// * */
//
//
//package project2;
//
//public enum Provider {
//
//    PATEL("Patel", Location.BRIDGEWATER, Specialty.FAMILY),
//    LIM("Lim", Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
//    ZIMNES("Zimnes", Location.CLARK, Specialty.FAMILY),
//    HARPER("Harper", Location.CLARK, Specialty.FAMILY),
//    KAUR("Kaur", Location.PRINCETON, Specialty.ALLERGIST),
//    TAYLOR("Taylor", Location.PISCATAWAY, Specialty.PEDIATRICIAN),
//    RAMESH("Ramesh", Location.MORRISTOWN, Specialty.ALLERGIST),
//    CERAVOLO("Ceravolo", Location.EDISON, Specialty.PEDIATRICIAN);
//
//    private final String lastName;
//    private final Location location;
//    private final Specialty specialty;
//
//
//    Provider(String lastName, Location location, Specialty specialty) {
//        this.lastName = lastName;
//        this.location = location;
//        this.specialty = specialty;
//    }
//
//    public static Provider getProviderByLastName(String lastName) {
//        for (Provider provider : Provider.values()) {
//            if (provider.getLastName().equalsIgnoreCase(lastName)) {
//                return provider;
//            }
//        }
//        System.out.println(lastName+ " - provider doesn't exist.");
//        System.out.flush();
//
//        return null;  // Return null if no provider matches
//    }
//
//
//    public String getLastName() {
//        return lastName.toUpperCase();
//    }
//
//    public Location getLocation() {
//        return location;
//    }
//
//    public Specialty getSpecialty() {
//        return specialty;
//    }
//
//    @Override
//    public String toString() {
//        return "[" + lastName.toUpperCase() + ", " + location.getName() + ", " + location.getCounty() + " " + location.getZip() + ", " + specialty + "]";
//    }
//
//    public static void main(String[] args) {
//
//    }
//}
