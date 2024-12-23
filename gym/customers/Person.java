package gym.customers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {
    private String name;
    private int balance;
    private Gender gender;
    private String birthDate;
    private final int id;

    private int age;
    private static int idcount=1111;
    // Constructor
    public Person(String name, int balance, Gender gender, String birthDate) {
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthDate = birthDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
         LocalDate date = LocalDate.parse(birthDate, formatter);
        this.age= Period.between(date, LocalDate.now()).getYears();
        this.id=idcount++;
    }
    protected Person(int id, String name, int balance, Gender gender, String birthDate) {
        this.id = id; // שמירת ID קיים
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthDate = birthDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(birthDate, formatter);
        this.age= Period.between(date, LocalDate.now()).getYears();
    }
    // Getters
    public String getName() {
        return name;
    }

    public int getBalance() {
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

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', balance=" + balance + ", gender=" + gender + ", birthDate='" + birthDate + "'}";
    }
    public int getAge(){
        return this.age;
    }
    public int getId(){
        return this.id;
    }
}


