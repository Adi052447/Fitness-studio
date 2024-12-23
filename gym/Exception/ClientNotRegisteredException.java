package gym.Exception;

public class ClientNotRegisteredException extends Exception {

    // קונסטרקטור ברירת מחדל עם הודעת שגיאה כללית
    public ClientNotRegisteredException() {
        super("The client is not registered in the system and cannot perform this action.");
    }

    // קונסטרקטור מותאם אישית שמאפשר העברת הודעת שגיאה
    public ClientNotRegisteredException(String message) {
        super(message);
    }

    // מתודת getMessage (אם רוצים לשנות או להוסיף לוגיקה ייחודית)
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}


