package exceptions;

// Our own exception. We throw it when a member ID does not exist.
public class MemberNotFoundException extends Exception {
    public MemberNotFoundException(String message) {
        super(message); // pass the error message to the parent Exception class
    }
}
