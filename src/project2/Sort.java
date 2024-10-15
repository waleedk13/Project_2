package project2;

public class Sort {

    //

    public static void sortByPatients(List<Appointment> list) {
        if (list.isEmpty()) {
            return;
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
                    int profileCompare = list.get(j).getPatientPerson().getProfile()
                            .compareTo(list.get(j + 1).getPatientPerson().getProfile());
                    int dateCompare = list.get(j).getDate()
                            .compareTo(list.get(j + 1).getDate());
                    int timeslotCompare = list.get(j).getTimeslot()
                            .compareTo(list.get(j + 1).getTimeslot());
                    if (profileCompare > 0 || (profileCompare == 0 &&
                            (dateCompare > 0 || (dateCompare == 0 && timeslotCompare > 0)))) {
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
        return;
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

                    if(locationCompare > 0 || (locationCompare == 0 && list.
                            get(j).getDate().compareTo(list.get(j+1).getDate())
                            >0) || (locationCompare == 0 && list.get(j).
                            getDate().compareTo(list.get(j+1).getDate()) == 0 &&
                            list.get(j).getTimeslot().compareTo(list.get(j+1).
                                    getTimeslot()) > 0)){
                        exchange(j,j+1,list);
                    }
                }
            }
        }
    }



    public static void providerSort(List<Provider> providers){
        int a = providers.size();

        for(int i = 0; i < providers.size() - 1; i++){
            for(int j = 0; j < providers.size() - i - 1; j++){
                if(providers.get(j).getProfile().compareTo(providers.
                        get(j + 1).getProfile()) > 0){
                    Provider tempSwitch = providers.get(j);
                    providers.set(j, providers.get(j+1));
                    providers.set(j+1, tempSwitch);
                }
            }
        }

    }



    private static void exchange(int i, int j, List<Appointment> list) {
        Appointment temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }













    public static void provider(List<Provider> list) {

    }


}


