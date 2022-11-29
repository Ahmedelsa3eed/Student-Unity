package csed.swe.studentunity.Sessions;

import java.util.HashMap;
import java.util.UUID;

// Singleton design pattern
public class ActiveUserService {
    private static ActiveUserService obj = null;

    private final static HashMap<UUID, String[]> sessions = new HashMap<>();

    private ActiveUserService(){}

    public static ActiveUserService getInstance() {
        if(obj == null) {
            obj = new ActiveUserService();
        }
        return obj;
    }

    public String[] checkLogin(UUID sessionId) {
        if(sessions.containsKey(sessionId)) {
            return sessions.get(sessionId);
        }
        return new String[] {null, null};
    }

    public UUID login(String email, String role) {
        UUID newSessionId = UUID.randomUUID();
        sessions.put(newSessionId, new String[]{"s","s"});
        return newSessionId;
    }

    public void logout(UUID sessionId) {
        sessions.remove(sessionId);
    }


}
