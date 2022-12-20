package csed.swe.studentunity.Logic.User;

import java.util.HashMap;
import java.util.UUID;

// Singleton design pattern
public class ActiveUserService {

    private static ActiveUserService instance = null;

    private static final HashMap<UUID, String[]> sessions = new HashMap<>();
    private static final HashMap<UUID, Long> users_Ids = new HashMap<>();
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

    public boolean checkIfEmailLoggedIn(String email) {
        return emails.containsKey(email);
    }

    public UUID getSessionID(String email) {
        return emails.get(email);
    }

    public void changeRole(String email, String role) {
        UUID sessionId = emails.get(email);
        sessions.replace(sessionId, new String[] {email, role});
    }

    public void login(String email, String role, Long id) {
        UUID newSessionId = UUID.randomUUID();
        sessions.put(newSessionId, new String[]{email, role});
        emails.put(email, newSessionId);
        users_Ids.put(newSessionId, id);

    }

    public String getSessionIdAsString(String email){
        return emails.get(email).toString();
    }

    public String getEmailFromSessionId(UUID sessionId){
        return sessions.get(sessionId)[0];
    }

    public Long getUserIdFromSessionId(UUID sessionId){
        return users_Ids.get(sessionId);
    }

    public void deleteSession(String email){
        sessions.remove(emails.get(email));
        emails.remove(email);
    }
    public void logout(UUID sessionId) {
        sessions.remove(sessionId);
        emails.remove(getEmailFromSessionId(sessionId));
    }

}
