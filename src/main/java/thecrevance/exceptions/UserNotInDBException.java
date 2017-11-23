package thecrevance.exceptions;

/**
 * Created by Greg on 10/31/17.
 */
public class UserNotInDBException extends Exception {
    public UserNotInDBException(String message) {
        super(message);
    }
}
