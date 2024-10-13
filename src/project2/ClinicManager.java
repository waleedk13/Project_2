package project2;
import java.util.Scanner;

public class ClinicManager {
    private List appointmentList;

    public void run(){
        System.out.println("Scheduler is running.");

        Scanner scanner = new Scanner(System.in);
        // Initialize the appointmentList if it's null
        if (appointmentList == null) {
            appointmentList = new List();
        }

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim(); // trimming any unwanted spaces

            if (command.isEmpty()) {
                continue; // skip empty lines
            }
            if (command.startsWith("Q")) {
                quitScheduler();
                break;
            }
            else if (command.startsWith("S")) {
                scheduleAppointment(command);
            }
            else if (command.startsWith("C")) {
                cancelAppointment(command);
            }
            else if (command.startsWith("R")) {
                rescheduleAppointment(command);
            }
            else if (command.startsWith("PA")) {
                appointmentList.printByAppointment();
            }
            else if (command.startsWith("PP")) {
                appointmentList.printByPatient();
            }
            else if (command.startsWith("PL")) {
                appointmentList.printByLocation();
            }
            else if (command.startsWith("PS")) {
                displayBillingStatements();
            }
            else {
                System.out.println("Invalid command!");
                System.out.flush();

            }
        }
        scanner.close();
    }

    public void quitScheduler(){
        System.out.println("Scheduler is terminated.");
        System.out.flush();
    }

    public void scheduleAppointment(String input){
        if (appointmentList == null) {
            appointmentList = new List(); // Initialize the list if it hasn't been initialized yet
        }

        Appointment appointment = createAppointmentFromString(input);
        if (appointment == null) {
            return;
        }
        if(appointmentList.contains(appointment)){
            System.out.println(appointment.getPatientProfile().getFirstName() + " " + appointment.getPatientProfile().getLastName() + " "
                    + appointment.getPatientProfile().getDob() + " has an existing appointment at the same time slot.");
            return;
        }

        if (!appointmentList.isProviderAvailable(appointment.getProvider(), appointment.getDate(), appointment.getTimeslot())) {
            return;
        }
        appointmentList.add(appointment);
        System.out.println(appointment.toString() + " booked.");
        System.out.flush();

    }

    public void cancelAppointment(String input) {
        if (appointmentList == null) {
            appointmentList = new List();
        }

        Appointment appointment = createAppointmentFromString(input);
        if (appointment == null) {
            return;
        }
        String outputMessage = appointmentList.remove(appointment);
        if(outputMessage!=null) {
            System.out.println(outputMessage + " has been canceled.");
            System.out.flush();
        }
    }

    public void rescheduleAppointment(String input) {
        if (appointmentList == null) {
            appointmentList = new List();
        }

        String[] inputArray = breakStringIntoArray(input);
        Appointment originalAppointment = createAppointmentUsingBrokenString(inputArray);
        if(originalAppointment==null){
            return;
        }
        Provider provider = originalAppointment.getProvider();
        int timeSlotNumber = Integer.parseInt(inputArray[6]);
        Timeslot newTimeSlot = Timeslot.timeslotFromNumber(timeSlotNumber);
        if (newTimeSlot == null) {
            return;
        }
        if (!appointmentList.isProviderAvailable(provider, originalAppointment.getDate(), newTimeSlot)){
            return;
        }

        appointmentList.remove(originalAppointment);
        Appointment newAppointment = new Appointment(originalAppointment.getDate(), newTimeSlot, originalAppointment.getPatientProfile(), originalAppointment.getProvider());
        appointmentList.add(newAppointment);
        System.out.println("Rescheduled to " + newAppointment.toString());
        System.out.flush();
        
    }

    public void displayBillingStatements(){
        if (!appointmentList.organizeByPatient()) {
            return; // If no appointments, just return
        }

        Profile currentPatient = null;
        int totalCharge = 0;
        int patientCounter = 1;  // Initialize patient counter

        System.out.println();
        System.out.println("** Billing statement ordered by patient **");

        for (int i = 0; i < appointmentList.getSize(); i++) {
            Appointment appointment = appointmentList.getAppointment(i);
            if (appointment == null) {
                continue;
            }

            Profile patientProfile = appointment.getPatientProfile();

            // Check if we're processing the same patient
            if (currentPatient == null) {
                // This is the first patient being processed
                currentPatient = patientProfile;
            } else if (!currentPatient.equals(patientProfile)) {
                // We've moved to a new patient, so print the previous patient's billing statement
                System.out.println("(" + patientCounter + ") " + currentPatient.toString() + " [amount due: $" + totalCharge + ".00]");

                // Reset the total charge for the next patient and increment patient counter
                totalCharge = 0;
                currentPatient = patientProfile;
                patientCounter++;
            }

            // Add up the charges for the current patient
            Specialty specialty = appointment.getProvider().getSpecialty();
            totalCharge += specialty.getCharge();
        }

        // Print the billing statement for the last patient processed
        if (currentPatient != null) {
            System.out.println("(" + patientCounter + ") " + currentPatient.toString() + " [amount due: $" + totalCharge + ".00]");
        }

        System.out.println("** end of list **");

        // Clear all appointments after billing is processed
        appointmentList.clear();
    }


    public String[] breakStringIntoArray(String input){
        return input.split(",");
    }

    public Appointment createAppointmentFromString(String input) {
        String[] inputArray = breakStringIntoArray(input);

        Date appointmentDate = new Date(inputArray[1]);
        String validatedAppointmentDateString = appointmentDate.validateAppointmentDate(appointmentDate);
        if(validatedAppointmentDateString!= null){
            System.out.println(validatedAppointmentDateString);
            return null;
        }

        if (!inputArray[2].matches("\\d+")) {
            System.out.println(inputArray[2] + " is not a valid time slot.");
            System.out.flush();
            return null;
        }

        int timeSlotNumber = Integer.parseInt(inputArray[2]);
        Timeslot selectedTimeSlot = Timeslot.timeslotFromNumber(timeSlotNumber);
        if (selectedTimeSlot == null) {
            return null;
        }

        Date dobDate = new Date(inputArray[5]);
        String validatedDobDateString = dobDate.validateDobDate(dobDate);
        if(validatedDobDateString!=null){
            System.out.println(validatedDobDateString);
            return null;
        }

        Profile profile = new Profile(inputArray[3], inputArray[4], dobDate);
        Provider provider = Provider.getProviderByLastName(inputArray[6]);
        if (provider == null) {
            return null;
        }

        return new Appointment(appointmentDate, selectedTimeSlot, profile, provider);
    }

    public Appointment createAppointmentUsingBrokenString(String[] inputArray){
        Date appointmentDate = new Date(inputArray[1]);
        String validatedAppointmentDateString = appointmentDate.validateAppointmentDate(appointmentDate);
        if(validatedAppointmentDateString!= null){
            System.out.println(validatedAppointmentDateString);
            return null;
        }
        Timeslot originalTimeslot = Timeslot.timeslotFromNumber(Integer.parseInt(inputArray[2]));

        Date dobDate = new Date(inputArray[5]);
        Profile profile = new Profile(inputArray[3], inputArray[4], dobDate);

        return appointmentList.findAppointmentByProfileAndDate(profile, appointmentDate, originalTimeslot);
    }

    public static void main(String[] args) {
        new ClinicManager().run();
    }
}
