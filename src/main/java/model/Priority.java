package model;

// Enum = a fixed set of allowed values. A task can only be HIGH, MEDIUM or LOW.
public enum Priority {
    HIGH(3),    // HIGH has the number 3
    MEDIUM(2),  // MEDIUM has the number 2
    LOW(1);     // LOW has the number 1

    private final int value; // the number stored for each priority

    // Constructor: runs once for each constant above and stores its number
    Priority(int value) {
        this.value = value;
    }

    // Returns the number. The sorting code uses it to compare two priorities.
    public int getValue() {
        return value;
    }
}
