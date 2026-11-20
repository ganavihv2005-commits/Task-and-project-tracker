# Task and Project Tracker

A console-based Java application for managing tasks and team members.

## Features

- Add and view team members
- Create tasks with priority and deadline
- Assign tasks to team members
- Update task status and priority
- View all tasks or search by task ID
- View tasks sorted by priority using Bubble Sort

## Technologies

- Java
- Java Collections Framework (`ArrayList`)
- `java.time.LocalDate`
- Java `Scanner`

## OOP Concepts

- Encapsulation: private fields with public getters and setters
- Inheritance: `Task` and `Project` extend `TrackableItem`
- Abstraction: `TrackableItem` is abstract and defines `displayDetails()`
- Polymorphism: a `TrackableItem` reference calls the overridden `displayDetails()` of `Task` or `Project`
- Custom exceptions: `TaskNotFoundException`, `MemberNotFoundException`

## DSA Algorithm

`datastructures.TaskSorter` implements Bubble Sort manually. It compares priority numbers with two nested loops and arranges tasks from HIGH to LOW. It does not use Java's built-in sorting.

## Folder Structure

```text
TaskAndProjectTracker/
├── docs/
│   └── COMMIT_GUIDE.md
├── src/
│   └── main/
│       └── java/
│           ├── app/            (Main.java)
│           ├── datastructures/ (TaskSorter.java)
│           ├── exceptions/     (MemberNotFoundException.java, TaskNotFoundException.java)
│           ├── model/          (Member, Priority, Project, Task, TaskStatus, TrackableItem)
│           ├── service/        (MemberService.java, TaskService.java)
│           └── util/           (InputHelper.java)
├── .gitignore
└── README.md
```

`.class` files are created automatically (in an `out` or `bin` folder) when the program is compiled or run. They are not part of the project and are ignored by Git.

## Run in VS Code

1. Install the **Extension Pack for Java** and a JDK (version 11 or higher).
2. Open the `TaskAndProjectTracker` folder (File > Open Folder).
3. Open `src/main/java/app/Main.java` and click **Run** above `public static void main`.

## Run from the terminal

From the `TaskAndProjectTracker` folder:

```bash
javac -d out -sourcepath src/main/java src/main/java/app/Main.java
java -cp out app.Main
```
