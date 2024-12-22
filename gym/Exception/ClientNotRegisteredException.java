package gym.Exception;

public class ClientNotRegisteredException {
    public ClientNotRegisteredException(){
        super();
    }
    public String getMessage(){
        String s="The client is not registered with the gym and cannot enroll in lessons";
        return s;
    }
}

