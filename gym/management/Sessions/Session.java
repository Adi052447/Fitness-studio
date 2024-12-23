package gym.management.Sessions;

import gym.customers.Client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Session {
    private SessionType type;
    private String dateTime;
    private ForumType forumType;
    private Instructor instructor;
    private ArrayList<Client> participants;

    private int maxParticipants;

    private double price;

    // Constructor
    public Session(SessionType type, String dateTime, ForumType forumType, Instructor instructor) {
        this.type = type;
        this.dateTime = dateTime;
        this.forumType = forumType;
        this.instructor = instructor;
        this.participants = new ArrayList<>();
    }

    public Date getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(this.dateTime, formatter);
        return date;
    }

    public ForumType getForumType(){
        return this.forumType;
    }
    // Add participant
    public void addParticipant(Client client) {
        participants.add(client);
    }

    // Getters
    public ArrayList<Client> getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        return "Session{" + "type=" + type + ", dateTime='" + dateTime + "', forumType=" + forumType + ", instructor=" + instructor + '}';
    }

    public int getMaxParticipants() {
        return this.maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


