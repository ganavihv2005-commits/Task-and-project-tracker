package model;

// Abstract parent class. We can never create a TrackableItem directly,
// we can only create its children (Task and Project).
public abstract class TrackableItem {
    // private = can only be used inside this class (Encapsulation)
    private String id;
    private String title;
    private String description;

    // Constructor: sets the values when a child object is created
    public TrackableItem(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Getters: the only way other classes can read the private values
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    // Abstract method: has no body here. Every child class MUST write its own version.
    public abstract void displayDetails();
}
