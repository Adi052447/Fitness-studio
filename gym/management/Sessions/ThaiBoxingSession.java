package gym.management.Sessions;
import gym.customers.Instructor;
public class ThaiBoxingSession extends Session {
    public ThaiBoxingSession(String dateTime, ForumType forumType, Instructor instructor) {
        super(SessionType.ThaiBoxing,dateTime,forumType,instructor);
        this.maxParticipants=20;
        this.price=100;
    }
}
