package gym.customers;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Instructor extends Person {
    private int hourlyRate;
    private ArrayList<SessionType> qualifications;

    // Constructor
    public Instructor(Person person, int hourlyRate, ArrayList<SessionType> qualifications) {
        super(person.getId(),person.getName(), person.getMoneyBalance(), person.getGender(), person.getBirthDate());
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
    public static String convertSessionsToString(ArrayList<SessionType> qual) {
        // שימוש ב-Stream להמרה למחרוזת וצירוף ללא סוגריים
        return qual.stream()
                .map(SessionType::toString) // המרה למחרוזת באמצעות toString
                .collect(Collectors.joining(", ")); // צירוף המחרוזות עם פסיקים
    }
}


