package gym.management.Sessions;

import gym.customers.Instructor;

public class PilatesSession extends Session {
    public PilatesSession(String dateTime, ForumType forumType, Instructor instructor) {
        super(SessionType.Pilates, dateTime, forumType, instructor);
        this.maxParticipants=30;
        this.price=60;
    }
}
