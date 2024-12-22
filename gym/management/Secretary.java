package gym.management;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.customers.Client;
import gym.customers.Gender;
import gym.customers.Person;
import gym.management.Sessions.*

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Secretary extends Person {
    private double salary;
    private ArrayList<Client> clients;
    private ArrayList<Instructor> instructors;
    private ArrayList<Session> sessions;
    private ArrayList<String> actions;

    // Constructor
    public Secretary(Person person, double salary) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        this.salary = salary;
        this.clients = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.actions = new ArrayList<>();
    }

    // Register a client
    public Client registerClient(Person person) throws DuplicateClientException {
        for (Client client : clients) {
            if (client.getName().equals(person.getName())) {
                throw new DuplicateClientException("Client already registered: " + person.getName());
            }
        }
        Client newClient = new Client(person);
        clients.add(newClient);
        actions.add("Registered client: " + person.getName());
        return newClient;
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!clients.contains(client)) {
            throw new ClientNotRegisteredException("This client is not registered.");
        }
        clients.remove(client);
        actions.add("Unregistered client: " + client.getName());
    }
    // Other methods (similar structure): , hireInstructor, addSession, etc.

    // Print actions history
    public void printActions() {
        for (String action : actions) {
            System.out.println(action);
        }
    }

    @Override
    public String toString() {
        return "Secretary{" + super.toString() + ", salary=" + salary + '}';
    }

    public Session addSession(SessionType type, String dateTime, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        // בדיקה אם המדריך מוסמך להעביר את סוג השיעור
        if (!instructor.isQualifiedFor(type)) {
            throw new InstructorNotQualifiedException("Instructor " + instructor.getName() + " is not qualified for " + type + " sessions.");
        }

        // יצירת שיעור חדש
        Session newSession = SessionFactory.createSession(type, dateTime, forumType, instructor);

        // הוספת השיעור לרשימת השיעורים
        sessions.add(newSession);

        // עדכון היסטוריית הפעולות
        actions.add("Added new session: " + type + " on " + dateTime + " with instructor " + instructor.getName());

        // החזרת השיעור שנוסף
        return newSession;
    }

    public void paySalaries() {

    }

    public void registerClientToLesson(Client client, Session session) throws Exception {
        // א. מוודאים שמועד השיעור טרם חלף
        if (new Date().after(session.getDateTime())) {
            throw new Exception("Session is not in the future");
        }

        // ב. בודקים אם פורום השיעור תואם את פרטי הלקוח
        if (session.getForumType() == ForumType.Female || session.getForumType() == ForumType.Male) {
            if (!isForumCompatible(client, session.getForumType())) {
                throw new Exception("Client's gender doesn't match the session's gender requirements");
            }
        }
        if (session.getForumType() == ForumType.Seniors) {
            if (!isForumCompatible(client, session.getForumType())) {
                throw new Exception("Client doesn't meet the age requirements for this session (Seniors)");
            }
        }
        // ג. מוודאים שנותרו מקומות פנויים לשיעור
        if (session.getParticipants().size() >= session.getMaxParticipants()) {
            throw new Exception("No spots available for this session.");
        }

        // ד. בודקים אם ללקוח יש יתרת כסף מספקת
        if (client.getBalance() < session.getCost()) {
            throw new Exception("The client does not have enough balance to pay for this session.");
        }

        // אם הכל בסדר, מוסיפים את הלקוח לשיעור
        session.addParticipant(client);
        client.setBalance(client.getBalance() - session.getCost());
        actions.add("Client " + client.getName() + " registered to session: " + session.getSessionType());
    }

    private int calculateAge(Date birthDate) {
        Calendar today = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
            age--; // אם היום הנוכחי הוא לפני יום ההולדת השנה
        }

        return age;
    }

    private boolean isForumCompatible(Client client, ForumType forumType) {
        switch (forumType) {
            case forumType.Male:
                return client.getGender().equals(Gender.Male);
            case forumType.Female:
                return client.getGender().equals(Gender.Female);
            case forumType.Seniors:
                int clientAge = calculateAge(client.getBirthDate());
                return clientAge >= 65;
            case forumType.All:
                return true; // כולם יכולים להשתתף
            default:
                return false; // פורום לא מוכר
        }
    }

    public Instructor hireInstructor(Person p6, int i, ArrayList<SessionType> sessionTypes) {
    }
}

