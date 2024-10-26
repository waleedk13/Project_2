package project2;

/**
 * The RunProject2 class is the driver class for running the entire Clinic Management software.
 * It creates an instance of ClinicManager and initiates the software by calling the run method.
 *
 * This class serves as the entry point to the project.
 *
 * @author Rehan Baig, Waleed Khalid
 *  * @implemented Waleed Khalid, Rehan Baig
 */
public class RunProject2 {

    /**
     * The main method serves as the entry point to the application.
     * It creates an instance of the ClinicManager class and starts the system.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new ClinicManager().run();
    }
}
