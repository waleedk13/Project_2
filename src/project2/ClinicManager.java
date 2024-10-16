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
        generateTechnicianCircularLinkedList(providerList);
        displayTechnicians(); //DOESN'T DO IT IN THE ORDER THEY WANT??????
        System.out.println("Clinic Manager is running.");
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        //Initialize the appointmentList if it's null
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();

            if (command.startsWith("Q")) {
                quitClinicManager();
                return;
            } else if (isOneLetterCommand(command)) { // Handle one-letter commands (D, T, C, R)
                oneLetterCommand(command);
            } else if (isTwoLetterCommand(command)) { // Handle two-letter commands (PA, PP, PL, PS, PO, PI, PC)
                twoLetterCommand(command);
            } else {
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
        }
        if (command.startsWith("R")) {
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

        } else if (command.startsWith("PO")) {

        } else if (command.startsWith("PI")) {

        } else if (command.startsWith("PC")) {

        }
    }


    public void displayProviderList(List<Provider> providerList) {
        for (int i = 0; i < providerList.size(); i++) {
            System.out.println(providerList.get(i).toString());
        }
        System.out.println();
    }

    public void quitClinicManager() {
        System.out.println("Clinic Manager terminated.");
    }

    public void scheduleDoctorAppointment(String input) {
        Appointment appointment = createDoctorAppointmentFromString(input);
        if (appointment == null) {
            return;
        }
        if(appointmentList.contains(appointment)){
            System.out.println(appointment.getPatientPerson().toString()+ " has an existing appointment at the same time slot.");
            return;
        }

        Provider provider = (Provider) appointment.getProviderPerson();
        if(!isProviderAvailable(provider, appointment.getDate(), appointment.getTimeslot())){
            return;
        }

        appointmentList.add(appointment);
        System.out.println(appointment.toString() + " booked.");
        System.out.flush();
    }


    public void scheduleImagingAppointment(String input) {
        Appointment appointment = createImagingAppointmentFromString(input);
        if (appointment == null) {
            return;
        }
        if(appointmentList.contains(appointment)){
            System.out.println(appointment.getPatientPerson().toString()+ " has an existing appointment at the same time slot.");
            return;
        }
        appointmentList.add(appointment);
        System.out.println(appointment.toString() + " booked.");
        System.out.flush();
    }

    public void cancelAppointment(String input) {
        Appointment tempAppointment = createTempAppointment(input);
        if (appointmentList.contains(tempAppointment)) {
            appointmentList.remove(tempAppointment);
            System.out.println(tempAppointment.toStringWithoutProvider() + " - appointment has been canceled.");
        } else {
            System.out.println(tempAppointment.toStringWithoutProvider() + " - does not exist.");
        }
    }

    public void rescheduleAppointment(String input) {
        String[] inputArray = breakStringIntoArray(input);

        //checks if originalAppt in list
        Appointment originalAppointment = createTempAppointment(input);
        if (!appointmentList.contains(originalAppointment)) {
            System.out.println(originalAppointment.toStringWithoutProvider() + " - does not exist.");
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
            System.out.println(npi + " - does not exist.");
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

    public Appointment createImagingAppointmentFromString(String input) {
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
        Technician technician = getAvailableTechnician(appointmentDate, selectedTimeSlot, room);
        if (technician == null){
            System.out.println("Cannot find an available technician at all locations for "
                    + room + " at slot " + Timeslot.getNumberFromTimeslot(selectedTimeSlot, timeslots) + ".");
            return null;
        }

        Imaging imagingAppointment = new Imaging(appointmentDate, selectedTimeSlot, patient, technician, room);
        if(appointmentAlreadyExists(imagingAppointment)){
            return null;
        }

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

    public boolean appointmentAlreadyExists(Appointment appointment){
        if(appointmentList.contains(appointment)){
            System.out.println(appointment.getPatientPerson().getProfile().toString()+ " has an existing appointment at the same time slot.");
            return true;
        }
        return false;
    }

    public boolean isProviderAvailable(Provider provider, Date date, Timeslot timeslot) {
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment existingAppointment = appointmentList.get(i);

            if (existingAppointment.getProviderPerson().equals(provider) &&
                    existingAppointment.getDate().equals(date) &&
                    existingAppointment.getTimeslot().equals(timeslot)) {

                System.out.println(provider.getProfile().toString() + " is not available at slot " + Timeslot.getNumberFromTimeslot(timeslot, timeslots) + ".");
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

    public void generateTechnicianCircularLinkedList(List<Provider> providerList) {
        for (int i = 0; i < providerList.size(); i++) {
            Provider provider = providerList.get(i);
            if (provider instanceof Technician) {
                Technician technician = (Technician) provider;
                technicianList.addTechnician(technician);  // Add technician to the circular list
            }
        }
    }


    private Technician getAvailableTechnician(Date appointmentDate, Timeslot requestedTimeslot, Radiology room) {
        Technician firstTechnician = technicianList.getNextTechnician(); // Start from the next technician in the list
        Technician currentTechnician = firstTechnician;

        do {
            boolean isAvailable = true; // Assume the technician is available

            for (int i = 0; i < appointmentList.size(); i++) {
                Appointment existingAppointment = appointmentList.get(i);
                if (existingAppointment.getProviderPerson().equals(currentTechnician) &&
                        existingAppointment.getDate().equals(appointmentDate) &&
                        existingAppointment.getTimeslot().equals(requestedTimeslot)) {
                    isAvailable = false;
                    break;
                }
            }

            if (isAvailable) {
                Location locationOfTechnician = technicianList.getNextTechnician().getLocation();
                if(isImagingRoomAvailable(currentTechnician, locationOfTechnician, appointmentDate, requestedTimeslot, room)){
                    return currentTechnician;
                }
            }
            currentTechnician = technicianList.getNextTechnician();

        } while (currentTechnician != firstTechnician);
        return null;
    }





    private boolean isImagingRoomAvailable(Technician technician, Location location, Date appointmentDate, Timeslot requestedTimeslot, Radiology room) {
        // Loop through all appointments to check if the room is already booked at the same location
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment existingAppointment = appointmentList.get(i);

            // Check if the appointment is an imaging appointment
            if (existingAppointment instanceof Imaging) {
                Imaging imagingAppointment = (Imaging) existingAppointment;

                Provider provider = (Provider) imagingAppointment.getProviderPerson();

                if (imagingAppointment.getRoom().equals(room) &&
                        provider.getLocation().equals(location) &&
                        imagingAppointment.getDate().equals(appointmentDate) &&
                        imagingAppointment.getTimeslot().equals(requestedTimeslot)) {

                    System.out.println("Room " + room + " at " + location.getName() + " is not available at the requested timeslot.");
                    return false;
                }
            }
        }
        return true;
    }






    public void displayTechnicians() {
        System.out.println("Rotation list for the technicians.");
        Technician firstTechnician = technicianList.getNextTechnician();
        Technician currentTechnician = firstTechnician;
        do {
            System.out.print(currentTechnician.toStringWithoutRate());
            currentTechnician = technicianList.getNextTechnician();

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







    public static void main(String[] args) {
        new ClinicManager().run();
    }
}


//    public void displayBillingStatements(){
//        if (!appointmentList.organizeByPatient()) {
//            return; // If no appointments, just return
//        }
//
//        Profile currentPatient = null;
//        int totalCharge = 0;
//        int patientCounter = 1;  // Initialize patient counter
//
//        System.out.println();
//        System.out.println("** Billing statement ordered by patient **");
//
//        for (int i = 0; i < appointmentList.getSize(); i++) {
//            Appointment appointment = appointmentList.getAppointment(i);
//            if (appointment == null) {
//                continue;
//            }
//
//            Profile patientProfile = appointment.getPatientProfile();
//
//            // Check if we're processing the same patient
//            if (currentPatient == null) {
//                // This is the first patient being processed
//                currentPatient = patientProfile;
//            } else if (!currentPatient.equals(patientProfile)) {
//                // We've moved to a new patient, so print the previous patient's billing statement
//                System.out.println("(" + patientCounter + ") " + currentPatient.toString() + " [amount due: $" + totalCharge + ".00]");
//
//                // Reset the total charge for the next patient and increment patient counter
//                totalCharge = 0;
//                currentPatient = patientProfile;
//                patientCounter++;
//            }
//
//            // Add up the charges for the current patient
//            Specialty specialty = appointment.getProvider().getSpecialty();
//            totalCharge += specialty.getCharge();
//        }
//
//        // Print the billing statement for the last patient processed
//        if (currentPatient != null) {
//            System.out.println("(" + patientCounter + ") " + currentPatient.toString() + " [amount due: $" + totalCharge + ".00]");
//        }
//
//        System.out.println("** end of list **");
//
//        // Clear all appointments after billing is processed
//        appointmentList.clear();
//    }
//
//
//
//    public Appointment createAppointmentFromString(String input) {
//
//    public Appointment createAppointmentUsingBrokenString(String[] inputArray){
//        Date appointmentDate = new Date(inputArray[1]);
//        String validatedAppointmentDateString = appointmentDate.validateAppointmentDate(appointmentDate);
//        if(validatedAppointmentDateString!= null){
//            System.out.println(validatedAppointmentDateString);
//            return null;
//        }
//        Timeslot originalTimeslot = Timeslot.timeslotFromNumber(Integer.parseInt(inputArray[2]));
//
//        Date dobDate = new Date(inputArray[5]);
//        Profile profile = new Profile(inputArray[3], inputArray[4], dobDate);
//
//        return appointmentList.findAppointmentByProfileAndDate(profile, appointmentDate, originalTimeslot);
//    }
//

//}
