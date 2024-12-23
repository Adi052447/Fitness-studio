package gym.management.Sessions;

public class MachinePilatesSession extends Session {
    public MachinePilatesSession(String dateTime, ForumType forumType, Instructor instructor) {
        super(SessionType.MachinePilates,dateTime,forumType,instructor);
        this.maxParticipants=10;
        this.price=80;
    }
}