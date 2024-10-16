package project2;
import java.util.Scanner;

public class ClinicManager {
    private Timeslot[] timeslots = new Timeslot[12];
    private List<Provider> providerList = new List<>();
    private List<Appointment> appointmentList = new List<>();
    private CircularlyLinkedList technicianList = new CircularlyLinkedList();

    public void run() {
        Timeslot.generateTimelots(timeslots);
        Provider.generateProviders(providerList);
        System.out.println("Providers loaded to the list.");
        Sort.sortProvider(providerList);
        displayProviders(providerList);
        generateTechnicianCircularLinkedList();
        displayTechnicians();
        System.out.println("Clinic Manager is running.");
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        //Initialize the appointmentList if it's null
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            if (command.isEmpty()) {
                continue;
            } else if (command.startsWith("Q")) {
                quitClinicManager();
                return;
            } else if (isOneLetterCommand(command)) { // Handle one-letter commands (D, T, C, R)
                oneLetterCommand(command);
            } else if (isTwoLetterCommand(command)) { // Handle two-letter commands (PA, PP, PL, PS, PO, PI, PC)
                twoLetterCommand(command);
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }


    private boolean isOneLetterCommand(String command) {
        return command.startsWith("D") || command.startsWith("T") || command.startsWith("C") || command.startsWith("R");
    }

    private boolean isTwoLetterCommand(String command) {
        return command.startsWith("PA") || command.startsWith("PP") || command.startsWith("PL")
                || command.startsWith("PS") || command.startsWith("PO") || command.startsWith("PI")
                || command.startsWith("PC");
    }

    public void oneLetterCommand(String command) {
        if (command.startsWith("D")) {
            scheduleDoctorAppointment(command);
        } else if (command.startsWith("T")) {
            scheduleImagingAppointment(command);
        } else if (command.startsWith("C")) {
            cancelAppointment(command);
        } else if (command.startsWith("R")) {
            rescheduleAppointment(command);
        }
    }


    public void twoLetterCommand(String command) {
        if (command.startsWith("PA")) {
            printByAppointment();
        } else if (command.startsWith("PP")) {
            printByPatient();
        } else if (command.startsWith("PL")) {
            printByLocation();
        } else if (command.startsWith("PS")) {
            displayBillingStatements();;
        } else if (command.startsWith("PO")) {
            displayOfficeAppointments();
        } else if (command.startsWith("PI")) {
            displayImagingAppointment();
        } else if (command.startsWith("PC")) {
            displayExpectedCreditAmount();
        }
    }


    public void quitClinicManager() {
        System.out.println("Clinic Manager terminated.");
    }

    public void scheduleDoctorAppointment(String input) {
        Appointment appointment = createDoctorAppointmentFromString(input);
        if (appointment == null) {
            return;
        }

        if (appointmentAlreadyExists(appointment)) {
            return;  // Do not proceed if the appointment already exists
        }

        Provider provider = (Provider) appointment.getProviderPerson();
        if (!isProviderAvailable(provider, appointment.getDate(), appointment.getTimeslot())) {
            return;
        }

        appointmentList.add(appointment);
        System.out.println(appointment.toString() + " booked.");
        System.out.flush();
    }


    public void scheduleImagingAppointment(String input) {
        Imaging imagingAppointment = createImagingAppointmentFromString(input); //also checks if appointment exists
        if (imagingAppointment == null) {
            return;
        }

        // If no duplicate exists, add the imaging appointment
        appointmentList.add(imagingAppointment);
        System.out.println(imagingAppointment.toString() + " booked.");
        System.out.flush();
    }


    public void cancelAppointment(String input) {
        Appointment tempAppointment = createTempAppointment(input);
        if (appointmentList.contains(tempAppointment)) {
            appointmentList.remove(tempAppointment);
            System.out.println(tempAppointment.toStringWithoutProvider() + " - appointment has been canceled.");
        } else {
            System.out.println(tempAppointment.toStringWithoutProvider() + " - doesn't exist.");
        }
    }

    public void rescheduleAppointment(String input) {
        String[] inputArray = breakStringIntoArray(input);

        //checks if originalAppt in list
        Appointment originalAppointment = createTempAppointment(input);
        if (!appointmentList.contains(originalAppointment)) {
            System.out.println(originalAppointment.toStringWithoutProvider() + " - doesn't exist.");
            return;
        }
        int timeSlotNumber = Integer.parseInt(inputArray[6]);

        //checks if valid timeslot
        Timeslot newTimeSlot = Timeslot.getTimeslotFromNumber(timeSlotNumber, timeslots);
        if (newTimeSlot == null) {
            System.out.println(timeSlotNumber+  " is not a valid time slot.");
            return;
        }

        Provider provider = (Provider) originalAppointment.getProviderPerson();
        if (!isProviderAvailable(provider, originalAppointment.getDate(), newTimeSlot)){
            return;
        }

        appointmentList.remove(originalAppointment);
        Doctor doctor = (Doctor) originalAppointment.getProviderPerson();
        Appointment newAppointment = new Appointment(originalAppointment.getDate(), newTimeSlot,
                originalAppointment.getPatientPerson(), doctor);
        appointmentList.add(newAppointment);
        System.out.println("Rescheduled to " + newAppointment.toStringWithoutProvider() + doctor.toString());
    }


    public Appointment createDoctorAppointmentFromString(String input) {
        String[] inputArray = breakStringIntoArray(input);
        if(!areAllTokensValid(inputArray)){
            return null;
        }

        String npi = inputArray[6];
        Doctor doctor = Doctor.getDoctorByNpi(npi, providerList);
        if (!npi.matches("\\d+") || doctor == null) { //for doctor, if NPI isn't numeric
            System.out.println(npi + " - provider does not exist.");
            return null;
        }

        Date appointmentDate = new Date(inputArray[1]);
        Timeslot selectedTimeSlot = Timeslot.getTimeslotFromNumber(Integer.parseInt(inputArray[2]), timeslots);
        Date dobDate = new Date(inputArray[5]);
        Profile patientProfile = new Profile(inputArray[3], inputArray[4], dobDate);
        Person patient = new Person(patientProfile);

        Appointment appointment = new Appointment(appointmentDate, selectedTimeSlot, patient, doctor);

        if(appointmentAlreadyExists(appointment)){
            return null;
        }

        return appointment;
    }



    public Imaging createImagingAppointmentFromString(String input) {
        String[] inputArray = breakStringIntoArray(input);
        if(!areAllTokensValid(inputArray)){
            return null;
        }

        Radiology room;
        String imagingType = inputArray[6];
        switch (imagingType) {
            case "xray":
                room = Radiology.XRAY;
                break;
            case "catscan":
                room = Radiology.CATSCAN;
                break;
            case "ultrasound":
                room = Radiology.ULTRASOUND;
                break;
            default:
                System.out.println(imagingType + " - imaging service not provided.");
                return null;
        }

        Date appointmentDate = new Date(inputArray[1]);
        Timeslot selectedTimeSlot = Timeslot.getTimeslotFromNumber(Integer.parseInt(inputArray[2]), timeslots);
        Date dobDate = new Date(inputArray[5]);
        Profile patientProfile = new Profile(inputArray[3], inputArray[4], dobDate);
        Person patient = new Person(patientProfile);

        Imaging imagingAppointment = new Imaging(appointmentDate, selectedTimeSlot, patient, null, room);

        // Check if the imaging appointment already exists
        if(appointmentAlreadyExists(imagingAppointment)){
            return null;
        }

        // Get the available technician only after ensuring the appointment doesn't already exist
        Technician technician = getAvailableTechnician(appointmentDate, selectedTimeSlot, room);
        if (technician == null) {
            System.out.println("Cannot find an available technician at all locations for "
                    + room + " at slot " + Timeslot.getNumberFromTimeslot(selectedTimeSlot, timeslots) + ".");
            return null;
        }

        imagingAppointment.setTechnician(technician);
        return imagingAppointment;
    }


    public String[] breakStringIntoArray(String input){
        return input.split(",");
    }


    public boolean areAllTokensValid(String[] inputArray){

        //missing data tokens
        if(inputArray.length!=7){
            System.out.println("Missing data tokens.");
            return false;
        }

        //date check
        Date appointmentDate = new Date(inputArray[1]);
        String validatedAppointmentDateString = appointmentDate.validateAppointmentDate(appointmentDate);
        if(validatedAppointmentDateString!= null){
            System.out.println(validatedAppointmentDateString);
            return false;
        }

        //timeslot check
        if (!inputArray[2].matches("\\d+") || Integer.parseInt(inputArray[2]) < 1 || Integer.parseInt(inputArray[2]) > 12) { //checks if it's integer
            System.out.println(inputArray[2] + " is not a valid time slot.");
            return false;
        }

        //dob check
        Date dobDate = new Date(inputArray[5]);
        String validatedDobDateString = dobDate.validateDobDate(dobDate);
        if(validatedDobDateString!=null){
            System.out.println(validatedDobDateString);
            return false;
        }

        return true;
    }

    public boolean appointmentAlreadyExists(Appointment appointment) {
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment existingAppointment = appointmentList.get(i);
            if (existingAppointment.equals(appointment)) {
                // Print a message if an existing appointment is found
                System.out.println(appointment.getPatientPerson().getProfile().toString() +
                        " has an existing appointment at the same time slot.");
                return true;  // Return true if the appointment already exists
            }
        }
        return false;  // No duplicate found
    }

