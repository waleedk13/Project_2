/*@author Waleed Khalid
 * @author Rehan Baig
 *
 * !! Explain what this class does !!
 * */


package project2;

public class MedicalRecord {
    private Patient[] patients;
    private int size; //number of patients objects in the array

    /*You are only able to add a patient object but not remove*/

    //constructor class
    public MedicalRecord(Patient[] patient, int size) {
        this.patients = patients;
        this.size = size;
    }


    //defaut constructor
    public MedicalRecord() {
        this.patients = new Patient[4];//not sure if it has to be 4, following the list class instructions
        this.size = 0;
    }


    /*include an add method as this class you can only add patient obijects to the bag but can not remove*/
    public boolean add(Patient patient) {
        /*check to see if the array is full*/

        if(size == patients.length){
            grow();
        }
        /*start with adding a patient to the array
        * starts with 0, mean*/
        patients[size] = patient;
        size++;
        return true;
    }

    public void grow(){
        Patient newPatient[] = new Patient[patients.length + 4];
        int i = 0;
        while(i < patients.length){
            newPatient[i] = patients[i];
            i++;
        }
        this.patients = newPatient;
    }



    //getter methods to return the value
    public Patient[] getPatients(){
        return patients;
    }

    public int getSize(){
        return size;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        //cast it to MedicalRecord
        MedicalRecord other = (MedicalRecord) obj; // Cast obj to MedicalRecord

        //check to see if both objects are the same size
        if(this.size != other.size){
            return false;
        }

        //compare each patients from both objects
        int i = 0;
        while(i < this.size){
            if(!this.patients[i].equals(other.patients[i])){
                return false;
            }
            i++;
        }
        return true;
    }


}
