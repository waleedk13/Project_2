package util;

import org.junit.Test;
import util.Date;

import static org.junit.Assert.*;
public class DateTest {

    @Test
    public void testDaysInFeb_NonLeap(){
        //1: false
        Date date = new Date("2/29/2011");
        assertFalse(date.isValid());
    }
    @Test
    public void invalidMonth(){
        //2: false
        Date date = new Date("13/21/1999");
        assertFalse(date.isValid());
    }

    @Test
    public void testInvalidDayInApril(){
        //4:false
        Date date = new Date("4/31/2004");
        assertFalse(date.isValid());
    }

    @Test
    public void testInvalidDayInJune(){
        //4:false
        Date date = new Date("6/31/2023");
        assertFalse(date.isValid());
    }

    @Test
    public void testValidDayInFeb(){
        //1: true
        Date date = new Date("2/28/2011");
        assertTrue(date.isValid());
    }



    @Test
    public void testValidDayInFeb_LeapYear(){
        //2: true
        Date date = new Date("2/29/2020");
        assertTrue(date.isValid());
    }






}


//regular test case
            /*Date date = new Date("2/29/2011");
            boolean expectedOutput = false;
            boolean actualOutput = date.isValid();
            System.out.print("*Test case #1: # of days in Feb. in a non-leap" +
                    " year is 28");
            testResult(date,expectedOutput,actualOutput);*/