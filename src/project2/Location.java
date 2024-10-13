/**
 *
 *
 * @author Waleed Khalid, Rehan Baig
 */

package project2;
public class Location {
    private String name;

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
