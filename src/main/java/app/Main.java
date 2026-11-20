package app;

import exceptions.MemberNotFoundException;
import exceptions.TaskNotFoundException;
import java.time.LocalDate;
import model.Priority;
import model.Project;
import model.Task;
import model.TaskStatus;
import model.TrackableItem;
import service.MemberService;
import service.TaskService;
import util.InputHelper;

// Starting point of the program. Shows the menu and calls the right method.
public class Main {

    // The two service objects are shared by all methods below
    private static TaskService taskService = new TaskService();
    private static MemberService memberService = new MemberService();
    private static Project project = new Project(
            "P001", "Task and Project Tracker", "In-memory task tracking project");

    public static void main(String[] args) {
        boolean running = true;

        // Keep showing the menu until the user chooses 10
        while (running) {
            displayMenu();
            int choice = InputHelper.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addTeamMember();
                    break;
                case 2:
                    memberService.viewAllMembers();
                    break;
                case 3:
                    createTask();
                    break;
                case 4:
                    assignTask();
                    break;
                case 5:
                    taskService.viewAllTasks();
                    break;
                case 6:
                    searchTask();
                    break;
                case 7:
                    updateStatus();
                    break;
                case 8:
                    changePriority();
                    break;
                case 9:
                    taskService.viewTasksSortedByPriority();
                    break;
                case 10:
                    running = false; // stops the while loop
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 10.");
            }
        }

        // Polymorphism: a TrackableItem variable holds a Project,
        // and Java calls the Project's version of displayDetails()
        TrackableItem projectReference = project;
        System.out.println("\nProject summary:");
        projectReference.displayDetails();
        System.out.println("Goodbye!");
    }

    // Prints the menu
    private static void displayMenu() {
        System.out.println("\nTask and Project Tracker");
        System.out.println("1. Add Team Member");
        System.out.println("2. View Team Members");
        System.out.println("3. Create Task");
        System.out.println("4. Assign Task to Member");
        System.out.println("5. View All Tasks");
        System.out.println("6. Search Task by ID");
        System.out.println("7. Update Task Status");
        System.out.println("8. Change Task Priority");
        System.out.println("9. View Tasks Sorted by Priority");
        System.out.println("10. Exit");
        System.out.println("========================================");
    }

    // Menu option 1
    private static void addTeamMember() {
        String id = InputHelper.readNonEmptyString("Enter member ID: ");
        String name = InputHelper.readNonEmptyString("Enter member name: ");

        if (memberService.addMember(id, name)) {
            System.out.println("Team member added successfully.");
        } else {
            System.out.println("A member with that ID already exists.");
        }
    }

    // Menu option 3
    private static void createTask() {
        String title = InputHelper.readNonEmptyString("Enter task title: ");
        String description = InputHelper.readOptionalString("Enter task description: ");
        Priority priority = readPriority();
        LocalDate deadline = InputHelper.readDate("Enter deadline (dd-MM-yyyy): ");

        Task task = taskService.createTask(title, description, priority, deadline);
        project.addTask(task); // also add the task to the project

        // Polymorphism: a TrackableItem variable holds a Task,
        // and Java calls the Task's version of displayDetails()
        TrackableItem taskReference = task;
        System.out.println("\nTask created successfully.");
        taskReference.displayDetails();
    }

    // Menu option 4
    private static void assignTask() {
        String taskId = InputHelper.readNonEmptyString("Enter task ID: ");
        String memberId = InputHelper.readNonEmptyString("Enter member ID: ");

        try {
            taskService.assignTaskToMember(taskId, memberId, memberService);
            System.out.println("Task assigned successfully.");
        } catch (TaskNotFoundException | MemberNotFoundException e) {
            // either exception lands here; we print its message instead of crashing
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Menu option 6
    private static void searchTask() {
        String taskId = InputHelper.readNonEmptyString("Enter task ID: ");

        try {
            Task task = taskService.searchTask(taskId);
            task.displayDetails();
        } catch (TaskNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Menu option 7
    private static void updateStatus() {
        String taskId = InputHelper.readNonEmptyString("Enter task ID: ");
        TaskStatus status = readStatus();

        try {
            taskService.updateTaskStatus(taskId, status);
            System.out.println("Task status updated successfully.");
        } catch (TaskNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Menu option 8
    private static void changePriority() {
        String taskId = InputHelper.readNonEmptyString("Enter task ID: ");
        Priority priority = readPriority();

        try {
            taskService.changeTaskPriority(taskId, priority);
            System.out.println("Task priority updated successfully.");
        } catch (TaskNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Asks the user to pick a priority. Repeats until the choice is 1, 2 or 3.
    private static Priority readPriority() {
        while (true) {
            System.out.println("Select priority:");
            System.out.println("1. HIGH");
            System.out.println("2. MEDIUM");
            System.out.println("3. LOW");
            int choice = InputHelper.readInt("Enter priority choice: ");

            switch (choice) {
                case 1:
                    return Priority.HIGH;
                case 2:
                    return Priority.MEDIUM;
                case 3:
                    return Priority.LOW;
                default:
                    System.out.println("Invalid priority. Please choose 1, 2, or 3.");
            }
        }
    }

    // Asks the user to pick a status. Repeats until the choice is 1, 2 or 3.
    private static TaskStatus readStatus() {
        while (true) {
            System.out.println("Select status:");
            System.out.println("1. TODO");
            System.out.println("2. IN_PROGRESS");
            System.out.println("3. COMPLETED");
            int choice = InputHelper.readInt("Enter status choice: ");

            switch (choice) {
                case 1:
                    return TaskStatus.TODO;
                case 2:
                    return TaskStatus.IN_PROGRESS;
                case 3:
                    return TaskStatus.COMPLETED;
                default:
                    System.out.println("Invalid status. Please choose 1, 2, or 3.");
            }
        }
    }
}
