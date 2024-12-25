package gym.Exception;

public class DuplicateClientException extends Exception {

    public DuplicateClientException() {
        super("Error: The client is already registered");
    }

    public DuplicateClientException(String message) {
        super(message);
    }


}


