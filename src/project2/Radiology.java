package project2;

public enum Radiology {
    XRAY,
    ULTRASOUND,
    CATSCAN;

    @Override
    public String toString() {
        if (this == XRAY) {
            return "XRAY";
        } else if (this == ULTRASOUND) {
            return "ULTRASOUND";
        } else if (this == CATSCAN) {
            return "CATSCAN";
        } else {
            throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }
}
