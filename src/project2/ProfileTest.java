package project2;

import org.junit.Test;
import util.Date;
import static org.junit.Assert.*;

public class ProfileTest {

    // Setup shared Date and Profile objects
    Date dob1 = new Date(3, 15, 1995);  // March 15, 1995
    Date dob2 = new Date(5, 10, 1992);  // May 10, 1992
    Date dob3 = new Date(7, 25, 1995);  // July 25, 1995
    Date dob4 = new Date(3, 15, 1995);  // March 15, 1995
    Date dob5 = new Date(6, 12, 2000);  // June 12, 2000
    Date dob6 = new Date(11, 11, 1998); // November 11, 1998
    Date dob7 = new Date(12, 5, 1988);  // December 5, 1988

    Profile profile1 = new Profile("John", "Doe", dob1);
    // John Doe, March 15, 1995
    Profile profile2 = new Profile("Jane", "Doe", dob2);
    // Jane Doe, May 10, 1992
    Profile profile3 = new Profile("John", "Smith", dob3);
    // John Smith, July 25, 1995
    Profile profile4 = new Profile("John", "Doe", dob4);
    // John Doe, March 15, 1995 (same as profile1)
    Profile profile5 = new Profile("Alice", "Brown", dob5);
    // Alice Brown, June 12, 2000
    Profile profile6 = new Profile("Bob", "Brown", dob6);
    // Bob Brown, November 11, 1998
    Profile profile7 = new Profile("John", "Smith", dob7);
    // John Smith, December 5, 1988

    // Test cases returning -1
    @Test
    public void testCompareTo_JaneDoeVsJohnDoe() {
        assertTrue(profile2.compareTo(profile1) < 0);  //-1
    }

    @Test
    public void testCompareTo_AliceBrownVsBobBrown() {
        assertTrue(profile5.compareTo(profile6) < 0);  //-1
    }

    @Test
    public void testCompareTo_JohnDoeEarlierDOB() {
        // Case 8: John Doe (1992) vs John Doe (1995)
        assertTrue(profile2.compareTo(profile1) < 0);  //-1
    }

    // Test cases returning 1
    @Test
    public void testCompareTo_JohnDoeVsJaneDoe() {
        assertTrue(profile1.compareTo(profile2) > 0);  //1
    }

    @Test
    public void testCompareTo_BobBrownVsAliceBrown() {
        assertTrue(profile6.compareTo(profile5) > 0);  //1
    }

    @Test
    public void testCompareTo_JohnSmith1995VsJohnSmith1988() {
        assertTrue(profile3.compareTo(profile7) > 0);  //1
    }

    // Test case returning 0
    @Test
    public void testCompareTo_EqualProfiles() {
        assertEquals(0, profile1.compareTo(profile4));  //0
    }
}