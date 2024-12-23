package gym.management.Sessions;

public class ThaiBoxingSession extends Session {


    public ThaiBoxingSession(String dateTime, ForumType forumType, Instructor instructor) {
        super(SessionType.ThaiBoxing,dateTime,forumType,instructor);
        this.maxParticipants=20;
        this.price=100;
    }

}
