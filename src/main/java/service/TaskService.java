package service;

import datastructures.TaskSorter;
import exceptions.MemberNotFoundException;
import exceptions.TaskNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Member;
import model.Priority;
import model.Task;
import model.TaskStatus;

// Stores all tasks and does the task related work
public class TaskService {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int nextTaskNumber = 1; // used to build IDs: T001, T002, ...

    // Creates a new task with an automatic ID and saves it in the list
    public Task createTask(String title, String description,
                           Priority priority, LocalDate deadline) {
        String taskId = "T" + String.format("%03d", nextTaskNumber); // 1 becomes T001
        nextTaskNumber++;
        Task task = new Task(taskId, title, description, priority, deadline);
        tasks.add(task);
        return task;
    }

    // Prints every task
    public void viewAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks have been created.");
            return;
        }

        System.out.println("\n--- All Tasks ---");
        for (Task task : tasks) {
            task.displayDetails();
            System.out.println("--------------------");
        }
    }

    // Finds a task by ID. Throws our exception if it does not exist.
    public Task searchTask(String id) throws TaskNotFoundException {
        for (Task task : tasks) {
            if (task.getId().equalsIgnoreCase(id)) { // ignore capital / small letters
                return task;
            }
        }
        throw new TaskNotFoundException("Task with ID '" + id + "' was not found.");
    }

    // Gives a task to a member
    public void assignTaskToMember(String taskId, String memberId,
                                   MemberService memberService)
            throws TaskNotFoundException, MemberNotFoundException {
        Task task = searchTask(taskId);
        Member member = memberService.findMember(memberId);
        task.setAssignedMember(member);
    }

    // Changes the status of a task
    public void updateTaskStatus(String taskId, TaskStatus status)
            throws TaskNotFoundException {
        Task task = searchTask(taskId);
        task.setStatus(status);
    }

    // Changes the priority of a task
    public void changeTaskPriority(String taskId, Priority priority)
            throws TaskNotFoundException {
        Task task = searchTask(taskId);
        task.setPriority(priority);
    }

    // Prints all tasks from HIGH to LOW priority
    public void viewTasksSortedByPriority() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks have been created.");
            return;
        }

        TaskSorter sorter = new TaskSorter();
        ArrayList<Task> sortedTasks = sorter.sortByPriority(tasks);

        System.out.println("\n--- Tasks Sorted by Priority ---");
        for (Task task : sortedTasks) {
            task.displayDetails();
            System.out.println("--------------------");
        }
    }
}
