package gym.Exception;

public class InstructorNotQualifiedException extends Exception {

    // קונסטרקטור ברירת מחדל עם הודעה כללית
    public InstructorNotQualifiedException() {
        super("The instructor is not qualified to lead this session.");
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
