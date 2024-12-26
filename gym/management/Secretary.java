package gym.management;

import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static gym.management.Sessions.ForumType.*;

public class Secretary extends Person {
    private int salary;
    private Gym gym;
    private boolean isActive = true;

    // Constructor
    private Secretary(Person person, int salary) {
        super(person.getId(), person.getName(), person.getMoneyBalance(), person.getGender(), person.getBirthDate());
        this.salary = salary;
        this.gym = Gym.getInstance();
        gym.getActions().add("A new secretary has started working at the gym: " + person.getName());
    }
     static Secretary createSecretary(Person person, int salary) {
        return new Secretary( person, salary);
    }
    // Register a client
    public Client registerClient(Person person) throws DuplicateClientException, InvalidAgeException {
        ArrayList<Client> clients = gym.getClients();
        for (Client client : clients) {
            if (person.getId() == client.getId()) {
                throw new DuplicateClientException();
            }
        }
        if (person.getAge() < 18) {
            throw new InvalidAgeException();
        }
        Client newClient = new Client(person);
        gym.getClients().add(newClient);
        gym.getActions().add("Registered new client: " + newClient.getName());
        return newClient;
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!gym.getClients().contains(client)) {
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }
        gym.getClients().remove(client);
        gym.getActions().add("Unregistered client: " + client.getName());
    }

    // Print actions history
    public void printActions() {
        ensureActive();
        ArrayList<String> actions = gym.getActions();
        for (String action : actions) {
            System.out.println(action);
        }
    }
    public Session addSession(SessionType type, String dateTime, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {

        ensureActive();
        // בדיקה אם המדריך מוסמך להעביר את סוג השיעור
        if (!instructor.isQualifiedFor(type)) {
            throw new InstructorNotQualifiedException();
        }

        // יצירת שיעור חדש
        Session newSession = SessionFactory.createSession(type, dateTime, forumType, instructor);
        // הוספת השיעור לרשימת השיעורים
        gym.getSessions().add(newSession);
        //                           עדכון היסטוריית הפעולות

        // המרה למבנה LocalDateTime
        DateTimeFormatter sourceFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTime, sourceFormatter);
        gym.getActions().add("Created new session: " + type + " on " + dateTime1 + " with instructor: " + instructor.getName());
        // החזרת השיעור שנוסף
        return newSession;
    }

    public void paySalaries() {
        ensureActive();
        int totalSalaries = 0;
        for (Instructor instructor : gym.getInstructors()) {
            int numSessions = 0;
            for (Session session : gym.getSessions()) {
                if (session.getInstructor().equals(instructor)) {
                    numSessions++;
                }
            }
            int salary = numSessions * instructor.getSalary();
            totalSalaries += salary;
            instructor.setMoneyBalance(instructor.getMoneyBalance() + salary);
        }

        totalSalaries += this.salary;
        gym.getSecretary().setMoneyBalance(this.getMoneyBalance() + this.salary);
        gym.setBalance(gym.getBalance() - totalSalaries);

        gym.getActions().add("Salaries have been paid to all employees");
    }

    public void registerClientToLesson(Client client, Session session) throws DuplicateClientException, ClientNotRegisteredException, NullPointerException {
        ensureActive();
        boolean canRegister = true;
        if (!gym.getClients().contains(client)) {
            canRegister = false;
            throw new ClientNotRegisteredException();
        }
        for (Client clients : session.getParticipants()) {
            if (client.getId() == clients.getId()) {
                canRegister = false;
                throw new DuplicateClientException("Error: The client is already registered for this lesson");
            }
        }

        // מוודאים שמועד השיעור טרם חלף
        if (LocalDateTime.now().isAfter(session.getDateTime())) {
            gym.getActions().add("Failed registration: Session is not in the future");
            canRegister = false;
        }

        //  בודקים אם פורום השיעור תואם את פרטי הלקוח
        if (session.getForumType() == Female || session.getForumType() == Male) {
            if (!isForumCompatible(client, session.getForumType())) {
                gym.getActions().add("Failed registration: Client's gender doesn't match the session's gender requirements");
            }
        }
        if (session.getForumType() == Seniors) {
            if (!isForumCompatible(client, session.getForumType())) {
                gym.getActions().add("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
                canRegister = false;
            }
        }
        //  וידוא שנותרו מקומות פנויים לשיעור
        if (session.getParticipants().size() >= session.getMaxParticipants()) {
            gym.getActions().add("Failed registration: No available spots for session");
            canRegister = false;
        }

        //  בודקים אם ללקוח יש יתרת כסף מספקת
        if (client.getMoneyBalance() < session.getPrice()) {
            canRegister = false;
            gym.getActions().add("Failed registration: Client doesn't have enough balance");
        }

        // אם הכל בסדר, מוסיפים את הלקוח לשיעור
        if (canRegister == true) {
            session.addParticipant(client);
            client.addSession(session);
            client.setMoneyBalance(client.getMoneyBalance() - session.getPrice());
            gym.setBalance(gym.getBalance() + session.getPrice());
            gym.getActions().add("Registered client: " + client.getName() + " to session: " + session.getType() + " on " + session.getDateTime() + " for price: " + session.getPrice());
        }
    }

    private boolean isForumCompatible(Client client, ForumType forumType) {
        switch (forumType) {
            case Male:
                return client.getGender().equals(Gender.Male);
            case Female:
                return client.getGender().equals(Gender.Female);
            case Seniors:
                return client.getAge() >= 65;
            case forumType.All:
                return true; // כולם יכולים להשתתף
            default:
                return false; // פורום לא מוכר
        }
    }

    public Instructor hireInstructor(Person p6, int i, ArrayList<SessionType> sessionTypes) throws
            InvalidAgeException {
        ensureActive();
        if (p6.getAge() < 18) {
            throw new InvalidAgeException();
        }
        Instructor newInstructor = new Instructor(p6, i, sessionTypes);
        // הוספת המדריך לרשימה
        gym.getInstructors().add(newInstructor);
        // הוספת הפעולה להיסטוריית הפעולות
        gym.getActions().add("Hired new instructor: " + newInstructor.getName() + " with salary per hour: " + i);
        return newInstructor;
    }

    public int getSalary() {
        return this.salary;
    }

    public void deactivate() {
        isActive = false;
    }

    private void ensureActive() {
        if (!isActive) {
            throw new NullPointerException();
        }
    }

}


