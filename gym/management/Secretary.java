package gym.management;

import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Secretary extends Person {
    private double salary;
    private ArrayList<Client> clients;
    private ArrayList<Instructor> instructors;
    private ArrayList<Session> sessions;
    private static ArrayList<String> actions = new ArrayList<>();

    // Constructor
    public Secretary(Person person, double salary) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        this.salary = salary;
        this.clients = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.sessions = new ArrayList<>();
        actions.add("A new secretary has started working at the gym: " +person.getName());
    }

    // Register a client
    public Client registerClient(Person person) throws DuplicateClientException,InvalidAgeException  {
        for (Client client : clients) {
            if (client.getName().equals(person.getName())&&client.getBalance()==person.getBalance()&&client.getGender().equals(person.getGender())&&client.getBirthDate().equals(person.getBirthDate())) {
                throw new DuplicateClientException();
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(person.getBirthDate(), formatter);
        if (calculateAge(date) < 18) {
            throw new InvalidAgeException();
        }

        Client newClient = new Client(person);
        clients.add(newClient);
        actions.add("Registered new client: " + newClient.getName());
        return newClient;
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
       if (!clients.contains(client)) {
            throw new ClientNotRegisteredException();
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

    public Session addSession(SessionType type, String dateTime, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException{
        // בדיקה אם המדריך מוסמך להעביר את סוג השיעור
        if (!instructor.isQualifiedFor(type)) {
            throw new InstructorNotQualifiedException();
        }



        // יצירת שיעור חדש
        Session newSession = SessionFactory.createSession(type, dateTime, forumType, instructor);

        // הוספת השיעור לרשימת השיעורים
        sessions.add(newSession);

        //   לתקן את הזמן                       עדכון היסטוריית הפעולות
        actions.add("Created new session: " + type + " on " + dateTime + " with instructor: " + instructor.getName());
        // החזרת השיעור שנוסף
        return newSession;
    }

    public void paySalaries() {

    }

    public void registerClientToLesson(Client client, Session session) throws DuplicateClientException,ClientNotRegisteredException{
        boolean canRegister = true;
        // א. מוודאים שמועד השיעור טרם חלף
       if (LocalDateTime.now().isAfter(session.getDateTime())) {
            actions.add("Failed registration: Session is not in the future");
            canRegister = false;
        }

        //  בודקים אם פורום השיעור תואם את פרטי הלקוח
        if (session.getForumType() == ForumType.Female || session.getForumType() == ForumType.Male) {
            if (!isForumCompatible(client, session.getForumType())) {
                actions.add("Failed registration: Client's gender doesn't match the session's gender requirements");
            }
        }
        if (session.getForumType() == ForumType.Seniors) {
            if (!isForumCompatible(client, session.getForumType())) {
                actions.add("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
                canRegister = false;
            }
        }
        //  וידוא שנותרו מקומות פנויים לשיעור
        if (session.getParticipants().size() >= session.getMaxParticipants()) {
            canRegister = false;
            actions.add("Failed registration: No available spots for session");
        }

        //  בודקים אם ללקוח יש יתרת כסף מספקת
        if (client.getBalance() < session.getPrice()) {
            canRegister = false;
            actions.add("Failed registration: Client doesn't have enough balance");
        }

        // אם הכל בסדר, מוסיפים את הלקוח לשיעור
        if(canRegister==true){
            session.addParticipant(client);
            client.addSession(session);
            client.setBalance(client.getBalance() - session.getPrice());
            actions.add("Registered client: "+client.getName()+ " to session: "+session.getType() +" on "+ session.getDateTime()+ " for price: "+session.getPrice());
        }

    }

    private int calculateAge(LocalDate birthDate) {
        LocalDate today = LocalDate.now(); // תאריך היום הנוכחי
        return Period.between(birthDate, today).getYears(); // חישוב השנים בין תאריך הלידה לתאריך הנוכחי
    }

    private boolean isForumCompatible(Client client, ForumType forumType) {
        switch (forumType) {
            case forumType.Male:
                return client.getGender().equals(Gender.Male);
            case forumType.Female:
                return client.getGender().equals(Gender.Female);
            case forumType.Seniors:
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(client.getBirthDate(), formatter);
                int clientAge = calculateAge(date);
                return clientAge >= 65;
            case forumType.All:
                return true; // כולם יכולים להשתתף
            default:
                return false; // פורום לא מוכר
        }
    }

    public Instructor hireInstructor(Person p6, int i, ArrayList<SessionType> sessionTypes) {
        Instructor newInstructor = new Instructor(p6, i, sessionTypes);
        // הוספת המדריך לרשימה
        instructors.add(newInstructor);
        // הוספת הפעולה להיסטוריית הפעולות
        actions.add("Hired new instructor: " + newInstructor.getName()+" with salary per hour: "+i);
        return newInstructor;
    }
}


