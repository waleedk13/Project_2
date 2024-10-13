/*@author Waleed Khalid
 * @author Rehan Baig
 *
 * !! Explain what this class does !!
 * */

package project2;


public class List {

    private Appointment[] appointments;
    private int size; //number of appointments in the array
    final int NOT_FOUND = -1;


    private int find(Appointment appointment) {
        int i = 0;
        while (i < size) {
            if (appointments[i].equals(appointment)) {
                return i;
            }
            i++;
        }
        return NOT_FOUND;
    }

    public Appointment getAppointment(int index){
            return appointments[index];
    }

    //helper method to increase the capacity by 4
    private void grow() {
        Appointment newAppointments[] = new Appointment[appointments.length + 4];
        int i = 0;
        while (i < appointments.length) {
            newAppointments[i] = appointments[i];
            i++;
        }
        this.appointments = newAppointments;// possibily change this
        //FIX SIZE ISSUE -------------------------------

    }


    /*searches for appointments in the array and return the index # once found
     * if not found, it will return 'NOT_FOUND' */
    public boolean contains(Appointment appointment) {

        int index = find(appointment);

        if (index == NOT_FOUND) {
            return false;
        }
        return true;
        }



    public void add(Appointment appointment) {
        // Check if the appointment already exists using the contains method
        if (contains(appointment)) {
            System.out.println(appointment.getPatientProfile().toString() + " has an existing appointment at the same time slot");
            System.out.flush();
            return; // Exit the method without adding the appointment
        }

        // If the array is full, grow the array
        if (size == appointments.length) {
            grow();
        }

        // Add the new appointment and increment the size
        appointments[size] = appointment;
        size++;
    }


    public String remove(Appointment appointment) {
        int index = find(appointment);
        if (index == NOT_FOUND) {
            System.out.println(appointment.toStringWithoutProvider() + " does not exist");
            System.out.flush();
            return null; // Exit the method as no appointment was found
        }

        appointments[index] = appointments[size - 1];
        appointments[size - 1] = null;
        size--;
        return appointment.toStringWithoutProvider();
    }


