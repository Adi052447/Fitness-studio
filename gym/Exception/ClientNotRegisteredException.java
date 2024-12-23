package gym.Exception;

public class ClientNotRegisteredException extends Exception {

    // קונסטרקטור ברירת מחדל עם הודעת שגיאה כללית
    public ClientNotRegisteredException() {
        super("Error: The client is not registered with the gym and cannot enroll in lessons");
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


