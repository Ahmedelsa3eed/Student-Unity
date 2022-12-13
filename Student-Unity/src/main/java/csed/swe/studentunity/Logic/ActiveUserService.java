package csed.swe.studentunity.Logic;

import java.util.HashMap;
import java.util.UUID;

// Singleton design pattern
public class ActiveUserService {

    private static ActiveUserService instance = null;

    private static final HashMap<UUID, String[]> sessions = new HashMap<>();
    private static final HashMap<String, UUID> emails = new HashMap<>();

    private ActiveUserService() {}

    public static ActiveUserService getInstance() {
        if(instance == null) {
            instance = new ActiveUserService();
        }
        return instance;
    }

    public String[] checkLogin(UUID sessionId) {
        if(sessions.containsKey(sessionId)) {
            return sessions.get(sessionId);
        }
        return new String[] {null, null};
    }

    public void login(String email, String role) {
        UUID newSessionId = UUID.randomUUID();
        sessions.put(newSessionId, new String[]{email, role});
        emails.put(email, newSessionId);
    }

    public String getSessionIdAsString(String email){
        return emails.get(email).toString();
    }


    public void logout(UUID sessionId) {
        sessions.remove(sessionId);
    }

}