    public boolean organizeByPatient() {
        if(size == 0){
            System.out.println("The schedule calendar is empty.");
            System.out.flush();
            return false;
        }

        int n = this.size;
        Appointment temp;
        boolean swapped;


        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                // Step 1: Compare profiles (last name, first name, DOB)
                int profileCompare = appointments[j].getPatientProfile().compareTo(appointments[j + 1].getPatientProfile());

                // Step 2: If profiles are the same, compare appointment date
                int dateCompare = appointments[j].getDate().compareTo(appointments[j + 1].getDate());

                // Step 3: If profile and date are the same, compare timeslot (appointment time)
                int timeslotCompare = appointments[j].getTimeslot().compareTo(appointments[j + 1].getTimeslot());

                // Step 4: Apply sorting logic
                if (profileCompare > 0 || (profileCompare == 0 && (dateCompare > 0 || (dateCompare == 0 && timeslotCompare > 0)))) {
                    // Swap appointments if they are out of order
                    temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return true;
    }

    public void printByPatient(){
        boolean trueCheck = organizeByPatient();
        if (!trueCheck){
            return;
        }
        System.out.println();
        System.out.println("** Appointments ordered by patient/date/time **");
        printAppointments();
        System.out.println("** end of list **");
        System.out.flush();
    }


    //ordered by patient profile, date/timeslot
    public void printByLocation() {

        if(size == 0){
            System.out.println("The schedule calendar is empty.");
            System.out.flush();
            return;
        }

        int n = this.size;
        Appointment temp;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                int locationCompare = appointments[j].getProvider().getLocation().getCounty()
                        .compareTo(appointments[j + 1].getProvider().getLocation().getCounty());

                int timeslotCompare = appointments[j].getTimeslot().compareTo(appointments[j + 1].getTimeslot());
                int providerCompare = appointments[j].getProvider().compareTo(appointments[j + 1].getProvider());

                if (locationCompare > 0 || (locationCompare == 0 && (timeslotCompare > 0 || (timeslotCompare == 0 && providerCompare > 0)))) {
                    temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

        System.out.println();
        System.out.println("** Appointments ordered by county/date/time **");
        // Print sorted appointments
        printAppointments();
        System.out.println("** end of list **");
        System.out.flush();
    }

    //ordered by county, date/timeslot
    public void printByAppointment() {
        if(size == 0){
            System.out.println("The schedule calendar is empty.");
            System.out.flush();
            return;
        }

        int n = this.size;
        Appointment temp;
        boolean swapped;

        // Bubble Sort based on date, timeslot, and provider
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {

                int dateComparison = appointments[j].getDate().compareTo(appointments[j + 1].getDate());
                int timeslotComparison = appointments[j].getTimeslot().compareTo(appointments[j + 1].getTimeslot());
                int providerComparison = appointments[j].getProvider().compareTo(appointments[j + 1].getProvider());

                if (dateComparison > 0 || (dateComparison == 0 && (timeslotComparison > 0 || (timeslotComparison == 0 && providerComparison > 0)))) {

                    temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

        System.out.println();
        System.out.println("** Appointments ordered by date/time/provider **");

        printAppointments();
        System.out.println("** end of list **");
        System.out.flush();
    }

    public void printAppointments(){
        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
    }

    public boolean isProviderAvailable(Provider provider, Date date, Timeslot timeslot) {
        for (int i = 0; i < size; i++) {
            // Check if the provider already has an appointment for the same date and timeslot
            if (appointments[i].getProvider().equals(provider) && appointments[i].getDate().equals(date) && appointments[i].getTimeslot().equals(timeslot)) {
                System.out.println(provider + " is not available at slot " + timeslot.getTimeslotNumber() + ".");
                System.out.flush();
                return false; // Provider is not available
            }
        }
        return true; // Provider is available
    }


    //ordered by date/timeslot, provider name


    public Appointment findAppointmentByProfileAndDate(Profile profile, Date appointmentDate, Timeslot timeslot) {
        for (int i = 0; i < size; i++) {
            Appointment currentAppointment = appointments[i];

            // Check if the patient's profile matches
            if (currentAppointment.getPatientProfile().equals(profile) &&
                    currentAppointment.getDate().equals(appointmentDate) &&
                    currentAppointment.getTimeslot().equals(timeslot)) {
                return currentAppointment; // Appointment found
            }
        }

        System.out.println(appointmentDate.toString() + " " + timeslot.toString() + " " + profile.toString() + " does not exist.");
        System.out.flush();
        return null;
    }

    public void clear() {
        // Simply reset the array and size
        this.appointments = new Appointment[4]; // Reset to the initial size
        this.size = 0; // Reset the size counter
    }

    public List(Appointment[] appointments, int size) {
        this.appointments = appointments;
        this.size = size;
    }

    //default constructor
    public List() {
        this.appointments = new Appointment[4];
        this.size = 0;

    }

    public int getSize() {
        return size;
    }


    public static void main(String[] args) {
        List appointmentList = new List();
        Profile johnDoe = new Profile("John", "Doe", new Date(1989, 12, 13));
        Provider patel = Provider.PATEL;
        Appointment nonExistentAppointment = new Appointment(new Date(2024, 9, 30), Timeslot.SLOT1, johnDoe, patel);

        // Try to remove the non-existent appointment
        appointmentList.remove(nonExistentAppointment);


        // Create a patient profile and appointment that does not exist in the list
        Profile janeDoe = new Profile("Jane", "Doe", new Date(1992, 7, 21));
        Provider taylor = Provider.TAYLOR;
        Date appointmentDate = new Date(2024, 9, 30);
        Timeslot timeslot = Timeslot.SLOT2;


        Appointment result = appointmentList.findAppointmentByProfileAndDate(janeDoe, appointmentDate, timeslot);

    }

}

