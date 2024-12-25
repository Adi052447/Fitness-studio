package gym.Exception;

public class InstructorNotQualifiedException extends Exception {

    // קונסטרקטור ברירת מחדל עם הודעה כללית
    public InstructorNotQualifiedException() {
        super("Error: Instructor is not qualified to conduct this session type.");
    }


    public InstructorNotQualifiedException(String message) {
        super(message);
    }

    }