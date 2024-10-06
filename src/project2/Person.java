package project2;

public class Person {

    public class Timeslot implements Comparable<Timeslot> {
        private int hour;
        private int minute;


        //constructor class
        public Timeslot(int hour, int minute){
            this.hour = hour;
            this.minute = minute;
        }

        //default constructor
        public Timeslot(){
            this.hour = 0;
            this.minute = 0;
        }
    }
}
