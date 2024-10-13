/**
 *
 *
 * @author Waleed Khalid, Rehan Baig
 */

package project2;
public class Specialty {
    private String name;
    private int ratePerVisit;

    public Specialty(String name, int ratePerVisit){
        this.name = name;
        this.ratePerVisit = ratePerVisit;
    }

    public String getName(){
        return name;
    }

    public int getRatePerVisit() {
        return ratePerVisit;
    }

    @Override
    public String toString() {
        return name + " (Rate per Visit: $" + ratePerVisit + ")";
    }


}
