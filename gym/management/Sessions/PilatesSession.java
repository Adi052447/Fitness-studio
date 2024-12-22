package gym.management.Sessions;

public class PilatesSession extends Session {
    public PilatesSession(String dateTime, ForumType forumType, Instructor instructor) {
        super(SessionType.Pilates, dateTime, forumType, instructor);
    }
}
