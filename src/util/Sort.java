package util;

import project2.Appointment;
import project2.Provider;

/**
 * The Sort class provides static methods for sorting lists of appointments
 * and providers based on various criteria like patient profile, location, and
 * appointment details.
 * <p>
 * This class is used to sort appointments and providers in the ClinicManager
 * system.
 * </p>
 *
 * @author Waleed Khalid, Rehan Baig
 * @implemented by Waleed Khalid
 */
public class Sort {

    /**
     * Sorts a list of appointments based on the specified key.
     * 'A' for sorting by appointment, 'L' for sorting by location, and 'P' for
     * sorting by patients.
     *
     * @param list The list of appointments to sort.
     * @param key  The key specifying how to sort the list.
     * @throws IllegalStateException If the list is empty or the key is invalid.
     */
    public static void appointment(List<Appointment> list, char key) {
        if (list.size() == 0) {
            throw new IllegalStateException("Scheduler calendar is empty");
        }

        switch (key) {
            case 'A':
                sortByAppointment(list);
                break;
            case 'L':
                sortByLocation(list);
                break;
            case 'P':
                sortByPatients(list);
                break;
            default:
                throw new IllegalStateException("Scheduler class is empty");
        }
    }

    /**
     * Sorts a list of appointments by patient profile, date, and timeslot.
     * This method uses a bubble sort algorithm to sort the list.
     *
     * @param list The list of appointments to sort.
     * @return true if the list is successfully sorted, false if the list is empty.
     */
    public static boolean sortByPatients(List<Appointment> list) {
        if (list.isEmpty()) {
            return false;
        }
        int n = list.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) == null) {
                    exchange(j, j + 1, list);
                } else if (list.get(j + 1) != null) {
                    int profileCompare = list.get(j).getPatientPerson().getProfile()
                            .compareTo(list.get(j + 1).getPatientPerson().getProfile());
                    int dateCompare = list.get(j).getDate().compareTo(list.get(j + 1).getDate());
                    int timeslotCompare = list.get(j).getTimeslot().compareTo(list.get(j + 1).getTimeslot());
                    if (profileCompare > 0 || (profileCompare == 0 && (dateCompare > 0 || (dateCompare == 0 && timeslotCompare > 0)))) {
                        exchange(j, j + 1, list);
                        swapped = true;
                    }
                }
            }
            if (!swapped) {
                break;
            }
        }
        return true;
    }

    /**
     * Sorts a list of appointments by date and timeslot.
     * This method uses the compareTo method of the Appointment class to compare
     * appointments.
     *
     * @param list The list of appointments to sort.
     */
    public static void sortByAppointment(List<Appointment> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j) != null && list.get(j + 1) != null && list.get(j).compareTo(list.get(j + 1)) > 0) {
                    exchange(j, j + 1, list); // Swap if out of order
                }
            }
        }
    }

    /**
     * Sorts a list of appointments by location (county), date, timeslot, and provider's first name.
     * This method is useful for displaying appointments sorted by geographical location.
     *
     * @param list The list of appointments to sort.
     */
    public static void sortByLocation(List<Appointment> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j) == null) {
                    exchange(j, j + 1, list);
                } else if (list.get(j + 1) != null && list.get(j).getProviderPerson() instanceof Provider && list.get(j + 1).getProviderPerson() instanceof Provider) {
                    Provider provider1 = (Provider) list.get(j).getProviderPerson();
                    Provider provider2 = (Provider) list.get(j + 1).getProviderPerson();
                    int locationCompare = provider1.getLocation().getCounty().compareTo(provider2.getLocation().getCounty());
                    int dateCompare = list.get(j).getDate().compareTo(list.get(j + 1).getDate());
                    int timeslotCompare = list.get(j).getTimeslot().compareTo(list.get(j + 1).getTimeslot());
                    int providerFirstNameCompare = provider1.getProfile().getFirstName().compareTo(provider2.getProfile().getFirstName());

                    if (locationCompare > 0 || (locationCompare == 0 && (dateCompare > 0 || (dateCompare == 0 && (timeslotCompare > 0 || (timeslotCompare == 0 && providerFirstNameCompare > 0)))))) {
                        exchange(j, j + 1, list);
                    }
                }
            }
        }
    }

    /**
     * Sorts a list of providers by their profile (name).
     * This method uses bubble sort to compare providers by their profiles.
     *
     * @param lists The list of providers to sort.
     */
    public static void sortProvider(List<Provider> lists) {
        for (int i = 0; i < lists.size() - 1; i++) {
            for (int j = 0; j < lists.size() - i - 1; j++) {
                if (lists.get(j).getProfile().compareTo(lists.get(j + 1).getProfile()) > 0) {
                    Provider tempSwitch = lists.get(j);
                    lists.set(j, lists.get(j + 1));
                    lists.set(j + 1, tempSwitch);
                }
            }
        }
    }

    /**
     * Exchanges two appointments in the list at the specified indices.
     *
     * @param i    The index of the first appointment.
     * @param j    The index of the second appointment.
     * @param list The list of appointments.
     */
    private static void exchange(int i, int j, List<Appointment> list) {
        Appointment temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * Prints the list of appointments ordered by patient profile.
     * Calls sortByPatients to sort the list before printing.
     *
     * @param list The list of appointments to print.
     */
    public void printByPatient(List<Appointment> list) {
        if (list.size() == 0) {
            System.out.println("The schedule calendar is empty.");
            return;
        }
        sortByPatients(list);
        boolean NULL = true;
        int i = 0;
        while (i < list.size()) {
            if (list.get(i) != null) {
                NULL = false;
            }
            i++;
        }
        if (NULL) {
            System.out.println("The scheduler class is empty");
            return;
        }
        int j = 0;
        while (j < list.size()) {
            if (list.get(j) != null) {
                System.out.println(list.get(j));
            }
            j++;
        }
    }
}
