package gym.Exception;

public class InstructorNotQualifiedException extends Exception {

    // קונסטרקטור ברירת מחדל עם הודעה כללית
    public InstructorNotQualifiedException() {
        super("Error: Instructor is not qualified to conduct this session type.");
    }

    // קונסטרקטור מותאם אישית שמאפשר להעביר הודעה
    public InstructorNotQualifiedException(String message) {
        super(message);
    }

    // מתודה getMessage (לא חובה להגדיר, היא כבר קיימת במחלקת האב)
    @Override
    public String getMessage() {
        return super.getMessage(); // מחזיר את ההודעה שהועברה
    }
}
