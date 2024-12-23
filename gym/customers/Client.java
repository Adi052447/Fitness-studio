package gym.customers;


import gym.management.Sessions.Session;

import java.util.ArrayList;

public class Client extends Person {
    private ArrayList<Session> sessions;
    private ArrayList<String> notifications;

    // Constructor
    public Client(Person person) {
        super(person.getId(), person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        this.sessions = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    // Add session
    public void addSession(Session session) {
        this.sessions.add(session);
    }

    // Add notification
    public void addNotification(String message) {
        this.notifications.add(message);
    }

    // Getters
    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    @Override
    public String toString() {
        return "Client{" + super.toString() + ", sessions=" + sessions.size() + ", notifications=" + notifications + '}';
    }
}


