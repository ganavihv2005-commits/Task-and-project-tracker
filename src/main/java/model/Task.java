package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Task is a child of TrackableItem (Inheritance), so it already has id, title, description
public class Task extends TrackableItem {
    private Priority priority;
    private LocalDate deadline;
    private TaskStatus status;
    private Member assignedMember;

    // Constructor: a new task starts as TODO and with nobody assigned
    public Task(String id, String title, String description,
                Priority priority, LocalDate deadline) {
        super(id, title, description); // send id, title, description to the parent class
        this.priority = priority;
        this.deadline = deadline;
        this.status = TaskStatus.TODO;
        this.assignedMember = null;
    }

    public Priority getPriority() {
        return priority;
    }

    // Setters: used when the user changes these values later
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setAssignedMember(Member assignedMember) {
        this.assignedMember = assignedMember;
    }

    // Task's own version of the abstract method (Polymorphism)
    @Override
    public void displayDetails() {
        // Show the date as day-month-year, for example 31-12-2026
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // If no member is assigned show "Unassigned", otherwise show the member's name
        String memberName = "Unassigned";
        if (assignedMember != null) {
            memberName = assignedMember.getName();
        }

        System.out.println("Task ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Description: " + getDescription());
        System.out.println("Assigned Member: " + memberName);
        System.out.println("Priority: " + priority);
        System.out.println("Deadline: " + deadline.format(formatter));
        System.out.println("Status: " + status);
    }
}
