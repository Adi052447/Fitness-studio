package gym.Exception;

public class InvalidAgeException extends Exception {

    // קונסטרקטור ברירת מחדל עם הודעת שגיאה כללית
    public InvalidAgeException() {
        super("The provided age is invalid for this action.");
    }

    // קונסטרקטור מותאם אישית שמאפשר להעברת הודעה ספציפית
    public InvalidAgeException(String message) {
        super(message);
    }

    // מתודת getMessage (כבר קיימת ב-Exception, אבל אפשר להוסיף לה לוגיקה מותאמת אם יש צורך)
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

