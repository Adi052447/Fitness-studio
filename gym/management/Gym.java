package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.Instructor;
import gym.management.Sessions.Session;

public class Gym {
    private static Gym instance = null;
    private String name;
    private Secretary secretary;

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

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSecretary(Person person, int salary) {
        this.secretary = new Secretary(person, salary);
    }

    // Getters
    public Secretary getSecretary() {
        return secretary;
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
                    .append(" | Balance: ").append(secretary.getBalance())
                    .append(" | Role: Secretary | Salary per Month: ").append(secretary.getSalary()).append("\n");
        }
        sb.append(sbb);
        // Gym Balance
        sb.append("Gym Balance: ").append(secretary.getBalance());
        sb.append("\n\n");


        sb.append("Clients Data:\n");
        for (Client client : secretary.getClients()) {
            sb.append("ID: ").append(client.getId())
                    .append(" | Name: ").append(client.getName())
                    .append(" | Gender: ").append(client.getGender())
                    .append(" | Birthday: ").append(client.getBirthDate())
                    .append(" | Age: ").append(client.getAge())
                    .append(" | Balance: ").append(client.getBalance()).append("\n");
        }
        sb.append("\n");

        // Employees Data
        sb.append("Employees Data:\n");
        for (Instructor employee : secretary.getInstructors()) {
            sb.append("ID: ").append(employee.getId())
                    .append(" | Name: ").append(employee.getName())
                    .append(" | Gender: ").append(employee.getGender())
                    .append(" | Birthday: ").append(employee.getBirthDate())
                    .append(" | Age: ").append(employee.getAge())
                    .append(" | Balance: ").append(employee.getBalance())
                    .append(" | Role: Instructor")
                    .append(" | Salary per Hour: ")
                    .append(employee.getSalary())
                    .append(" | Certified Classes: ").append(employee.getQualifications()).append("\n");
        }
        sb.append(sbb);
        sb.append("\n");

        // Sessions Data
        sb.append("Sessions Data:\n");
        for (Session session : secretary.getSessions()) {
            sb.append("Session Type: ").append(session.getType())
                    .append(" | Date: ").append(session.getDateTime())
                    .append(" | Forum: ").append(session.getForumType())
                    .append(" | Instructor: ").append(session.getInstructor().getName())
                    .append(" | Participants: ").append(session.getParticipants().size())
                    .append("/").append(session.getMaxParticipants()).append("\n");
        }


        return sb.toString();

    }
    //"Gym Name: " + name+"\n" + "Gym Secretary: ID: "+secretary.getId()+" | Name: " + secretary.getName() +" | Gender: "+secretary.getGender()+" | Birthday: "+secretary.getBirthDate()+" | Age: "+secretary.getAge();
}



