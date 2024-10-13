/**
 *
 *
 * @author Waleed Khalid, Rehan Baig
 */

package project2;
public class Visit {
    private Date visitDate;  // The date of the visit
    private String serviceProvided;  // Type of service
    private Person provider;  // The doctor providing service
    private int cost;  // The cost for the service

    public Visit(Date visitDate, String serviceProvided,
                 Person provider, int cost) {
        this.visitDate = visitDate;
        this.serviceProvided = serviceProvided;
        this.provider = provider;
        this.cost = cost;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public String getServiceProvided() {
        return serviceProvided;
    }

    public Person getProvider() {
        return provider;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Visit on " + visitDate + " for " +
                serviceProvided + " with " + provider + ", Cost: $" + cost;
    }
}
