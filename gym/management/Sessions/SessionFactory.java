package gym.management.Sessions;

import gym.customers.Instructor;

public class SessionFactory {

    // מתודה ליצירת שיעור חדש בהתבסס על סוג השיעור
    public static Session createSession(SessionType sessionType, String dateTime, ForumType forumType, Instructor instructor) {
        switch (sessionType) {
            case SessionType.Pilates:
                return new PilatesSession(dateTime, forumType, instructor);
            case SessionType.ThaiBoxing:
                return new ThaiBoxingSession(dateTime, forumType, instructor);
            case SessionType.MachinePilates:
                return new MachinePilatesSession(dateTime, forumType, instructor);
            case SessionType.Ninja:
                return new NinjaSession(dateTime, forumType, instructor);
            default:
                throw new IllegalArgumentException("Unknown session type: " + sessionType);
        }
    }
}
