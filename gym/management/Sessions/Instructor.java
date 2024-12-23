package gym.management.Sessions;

import gym.customers.Person;

import java.util.ArrayList;

public class Instructor extends Person {
    private int hourlyRate;
    private ArrayList<SessionType> qualifications;

    // Constructor
    public Instructor(Person person, int hourlyRate, ArrayList<SessionType> qualifications) {
        super(person.getId(),person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
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
    public int getSalary(){
        return this.hourlyRate;
    }

    public ArrayList<SessionType> getQualifications(){
        return this.qualifications;
    }
}


