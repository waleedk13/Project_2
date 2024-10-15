package project2;

public class Node {
    private Technician technician;
    Node next;

    public Node(Technician technician, Node next) {
        this.technician = technician;
        this.next = next;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
