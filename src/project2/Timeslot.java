package project2;

public class Timeslot {

    public class Person implements Comparable<Person>{
        private Profile profile;


        /**
         * **FILL THIS IN**
         *
         * @param profile **FILL THIS IN**
         *
         */
        public Person(Profile profile){
            this.profile = profile;
        }


        //default constructor
        public Person(){
            this.profile = null;
        }




        @Override
        public int compareTo(Person o) {
            return 0;
        }
    }
}
