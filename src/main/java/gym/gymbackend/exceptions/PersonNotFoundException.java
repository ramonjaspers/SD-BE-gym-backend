package gym.gymbackend.exceptions;

/**
 * User friendly exception
 */
public class PersonNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PersonNotFoundException(String username) {
        super("Cannot find user " + username);
    }

    public PersonNotFoundException() {
        super("User not found.");
    }
}
