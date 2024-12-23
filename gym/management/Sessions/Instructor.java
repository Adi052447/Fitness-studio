package gym.management.Sessions;

import gym.customers.Person;

import java.util.ArrayList;

public class Instructor extends Person {
    private double hourlyRate;
    private ArrayList<SessionType> qualifications;

    // Constructor
    public Instructor(Person person, double hourlyRate, ArrayList<SessionType> qualifications) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        this.hourlyRate = hourlyRate;
        this.qualifications = new ArrayList<>(qualifications);
    }

    // Check if qualified for a session type
    public boolean isQualifiedFor(SessionType type) {
        return qualifications.contains(type);
    }

    @Override
    public String toString() {
        return "Instructor{" + super.toString() + ", hourlyRate=" + hourlyRate + ", qualifications=" + qualifications + '}';
    }
}


