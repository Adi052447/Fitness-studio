package gym.Exception;

public class InvalidAgeException extends Exception {

    public InvalidAgeException() {
        super("Error: Client must be at least 18 years old to register");
    }

    // קונסטרקטור מותאם אישית שמאפשר להעברת הודעה ספציפית
    public InvalidAgeException(String message) {
        super(message);
    }

}

