/**
 *
 *
 * @author Waleed Khalid, Rehan Baig
 */

package project2;

public abstract class Provider extends Person{

    private Location location;

    public Provider(Profile profile, Location location) {
        super(profile);
        this.location = location;
    }

    public Location getLocation(){
        return location;
    }
    public abstract int rate();

}
