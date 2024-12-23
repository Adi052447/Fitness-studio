package gym.customers;

public class Person {
    private String name;
    private double balance;
    private Gender gender;
    private String birthDate;
    private String id;
    // Constructor
    public Person(String name, double balance, Gender gender, String birthDate) {
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthDate = birthDate;

    }

    // Getters
    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public Gender getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', balance=" + balance + ", gender=" + gender + ", birthDate='" + birthDate + "'}";
    }
}


