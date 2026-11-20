package exceptions;

// Our own exception. We throw it when a task ID does not exist.
public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
