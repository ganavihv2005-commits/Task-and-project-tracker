package model;

import java.util.ArrayList;

// Project is also a child of TrackableItem. It keeps a list of its tasks.
public class Project extends TrackableItem {
    private ArrayList<Task> tasks;

    public Project(String id, String title, String description) {
        super(id, title, description);
        this.tasks = new ArrayList<>(); // start with an empty list
    }

    // Adds one task to this project
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Project's own version of the abstract method (Polymorphism)
    @Override
    public void displayDetails() {
        System.out.println("Project ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Description: " + getDescription());
        System.out.println("Task Count: " + tasks.size());
    }
}
