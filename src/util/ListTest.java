package util;

import org.junit.Before;
import org.junit.Test;
import project2.*;

import static org.junit.Assert.*;

public class ListTest {

    List<Provider> providerList;
    Doctor doctor;
    Technician technician;

    // Initialize before each test
    @Before
    public void setup() {
        providerList = new List<>();

        // Create Doctor and Technician objects for testing
        Profile doctorProfile = new Profile("John",
                "Doe", new Date("3/15/1980"));
        doctor = new Doctor(Specialty.FAMILY, "123456",
                doctorProfile, Location.BRIDGEWATER);

        Profile technicianProfile = new Profile("Jane",
                "Smith", new Date("5/20/1990"));
        technician = new Technician(200, technicianProfile,
                Location.EDISON);
    }

    // Test adding a Doctor object to the list
    @Test
    public void testAddDoctor() {
        providerList.add(doctor);
        assertTrue(providerList.contains(doctor));
        assertEquals(1, providerList.size());
    }

    // Test adding a Technician object to the list
    @Test
    public void testAddTechnician() {
        providerList.add(technician);
        assertTrue(providerList.contains(technician));
        assertEquals(1, providerList.size());
    }

    // Test removing a Doctor object from the list
    @Test
    public void testRemoveDoctor() {
        providerList.add(doctor);
        assertTrue(providerList.remove(doctor));
        assertFalse(providerList.contains(doctor));
        assertEquals(0, providerList.size());
    }

    // Test removing a Technician object from the list
    @Test
    public void testRemoveTechnician() {
        providerList.add(technician);
        assertTrue(providerList.remove(technician));
        assertFalse(providerList.contains(technician));
        assertEquals(0, providerList.size());
    }
}