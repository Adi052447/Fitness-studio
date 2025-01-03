package gym.management;
import gym.customers.*;
import gym.management.Sessions.Session;

import java.util.ArrayList;

public class Gym {
    private static Gym instance = null;
    private String name;
    private int balance = 0;
    private Secretary secretary;

    private ArrayList<Client> clients = new ArrayList<>();
    private ArrayList<Instructor> instructors = new ArrayList<>();
    private ArrayList<Session> sessions = new ArrayList<>();

    private ArrayList<String> actions = new ArrayList<>();


    // Private constructor for Singleton
    private Gym() {
    }

    // Get Gym instance
    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }

    public ArrayList<String> getActions() {
        return this.actions;
    }


    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSecretary(Person person, int salary) {
        if (secretary != null) {
            secretary.deactivate();
        }


        // יצירת מזכירה חדשה אם היא לא לקוח או מדריך
        this.secretary = Secretary.createSecretary(person, salary);
    }


    // Getters
    public Secretary getSecretary() {
        return this.secretary;
    }

    public ArrayList<Instructor> getInstructors() {
        return this.instructors;
    }

    public ArrayList<Session> getSessions() {
        return this.sessions;
    }

    public ArrayList<Client> getClients() {
        return this.clients;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Gym Name
        sb.append("Gym Name: ").append(this.name).append("\n");
        StringBuilder sbb = new StringBuilder();

        // Gym Secretary
        Secretary secretary = this.getSecretary();
        if (secretary != null) {
            sb.append("Gym Secretary: ");
            sbb.append("ID: ").append(secretary.getId())
                    .append(" | Name: ").append(secretary.getName())
                    .append(" | Gender: ").append(secretary.getGender())
                    .append(" | Birthday: ").append(secretary.getBirthDate())
                    .append(" | Age: ").append(secretary.getAge())
                    .append(" | Balance: ").append(secretary.getMoneyBalance())
                    .append(" | Role: Secretary | Salary per Month: ").append(secretary.getSalary()).append("\n");
        }
        sb.append(sbb);
        // Gym Balance
        sb.append("Gym Balance: ").append(this.balance);
        sb.append("\n\n");


        sb.append("Clients Data:\n");

        for (Client client : this.clients) {
            sb.append("ID: ").append(client.getId())
                    .append(" | Name: ").append(client.getName())
                    .append(" | Gender: ").append(client.getGender())
                    .append(" | Birthday: ").append(client.getBirthDate())
                    .append(" | Age: ").append(client.getAge())
                    .append(" | Balance: ").append(client.getMoneyBalance()).append("\n");
        }
        sb.append("\n");

        // Employees Data
        sb.append("Employees Data:\n");
        for (Instructor employee : this.instructors) {
            sb.append("ID: ").append(employee.getId())
                    .append(" | Name: ").append(employee.getName())
                    .append(" | Gender: ").append(employee.getGender())
                    .append(" | Birthday: ").append(employee.getBirthDate())
                    .append(" | Age: ").append(employee.getAge())
                    .append(" | Balance: ").append(employee.getMoneyBalance())
                    .append(" | Role: Instructor")
                    .append(" | Salary per Hour: ")
                    .append(employee.getSalary())
                    .append(" | Certified Classes: ").append(employee.convertSessionsToString(employee.getQualifications())).append("\n");
        }
        sb.append(sbb);
        sb.append("\n");

        // Sessions Data
        sb.append("Sessions Data:");
        for (Session session : this.sessions) {
            sb.append("\n").append("Session Type: ").append(session.getType())
                    .append(" | Date: ").append(changeFormat(session.getDate()))
                    .append(" | Forum: ").append(session.getForumType())
                    .append(" | Instructor: ").append(session.getInstructor().getName())
                    .append(" | Participants: ").append(session.getParticipants().size())
                    .append("/").append(session.getMaxParticipants());
        }


        return sb.toString();

    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String changeFormat(String inputDateTime) {
        String formattedDateTime = inputDateTime.replace("T", " ");
        return formattedDateTime;
    }
}



