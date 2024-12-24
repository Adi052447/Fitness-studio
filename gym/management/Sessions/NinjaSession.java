package gym.management.Sessions;

import gym.customers.Instructor;

public class NinjaSession extends Session {

    public NinjaSession(String dateTime, ForumType forumType, Instructor instructor) {
        super(SessionType.Ninja, dateTime, forumType, instructor);
        this.maxParticipants=5;
        this.price=150;
    }

}
