package gym.management.Sessions;

public class NinjaSession extends Session {
    private int maxParticipate = 2;

    public NinjaSession(String dateTime, ForumType forumType, Instructor instructor) {
        super(SessionType.Ninja, dateTime, forumType, instructor);
        this.maxParticipate=5;
        this.price=150;
    }

}