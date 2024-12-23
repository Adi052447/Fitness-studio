package gym.Exception;

public class DuplicateClientException extends Exception {

    // קונסטרקטור ברירת מחדל עם הודעת שגיאה כללית
    public DuplicateClientException() {
        super("Error: The client is already registered");
    }

    // קונסטרקטור מותאם אישית שמאפשר העברת הודעת שגיאה
    public DuplicateClientException(String message) {
        super(message);
    }

    // מתודת getMessage (אם רוצים לשנות או להוסיף משהו להודעה)
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}


