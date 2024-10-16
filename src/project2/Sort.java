package project2;

public class Sort {


    public static void appointment(List<Appointment> list, char key) {
        if (list.size() == 0) {
            throw new IllegalStateException("Scheduler calendar is empty");

        }

        switch(key){
            case 'A':
                sortByAppointment(list);
                break;
            case 'L':
                sortByLocation(list);
                break;
            case 'P':
                sortByPatients(list);
                break;
            default:
                throw new IllegalStateException("Scheduler class is empty");
        }
    }




    public static boolean sortByPatients(List<Appointment> list) {
        if (list.isEmpty()) {
            return false;
        }
        int n = list.size();
        Appointment temp;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) == null) {
                    exchange(j, j + 1, list);
                } else if (list.get(j + 1) != null) {
                    int profileCompare = list.get(j).getPatientPerson().
                            getProfile().compareTo(list.get(j + 1)
                                    .getPatientPerson().getProfile());
                    int dateCompare = list.get(j).getDate()
                            .compareTo(list.get(j + 1).getDate());
                    int timeslotCompare = list.get(j).getTimeslot()
                            .compareTo(list.get(j + 1).getTimeslot());
                    if (profileCompare > 0 || (profileCompare == 0 &&
                            (dateCompare > 0 || (dateCompare == 0 &&
                                    timeslotCompare > 0)))) {
                        exchange(j, j + 1, list);
                        swapped = true;
                    }
                }
            }
            // If no elements were swapped, the list is already sorted
            if (!swapped) {
                break;
            }
        }
        return true;
    }




    public static void sortByAppointment(List<Appointment> list){
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if(list.get(j) == null){
                    exchange(j, j+1, list);
                }else if(list.get(j+1) != null && list.get(j).
                        compareTo(list.get(j+1)) > 0){
                    exchange(j, j+1,list);
                }
            }
        }
    }

    public static void sortByLocation(List<Appointment> list){
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if(list.get(j) == null){
                    exchange(j, j+1, list);
                }else if(list.get(j+1) != null &&
                        list.get(j).getProviderPerson() instanceof Provider &&
                        list.get(j+1).getProviderPerson() instanceof Provider){
                    Provider provider1 = (Provider) list.get(j).
                            getProviderPerson();
                    Provider provider2 = (Provider) list.get(j+1).
                            getProviderPerson();
                    int locationCompare = provider1.getLocation().getCounty().
                            compareTo(provider2.getLocation().getCounty());
                    int dateCompare = list.get(j).getDate().compareTo(list.
                            get(j+1).getDate());
                    int timeslotCompare = list.get(j).getTimeslot().
                            compareTo(list.get(j+1).getTimeslot());


                    boolean isLocationGreater = locationCompare > 0;
                    boolean isDateGreater = locationCompare == 0 &&
                            dateCompare > 0;
                    boolean isTimeslotGreater = locationCompare == 0 &&
                            dateCompare == 0 && timeslotCompare > 0;

                    if (isLocationGreater || isDateGreater || isTimeslotGreater) {
                        exchange(j, j + 1, list);
                    }
                }
            }
        }
    }

    public static void sortProvider(List<Provider> lists){
        int a = lists.size();


        for(int i = 0; i < lists.size() - 1; i++){
            for(int j = 0; j < lists.size() - i - 1; j++){
                if(lists.get(j).getProfile().compareTo(lists.
                        get(j + 1).getProfile()) > 0){
                    Provider tempSwitch = lists.get(j);
                    lists.set(j, lists.get(j+1));
                    lists.set(j+1, tempSwitch);
                }
            }
        }
    }


    private static void exchange(int i, int j, List<Appointment> list) {
        Appointment temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    //move to clinical manager
    public void printByPatient(List<Appointment> list){
        if(list.size() == 0){
            System.out.println("The schedule calendar is empty.");
        }
        sortByPatients(list);
        boolean NULL = true;
        int i = 0;
        while(i < list.size()){
            if(list.get(i) != null){
                NULL = false;
            }
        }
        if(NULL){
            System.out.println("The scheduler class is empty");
            return;
        }
        int j = 0;
        while(j < list.size()){
            if(list.get(i) == null){
                continue;
            }
            System.out.println(list.get(j));
        }
    }
}


