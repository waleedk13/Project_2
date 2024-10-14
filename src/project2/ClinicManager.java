package project2;
import java.util.Scanner;

public class ClinicManager{
    //private List appointmentList;
    Timeslot[] timeslots = new Timeslot[12];
    Provider[] providers = new Provider[16];

    public void run() {
        Provider.generateProviders(providers);
        //sort by provider profile!
        //Creates a rotation list of technicians for scheduling imaging appointments.
        displayProviderList();
        System.out.println("Clinic Manager is running.");
        Scanner scanner = new Scanner(System.in);

        //Initialize the appointmentList if it's null
//        if (appointmentList == null) {
//            appointmentList = new List();
//        }
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();

            if (command.startsWith("Q")) {
                quitClinicManager();
                return;
            }
            else if (isOneLetterCommand(command)) { // Handle one-letter commands (D, T, C, R)
                oneLetterCommand(command);
            }
            else if (isTwoLetterCommand(command)) { // Handle two-letter commands (PA, PP, PL, PS, PO, PI, PC)
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

    public void oneLetterCommand(String command){
        if (command.startsWith("D")) {
            scheduleDoctorAppointment(command);
        }
        else if (command.startsWith("T")) {
            scheduleImagingAppointment(command);
        }
        else if (command.startsWith("C")) {
            cancelAppointment(command);
        }
        if (command.startsWith("R")) {
            rescheduleAppointment(command);
        }
    }


    public void twoLetterCommand(String command) {
        if (command.startsWith("PA")) {

        } else if (command.startsWith("PP")) {

        } else if (command.startsWith("PL")) {

        } else if (command.startsWith("PS")) {

        } else if (command.startsWith("PO")) {

        } else if (command.startsWith("PI")) {

        } else if (command.startsWith("PC")) {

        }
    }


    public void displayProviderList(){
        for (Provider provider : providers) {
            System.out.println(provider.toString());
        }
    }

    public void quitClinicManager(){
        System.out.println("Clinic Manager terminated.");
    }

    public void scheduleDoctorAppointment(String input){

    }

    public void scheduleImagingAppointment(String input){

    }

    public void cancelAppointment(String input){

    }

    public void rescheduleAppointment(String input) {

    }



    public static void main(String[] args) {
        new ClinicManager().run();
    }
}




//            else if (command.startsWith("PA")) {
//                appointmentList.printByAppointment();
//            }
//            else if (command.startsWith("PP")) {
//                appointmentList.printByPatient();
//            }
//            else if (command.startsWith("PL")) {
//                appointmentList.printByLocation();
//            }
//            else if (command.startsWith("PS")) {
//                displayBillingStatements();
//            }
//        }
//
//    }
//

//
//    public void scheduleAppointment(String input){
//        if (appointmentList == null) {
//            appointmentList = new List(); // Initialize the list if it hasn't been initialized yet
//        }
//
//        Appointment appointment = createAppointmentFromString(input);
//        if (appointment == null) {
//            return;
//        }
//        if(appointmentList.contains(appointment)){
//            System.out.println(appointment.toStringNameandDob()+ " has an existing appointment at the same time slot.");
//            return;
//        }
//
//        if (!appointmentList.isProviderAvailable(appointment.getProvider(), appointment.getDate(), appointment.getTimeslot())) {
//            return;
//        }
//        appointmentList.add(appointment);
//        System.out.println(appointment.toString() + " booked.");
//        System.out.flush();
//
//    }
//
//    public void cancelAppointment(String input) {
//        if (appointmentList == null) {
//            appointmentList = new List();
//        }
//
//        Appointment appointment = createAppointmentFromString(input);
//        if (appointment == null) {
//            return;
//        }
//        String outputMessage = appointmentList.remove(appointment);
//        if(outputMessage!=null) {
//            System.out.println(outputMessage + " has been canceled.");
//            System.out.flush();
//        }
//    }
//
//    public void rescheduleAppointment(String input) {
//        if (appointmentList == null) {
//            appointmentList = new List();
//        }
//
//        String[] inputArray = breakStringIntoArray(input);
//        Appointment originalAppointment = createAppointmentUsingBrokenString(inputArray);
//        if(originalAppointment==null){
//            return;
//        }
//        Provider provider = originalAppointment.getProvider();
//        int timeSlotNumber = Integer.parseInt(inputArray[6]);
//        Timeslot newTimeSlot = Timeslot.timeslotFromNumber(timeSlotNumber, timeslots);
//        if (newTimeSlot == null) {
//            return;
//        }
//        if (!appointmentList.isProviderAvailable(provider, originalAppointment.getDate(), newTimeSlot)){
//            return;
//        }
//
//        appointmentList.remove(originalAppointment);
//        Appointment newAppointment = new Appointment(originalAppointment.getDate(), newTimeSlot, originalAppointment.getPatientProfile(), originalAppointment.getProvider());
//        appointmentList.add(newAppointment);
//        System.out.println("Rescheduled to " + newAppointment.toString());
//        System.out.flush();
//
//    }
//
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
//    public String[] breakStringIntoArray(String input){
//        return input.split(",");
//    }
//
//
//    public Appointment createAppointmentFromString(String input) {
//        String[] inputArray = breakStringIntoArray(input);
//
//        Date appointmentDate = new Date(inputArray[1]);
//        String validatedAppointmentDateString = appointmentDate.validateAppointmentDate(appointmentDate);
//        if(validatedAppointmentDateString!= null){
//            System.out.println(validatedAppointmentDateString);
//            return null;
//        }
//
//
//        if (!inputArray[2].matches("\\d+") || Integer.parseInt(inputArray[2]) < 1 || Integer.parseInt(inputArray[2]) > 12) { //checks if it's integer
//            System.out.println(inputArray[2] + " is not a valid time slot.");
//            return null;
//        }
//
//        Timeslot selectedTimeSlot = Timeslot.timeslotFromNumber(Integer.parseInt(inputArray[2]), timeslots);
//
//        Date dobDate = new Date(inputArray[5]);
//        String validatedDobDateString = dobDate.validateDobDate(dobDate);
//        if(validatedDobDateString!=null){
//            System.out.println(validatedDobDateString);
//            return null;
//        }
//
//        Doctor doctor = Doctor.getDoctorByNpi(inputArray[6], doctors);
//        if (doctor == null) {
//            return null;
//        }
//
//        Profile profile = new Profile(inputArray[3], inputArray[4], dobDate);
//
//        return new Appointment(appointmentDate, selectedTimeSlot, profile, doctor);
//    }
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
