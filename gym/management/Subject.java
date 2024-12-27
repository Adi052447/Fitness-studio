package gym.management;

import gym.customers.Observer;
import gym.management.Sessions.Session;

public interface Subject {
    void attach(Observer observer); // הוספת observer
    void detach(Observer observer); // הסרת observer
    void notify(String message); // שליחת עדכון ל-observers
    void notify(Session session, String message);
    void notify(String date, String message);
}