    public boolean isProviderAvailable(Provider provider, Date date, Timeslot timeslot) {
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment existingAppointment = appointmentList.get(i);

            if (existingAppointment.getProviderPerson().equals(provider) &&
                    existingAppointment.getDate().equals(date) &&
                    existingAppointment.getTimeslot().equals(timeslot)) {

                if(provider instanceof Doctor){
                    Doctor doctor = (Doctor) provider;
                    System.out.println(doctor.toString() + " is not available at slot " + Timeslot.getNumberFromTimeslot(timeslot, timeslots) + ".");
                }
                else{
                    System.out.println(provider.getProfile().toString() + " is not available at slot " + Timeslot.getNumberFromTimeslot(timeslot, timeslots) + ".");
                }
                return false;
            }
        }
        return true;
    }

    public Appointment createTempAppointment(String input){
        String[] inputArray = breakStringIntoArray(input);
        Date appointmentDate = new Date(inputArray[1]);
        Timeslot selectedTimeSlot = Timeslot.getTimeslotFromNumber(Integer.parseInt(inputArray[2]), timeslots);
        Date dobDate = new Date(inputArray[5]);
        Profile patientProfile = new Profile(inputArray[3], inputArray[4], dobDate);  // First name, Last name, DOB
        Person patient = new Person(patientProfile);
        return new Appointment(appointmentDate, selectedTimeSlot, patient, null);
    }

    public void displayProviders(List<Provider> providerList) {
        for (int i = 0; i < providerList.size(); i++) {
            System.out.println(providerList.get(i).toString());
        }
        System.out.println();
    }

    public void generateTechnicianCircularLinkedList() {
        // Add technicians manually in the desired order
        Technician jennyPatel = findTechnicianByNameAndLocation("Jenny", "Patel", Location.BRIDGEWATER);
        Technician monicaFox = findTechnicianByNameAndLocation("Monica", "Fox", Location.BRIDGEWATER);
        Technician charlesBrown = findTechnicianByNameAndLocation("Charles", "Brown", Location.BRIDGEWATER);
        Technician frankLin = findTechnicianByNameAndLocation("Frank", "Lin", Location.PISCATAWAY);
        Technician benJerry = findTechnicianByNameAndLocation("Ben", "Jerry", Location.PISCATAWAY);
        Technician garyJohnson = findTechnicianByNameAndLocation("Gary", "Johnson", Location.PISCATAWAY);

        // Add to circular linked list in the exact order
        technicianList.addTechnician(jennyPatel);
        technicianList.addTechnician(monicaFox);
        technicianList.addTechnician(charlesBrown);
        technicianList.addTechnician(frankLin);
        technicianList.addTechnician(benJerry);
        technicianList.addTechnician(garyJohnson);
    }

    // Helper method to find a technician by first name, last name, and location
    private Technician findTechnicianByNameAndLocation(String firstName, String lastName, Location location) {
        for (int i = 0; i < providerList.size(); i++) {
            Provider provider = providerList.get(i);
            if (provider instanceof Technician) {
                Technician technician = (Technician) provider;
                if (technician.getProfile().getFirstName().equalsIgnoreCase(firstName) &&
                        technician.getProfile().getLastName().equalsIgnoreCase(lastName) &&
                        technician.getLocation() == location) {
                    return technician;
                }
            }
        }
        return null; // Return null if no match is found
    }


    private Technician getAvailableTechnician(Date appointmentDate, Timeslot requestedTimeslot, Radiology room) {
        Technician firstTechnician = technicianList.getCurrentTechnician(); // Start from the current technician
        Technician currentTechnician = firstTechnician;

        do {
            if (isTechnicianAvailable(currentTechnician, appointmentDate, requestedTimeslot)) {
                Location locationOfTechnician = currentTechnician.getLocation();

                // Check if the room is available at the location the technician works
                if (isImagingRoomAvailable(locationOfTechnician, appointmentDate, requestedTimeslot, room)) {

                    // Advance to the next technician before returning to avoid re-checking the same technician
                    technicianList.advanceToNextTechnician(); // Ensure rotation continues after assigning

                    return currentTechnician;  // Return the first available technician with a free room
                }
            }

            technicianList.advanceToNextTechnician();
            currentTechnician = technicianList.getCurrentTechnician();

        } while (currentTechnician != firstTechnician);  // Loop back to the start of the technician list

        return null;  // No available technician found
    }

    // Separate method to check if the technician is available
    private boolean isTechnicianAvailable(Technician technician, Date appointmentDate, Timeslot requestedTimeslot) {
        for (int i = 0; i < appointmentList.size(); i++) {
            if(!(appointmentList.get(i) instanceof Imaging existingAppointment)) {
                continue;
            }
            if (existingAppointment.getProviderPerson().equals(technician) &&
                    existingAppointment.getDate().equals(appointmentDate) &&
                    existingAppointment.getTimeslot().equals(requestedTimeslot)) {
                return false;  // Technician is already booked
            }
        }
        return true;
    }

    // Check if the imaging room is available at the technician's location
    private boolean isImagingRoomAvailable(Location location, Date appointmentDate, Timeslot requestedTimeslot, Radiology room) {
        // Loop through all appointments to check if the room is booked at the given location
        for (int i=0; i<appointmentList.size(); i++){
            Appointment existingAppointment = appointmentList.get(i);
            if (existingAppointment instanceof Imaging imagingAppointment) {
                Technician technician = (Technician) imagingAppointment.getProviderPerson();
                if (imagingAppointment.getRoom().equals(room) &&
                        imagingAppointment.getDate().equals(appointmentDate) &&
                        imagingAppointment.getTimeslot().equals(requestedTimeslot) &&
                        technician.getLocation().equals(location)) {
                    // Room is already booked
                    return false;
                }
            }
        }
        return true;  // Room is available
    }




    public void displayTechnicians() {
        System.out.println("Rotation list for the technicians.");
        Technician firstTechnician = technicianList.getCurrentTechnician();
        Technician currentTechnician = firstTechnician;
        do {
            System.out.print(currentTechnician.toStringForRotationList());
            technicianList.advanceToNextTechnician();  // Move to the next technician
            currentTechnician = technicianList.getCurrentTechnician();

            if (currentTechnician != firstTechnician) {
                System.out.print(" --> ");
            }
        } while (currentTechnician != firstTechnician);

        System.out.println();
        System.out.println();
    }



    public void printByAppointment() {
        if (appointmentList.isEmpty()) {
            System.out.println("The schedule calendar is empty.");
            return;
        }
        // Sort the appointment list by date, timeslot, and provider
        Sort.sortByAppointment(appointmentList);
        System.out.println();
        System.out.println("** Appointments ordered by date/time/provider **");
        printAppointments();
    }

    public void printByPatient() {
        if (appointmentList.isEmpty()) {
            System.out.println("The schedule calendar is empty.");
            return;
        }

        // Sort the appointment list by patient profile, date, and timeslot
        Sort.sortByPatients(appointmentList);
        System.out.println();
        System.out.println("** Appointments ordered by patient/date/time **");
        printAppointments();
    }


    public void printByLocation() {
        if (appointmentList.isEmpty()) {
            System.out.println("The schedule calendar is empty.");
            return;
        }

        // Sort the appointment list by location, date, and timeslot
        Sort.sortByLocation(appointmentList);
        System.out.println("** Appointments ordered by county/date/time **");
        printAppointments();
    }


    public void printAppointments(){
        for (int i = 0; i < appointmentList.size(); i++) {
            System.out.println(appointmentList.get(i).toString());
        }
        System.out.println("** end of list **");
    }


    public void displayBillingStatements() {
        if(appointmentList.isEmpty()){
            System.out.println("The scheduler calendar is empty");
            return;
        }
        if (!Sort.sortByPatients(appointmentList)) {
            return;
        }

        Profile currentPatient = null;
        int totalCharge = 0;
        int patientCounter = 1;  // Initialize patient counter

        System.out.println();
        System.out.println("** Billing statement ordered by patient **");

        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment appointment = appointmentList.get(i);
            if (appointment == null) {
                continue;
            }

            Profile patientProfile = appointment.getPatientPerson().getProfile(); //Come back
            if (currentPatient == null) {
                // This is the first patient being processed
                currentPatient = patientProfile;
            } else if (!currentPatient.equals(patientProfile)) {
                // We've moved to a new patient, so print the previous patient's billing statement
                System.out.println("(" + patientCounter + ") " + currentPatient.toString() + " [amount due: $" + totalCharge + ".00]");

                // Reset the total charge for the next patient and increment patient counter
                currentPatient = patientProfile;
                patientCounter++;
            }

            if (appointment instanceof Imaging) {
                Technician technician = (Technician) appointment.getProviderPerson();
                totalCharge += technician.rate();
            } else {
                Doctor doctor = (Doctor) appointment.getProviderPerson();
                Specialty specialty = doctor.getSpecialty();
                totalCharge += specialty.getCharge();
            }
        }

        //Print the billing statement for the last patient processed
        if (currentPatient != null) {
            System.out.println("(" + patientCounter + ") " + currentPatient.toString() + " [amount due: $" + totalCharge + ".00]");
        }
        System.out.println("** end of list **");
        appointmentList.clear();
    }

    //PO
    public void displayOfficeAppointments(){
        if(appointmentList.isEmpty()){
            System.out.println("The scheduler calendar is empty");
            return;
        }
        Sort.sortByLocation(appointmentList);
        // Display the sorted appointments
        System.out.println("Office Appointments (Sorted by County, Date, " +
                "and Timeslot):");
        int i = 0;
        while(i < appointmentList.size()){
            if(appointmentList.get(i) instanceof Appointment){
                Appointment officeAppointment = (Appointment) appointmentList.
                        get(i);
                System.out.println(officeAppointment.toString());
                i++;
            }
        }
    }

    //PI
    public void displayImagingAppointment() {
        if (appointmentList.isEmpty()) {
            System.out.println("The scheduler calendar is empty");
            return;
        }

        System.out.println();
        System.out.println("** List of radiology appointments ordered by county/date/time.");
        int i = 0;
        while(i < appointmentList.size()){
            if(appointmentList.get(i) instanceof Imaging){
                Imaging imagingAppointment = (Imaging) appointmentList.get(i);
                System.out.println(imagingAppointment.toString());
            }
            i++;
        }
        System.out.println("** end of list **");
    }

    //PC
    public void displayExpectedCreditAmount(){
        if (appointmentList.isEmpty()) {
            System.out.println("The scheduler calendar is empty");
            return;
        }
        for (int i = 0; i < providerList.size(); i++) {
            Provider provider = providerList.get(i);  // Get the current provider
            int totalCredits = 0;  // Initialize the total credits for this provider

            for (int j = 0; j < appointmentList.size(); j++) {
                Appointment appointment = appointmentList.get(j);  // Get the current appointment
                if (appointment.getProviderPerson().equals(provider)) {
                    totalCredits += provider.rate();  // Add the provider's rate for each appointment
                }
            }
            System.out.println(provider.getProfile() + ": $" + totalCredits);


        }
    }



    public static void main(String[] args) {
        new ClinicManager().run();
    }
}