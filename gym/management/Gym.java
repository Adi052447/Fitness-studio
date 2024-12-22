package gym.management;

import gym.customers.Person;

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

    public void setSecretary(Person person, double salary) {
        this.secretary = new Secretary(person, salary);
    }

    // Getters
    public Secretary getSecretary() {
        return secretary;
    }

    @Override
    public String toString() {
        return "Gym{name='" + name + "', secretary=" + secretary + '}';
    }
}



