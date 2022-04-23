package gym.gymbackend.exceptions;

import java.io.Serial;

public class PersonNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public PersonNotFoundException(String username) {
        super("Cannot find user " + username);
    }

    public PersonNotFoundException() {
        super("User not found.");
    }
}
