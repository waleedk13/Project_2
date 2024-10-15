package project2;

public enum Radiology {
    XRAY,
    ULTRASOUND,
    CATSCAN;

    @Override
    public String toString() {
        switch(this) {
            case XRAY: return "XRAY";
            case ULTRASOUND: return "ULTRASOUND";
            case CATSCAN: return "CATSCAN";
        }
    }
}
