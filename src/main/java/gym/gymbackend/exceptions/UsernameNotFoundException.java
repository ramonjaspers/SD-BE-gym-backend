package gym.gymbackend.exceptions;

/**
 * User friendly exception
 */
public class UsernameNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UsernameNotFoundException(String username) {
        super("Cannot find user " + username);
    }

}
